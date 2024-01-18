package frc.robot.subsystems;

import java.lang.Character.Subset;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.MotorCommutation;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMaxLowLevel.PeriodicFrame;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.DeviceConstants;
import frc.robot.Constants.ShootingConstants;

public class Shooter extends SubsystemBase{

    private TalonFX shootWheel1 = new TalonFX(DeviceConstants.shootWheel);
    private TalonFX shootWheel2 = new TalonFX(DeviceConstants.indexWheel);

    private TalonFX storageIndexMotor = new TalonFX(5);
    
    private Vision vision = new Vision();

    public Shooter() {
        shootWheel1.setInverted(false);
        shootWheel2.setInverted(false);

        shootWheel1.configOpenloopRamp(0);
        shootWheel1.configClosedloopRamp(1);
        shootWheel2.configOpenloopRamp(0);
        shootWheel2.configClosedloopRamp(1);

        shootWheel1.setNeutralMode(NeutralMode.Coast);
        shootWheel2.setNeutralMode(NeutralMode.Coast);

        shootWheel1.setInverted(false);
        shootWheel2.setInverted(false);

        storageIndexMotor.setInverted(false);

        storageIndexMotor.setNeutralMode(NeutralMode.Brake);

        storageIndexMotor.configClosedloopRamp(0);
        storageIndexMotor.configOpenloopRamp(0);
    }

    public void setDualSpeedPercent(double speed) {
        shootWheel1.set(ControlMode.PercentOutput, speed);
        shootWheel2.set(ControlMode.PercentOutput, speed);
    }

    public void setDualPRM(double RPM) {
        shootWheel1.set(ControlMode.Velocity, RPM);
        shootWheel2.set(ControlMode.Velocity, RPM);
    }

    public void setShooterRPM(double rpm) {
        double motorSpeed = (2048 / 600.0) * rpm;

        shootWheel1.set(ControlMode.Velocity, motorSpeed);
        shootWheel2.set(ControlMode.Velocity, motorSpeed);

        SmartDashboard.putNumber("Shooter RPM", ( (600.0 / 2048) * shootWheel1.getSelectedSensorVelocity()));
    }

    public void setRevwheelRPM(double rpm) {
        // Max RPM: ~6540 RPM
        // SmartDashboard.putNumber("Target Revwheel RPM", rpm);
        double revolutionsPerSecond = rpm / 60.0;
        shootWheel1.set(ControlMode.Velocity, revolutionsPerSecond);
        shootWheel2.set(ControlMode.Velocity, revolutionsPerSecond);
    }

    public static double inchesToRPM(double inches) {
        double testRPM = SmartDashboard.getNumber("Test Number", 0);

        if (testRPM != 0) {
            return testRPM;
        } else {
            return inches * 7.47 + 3800.0;
        }
    }
    
    
    public void setIndexRPM(double rpm) {
        double motorSpeed = (2048 / 600.0) * rpm;

        storageIndexMotor.set(ControlMode.Velocity, motorSpeed);

        SmartDashboard.putNumber("Index Wheel RPM", ((600.0 / 2048) * shootWheel2.getSelectedSensorVelocity()));
    }

    public void stop() {
        shootWheel1.set(ControlMode.Velocity, 0);
        shootWheel2.set(ControlMode.Velocity, 0);
    }

    public double getVelocity() {
        return shootWheel1.getSelectedSensorPosition();
    }

    public Command getTopIntakeCommand() {
        // The startEnd helper method takes a method to call when the command is initialized and one to
        // call when it ends
        return this.startEnd(
            () -> {
              setShooterRPM(ShootingConstants.IntakeShooterSpeed);
              setIndexRPM(ShootingConstants.IntakeIndexSpeed);
            },
            () -> {
              stop();
            });
    }
}
