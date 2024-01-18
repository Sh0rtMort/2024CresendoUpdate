package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.DeviceConstants;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {

    private TalonFX intakeAngleMotor = new TalonFX(DeviceConstants.intakeAngleMotorID);
    private TalonFX intakePowerMotor = new TalonFX(DeviceConstants.intakePowerMotorID);
    private final DigitalInput m_IntakeLimitSwitch = new DigitalInput(DeviceConstants.limitSwitchID);

    private PIDController pivotPIDController = new PIDController(0.12, 0, 0.001);

    private final double tick2meter = 1.0 / 2048.0 * 0.1 * Math.PI;

    public Intake() {
        intakeAngleMotor.setInverted(false);
        intakePowerMotor.setInverted(false);

        intakeAngleMotor.setNeutralMode(NeutralMode.Brake);
        intakePowerMotor.setNeutralMode(NeutralMode.Brake);
    }

    public double getEncoderMeters() {
        return intakeAngleMotor.getSelectedSensorPosition();
    }

    public void zeroAngleEncoder() {
        intakeAngleMotor.setSelectedSensorPosition(0);
    }

    public void setAngleMotor(double speed) {
        intakeAngleMotor.set(ControlMode.PercentOutput, speed);
    }

    public Command intakeSetpointCommand(double setpoint) {
        pivotPIDController.reset();
        return runEnd(
            () -> {
            pivotPIDController.setSetpoint(setpoint);
            double speed = pivotPIDController.calculate(getEncoderMeters());
            setAngleMotor(speed);
            System.out.println("Intake Moving to:" + setpoint);
        }, () -> {
            pivotPIDController.setSetpoint(IntakeConstants.k_pivotAngleStow);
            double speed = pivotPIDController.calculate(getEncoderMeters());
            setAngleMotor(speed);
            System.out.println("Intake Moving to stow");
        });
    } 

}
