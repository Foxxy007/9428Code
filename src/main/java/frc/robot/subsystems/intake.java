// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.Intake;

public class intake extends SubsystemBase {
  private final CANSparkMax m_bottomIntakeRoller;
  private final CANSparkMax m_intakeBelt;
  private final CANSparkMax m_topIntakeRoller;
  /** Creates a new intake. */
  public intake() {
    m_bottomIntakeRoller = new CANSparkMax(Constants.bottomIntakeRollerMotor, MotorType.kBrushed);
    m_intakeBelt = new CANSparkMax(Constants.intakeBeltMotor, MotorType.kBrushless);
    m_topIntakeRoller = new CANSparkMax(Constants.topIntakeRollerMotor, MotorType.kBrushed);
    
    m_bottomIntakeRoller.setInverted(true);
    m_topIntakeRoller.setInverted(true);

    
    this.setDefaultCommand(new Intake(this));
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Top Intake Roller", m_topIntakeRoller.get());
    SmartDashboard.putNumber("Intake Belt", m_intakeBelt.get());
    SmartDashboard.putNumber("Bottom Intake Roller", m_bottomIntakeRoller.get());
  }
  public void scoop() {
    if(Robot.GameStage.equals("auto")){
      m_bottomIntakeRoller.set(Constants.spinnerSpeed);
      m_intakeBelt.set(Constants.spinnerSpeed);
      m_topIntakeRoller.set(Constants.spinnerSpeed);
    }else if(Robot.GameStage.equals("teleop")&&!RobotContainer.m_controller.getRawButton(Constants.buttonHPort)){
      m_bottomIntakeRoller.set(RobotContainer.m_controller.getRawAxis(1));
      m_intakeBelt.set(RobotContainer.m_controller.getRawAxis(1));
      m_topIntakeRoller.set(RobotContainer.m_controller.getRawAxis(1));
    }else{
      m_bottomIntakeRoller.set(0);
      m_intakeBelt.set(0);
      m_topIntakeRoller.set(0);
    }
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
