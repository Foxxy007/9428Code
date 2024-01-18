// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.math.proto.Controller;
//Unused imports
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.Joystick;
// Utility class
import frc.robot.Utility;
public class Robot extends TimedRobot {
  // Drive motors
  WPI_VictorSPX motorLeftFront = new WPI_VictorSPX(1);
  WPI_VictorSPX motorLeftBack = new WPI_VictorSPX(2);
  WPI_VictorSPX motorRightFront = new WPI_VictorSPX(3);
  WPI_VictorSPX motorRightBack = new WPI_VictorSPX(4);
  final GenericHID Controller1 = new GenericHID(0);
  // Movement
  double turn;
  double drive;
  // Flywheel
  CANSparkMax flywheelFront = new CANSparkMax(11, MotorType.kBrushed);
  CANSparkMax flywheelBack = new CANSparkMax(10, MotorType.kBrushed);
  double speed;
  boolean a;
  public Robot() {
    
  }
  
  @Override
  public void robotInit() {

  }

  @Override
  public void teleopPeriodic() {
    //Re-defining
    turn = Controller1.getRawAxis(0);//x-axis 1
    drive = Controller1.getRawAxis(1);//y-axis 1

    speed = Controller1.getRawAxis(3);//x-axis 2
    
    a = Controller1.getRawButton(1);
    //Drive
    motorLeftFront.set(drive+turn);
    motorLeftBack.set(drive+turn);
    motorRightFront.set(-drive+turn);
    motorRightBack.set(-drive+turn);
    //Flywheel
    flywheelFront.set(speed);
    if(a==true){
      flywheelBack.set(speed);
    }else{
      flywheelBack.set(0);
    }
  }

  }

