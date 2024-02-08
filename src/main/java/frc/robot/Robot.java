// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkRelativeEncoder;

import edu.wpi.first.math.controller.RamseteController;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;


public class Robot extends TimedRobot {
  AHRS ahrs;
  // Drive motors
  CANSparkMax motorLeft = new CANSparkMax(1, MotorType.kBrushless);
  CANSparkMax motorLeftFollower = new CANSparkMax(2, MotorType.kBrushless);
  CANSparkMax motorRight = new CANSparkMax(3, MotorType.kBrushless);
  CANSparkMax motorRightFollower = new CANSparkMax(4, MotorType.kBrushless);

  // Create Controller
  GenericHID Controller1 = new GenericHID(0);
  double turn;
  double drive;

  // Flywheel
  WPI_VictorSPX flywheelLeftFront = new WPI_VictorSPX(1);
  WPI_VictorSPX flywheelLeftBack = new WPI_VictorSPX(2);
  WPI_VictorSPX flywheelRightFront = new WPI_VictorSPX(3);
  WPI_VictorSPX flywheelRightBack = new WPI_VictorSPX(4);
  CANSparkMax intakeBelt = new CANSparkMax(6, MotorType.kBrushed);
  CANSparkMax intakeTopRoller = new CANSparkMax(5, MotorType.kBrushed);
  CANSparkMax intakeBottomRoller = new CANSparkMax(7, MotorType.kBrushed);

  double leftSlider;
  double rightSlider;

  CANSparkMax frontMotor = new CANSparkMax(10, MotorType.kBrushless);

  boolean a;
  boolean d;

  //Current Sensing
  PowerDistribution powerPanel = new PowerDistribution(1, ModuleType.kRev);
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

  public Robot() { 
  }
  
  @Override
  public void robotInit() {

    flywheelRightFront.setInverted(true);
    flywheelRightBack.setInverted(true);
    motorLeftFollower.follow(motorLeft);
    motorRightFollower.follow(motorRight);
    ahrs = new AHRS(SPI.Port.kMXP);
    
  }
  @Override
  public void autonomousPeriodic() {
    //Autonomous
    //RamseteController controller1 = new RamseteController();
    
  }
  @Override
  public void teleopPeriodic() {
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

    //Robot actions
    turn = Util.clamp(Util.inputCurve(Controller1.getRawAxis(0),0.2));//x-axis 1
    drive = Util.clamp(Controller1.getRawAxis(3));//y-axis 2
    leftSlider = Controller1.getRawAxis(4);//left slider
    rightSlider = Controller1.getRawAxis(5);//left slider

    a = Controller1.getRawButton(7);//a-button
    d = Controller1.getRawButton(8);//a-button

    //Drive
    motorLeft.set(drive+turn);
    motorRight.set(-drive+turn);

    //Flywheel
    
  /*   if(a){ //Spins Rear Flywheels
      flywheelLeftBack.set(rightSlider);
      flywheelRightBack.set(rightSlider);
    }else{
      flywheelLeftBack.set(0);
      flywheelRightBack.set(0);
    }  */
    if(a){
      intakeBelt.set(leftSlider);
      intakeTopRoller.set(rightSlider);
      intakeBottomRoller.set(rightSlider);
    }else{
      intakeBelt.set(0);
      intakeTopRoller.set(0);
      intakeBottomRoller.set(0);
    }
    if(d){
      frontMotor.set(-0.1);
      flywheelLeftBack.set(0.70);
      flywheelRightBack.set(0.70);
      flywheelLeftFront.set(0.05);
      flywheelRightFront.set(0.05);
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
    SmartDashboard.putNumber("Front Flywheel Speed",leftSlider);
    SmartDashboard.putNumber("Rear Flywheel Speed",rightSlider);



  }

  }

