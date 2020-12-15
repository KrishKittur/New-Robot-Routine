package frc.robot.commands;

import frc.robot.subsystems.SpindexerSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TurnSpindexerCommand extends CommandBase {
    
    SpindexerSubsystem req_subsystem;

    public TurnSpindexerCommand(SpindexerSubsystem subsystem) {
        req_subsystem = subsystem;
        
        addRequirements(subsystem);
    }

  
}
