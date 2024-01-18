package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.MotorCommutation;
import com.ctre.phoenix.motorcontrol.NeutralMode;
// import com.ctre.phoenix.motorcontrol.can.TalonFX;
// import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.revrobotics.CANSparkMax;
import com.ctre.phoenix6.StatusSignal;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;
// import com.revrobotics.CANSparkMaxLowLevel.PeriodicFrame;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.DeviceConstants;

public class ElevatorSubsystem extends SubsystemBase{

    // private CANSparkMax elevator1 = new CANSparkMax(DeviceConstants.elevator1, MotorType.kBrushless);
    // private CANSparkMax elevator2 = new CANSparkMax(DeviceConstants.elevator2, MotorType.kBrushless);

    private TalonFX elevator1 = new TalonFX(DeviceConstants.elevator1);
    private TalonFX elevator2 = new TalonFX(DeviceConstants.elevator2);

    private PIDController elevatorPID = new PIDController(0, 0, 0);

    public ElevatorSubsystem() {

        elevator1.setInverted(true);
        elevator2.setInverted(false);

        elevator1.setNeutralMode(NeutralModeValue.Brake);
        elevator2.setNeutralMode(NeutralModeValue.Brake);

        // elevator1.configClosedloopRamp(0);
        // elevator1.configOpenloopRamp(0);
        // elevator2.configClosedloopRamp(0);
        // elevator2.configOpenloopRamp(0);

    }

    public double getEncoderMeters() {
        return 0;
    }

    public void resetEncoders() {
        // elevator1.setSelectedSensorPosition(0);
        // elevator2.setSelectedSensorPosition(0);
    }
    
    public void setElevatorSpeed(double speed) {
        elevator1.set(speed);
        elevator2.set(speed);
    }

    public Command elevatorSetpointCommand(double setpoint) {
        elevatorPID.setSetpoint(setpoint);
        elevatorPID.reset();
        double speed = elevatorPID.calculate(getEncoderMeters());
        return run(() -> {
            setElevatorSpeed(speed);
        });
    }
}
