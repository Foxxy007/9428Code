// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.SpinStop;

public class spinner extends SubsystemBase {
  /** Creates a new spinner. */
  private final VictorSP m_spin;
  private double current_speed = 0;
  public spinner() {
    m_spin = new VictorSP(Constants.spinnerMotor);
    setDefaultCommand(new SpinStop(this));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_spin.set(current_speed);
    SmartDashboard.putNumber("spinner", m_spin.get());
  }

  public void SpinFaster() {
    current_speed = Math.min(1.0, current_speed + 0.1);
  }
  public void SpinSlower() {
    current_speed = Math.max(-1.0, current_speed - 0.1);
  }
  public void SpinStop() {
    m_spin.set(0);
  }
}
