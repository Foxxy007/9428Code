// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;

// import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import frc.robot.commands.DrivewithJoysticks;
// import frc.robot.commands.Spin;
import frc.robot.commands.Intake;
import frc.robot.commands.shoot;
import frc.robot.subsystems.drivetrain;
import frc.robot.subsystems.hook;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.shooter;
// import frc.robot.subsystems.spinner;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  //Declare objects that will be used?
  public static final GenericHID m_controller = new GenericHID(0);
  // private static final spinner m_spinner = new spinner();
  private static final drivetrain m_drive = new drivetrain();
  private static final intake m_intake = new intake();
  private static final shooter m_shooter = new shooter();
  private static final hook m_hook = new hook();



  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    //Sets the default state of the drivetrain to be the controller axis'
    m_drive.setDefaultCommand(new DrivewithJoysticks(m_drive));
    // m_spinner.setDefaultCommand(new Spin(m_spinner));
    m_intake.setDefaultCommand(new Intake(m_intake));
    m_shooter.setDefaultCommand(new shoot(m_shooter));
    

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // TODO Undestand what this does.
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new PrintCommand("test");
  }
}
