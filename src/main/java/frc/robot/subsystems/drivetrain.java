// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.DrivewithJoysticks;

public class drivetrain extends SubsystemBase {
  /** Creates a new drivetrain. */
  TalonFX leftMotor;
  TalonFX rightMotor;  
  TalonFX leftFollowerMotor;
  TalonFX rightFollowerMotor;

  DifferentialDrive drive;
  double throttle;
  double twist;

  
  public drivetrain() {
    leftMotor = new TalonFX(2);
    rightMotor = new TalonFX(1);
    leftFollowerMotor = new TalonFX(3);
    rightFollowerMotor = new TalonFX(4);
    
    leftFollowerMotor.setControl(new Follower(leftMotor.getDeviceID(), false));
    rightFollowerMotor.setControl(new Follower(rightMotor.getDeviceID(), false));

    drive = new DifferentialDrive(leftMotor, rightMotor);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }

  public void arcadedrive() {
    throttle = RobotContainer.m_joystick.getRawAxis(0);
    twist = RobotContainer.m_joystick.getRawAxis(1);
    drive.arcadeDrive(throttle, twist);
  }
}
