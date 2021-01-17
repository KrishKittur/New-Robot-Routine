package frc.robot.subsystems;

import static frc.robot.Constants.Vision.*;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPipelineResult;
import org.photonvision.PhotonUtils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {

    // Instantiate the Camera
    PhotonCamera camera = new PhotonCamera("Bigboy");

    // Method to return the camera yaw
    public double getYaw() {
        PhotonPipelineResult result = camera.getLatestResult();
        if (result.hasTargets()) {
            return -(result.getBestTarget().getYaw() + 1);
        } else {
            return 0.0;
        }
    }

    // Method to return the camera distance
    public double getDistance() {
        PhotonPipelineResult result = camera.getLatestResult();
        if (result.hasTargets()) {
            double targetDistance = PhotonUtils.calculateDistanceToTargetMeters(
                CAMERA_HEIGHT, TARGET_HEIGHT, CAMERA_TILT, Units.degreesToRadians(result.getBestTarget().getPitch())
            );
            return targetDistance;
        } else {
            return 0.0;
        }
    }

    // In the periodic method, send the distance to smart dashboard
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Distance From Target", this.getDistance());
    }

}