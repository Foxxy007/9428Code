// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.Spin;

public class spinner extends SubsystemBase {
  /** Creates a new spinner. */
  private final CANSparkMax m_spin;
  public spinner() {
    m_spin = new CANSparkMax(Constants.spinnerMotor, MotorType.kBrushless);
    m_spin.setInverted(true);
    m_spin.set(Constants.spinnerSpeed);
    setDefaultCommand(new Spin(this));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("spinner", m_spin.get());
  }

  public void spin() {
    if(Robot.GameStage.equals("auto")){
      m_spin.set(Constants.spinnerSpeed);
    }else if(Robot.GameStage.equals("teleop")){
      m_spin.set(RobotContainer.m_controller.getRawButton(4)?Constants.spinnerSpeed:0);
    }
  }
}