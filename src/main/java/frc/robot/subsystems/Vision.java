package frc.robot.subsystems;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.targeting.TargetCorner;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.VisionConstants;

public class Vision extends SubsystemBase{

    private PhotonCamera limelight = new PhotonCamera("limelight");

    //    private PhotonPipelineResult result = limelight.getLatestResult();
    //    private boolean hasTarget = limelight.getLatestResult().hasTargets();
    //    private List<PhotonTrackedTarget> targets = result.getTargets();
    //    private PhotonTrackedTarget bestTarget = result.getBestTarget();
    //    private double yaw = bestTarget.getYaw();
    //    private double pitch = bestTarget.getPitch();
    //    private double area = bestTarget.getArea();
    //    private double skew = bestTarget.getSkew();
    
    public Vision() {

        // var result = limelight.getLatestResult();
        // boolean hasTarget = limelight.hasTargets();
        // List<PhotonTrackedTarget> targets = result.getTargets();
        // PhotonTrackedTarget bestTarget = result.getBestTarget();
        // double yaw = bestTarget.getYaw();
        // double pitch = bestTarget.getPitch();
        // double area = bestTarget.getArea();
        // double skew = bestTarget.getSkew();
        // Transform2d pose = bestTarget.getCameraToTarget();
        // List<TargetCorner> corners = bestTarget.getCorners();

        // int targetID = bestTarget.getFiducialId();
        // double poseAmbiguity = bestTarget.getPoseAmbiguity();
        // Transform3d bestCameraToTarget = bestTarget.getBestCameraToTarget();
        // Transform3d alternateCameraToTarget = bestTarget.getAlternateCameraToTarget();

        // Pose3d robotPose = PhotonUtils.estimateFieldToRobotAprilTag(
        //     bestTarget.getBestCameraToTarget(), 
        //     aprilTagFieldLayout.getTagPose(bestTarget.getFiducialId()),
        //     cameraToRobot
        // );

        // PhotonUtils.calculateDistanceToTargetMeters(poseAmbiguity, targetID, pitch, targetID);
    }

    public double getDistance() {
        var result = limelight.getLatestResult();
        boolean hasTarget = limelight.getLatestResult().hasTargets();

        if (result.hasTargets()) {
            double range = PhotonUtils.calculateDistanceToTargetMeters(
                0, //test numbers
                0, //test numbers
                0, //test numbers
                Units.degreesToRadians(result.getBestTarget().getPitch())
            );
            return range;
        } 
        return 0;
    } 

    public double getYaw() {
        var result = limelight.getLatestResult();
        // boolean hasTarget = limelight.getLatestResult().hasTargets();

        if (result.hasTargets()) {
            return result.getBestTarget().getYaw();
        } 
        return 0;
    }

    

    @Override
    public void periodic() {
        // var result = limelight.getLatestResult();
        // boolean hasTarget = limelight.getLatestResult().hasTargets();
    }
}
