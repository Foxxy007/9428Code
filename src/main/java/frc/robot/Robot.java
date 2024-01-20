// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

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
  private Util util = new Util();
  // Drive motors
  WPI_VictorSPX motorLeftFront = new WPI_VictorSPX(1);
  WPI_VictorSPX motorLeftBack = new WPI_VictorSPX(2);
  WPI_VictorSPX motorRightFront = new WPI_VictorSPX(3);
  WPI_VictorSPX motorRightBack = new WPI_VictorSPX(4);
  final GenericHID Controller1 = new GenericHID(0);
  double turn;
  double drive;
  // Flywheel
  CANSparkMax flywheelFront = new CANSparkMax(11, MotorType.kBrushed);
  CANSparkMax flywheelBack = new CANSparkMax(10, MotorType.kBrushed);
  double flywheelSpeed;
  boolean a;
  //Current Sensing
  PowerDistribution powerPanel = new PowerDistribution(1, ModuleType.kRev);

  public Robot() { 
  }
  
  @Override
  public void robotInit() {
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
    turn = Controller1.getRawAxis(0);//x-axis 1
    drive = Controller1.getRawAxis(3);//y-axis 2
    flywheelSpeed = Controller1.getRawAxis(4);//x-axis 2
    a = Controller1.getRawButton(7);//a-button
    //Drive
    motorLeftFront.set(drive+turn);
    motorLeftBack.set(drive+turn);
    motorRightFront.set(-drive+turn);
    motorRightBack.set(-drive+turn);
    //Flywheel
    flywheelFront.set(flywheelSpeed);
    if(a){
      flywheelBack.set(flywheelSpeed);
    }else{
      flywheelBack.set(0);
    }
    
    //Telemetry
    SmartDashboard.putNumber("LimelightX", rawX);
    SmartDashboard.putNumber("LimelightY", rawY);
    SmartDashboard.putNumber("LimelightArea", rawArea);
  }

  }

