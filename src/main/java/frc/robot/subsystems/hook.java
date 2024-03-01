// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.Intake;

public class hook extends SubsystemBase {
  private CANSparkMax m_Lhook;
  private RelativeEncoder m_LhookEncoder;
  private SparkPIDController m_LhookPID;
  private CANSparkMax m_Rhook;
  private RelativeEncoder m_RhookEncoder;
  private SparkPIDController m_RhookPID;
  private double RhookSetPoint;
  private double LhookSetPoint;

  public hook() {
    m_Lhook = new CANSparkMax(Constants.telescopicArmLeftMotor, MotorType.kBrushless); 
    m_LhookEncoder = m_Lhook.getEncoder(); 
    m_Rhook = new CANSparkMax(Constants.telescopicArmRightMotor, MotorType.kBrushless); 
    m_RhookEncoder = m_Rhook.getEncoder(); 

    //PID for closed loop control
    m_LhookPID = m_Lhook.getPIDController();
    m_LhookPID.setP(Constants.kP);
    m_LhookPID.setI(Constants.kI);
    m_LhookPID.setD(Constants.kD);
    m_LhookPID.setIZone(Constants.kIz);
    m_LhookPID.setFF(Constants.kFF);
    m_LhookPID.setOutputRange(Constants.kMinOutput, Constants.kMaxOutput); 

    m_RhookPID = m_Rhook.getPIDController();
    m_RhookPID.setP(Constants.kP);
    m_RhookPID.setI(Constants.kI);
    m_RhookPID.setD(Constants.kD);
    m_RhookPID.setIZone(Constants.kIz);
    m_RhookPID.setFF(Constants.kFF);
    m_RhookPID.setOutputRange(Constants.kMinOutput, Constants.kMaxOutput); 

  }
  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *  
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }
  public void goUp(){
    if(Robot.GameStage.equals("auto")){
      
    }else if(Robot.GameStage.equals("teleop")){
      
    }
  }
  @Override
  public void periodic() {
      SmartDashboard.putNumber("LhookEncoder Position", m_LhookEncoder.getPosition());
      SmartDashboard.putNumber("RhookEncoder Position", m_RhookEncoder.getPosition());
      if(RobotContainer.m_controller.getRawButton(Constants.switchFPort)){
        m_RhookPID.setReference(RhookSetPoint, CANSparkMax.ControlType.kPosition);
        m_LhookPID.setReference(LhookSetPoint, CANSparkMax.ControlType.kPosition);
      }else{
        m_Lhook.set(0);
        m_Rhook.set(0);
      }
  }
  public void hook(){
    if(RobotContainer.m_controller.getRawButton(Constants.buttonHPort)){
      RhookSetPoint = RhookSetPoint*10*RobotContainer.m_controller.getRawAxis(4);

    }
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
