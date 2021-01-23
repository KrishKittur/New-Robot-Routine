package frc.robot.commands.hood;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HoodSubsystem;

public class HoodHomingRoutine extends CommandBase {

    // Insantiate the global variables
    HoodSubsystem req_subsystem;
    double time;

    public HoodHomingRoutine(HoodSubsystem subsystem) {
        // Establish the command requirements
        req_subsystem = subsystem;
        addRequirements(subsystem);
    }

    // In the initalize method set the hood motor
    @Override
    public void initialize() {
        req_subsystem.setHoodMotor(-3);
        time = RobotController.getFPGATime() * 1.0/1000000.0;
    }

    // If the current is greater than the current threshold than end the command
    @Override
    public boolean isFinished() {
        return req_subsystem.getHoodVelocity() < 0.1 && RobotController.getFPGATime() * 1.0/1000000.0 >= time + 0.25;
    }

    // In the end method turn off the motor
    @Override
    public void end(boolean interrupted) {
        req_subsystem.resetHoodEncoder();
        req_subsystem.setHoodMotor(0);
    }


}