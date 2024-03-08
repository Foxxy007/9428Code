// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.controls.PositionVoltage;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Util;
import frc.robot.commands.DrivewithJoysticks;

public class drivetrain extends SubsystemBase {
  /** Creates a new drivetrain. */
  TalonFX leftMotor;
  TalonFX rightMotor;  
  TalonFX leftFollowerMotor;
  TalonFX rightFollowerMotor;
  double setPosition  = 0;

  DifferentialDrive Drive;
  double drive;
  double turn;
  
  PositionVoltage m_request = new PositionVoltage(0).withSlot(0);


  
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
    var slot0Configs = new Slot0Configs();
    slot0Configs.kP = 0.04; // An error of 0.5 rotations results in 12 V output
    slot0Configs.kI = 0; // no output for integrated error
    slot0Configs.kD = 0.001; // A velocity of 1 rps results in 0.1 V output
    leftMotor.getConfigurator().apply(slot0Configs);
    rightMotor.getConfigurator().apply(slot0Configs);
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
    if(RobotContainer.m_controller.getRawButton(Constants.buttonHPort)){
        leftMotor.setControl(m_request.withPosition(setPosition)); 
        rightMotor.setControl(m_request.withPosition(setPosition)); 
        SmartDashboard.putNumber("Set Position",setPosition);
    }
  }
  public void autoDrive(){ 
    SmartDashboard.putNumber("Drive", drive);
    SmartDashboard.putNumber("Turn", turn);
    drive = 0.1;
    turn = 0;
    if(Util.TimeElapsed()>1000){
      drive = 0;
      turn = 0;
    }
    Drive.arcadeDrive(drive, turn);
    
  }
}
