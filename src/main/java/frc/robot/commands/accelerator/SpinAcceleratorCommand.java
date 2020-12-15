package frc.robot.commands.accelerator;

import frc.robot.subsystems.AcceleratorSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinAcceleratorCommand extends CommandBase {
    
    AcceleratorSubsystem req_subsystem;
    double speed;

    public SpinAcceleratorCommand(AcceleratorSubsystem subsystem, double speed) {
        req_subsystem = subsystem;
        addRequirements(subsystem);
        this.speed = speed;
    }

    // On the intialization turn the spin up the accelerator at the desired speed
    @Override
    public void initialize() {
        req_subsystem.setAcceleratorMotorBP(this.speed);
    } 

    // On the ending turn the accelerator off
    @Override
    public void end(boolean interrupted) {
        req_subsystem.setAcceleratorMotorBP(0);
    }
      
}
