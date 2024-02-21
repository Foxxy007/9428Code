// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.spinner;

public class SpinFaster extends Command {
  
  spinner m_spinner; 
  /** Creates a new SpinFaster. */
  public SpinFaster(spinner param_spin) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_spinner = param_spin;
    addRequirements(m_spinner);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_spinner.SpinFaster();
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
