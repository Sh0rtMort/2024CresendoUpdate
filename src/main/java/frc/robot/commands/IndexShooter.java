package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShootingConstants;
import frc.robot.subsystems.Shooter;

public class IndexShooter extends Command{

    private Shooter shooter;
    
    public IndexShooter(Shooter shooter) {
        addRequirements(shooter);
        this.shooter = shooter;
    }

    @Override
    public void execute() {
        shooter.setIndexRPM(ShootingConstants.rpmIndexSupply);
    }
}
