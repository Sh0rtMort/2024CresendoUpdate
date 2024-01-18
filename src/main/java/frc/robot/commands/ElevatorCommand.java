package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorCommand extends Command {

    private PIDController elevator1Pid;
    private PIDController elevator2Pid;

    private int setpoint;
    private ElevatorSubsystem elevatorSubsystem;

    public ElevatorCommand(ElevatorSubsystem elevatorSubsystem, int setpoint) {
        addRequirements(elevatorSubsystem);
        this.elevatorSubsystem = elevatorSubsystem;
        this.setpoint = setpoint;

        this.elevator1Pid = new PIDController(0, 0, 0);
        elevator1Pid.setTolerance(2);
        elevator1Pid.setSetpoint(setpoint);

        this.elevator2Pid = new PIDController(0, 0, 0);
        elevator2Pid.setTolerance(2);
        elevator2Pid.setSetpoint(setpoint);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        double speed = elevator1Pid.calculate(elevatorSubsystem.getEncoderMeters());

        elevatorSubsystem.setElevatorSpeed(speed);
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.setElevatorSpeed(0);
    }
}
