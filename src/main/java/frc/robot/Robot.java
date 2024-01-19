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
//Controller Binds
/* Axis 0 - X Stick 1
Axis 1 - Y Stick 1(Not Spring-Loaded)
Axis 2 - X Stick 2
Axis 3 - Y Stick 2
Axis 4 - Left Slider(Potentiometer)
Axis 5 - Right Slider(Potentiometer)
Axis 6 - Switch E(three way top left)
Axis 7 - Switch F(three way top left)
Button 1,2,3 - Switch B(three way front left)
Button 4,5,6 - Switch C(three way front right)
Button 7 - Button a
Button 8 - Button D */

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
    drive = Controller1.getRawAxis(3);//y-axis 2

    speed = Controller1.getRawAxis(4);//x-axis 2
    
    a = Controller1.getRawButton(7);
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

