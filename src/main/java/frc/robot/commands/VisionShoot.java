package frc.robot.commands;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

public class VisionShoot extends Command{
    private Vision vision;
    private Shooter shooter;
    private PhotonCamera limelight = new PhotonCamera("2531photonvision");
    // private boolean hasTarget = limelight.getLatestResult().hasTargets();


    public VisionShoot(Vision vision, Shooter shooter) {
        this.shooter = shooter;
        this.vision = vision;
        addRequirements(shooter);
        addRequirements(vision);

        
    }

    @Override
    public void initialize() {
        // boolean hasTarget = limelight.getLatestResult().hasTargets();

    }

    @Override
    public void execute() {
        var result = limelight.getLatestResult();
        boolean hasTarget = limelight.getLatestResult().hasTargets();
        double range;

        if (true) {
            range = PhotonUtils.calculateDistanceToTargetMeters(
                0, 
                0, 
                0,
                Units.degreesToRadians(result.getBestTarget().getPitch())
            );
            System.out.println("Target Found and Firing");
            new InstantCommand(() -> shooter.setDualPRM(shooter.inchesToRPM(range)), shooter);
        } 
        System.out.println("Target Not Readable");
    }

    @Override
    public void end(boolean interrupted) {
        shooter.setDualPRM(0);
    }
    
}
