package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Shooter;

public class FireShooter extends SequentialCommandGroup{

    public FireShooter(Shooter shooter) {
        addCommands(
            // new RevShooter(shooter),
            // new WaitCommand(2),
            // new IndexShooter(shooter)
           new RevShooter(shooter).withTimeout(1),
           new WaitCommand(0.3),
          new IndexShooter(shooter).handleInterrupt(() -> shooter.stop())
        );
    }   
}
