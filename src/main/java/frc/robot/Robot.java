// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkRelativeEncoder;

import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;


public class Robot extends TimedRobot {
  // Initialization of sensors(Gyro, Accelerometer et cetera)
  AHRS ahrs;
  // Drive motors(CANs?)
  TalonFX motorLeft = new TalonFX(2);
  TalonFX motorLeftFollower = new TalonFX(3);
  TalonFX motorRight = new TalonFX(1);
  TalonFX motorRightFollower = new TalonFX(4);

  // Create Controller
  GenericHID Controller1 = new GenericHID(0);
  double turn;
  double drive;

  // Flywheel & Intake
  CANSparkMax flywheelLeftFront = new CANSparkMax(6, MotorType.kBrushless);
  CANSparkMax flywheelLeftBack = new CANSparkMax(7, MotorType.kBrushless);
  CANSparkMax flywheelRightFront = new CANSparkMax(5, MotorType.kBrushless);
  CANSparkMax flywheelRightBack = new CANSparkMax(8, MotorType.kBrushless);
  CANSparkMax intakeBelt = new CANSparkMax(3, MotorType.kBrushless);
  CANSparkMax intakeTopRoller = new CANSparkMax(4, MotorType.kBrushed);
  CANSparkMax intakeBottomRoller = new CANSparkMax(2, MotorType.kBrushed);
<<<<<<< Updated upstream

  double leftSlider;
  double rightSlider;

  CANSparkMax frontMotor = new CANSparkMax(10, MotorType.kBrushless);//? What is this motor for. Too ambigious.


  //Controler Values
  double stickX1;
=======
  CANSparkMax frontMotor = new CANSparkMax(10, MotorType.kBrushless);

  //LED
  Spark blinkin = new Spark(0);
  ShuffleboardTab tab = Shuffleboard.getTab("LED");
  GenericEntry ledPWM = tab.add("Max Speed", 1).getEntry();


  double stickX2;
>>>>>>> Stashed changes
  double stickY1;
  double stickY2;
  boolean buttonD;
  boolean buttonA;
  boolean buttonG;



  //Current Sensing
  PowerDistribution powerPanel = new PowerDistribution(1, ModuleType.kRev);
  //Initialization of NetworkTable for Limelight
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

  public Robot() {// class constructor.
  }
  
  @Override
  public void robotInit() {
    //Inverts the motors
    frontMotor.setInverted(true);
    flywheelRightFront.setInverted(true);
    flywheelRightBack.setInverted(true);
    intakeTopRoller.setInverted(true);
    intakeBottomRoller.setInverted(true);

    motorLeftFollower.setControl(new Follower(motorLeft.getDeviceID(), false));
    motorRightFollower.setControl(new Follower(motorRight.getDeviceID(), false));

    ahrs = new AHRS(SPI.Port.kMXP);
    
  }
  @Override
  public void autonomousPeriodic() {
    //Autonomous
    //RamseteController controller1 = new RamseteController();
    
  }
  @Override
  public void teleopPeriodic() {
    // Check for sensor calibration
    if (ahrs.isCalibrating()) {
      SmartDashboard.putNumber("Is Calibrating", 1 );
    }
    if(ahrs.isConnected()){
      SmartDashboard.putNumber("Is Connected", 1 );
    }

    //LimeLight Variable Updates
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    double rawX = tx.getDouble(0.0);
    double rawY = ty.getDouble(0.0);
    double rawArea = ta.getDouble(0.0);

    //Controller Mapping
    stickX2 = Controller1.getRawAxis(3);
    stickY1 = Controller1.getRawAxis(1);
    stickY2 = Controller1.getRawAxis(4);
    buttonA = Controller1.getRawButton(3);
    buttonD = Controller1.getRawButton(4);
    buttonG = Controller1.getRawButton(5);

    //Robot actions
    turn = Util.clamp(Util.inputCurve(stickX2,0.2));//x-axis 1
    drive = Util.clamp(-Util.inputCurve(stickY1, 0.2));//y-axis 2

    //LED
    double led = ledPWM.getDouble(1.0);
    blinkin.set(led);
    //Drive
    motorLeft.set(drive+turn);
    motorRight.set(-drive+turn);
    

    //Flywheel and Intake
    intakeBelt.set(stickY2);
    intakeTopRoller.set(stickY2);
    intakeBottomRoller.set(stickY2);

    if(buttonD){ //AmpScoring
      frontMotor.set(0.1);
      flywheelLeftBack.set(0.20);
      flywheelRightBack.set(0.20);
      flywheelLeftFront.set(0.1);
      flywheelRightFront.set(0.1);
    }
    else if(buttonA){ //Speaker Scoring
      flywheelLeftBack.set(1);
      flywheelRightBack.set(1);
      flywheelLeftFront.set(1);
      flywheelRightFront.set(1);
    }else if(buttonG){//Intake through shooter
      flywheelLeftBack.set(-0.4);
      flywheelRightBack.set(-0.4);
      flywheelLeftFront.set(-0.4);
      flywheelRightFront.set(-0.4);
    }else{
      frontMotor.set(0);
      flywheelLeftBack.set(0);
      flywheelRightBack.set(0);
      flywheelLeftFront.set(0);
      flywheelRightFront.set(0);
    } 
    
    //Telemetry
    SmartDashboard.putNumber("Limelight X", rawX);
    SmartDashboard.putNumber("Limelight Y", rawY);
    SmartDashboard.putNumber("Limelight Area", rawArea);
    SmartDashboard.putNumber("Total Current", powerPanel.getTotalCurrent());
    SmartDashboard.putNumber("Test Angle",ahrs.getYaw());
    //SmartDashboard.putNumber("Front Flywheel Speed",leftSlider);
    //SmartDashboard.putNumber("Rear Flywheel Speed",rightSlider);



  }

  }

