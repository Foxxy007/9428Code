// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Util;
import frc.robot.subsystems.drivetrain;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.shooter;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class AutonomousShooter extends Command {
  // @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final shooter m_shooterAuto;
  private final intake m_intakeAuto;
  private final drivetrain m_drivetrainAuto;

  /**
   * Creates a new Autonomous.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutonomousShooter(shooter shooterSubsystem,intake intakeSubsystem, drivetrain drivetrainSubsystem) {
    m_shooterAuto = shooterSubsystem;
    m_intakeAuto = intakeSubsystem;
    m_drivetrainAuto = drivetrainSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooterSubsystem);
    addRequirements(intakeSubsystem);
    addRequirements(drivetrainSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute(){
    if(Util.TimeElapsed()<2000){
    m_shooterAuto.autoShooter(Constants.shooterSpeakerSpeed);
    }
    if(Util.TimeElapsed()>2000 && Util.TimeElapsed()<4000){
      m_intakeAuto.autoIntake(Constants.intakeSpeed);
    }
    if(Util.TimeElapsed()>5000){
      m_shooterAuto.autoShooter(0);
      m_intakeAuto.autoIntake(0);
     }
    /*if(Util.TimeElapsed()>5000){
      m_drivetrainAuto.autoDrivetrain(Constants.drivetrainAutoSpeed, 0);
    } */
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrainAuto.autoDrivetrain(0, 0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
