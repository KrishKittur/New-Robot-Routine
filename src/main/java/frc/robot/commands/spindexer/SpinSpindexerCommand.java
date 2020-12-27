package frc.robot.commands.spindexer;

import frc.robot.subsystems.SpindexerSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinSpindexerCommand extends CommandBase {
    
    SpindexerSubsystem req_subsystem;
    double startDistance;

    public SpinSpindexerCommand(SpindexerSubsystem subsystem) {
        req_subsystem = subsystem;
        
        addRequirements(subsystem);
    }

    // On the initialization set the starting distance and spin up the spindexer motor
    @Override
    public void initialize() {
        startDistance = req_subsystem.readSpindexerEncoder();
        req_subsystem.setSpindexerMotorBP(0.5);
    }

    // When the command finishes turn the spindexer motor off
    @Override
    public void end(boolean interrupted) {
        req_subsystem.setSpindexerMotorBP(0);
    }

    // Returns true when the spindexer has completed a full rotation
    @Override
    public boolean isFinished() {
        return startDistance - 360 >= req_subsystem.readSpindexerEncoder();
    }
     
}
