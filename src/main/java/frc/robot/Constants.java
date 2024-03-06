// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static int mainLeft = 1;
    public static int followerLeft = 2;
    public static int mainRight = 1;
    public static int followerRight = 2;

    public static int spinnerMotor = 10;
    public static double spinnerSpeed = 0.1;

    public static int bottomIntakeRollerMotor = 4;
    public static int intakeBeltMotor = 3;
    public static int topIntakeRollerMotor = 2;

    public static int backLeftFlywheelMotor = 7;
    public static int frontRightFlywheelMotor = 5;
    public static int frontLeftFlywheelMotor = 6;
    public static int backRightFlywheelMotor = 8;

    public static int telescopicArmLeftMotor = 1;
    public static int telescopicArmRightMotor = 9;

    public static int GenericHIDPort = 0;

    public static int buttonDPort = 4;
    public static int buttonAPort = 3;
    public static int buttonGPort = 5;
    public static int buttonHPort = 6;
    public static int switchFPort = 2;
    public static int switchCPort = 7;
    public static double idleLED = -0.21;
    public static double ringLED = 0.07;
    public static double speakerLED = -0.97;
    public static double ampLED = -0.92;

    //Hook's Proportion-Integral-Derivative(PID) Constants
    public static double kP = 0.1; 
    public static double kI = 0;
    public static double kD = 0; 
    public static double kIz = 0; 
    public static double kFF = 0; 
    public static double kMaxOutput = 1; 
    public static double kMinOutput = -1;

    //Autonomous Time
    public static long startTime;
}
