package frc.robot.commands.spindexer;

import static frc.robot.Constants.Spindexer.*;
import frc.robot.subsystems.SpindexerSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;

public class TurnSpindexerCommand extends CommandBase {
    
    // Initialize the subsystems, controllers, and controller values
    SpindexerSubsystem req_subsystem;
    double spindexerSetpoint;
    PIDController spindexerPidController = new PIDController(SPINDEXER_ENCODER_KP, SPINDEXER_ENCODER_KI, SPINDEXER_ENCODER_KD);

    public TurnSpindexerCommand(SpindexerSubsystem subsystem) {
        // Establish the commands requirements and set the setters
        req_subsystem = subsystem;
        addRequirements(subsystem);
        spindexerPidController.setTolerance(0.05, 0.5); // For Tuning
    }

    // In the initialize method, set the setpoint to the nearest "valid" angle
    @Override
    public void initialize() {
        spindexerSetpoint = nearestAngle(req_subsystem.readSpindexerEncoder());
    }

    // In the execute method set the spindexer motors according to the PID controllers calculations 
    @Override
    public void execute() {
        req_subsystem.setSpindexerMotorBV(-1 * MathUtil.clamp(spindexerPidController.calculate(req_subsystem.readSpindexerEncoder(), spindexerSetpoint), -10, 10));
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
        double fifthPos = 360/5;
        double remainder = currentAngle % 360;
        double ratio = remainder / fifthPos;
        double multipleOfPos = Math.floor(ratio);
        double addNum = multipleOfPos * fifthPos;
        double targetAngle = (currentAngle - remainder) + addNum;
        return targetAngle - SPINDEXER_OFFSET_POSITION;
    }
    



}
