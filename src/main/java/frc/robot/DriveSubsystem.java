/* package frc.robot;
 
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
import edu.wpi.first.wpilibj.examples.ramsetecommand.Constants.DriveConstants;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {

  CANSparkMax motorLeft = new CANSparkMax(1, MotorType.kBrushless);
  CANSparkMax motorLeftFollower = new CANSparkMax(2, MotorType.kBrushless);
  CANSparkMax motorRight = new CANSparkMax(3, MotorType.kBrushless);
  CANSparkMax motorRightFollower = new CANSparkMax(4, MotorType.kBrushless);

  private final DifferentialDrive drive = new DifferentialDrive(motorLeft, motorRight);

  RelativeEncoder motorLeftEncoder = motorLeft.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
  RelativeEncoder motorRightEncoder = motorRight.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
  //Gyroscope - replace with our navx2 module
  //private final ADXRS450_Gyro m_gyro = new ADXRS450_Gyro();

  // Odometry class for tracking robot pos
  private final DifferentialDriveOdometry m_odometry;

  public DriveSubsystem() {
    //Set the motors to work in pairs so you only control motor 1 and 3
    motorLeftFollower.follow(motorLeft);
    motorRightFollower.follow(motorRight);
    // We need to invert one side of the drivetrain so that positive voltages 
    //result in both sides moving forward. Depending on how your robot's
    motorRight.setInverted(true);

    // Sets the distance per pulse for the encoders
    motorLeftEncoder.setDistancePerPulse(Constants.kEncoderDistancePerPulse);
    m_rightEncoder.setDistancePerPulse(Constants.kEncoderDistancePerPulse);

    // Reset Encoder values to 0
    
    m_odometry =

        new DifferentialDriveOdometry(

            m_gyro.getRotation2d(), m_leftEncoder.getDistance(), m_rightEncoder.getDistance());

  }


  @Override

  public void periodic() {

    // Update the odometry in the periodic block

    m_odometry.update(

        m_gyro.getRotation2d(), m_leftEncoder.getDistance(), m_rightEncoder.getDistance());

  }


  /**

   * Returns the currently-estimated pose of the robot.

   *

   * @return The pose.

   */
/*
  public Pose2d getPose() {

    return m_odometry.getPoseMeters();

  }


  /**

   * Returns the current wheel speeds of the robot.

   *

   * @return The current wheel speeds.

   */
/*
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {

    return new DifferentialDriveWheelSpeeds(m_leftEncoder.getRate(), m_rightEncoder.getRate());

  }


  /**

   * Resets the odometry to the specified pose.

   *

   * @param pose The pose to which to set the odometry.

   */
/*
  public void resetOdometry(Pose2d pose) {

    resetEncoders();

    m_odometry.resetPosition(

        m_gyro.getRotation2d(), m_leftEncoder.getDistance(), m_rightEncoder.getDistance(), pose);

  }


  /**

   * Drives the robot using arcade controls.

   *

   * @param fwd the commanded forward movement

   * @param rot the commanded rotation

   */
/*
  public void arcadeDrive(double fwd, double rot) {

    m_drive.arcadeDrive(fwd, rot);

  }


  /**

   * Controls the left and right sides of the drive directly with voltages.

   *

   * @param leftVolts the commanded left output

   * @param rightVolts the commanded right output

   */
/*
  public void tankDriveVolts(double leftVolts, double rightVolts) {

    m_leftMotors.setVoltage(leftVolts);

    m_rightMotors.setVoltage(rightVolts);

    m_drive.feed();

  }


  /** Resets the drive encoders to currently read a position of 0. */
/*
  public void resetEncoders() {

    m_leftEncoder.reset();

    m_rightEncoder.reset();

  }


  /**

   * Gets the average distance of the two encoders.

   *

   * @return the average of the two encoder readings

   */
/*
  public double getAverageEncoderDistance() {

    return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2.0;

  }


  /**

   * Gets the left drive encoder.

   *

   * @return the left drive encoder

   */
/*
  public Encoder getLeftEncoder() {

    return m_leftEncoder;

  }


  /**

   * Gets the right drive encoder.

   *

   * @return the right drive encoder

   */
/*
  public Encoder getRightEncoder() {

    return m_rightEncoder;

  }


  /**

   * Sets the max output of the drive. Useful for scaling the drive to drive more slowly.

   *

   * @param maxOutput the maximum output to which the drive will be constrained

   */
/*
  public void setMaxOutput(double maxOutput) {

    m_drive.setMaxOutput(maxOutput);

  }


  /** Zeroes the heading of the robot. */
/*
  public void zeroHeading() {

    m_gyro.reset();

  }


  /**

   * Returns the heading of the robot.

   *

   * @return the robot's heading in degrees, from -180 to 180

   */
/*
  public double getHeading() {

    return m_gyro.getRotation2d().getDegrees();

  }


  /**

   * Returns the turn rate of the robot.

   *

   * @return The turn rate of the robot, in degrees per second

   */
/*
  public double getTurnRate() {

    return -m_gyro.getRate();

  }

} */ 