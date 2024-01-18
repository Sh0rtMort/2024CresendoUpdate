package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import frc.lib.util.COTSFalconSwerveConstants;
import frc.lib.util.SwerveModuleConstants;

public final class Constants {
    public static final double stickDeadband = 0.1;

    public static final class Swerve {
        public static final int pigeonID = 0;
        public static final boolean invertGyro = false; // Always ensure Gyro is CCW+ CW-

        public static final COTSFalconSwerveConstants chosenModule =  //TODO: This must be tuned to specific robot
            COTSFalconSwerveConstants.SDSMK4i(COTSFalconSwerveConstants.driveGearRatios.SDSMK4i_L2);

        /* Drivetrain Constants */
        public static final double trackWidth = Units.inchesToMeters(21); //TODO: This must be tuned to specific robot
        public static final double wheelBase = Units.inchesToMeters(21); //TODO: This must be tuned to specific robot
        public static final double wheelCircumference = chosenModule.wheelCircumference;

        /* Swerve Kinematics 
         * No need to ever change this unless you are not doing a traditional rectangular/square 4 module swerve */
         public static final SwerveDriveKinematics swerveKinematics = new SwerveDriveKinematics(
            new Translation2d(wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(wheelBase / 2.0, -trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, -trackWidth / 2.0));

        /* Module Gear Ratios */
        public static final double driveGearRatio = chosenModule.driveGearRatio;
        public static final double angleGearRatio = chosenModule.angleGearRatio;

        /* Motor Inverts */
        public static final boolean angleMotorInvert = chosenModule.angleMotorInvert;
        public static final boolean driveMotorInvert = chosenModule.driveMotorInvert;

        /* Angle Encoder Invert */
        public static final boolean canCoderInvert = chosenModule.canCoderInvert;

        /* Swerve Current Limiting */
        public static final int angleContinuousCurrentLimit = 25;
        public static final int anglePeakCurrentLimit = 40;
        public static final double anglePeakCurrentDuration = 0.1;
        public static final boolean angleEnableCurrentLimit = true;

        public static final int driveContinuousCurrentLimit = 35;
        public static final int drivePeakCurrentLimit = 60;
        public static final double drivePeakCurrentDuration = 0.1;
        public static final boolean driveEnableCurrentLimit = true;

        /* These values are used by the drive falcon to ramp in open loop and closed loop driving.
         * We found a small open loop ramp (0.25) helps with tread wear, tipping, etc */
        public static final double openLoopRamp = 0.055;
        public static final double closedLoopRamp = 0.0;

        /* Angle Motor PID Values */
        public static final double angleKP = chosenModule.angleKP;
        public static final double angleKI = chosenModule.angleKI;
        public static final double angleKD = chosenModule.angleKD;
        public static final double angleKF = chosenModule.angleKF;

        /* Drive Motor PID Values */
        // public static final double driveKP = 0.84099; //TODO: This must be tuned to specific robot
        public static final double driveKP = 0.1000; //TODO: This must be tuned to specific robot
        public static final double driveKI = 0.0;
        public static final double driveKD = 0.0;
        public static final double driveKF = 0.0;

        /* Drive Motor Characterization Values 
         * Divide SYSID values by 12 to convert from volts to percent output for CTRE */
        public static final double driveKS = (0.32 / 12); //TODO: This must be tuned to specific robot
        public static final double driveKV = (1.51 / 12);
        public static final double driveKA = (0.27 / 12);

        /* Swerve Profiling Values */
        /** Meters per Second */
        public static final double maxSpeed = 0.05; //TODO: This must be tuned to specific robot
        //0.375
        /** Radians per Second */
        public static final double maxAngularVelocity = 0.10; //TODO: This must be tuned to specific robot
        //0.7
        /* Neutral Modes */
        public static final NeutralMode angleNeutralMode = NeutralMode.Coast;
        public static final NeutralMode driveNeutralMode = NeutralMode.Brake;

        /* Module Specific Constants */
        /* Front Left Module - Module 0 */
        public static final class Mod0 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 10; //CAN ID for the drive motor
            public static final int angleMotorID = 11; //CAN ID for the angle/steerer motor
            public static final int canCoderID = 12; //CAN ID for steer encoder
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(59.67773437500001);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }

        /* Front Right Module - Module 1 */
        public static final class Mod1 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 7; //CAN ID for the drive motor
            public static final int angleMotorID = 8; //CAN ID for the angle/steerer motor
            public static final int canCoderID = 9; //CAN ID for steer encoder
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(2.021484375);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }
        
        /* Back Left Module - Module 2 */
        public static final class Mod2 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 1; //CAN ID for the drive motor
            public static final int angleMotorID = 2; //CAN ID for the angle/steerer motor
            public static final int canCoderID = 3; //CAN ID for steer encoder
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(278.26171875);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }

        /* Back Right Module - Module 3 */
        public static final class Mod3 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 4; //CAN ID for the drive motor NEEDS TO BE FIXED
            public static final int angleMotorID = 5; //CAN ID for the angle/steerer motor NEEDS TO BE FIXED
            public static final int canCoderID = 6; //CAN ID for steer encoder
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(321.85546875-180);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }

    }

    public static final class AutoConstants { //TODO: The below constants are used in the example auto, and must be tuned to specific robot
        public static final double kMaxSpeedMetersPerSecond = 0.2;
        // public static final double kMaxAccelerationMetersPerSecondSquared = 0.5;
        public static final double kMaxAccelerationMetersPerSecondSquared = 0.25;
        public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
        public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;
    
        // public static final double kPXController = 2;
        public static final double kPXController = 0.5;
        public static final double kPYController = 1.75;
        public static final double kPThetaController = 0;
    
        /* Constraint for the motion profilied robot angle controller */
        public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
            new TrapezoidProfile.Constraints(
                kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
    }

    public static final class ShootingConstants {
        public static final int rpmShootSupply = 200;
        public static final int rpmIndexSupply = 50;

        public static final double triggerDeadband = 0.13;

        public static final int IntakeShooterSpeed = -50;
        public static final int IntakeIndexSpeed = -75;
    }

    public static final class IntakeConstants {
        public static final int k_pivotAngleGround = 100;
        public static final int k_pivotAngleSource = 20;
        public static final int k_pivotAngleAmp = 45;
        public static final int k_pivotAngleStow = 0;
    }

    public static final class ElevatorConstants {
        public static final double highSetpoint = 20;
        public static final double storeSetpoint = 20;
    }

    public static final class VisionConstants {
        public static final double CameraHeightMeters = 0.3; //sample height
        public static final double TargetHeightMeters = 0.3; //sample height
        public static final double CameraPitchRadians = 0.3; //sample height
    }
}
