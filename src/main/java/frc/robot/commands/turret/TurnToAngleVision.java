package frc.robot.commands.turret;

import static frc.robot.Constants.Hood.*;
import edu.wpi.first.wpilibj.MedianFilter;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class TurnToAngleVision extends CommandBase {
    
    // Initialize the subsystems, controllers, and the controllers values
    PIDController turretController = new PIDController(HOOD_ENCODER_KP, HOOD_ENCODER_KI, HOOD_ENCODER_KD);
    TurretSubsystem req_subsystem;
    VisionSubsystem vision_subsystem;
    double turretSetpoint;
    MedianFilter filter = new MedianFilter(7);

    public TurnToAngleVision(TurretSubsystem subsystem, VisionSubsystem vision_subsystem) {
        // Establish the commands requirements and set the setters
        req_subsystem = subsystem;
        addRequirements(subsystem);
        turretController.setTolerance(5, 2);

        this.vision_subsystem = vision_subsystem;
    }

    // In the execute method set the setpoint and turret motor based on the controllers readings/vision readings
    @Override
    public void execute() {

        double desiredPosition = req_subsystem.readTurretEncoder() + vision_subsystem.getYaw();
        double avgHeading = filter.calculate(desiredPosition);

        turretSetpoint = calcWhereToTurn(avgHeading, req_subsystem.readTurretEncoder());

        double outputPID = turretController.calculate(req_subsystem.readTurretEncoder(), turretSetpoint);
        req_subsystem.setTurretMotor(MathUtil.clamp(outputPID, -5, 5));
        System.out.println(outputPID + ", " + turretSetpoint);
    }

    // In the end method of this command set the turret motor to 0
    @Override
    public void end(boolean interrupted) {
        req_subsystem.setTurretMotor(0);
    }

    // Function to covert any angle to an angle for the turret to turn to 
    static double calcWhereToTurn(double angle, double turretPosition) {

        double angle360 = Math.abs(angle % 360);
        if (angle < 0.0) {
            if (angle360 != 0.0) {
                angle360 = 360 - angle360;
            }
        }

        double finalAngle;
        if (angle360 > 180) {
            finalAngle = -360 + angle360;
        } else {
            finalAngle = angle360;
        }
        
        if (Math.abs(angle) == 180) {
            finalAngle = angle;
        }

        return calcWhereToTurnLogic(finalAngle, turretPosition);
    }

    // Function to return where the turret should turn, based on a given angle between the range of (-180, 180)
    static double calcWhereToTurnLogic(double angle, double currentTurretPosition) {
        double angleToReturn = 0.0;
        if (angle > -150.0 && angle < 150.0) {
            angleToReturn = angle;
        }
        else {
            if (angle < 0.0) {
                angleToReturn = calcClosestTo(angle, (angle + 360), currentTurretPosition);
            } else {
                angleToReturn = calcClosestTo(angle, (angle - 360), currentTurretPosition);
            }
        }
        return angleToReturn;
    }

    // Function to calculate the closest number
    static double calcClosestTo(double num1, double num2, double closestToNum) {
        if (Math.abs(num1 - closestToNum) < Math.abs(num2 - closestToNum)) {
            return num1;
        } else {
            return num2;
        }
    }

}
