// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.Vision;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static Command SimpleMoveAuto(Swerve s_Swerve) {
    return Commands.sequence(new InstantCommand(() -> s_Swerve.drive(new Translation2d(0.15 / 20,0), 0, false, true)));
  }

  public static Command VisionMoveAuto(Swerve swerve, Vision vision) {
    double targetDistanceInMeters = Units.feetToMeters(2);

    PIDController xController = new PIDController(Constants.AutoConstants.kPXController, 0, 0);
    // PIDController rotController = new PIDController(Constants.AutoConstants.kPThetaController, 0, 0);
    ProfiledPIDController thetaController = new ProfiledPIDController(
                Constants.AutoConstants.kPThetaController, 0, 0, Constants.AutoConstants.kThetaControllerConstraints);
        thetaController.enableContinuousInput(-Math.PI, Math.PI);

    return Commands.sequence(new InstantCommand(() ->
    swerve.drive(new Translation2d(xController.calculate(
      vision.getDistance(), targetDistanceInMeters),0),
        thetaController.calculate(vision.getYaw(), 0),
         false,
          true
          )
    ));
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
