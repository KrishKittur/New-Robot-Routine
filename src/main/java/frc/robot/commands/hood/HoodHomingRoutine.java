package frc.robot.commands.hood;

import static frc.robot.Constants.Hood.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HoodSubsystem;

public class HoodHomingRoutine extends CommandBase {

    // Insantiate the global variables
    HoodSubsystem req_subsystem;

    public HoodHomingRoutine(HoodSubsystem subsystem) {
        // Establish the command requirements
        req_subsystem = subsystem;
        addRequirements(subsystem);
    }

    // In the initalize method set the hood motor
    @Override
    public void initialize() {
        req_subsystem.setHoodMotor(-3);
        req_subsystem.setRampRate(5);
        System.out.println(req_subsystem.getCurrent());
    }

    // If the current is greater than the current threshold than end the command
    @Override
    public boolean isFinished() {
        return req_subsystem.getCurrent() > HOOD_CURRENT_THRESHOLD;
    }

    // In the end method turn off the motor
    @Override
    public void end(boolean interrupted) {
        req_subsystem.resetHoodEncoder();
        req_subsystem.setHoodMotor(0);
        req_subsystem.setRampRate(0);
        System.out.println(req_subsystem.getCurrent());
    }


}