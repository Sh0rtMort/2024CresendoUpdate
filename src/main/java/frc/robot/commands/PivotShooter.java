package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class PivotShooter extends Command {
    private Shooter shooter;

    private PIDController pivotPIDs = new PIDController(0, 0, 0);
    private double setpoint;

    public PivotShooter(Shooter shooter, double setpoint) {
        this.shooter = shooter;
        this.setpoint = setpoint;
        addRequirements(shooter);

        pivotPIDs.setSetpoint(setpoint);
        pivotPIDs.setTolerance(0.2);

    }

    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {
        double speed = pivotPIDs.calculate(0, setpoint);
    }

    @Override
    public void end(boolean interrupted) {
        
    }
}
