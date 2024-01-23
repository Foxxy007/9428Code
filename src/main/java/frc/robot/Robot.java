// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {
  //Create util object so we can use functions from the Util file
  //No idea how this works but i've had this line for like 3 years
  
  // Drive motors
  CANSparkMax motorLeftFront = new CANSparkMax(1, MotorType.kBrushless);
  CANSparkMax motorLeftBack = new CANSparkMax(2, MotorType.kBrushless);
  CANSparkMax motorRightFront = new CANSparkMax(3, MotorType.kBrushless);
  CANSparkMax motorRightBack = new CANSparkMax(4, MotorType.kBrushless);
  final GenericHID Controller1 = new GenericHID(0);
  double turn;
  double drive;
  // Flywheel
  //CANSparkMax flywheelFront = new CANSparkMax(11, MotorType.kBrushed);
  //CANSparkMax flywheelBack = new CANSparkMax(10, MotorType.kBrushed);
  double flywheelSpeed;
  boolean a;
  //Current Sensing
  PowerDistribution powerPanel = new PowerDistribution(1, ModuleType.kRev);

  public Robot() { 
  }
  
  @Override
  public void robotInit() {
  }
  @Override
  public void autonomousPeriodic() {
    //Autonomous
    RamseteController controller1 = new RamseteController();
    
  }
  @Override
  public void teleopPeriodic() {
    //LimeLight Variable Updates
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    double rawX = tx.getDouble(0.0);
    double rawY = ty.getDouble(0.0);
    double rawArea = ta.getDouble(0.0);
    //Robot actions
    turn = Util.clamp(Util.inputCurve(Controller1.getRawAxis(0),0.2));//x-axis 1
    drive = Util.clamp(Controller1.getRawAxis(3));//y-axis 2
    flywheelSpeed = Controller1.getRawAxis(4);//x-axis 2
    a = Controller1.getRawButton(7);//a-button
    //Drive
    motorLeftFront.set(drive+turn);
    motorLeftBack.set(drive+turn);
    motorRightFront.set(-drive+turn);
    motorRightBack.set(-drive+turn);
    //Flywheel
    /* flywheelFront.set(flywheelSpeed);
    if(a){
      flywheelBack.set(flywheelSpeed);
    }else{
      flywheelBack.set(0);
    } */
    
    //Telemetry
    SmartDashboard.putNumber("Limelight X", rawX);
    SmartDashboard.putNumber("Limelight Y", rawY);
    SmartDashboard.putNumber("Limelight Area", rawArea);
    SmartDashboard.putNumber("Total Current", powerPanel.getCurrent(0));

  }

  }

