/* 
* DO NOT RUN THIS COMMAND BY ITSELF
* DOING SO WILL RESULT IN THE ACCELERATOR NOT STOPPING
* YOU MUST RUN THE RUNACCELERATORCOMMAND AFTER THIS COMMAND
*/

package frc.robot.commands.accelerator;

import frc.robot.subsystems.AcceleratorSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class StartAcceleratorCommand extends CommandBase {
    
    AcceleratorSubsystem req_subsystem;
    double speed;

    public StartAcceleratorCommand(AcceleratorSubsystem subsystem, double speed) {
        req_subsystem = subsystem;
        addRequirements(subsystem);
        this.speed = speed;
    }

    // On the initialization spin up the accelerator
    @Override
    public void initialize() {
        req_subsystem.setAcceleratorMotorBP(this.speed);
    }
     
}
