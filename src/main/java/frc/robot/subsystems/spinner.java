// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.stopSpin;

public class spinner extends SubsystemBase {
  /** Creates a new spinner. */
  private final CANSparkMax m_spin;
  private double current_speed = 0;
  public spinner() {
    m_spin = new CANSparkMax(Constants.spinnerMotor, MotorType.kBrushless);
    m_spin.setInverted(true);
    setDefaultCommand(new stopSpin(this));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_spin.set(current_speed);
    SmartDashboard.putString("spinner", (m_spin.get()>0)?"ON":"OFF");
  }

  public void fullSpin() {
    m_spin.set(Constants.spinnerSpeed);
  }
  public void stopSpin() {
    m_spin.set(0);
  }
}
