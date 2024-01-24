package frc.robot;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.examples.ramsetecommand.Constants.DriveConstants;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  private final MotorControllerGroup m_leftMotors =
      new MotorControllerGroup(
          new PWMSparkMax(DriveConstants.kLeftMotor1Port),
          new PWMSparkMax(DriveConstants.kLeftMotor2Port));

  private final MotorControllerGroup m_rightMotors =
      new MotorControllerGroup(
          new PWMSparkMax(DriveConstants.kRightMotor1Port),
          new PWMSparkMax(DriveConstants.kRightMotor2Port));

}
