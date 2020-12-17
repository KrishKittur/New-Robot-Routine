package frc.robot.commands.spindexer;

import static frc.robot.Constants.Spindexer.*;
import frc.robot.subsystems.SpindexerSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TurnSpindexerCommand extends CommandBase {
    
    // Initialize the subsystems, controllers, and controller values
    SpindexerSubsystem req_subsystem;
    double spindexerSetpoint;
    PIDController spindexerPidController = new PIDController(SPINDEXER_ENCODER_KP, SPINDEXER_ENCODER_KI, SPINDEXER_ENCODER_KD);

    public TurnSpindexerCommand(SpindexerSubsystem subsystem) {
        // Establish the commands requirements and set the setters
        req_subsystem = subsystem;
        addRequirements(subsystem);
        spindexerPidController.setTolerance(4, 1);
    }

    // In the initialize method, set the setpoint to the nearest "valid" angle
    @Override
    public void initialize() {
        spindexerSetpoint = nearestAngle(req_subsystem.readSpindexerEncoder());
    }

    // In the execute method set the spindexer motors according to the PID controllers calculations 
    @Override
    public void execute() {
        req_subsystem.setSpindexerMotorBV(spindexerPidController.calculate(req_subsystem.readSpindexerEncoder(), spindexerSetpoint));
    }

    // In the end method set the spindexer motor to 0 
    @Override
    public void end(boolean interrupted) {
        req_subsystem.setSpindexerMotorBV(0);
    }

    // If the spindexer has reached its setpoint, then end the command
    @Override
    public boolean isFinished() {
        return spindexerPidController.atSetpoint();
    }

    // Method to calculate the nearest "valid" angle
    private double nearestAngle(double currentAngle) {
        double fifthPosition = Units.degreesToRadians(360/5);
        double angleRemainder = currentAngle % fifthPosition;
        double ratio = angleRemainder / fifthPosition;
        double nextMultipleOfPos = Math.round(ratio);
        double smallTargetAngle = nextMultipleOfPos * fifthPosition;
        double targetAngle = (currentAngle - angleRemainder) + smallTargetAngle;
        return targetAngle;
    }


}
