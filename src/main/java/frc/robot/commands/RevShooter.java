package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShootingConstants;
import frc.robot.subsystems.Shooter;

public class RevShooter extends Command {

    private Shooter shooter = new Shooter();


    public RevShooter(Shooter shooter) {
        addRequirements(shooter);
        this.shooter = shooter;
    }

    @Override
    public void initialize() {
        shooter.setShooterRPM(ShootingConstants.rpmShootSupply);
    }

    @Override
    public void execute() {
        // Benny Blanco
    }

    @Override
    public void end(boolean interrupted) {
        // shooter.setShooterRPM(0);
    }
}
