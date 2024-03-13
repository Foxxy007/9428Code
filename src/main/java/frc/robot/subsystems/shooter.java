// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class shooter extends SubsystemBase {
  private final drivetrain m_driveShooter;
  private final CANSparkMax m_spin;
  private final CANSparkMax m_FLflywheel;
  private final CANSparkMax m_FRflywheel;
  private final CANSparkMax m_BLflywheel;
  private final CANSparkMax m_BRflywheel;
  private final Spark m_LED;

  /** Creates a new shooter. */
  public shooter() {

    m_driveShooter = new drivetrain();

    m_LED = new Spark(Constants.LEDChannel);
    m_spin = new CANSparkMax(Constants.spinnerMotor,MotorType.kBrushless);

    m_BLflywheel = new CANSparkMax(Constants.backLeftFlywheelMotor,MotorType.kBrushless);
    m_FRflywheel = new CANSparkMax(Constants.frontRightFlywheelMotor,MotorType.kBrushless);
    m_FLflywheel = new CANSparkMax(Constants.frontLeftFlywheelMotor,MotorType.kBrushless);
    m_BRflywheel = new CANSparkMax(Constants.backRightFlywheelMotor,MotorType.kBrushless);


    m_spin.setInverted(true);

    m_BLflywheel.setInverted(false);
    m_FRflywheel.setInverted(true);
    m_FLflywheel.setInverted(false);
    m_BRflywheel.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("BLflywheel",m_BLflywheel.get());
    SmartDashboard.putNumber("BRflywheel",m_BRflywheel.get());
    SmartDashboard.putNumber("FLflywheel",m_FLflywheel.get());
    SmartDashboard.putNumber("FRflywheel",m_FRflywheel.get());
    SmartDashboard.putNumber("Front Spinner", m_spin.get());
    
  }

  public void shoot(){
    if(Robot.GameStage.equals("auto")){

    }else if(Robot.GameStage.equals("teleop")){
      if(RobotContainer.m_controller.getRawButton(Constants.buttonAPort)){//Speaker Scoring
        //m_driveShooter.shooterBoost(0);
        m_spin.set(0);
        m_BLflywheel.set(1);
        m_BRflywheel.set(1);
        m_FLflywheel.set(1);
        m_FRflywheel.set(1);
        m_LED.set(Constants.speakerLED);
      }else if(RobotContainer.m_controller.getRawButton(Constants.buttonGPort)){//Intake through shooter
        // m_driveShooter.shooterBoost(0);
        m_spin.set(-0.2);
        m_BLflywheel.set(-0.15);
        m_BRflywheel.set(-0.15);
        m_FLflywheel.set(-0.15);
        m_FRflywheel.set(-0.15);
      }else if(RobotContainer.m_controller.getRawButton(Constants.buttonDPort)){//AmpScoring
        //m_driveShooter.shooterBoost(0);
        m_spin.set(Constants.spinnerSpeed);
        m_BLflywheel.set(0.25);
        m_BRflywheel.set(0.25);
        m_FLflywheel.set(0.1);
        m_FRflywheel.set(0.1);
        m_LED.set(Constants.ampLED);
      }else{// Idle
        // m_driveShooter.shooterBoost(0);// Uncomment in case of we do not stop driving
        m_spin.set(0);
        m_BLflywheel.set(0);
        m_BRflywheel.set(0);
        m_FLflywheel.set(0);
        m_FRflywheel.set(0);
        m_LED.set(Constants.idleLED);
      }  
    }
  }
  public void autoShooter(double power){
        m_BLflywheel.set(power);
        m_BRflywheel.set(power);
        m_FLflywheel.set(power);
        m_FRflywheel.set(power);

  }
  public void shootAmp(){//TODO delete since it has been Depricated
    // TODO: set flywheels to amp percentage (look in old code)
    if(Robot.GameStage.equals("auto")){

    }else if(Robot.GameStage.equals("teleop")){
      if(RobotContainer.m_controller.getRawButton(Constants.buttonDPort)){ //AmpScoring
        m_spin.set(0.1);
        m_BLflywheel.set(0.25);
        m_BRflywheel.set(0.25);
        m_FLflywheel.set(0.1);
        m_FRflywheel.set(0.1);
        m_LED.set(Constants.ampLED);
      }else{
        m_spin.set(0);
        m_BLflywheel.set(0);
        m_BRflywheel.set(0);
        m_FLflywheel.set(0);
        m_FRflywheel.set(0);
        m_LED.set(Constants.idleLED);
      }  
    }
  }
  public void reloadNote(){//TODO delete since it has been Depricated
    // TODO: reverse flywheels at 0.2 power
    if(Robot.GameStage.equals("auto")){

    }else if(Robot.GameStage.equals("teleop")){
      if(RobotContainer.m_controller.getRawButton(Constants.buttonGPort)){//Intake through shooter
        m_BLflywheel.set(-0.15);
        m_BRflywheel.set(-0.15);
        m_FLflywheel.set(-0.15);
        m_FRflywheel.set(-0.15);
      }else{
        m_spin.set(0);
        m_BLflywheel.set(0);
        m_BRflywheel.set(0);
        m_FLflywheel.set(0);
        m_FRflywheel.set(0);
        m_LED.set(Constants.idleLED);
      }  
    }
  }
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
