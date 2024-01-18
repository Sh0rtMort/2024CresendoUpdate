package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShootingConstants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorManual extends Command{
    private XboxController operator;
    private double speed;
    private ElevatorSubsystem elevatorSubsystem;
    
    public ElevatorManual(ElevatorSubsystem elevatorSubsystem, XboxController operator, double speed) {
        addRequirements(elevatorSubsystem);
        this.elevatorSubsystem = elevatorSubsystem;
        this.operator = operator;
        this.speed = speed;

    }

    @Override
    public void execute() {
        if (operator.getRightTriggerAxis() > ShootingConstants.triggerDeadband) {
            elevatorSubsystem.setElevatorSpeed(operator.getRightTriggerAxis() * speed);
        } 

        if (operator.getLeftTriggerAxis() > ShootingConstants.triggerDeadband) {
            elevatorSubsystem.setElevatorSpeed(operator.getLeftTriggerAxis() * -speed);
        }
    }
}
