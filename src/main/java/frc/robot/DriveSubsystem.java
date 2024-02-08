/*package frc.robot;
 
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkRelativeEncoder;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;



public class DriveSubsystem extends SubsystemBase {

  CANSparkMax motorLeft = new CANSparkMax(1, MotorType.kBrushless);
  CANSparkMax motorLeftFollower = new CANSparkMax(2, MotorType.kBrushless);
  CANSparkMax motorRight = new CANSparkMax(3, MotorType.kBrushless);
  CANSparkMax motorRightFollower = new CANSparkMax(4, MotorType.kBrushless);

  private final DifferentialDrive drive = new DifferentialDrive(motorLeft, motorRight);

  RelativeEncoder motorLeftEncoder = motorLeft.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
  RelativeEncoder motorRightEncoder = motorRight.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
  //Gyroscope - replace with our navx2 module
  AHRS ahrs = new AHRS(SPI.Port.kMXP);

  // Odometry class for tracking robot pos
  private final DifferentialDriveOdometry odometry;

  public DriveSubsystem() {
    //Set the motors to work in pairs so you only control motor 1 and 3
    motorLeftFollower.follow(motorLeft);
    motorRightFollower.follow(motorRight);

    //Invert one side of the drivetrain
    motorRight.setInverted(true);

    // Sets the distance per pulse for the encoders. Still troubleshooting to 
    //figure out the proper ratio/how the ratio is set up
    motorLeftEncoder.setPositionConversionFactor(Constants.kEncoderDistancePerPulse);
    motorRightEncoder.setPositionConversionFactor(Constants.kEncoderDistancePerPulse);

    // Reset Encoder values to 0
    motorLeftEncoder.setPosition(0);
    motorRightEncoder.setPosition(0);

    odometry =
      new DifferentialDriveOdometry(
      ahrs.getRotation2d(),
      motorLeftEncoder.getPosition(),
      motorRightEncoder.getPosition()); //Need to set the conversion factor

  }
  @Override

  // Update the odometry in the periodic block
  public void periodic() {
    odometry.update(
      ahrs.getRotation2d(),
      motorLeftEncoder.getPosition(),
      motorRightEncoder.getPosition()); //Need to set the conversion factor
  }

  //Returns the currently-estimated pose of the robot.
  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }

  //Returns the current wheel speeds of the robot.
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(m_leftEncoder.getRate(), m_rightEncoder.getRate());
  }

  //Resets the odometry to the specified pose.
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    odometry.resetPosition(
    gyro.getRotation2d(), m_leftEncoder.getDistance(), m_rightEncoder.getDistance(), pose);
  }
    
  //Drives the robot using arcade controls.
  public void arcadeDrive(double fwd, double rot) {
    drive.arcadeDrive(fwd, rot);
  }

  //Controls the left and right sides of the drive directly with voltages.
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    motorLeft.setVoltage(leftVolts);
    motorRight.setVoltage(rightVolts);
    drive.feed();
  }

  //Resets the drive encoders to currently read a position of 0.
  public void resetEncoders() {
    motorLeftEncoder.setPosition(0);
    motorRightEncoder.setPosition(0);
  }

  //Gets the average distance of the two encoders.
  public double getAverageEncoderDistance() {
    return (motorLeftEncoder.getPosition() + motorRightEncoder.getPosition()) / 2.0;
  }
/* 
  //Gets the left drive encoder.
  public Encoder getLeftEncoder() {
    return motorLeftEncoder;
  } */
/* 
  //Gets the right drive encoder.
  public Encoder getRightEncoder() {
    return m_rightEncoder;
  } */
/* 
  //Sets the max output of the drive. Useful for scaling the drive to drive more slowly.
  public void setMaxOutput(double maxOutput) {
    drive.setMaxOutput(maxOutput);
  }

  //Zeroes the heading of the robot. 
  public void zeroHeading() {
    ahrs.zeroYaw();
  }

  //Returns the heading of the robot.
  public double getHeading() {
    return gyro.getRotation2d().getDegrees();
  }
  
  //Returns the turn rate of the robot.
  public double getTurnRate() {
    return -gyro.getRate();
  }
}
*/