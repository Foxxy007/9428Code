// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.spinner;

public class Spin extends Command {
  
  spinner m_spinner; 
  double speed;
  /** Creates a new SpinFaster. */
  public Spin(spinner param_spin, double param_speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_spinner = param_spin;
    speed = param_speed;
    addRequirements(m_spinner);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_spinner.spin();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
