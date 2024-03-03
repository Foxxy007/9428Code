// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
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

  DifferentialDrive Drive;
  double drive;
  double turn;

  
  public drivetrain() {
    leftMotor = new TalonFX(2);
    rightMotor = new TalonFX(1);
    leftFollowerMotor = new TalonFX(3);
    rightFollowerMotor = new TalonFX(4);
    leftFollowerMotor.setControl(new Follower(leftMotor.getDeviceID(), false));
    rightFollowerMotor.setControl(new Follower(rightMotor.getDeviceID(), false));
    leftMotor.setInverted(true);
    rightMotor.setInverted(false);
    Drive = new DifferentialDrive(leftMotor, rightMotor);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }

  public void arcadedrive() {
    if(!RobotContainer.m_controller.getRawButton(Constants.buttonHPort)){
      drive = RobotContainer.m_controller.getRawAxis(4);
      turn = RobotContainer.m_controller.getRawAxis(0);
    }else{
      drive = 0;
      turn = 0;
    }
    Drive.arcadeDrive(drive, turn);
    
  }
  public void autoDrive() throws InterruptedException{
    drive = 0.1;
    turn = 0;
    Drive.arcadeDrive(drive, turn);
    if(frc.robot.){
    drive = 0;
    turn = 0;
    Drive.arcadeDrive(drive, turn);
    }
  }
}
