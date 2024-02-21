// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.DrivewithJoysticks;

public class drivetrain extends SubsystemBase {
  /** Creates a new drivetrain. */
  VictorSP leftmotor;
  VictorSP rightmotor;
  DifferentialDrive drive;
  double throttle;
  double twist;

  
  public drivetrain() {
    leftmotor = new VictorSP(Constants.leftDriveMotor);
    rightmotor = new VictorSP(Constants.rightDriveMotor);
    drive = new DifferentialDrive(leftmotor, rightmotor);
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
