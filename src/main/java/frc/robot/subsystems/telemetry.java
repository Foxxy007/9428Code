// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class telemetry extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  AHRS ahrs;
  PIDController turnController;
  double rotateToAngleRate;
  

  static final double kP = 0.03;
  static final double kI = 0.00;
  static final double kD = 0.00;
  static final double kF = 0.001;

  

  static final double kToleranceDegrees = 2.0f;    
  static final double kTargetAngleDegrees = 90.0f;

  public telemetry() {
      try {
        ahrs = new AHRS(SPI.Port.kMXP); 
      }catch(RuntimeException ex ){
          DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
      }
      ahrs.resetDisplacement();

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
  public void action(){
    if(Robot.GameStage.equals("auto")){

    }else if(Robot.GameStage.equals("teleop")){


    }
  }
  public void update(){
    SmartDashboard.putBoolean("AHRS Connected", ahrs.isConnected());
    SmartDashboard.putNumber("AHRS Yaw", ahrs.getYaw());


  }

  @Override
  public void periodic() {
  }
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
