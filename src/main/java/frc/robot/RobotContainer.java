// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// Limelight imports

// Power Distribution Board imports
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
// Command imports
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
// Our commands imports
import frc.robot.commands.DrivewithJoysticks;
import frc.robot.commands.Intake;
import frc.robot.commands.shoot;
import frc.robot.commands.hang;
import frc.robot.commands.AutonomousOut;//Leave the marked zone
import frc.robot.commands.AutonomousShooter;//Shoot the speaker
import frc.robot.commands.update_yaw;

// Our subsystem imports
import frc.robot.subsystems.drivetrain;
import frc.robot.subsystems.hook;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.shooter;
import frc.robot.subsystems.telemetry;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  //Declare objects that will be used
  public static final GenericHID m_controller = new GenericHID(Constants.GenericHIDPort);
  public static final GenericHID m_controller2 = new GenericHID(Constants.GenericHIDPort2);
  private static final drivetrain m_drive = new drivetrain();
  private static final intake m_intake = new intake();
  private static final shooter m_shooter = new shooter();
  private static final hook m_hook = new hook();
  private static final telemetry m_telemetry = new telemetry();

  public PowerDistribution powerPanel = new PowerDistribution(Constants.powerPanelModule, ModuleType.kRev);
  
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  
  private NetworkTableEntry tx, ty, ta;

  public static double rawX=0, rawY=0, rawArea=0;
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    //Sets the default state of the drivetrain to be the controller axis'
    SmartDashboard.putNumber("Total Current", powerPanel.getTotalCurrent());
    m_drive.setDefaultCommand(new DrivewithJoysticks(m_drive));
    m_intake.setDefaultCommand(new Intake(m_intake));
    m_shooter.setDefaultCommand(new shoot(m_shooter));
    //m_hook.setDefaultCommand(new hang(m_hook));//?Comment out
    m_telemetry.setDefaultCommand(new update_yaw(m_telemetry));


    //LimeLight Variable Updates
    tx = table.getEntry("tx"); 
    ty = table.getEntry("ty");
    ta = table.getEntry("ta");
    rawX = tx.getDouble(0.0);
    rawY = ty.getDouble(0.0);
    rawArea = ta.getDouble(0.0);

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // TODO Undestand how to use this
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new AutonomousShooter(m_shooter,m_intake,m_drive);
  }
}
