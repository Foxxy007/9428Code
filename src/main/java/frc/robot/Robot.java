// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
//import com.revrobotics.RelativeEncoder;
import com.revrobotics.Rev2mDistanceSensor;
//import com.revrobotics.SparkRelativeEncoder;

//import edu.wpi.first.math.controller.RamseteController;
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
//import edu.wpi.first.wpilibj.util.Color;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
//import edu.wpi.first.wpilibj.I2C;
import com.revrobotics.Rev2mDistanceSensor.Port;
public class Robot extends TimedRobot {
  private Rev2mDistanceSensor distance; 
  AHRS ahrs;
  // Drive motors
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
  CANSparkMax frontMotor = new CANSparkMax(10, MotorType.kBrushless);

  //LED
  Spark blinkin = new Spark(0);
  ShuffleboardTab tab = Shuffleboard.getTab("LED");
  GenericEntry ledPWM = tab.add("Max Speed", 1).getEntry();
  double idleLED = -0.21;
  double ringLED = 0.07; //0.33
  double speakerLED = -0.97;
  double ampLED = -0.92; 
  double stickX2;
  double stickY1;
  double stickY2;
  boolean buttonD;
  boolean buttonA;
  boolean buttonG;



  //Current Sensing
  PowerDistribution powerPanel = new PowerDistribution(1, ModuleType.kRev);
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

  public Robot() { 
  }
  
  @Override
  public void robotInit() {

    frontMotor.setInverted(true);
    flywheelRightFront.setInverted(true);
    flywheelRightBack.setInverted(true);
    intakeTopRoller.setInverted(true);
    intakeBottomRoller.setInverted(true);

    motorLeftFollower.setControl(new Follower(motorLeft.getDeviceID(), false));
    motorRightFollower.setControl(new Follower(motorRight.getDeviceID(), false));

    ahrs = new AHRS(SPI.Port.kMXP);
    distance = new Rev2mDistanceSensor(Port.kOnboard);
  }
  @Override
  public void autonomousPeriodic() {
    //Autonomous
    //RamseteController controller1 = new RamseteController();
    
  }
  @Override
  public void teleopInit() {
    distance.setAutomaticMode(true);
    distance = new Rev2mDistanceSensor(Port.kOnboard);
    ahrs = new AHRS(SPI.Port.kMXP);

  }
  public void teleopPeriodic() {
    //Distance Sensor for internal ring detection
    if(distance.getRange() == -1) {
      //distance = new Rev2mDistanceSensor(Port.kOnboard);
    }
    if (ahrs.isCalibrating()) {
      //Thread.yield();
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
    stickX2 = Controller1.getRawAxis(0);
    stickY1 = Controller1.getRawAxis(1);
    //stickY2 = Controller1.getRawAxis(4);
    buttonA = Controller1.getRawButton(4);
    buttonD = Controller1.getRawButton(3);
    buttonG = Controller1.getRawButton(5);

    //Robot actions
    turn = Util.clamp(Util.inputCurve(stickX2,1));//x-axis 1
    drive = Util.clamp(Util.inputCurve(stickY1, 1));//y-axis 2

    //LED
    //double led = ledPWM.getDouble(1.0);
    //blinkin.set(led);
    //Drive Reverse
    if(Controller1.getRawButton(13)){
    motorLeft.set(drive+turn);
    motorRight.set(-drive+turn);
    }
    else{
      motorLeft.set(-drive+turn);
      motorRight.set(drive+turn);
    }
    
    

    //Flywheel and Intake
    /* intakeBelt.set(stickY2);
    intakeTopRoller.set(stickY2);
    intakeBottomRoller.set(stickY2); */
    if(Controller1.getRawButton(8)){
      intakeBelt.set(1);
      intakeTopRoller.set(1);
      intakeBottomRoller.set(1);
    }
    else if(Controller1.getRawButton(7)){
      intakeBelt.set(-1);
      intakeTopRoller.set(-1);
      intakeBottomRoller.set(-1);
    }
    else{
      intakeBelt.set(0);
      intakeTopRoller.set(0);
      intakeBottomRoller.set(0);
    }
 
    if(buttonD){ //AmpScoring
      frontMotor.set(0.1);
      flywheelLeftBack.set(0.25);
      flywheelRightBack.set(0.25);
      flywheelLeftFront.set(0.1);
      flywheelRightFront.set(0.1);
      blinkin.set(ampLED);
    }
    else if(buttonA){ //Speaker Scoring
      flywheelLeftBack.set(1);
      flywheelRightBack.set(1);
      flywheelLeftFront.set(1);
      flywheelRightFront.set(1);
      blinkin.set(speakerLED);
    }else if(buttonG){//Intake through shooter
      flywheelLeftBack.set(-0.15);
      flywheelRightBack.set(-0.15);
      flywheelLeftFront.set(-0.15);
      flywheelRightFront.set(-0.15);
    }else if(distance.getRange() < 14 && distance.getRange() != -1){
      frontMotor.set(0);
      flywheelLeftBack.set(0);
      flywheelRightBack.set(0);
      flywheelLeftFront.set(0);
      flywheelRightFront.set(0);
      blinkin.set(ringLED);
    }else{
      frontMotor.set(0);
      flywheelLeftBack.set(0);
      flywheelRightBack.set(0);
      flywheelLeftFront.set(0);
      flywheelRightFront.set(0);
      blinkin.set(idleLED);
    }  
    
    
    //Telemetry
    SmartDashboard.putNumber("Limelight X", rawX);
    SmartDashboard.putNumber("Limelight Y", rawY);
    SmartDashboard.putNumber("Limelight Area", rawArea);
    SmartDashboard.putNumber("Total Current", powerPanel.getTotalCurrent());
    SmartDashboard.putNumber("Test Angle",ahrs.getYaw());
    //SmartDashboard.putNumber("Front Flywheel Speed",leftSlider);
    //SmartDashboard.putNumber("Rear Flywheel Speed",rightSlider);
    SmartDashboard.putNumber("Ring Range", distance.getRange());
    SmartDashboard.putNumber("Timestamp Onboard", distance.getTimestamp());
    
  }

  }

