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


public class Robot extends TimedRobot {
  //Create util object so we can use functions from the Util file
  //No idea how this works but i've had this line for like 3 years
  
  // Drive motors
  CANSparkMax motorLeft = new CANSparkMax(1, MotorType.kBrushless);
  CANSparkMax motorLeftFollower = new CANSparkMax(2, MotorType.kBrushless);
  CANSparkMax motorRight = new CANSparkMax(3, MotorType.kBrushless);
  CANSparkMax motorRightFollower = new CANSparkMax(4, MotorType.kBrushless);

  final GenericHID Controller1 = new GenericHID(0);
  double turn;
  double drive;
  // Flywheel
  WPI_VictorSPX flywheelLeftFront = new WPI_VictorSPX(1);
  WPI_VictorSPX flywheelLeftBack = new WPI_VictorSPX(2);
  WPI_VictorSPX flywheelRightFront = new WPI_VictorSPX(3);
  WPI_VictorSPX flywheelRightBack = new WPI_VictorSPX(4);

  double flywheelSpeed;
  boolean a;
  //Current Sensing
  PowerDistribution powerPanel = new PowerDistribution(1, ModuleType.kRev);

  public Robot() { 
  }
  
  @Override
  public void robotInit() {
    flywheelRightFront.setInverted(true);
    flywheelRightBack.setInverted(true);
    motorLeftFollower.follow(motorLeft);
    motorRightFollower.follow(motorRight);



  }
  @Override
  public void autonomousPeriodic() {
    //Autonomous
    //RamseteController controller1 = new RamseteController();
    
  }
  @Override
  public void teleopPeriodic() {
    //LimeLight Variable Updates
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    double rawX = tx.getDouble(0.0);
    double rawY = ty.getDouble(0.0);
    double rawArea = ta.getDouble(0.0);
    //Robot actions
    turn = Util.clamp(Util.inputCurve(Controller1.getRawAxis(0),0.2));//x-axis 1
    drive = Util.clamp(Controller1.getRawAxis(3));//y-axis 2
    flywheelSpeed = Controller1.getRawAxis(4);//x-axis 2
    a = Controller1.getRawButton(7);//a-button
    //Drive
    motorLeft.set(drive+turn);
    motorRight.set(-drive+turn);
    //Flywheel
    flywheelLeftFront.set(flywheelSpeed);
    flywheelRightFront.set(flywheelSpeed);
    if(a){
      flywheelLeftBack.set(0.3+flywheelSpeed*0.7);
      flywheelRightBack.set(0.3+flywheelSpeed*0.7);
    }else{
      flywheelLeftBack.set(0);
      flywheelRightBack.set(0);
    } 
    
    //Telemetry
    SmartDashboard.putNumber("Limelight X", rawX);
    SmartDashboard.putNumber("Limelight Y", rawY);
    SmartDashboard.putNumber("Limelight Area", rawArea);
    SmartDashboard.putNumber("Total Current", powerPanel.getTotalCurrent());
  }

  }

