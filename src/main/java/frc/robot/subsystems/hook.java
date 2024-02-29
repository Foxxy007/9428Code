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

public class hook extends SubsystemBase {
  private CANSparkMax m_Lhook;
  private RelativeEncoder m_LhookEncoder;
  private SparkPIDController m_LhookPID;
  public hook() {
    m_Lhook = new CANSparkMax(Constants.telescopicArmLeftMotor, MotorType.kBrushless); 
    m_LhookEncoder = m_Lhook.getEncoder(); 
    //PID for closed loop control
    m_LhookPID = m_Lhook.getPIDController();
    m_LhookPID.setP(Constants.kP);
    m_LhookPID.setI(Constants.kI);
    m_LhookPID.setD(Constants.kD);
    m_LhookPID.setIZone(Constants.kIz);
    m_LhookPID.setFF(Constants.kFF);
    m_LhookPID.setOutputRange(Constants.kMinOutput, Constants.kMaxOutput);   
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
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
