package frc.robot.commands.accelerator;

import frc.robot.subsystems.AcceleratorSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunEndAcceleratorCommand extends CommandBase {
    
    AcceleratorSubsystem req_subsystem;

    public RunEndAcceleratorCommand(AcceleratorSubsystem subsystem) {
        req_subsystem = subsystem;
        addRequirements(subsystem);
    }

    // On the ending turn the accelerator off
    @Override
    public void end(boolean interrupted) {
        req_subsystem.setAcceleratorMotorBP(0);
    }
    
     
}
