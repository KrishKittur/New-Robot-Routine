package frc.robot.commands.hood;

import static frc.robot.Constants.Hood.*;
import frc.robot.subsystems.HoodSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;

public class MoveHoodCommand extends CommandBase {

    // Instantiate the global variables
    PIDController hoodPIDController = new PIDController(HOOD_ENCODER_KP, HOOD_ENCODER_KI, HOOD_ENCODER_KD);
    double hoodSetpoint;
    HoodSubsystem req_subsystem;
    
    public MoveHoodCommand(HoodSubsystem subsystem, double hoodSetpoint) {
        // Establish the required subsystems
        req_subsystem = subsystem;
        addRequirements(subsystem);

        // Set the hood setpoint
        this.hoodSetpoint = hoodSetpoint;

        // Set the tolerance for our PID controller
        hoodPIDController.setTolerance(3, 1);
    }

    // Im the execute method, set the hood motor based on the PID controllers outputs
    @Override
    public void execute() {
        double outputPid = hoodPIDController.calculate(req_subsystem.readHoodEncoder(), hoodSetpoint);
        double clampedPid = MathUtil.clamp(outputPid, -10, 10);
        req_subsystem.setHoodMotor(clampedPid);
    }

    // If the PID controller is at its setpoint then the command is finished
    @Override
    public boolean isFinished() {
        return hoodPIDController.atSetpoint();
    }

    // At the end of the command, turn the hood motor off
    @Override
    public void end(boolean interrupted) {
        req_subsystem.setHoodMotor(0.0);
    }

    

}