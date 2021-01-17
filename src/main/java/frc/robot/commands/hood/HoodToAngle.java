package frc.robot.commands.hood;

import static frc.robot.Constants.Hood.*;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.subsystems.HoodSubsystem;

public class HoodToAngle extends CommandBase {

    // Instaniate the global variables for this command
    PIDController hoodPID = new PIDController(HOOD_ENCODER_KP, HOOD_ENCODER_KI, HOOD_ENCODER_KD);
    HoodSubsystem req_subsystem;
    double hoodSetpoint;

    public HoodToAngle(HoodSubsystem subsystem) {
        // Establish the requirements for this command and set the tolerance for the PID controller
        req_subsystem = subsystem;
        addRequirements(subsystem);
        hoodPID.setTolerance(3, 1);
    }

    // In the execute method of this command, set the setpoint and set the hood motor based on the PID controllers output
    @Override
    public void execute() {
        hoodSetpoint = MathUtil.clamp(SmartDashboard.getNumber("Hood-Setpoint", 0), 0, 62);
        double outputPID = hoodPID.calculate(req_subsystem.getHoodDistance(), hoodSetpoint);
        req_subsystem.setHoodMotor(MathUtil.clamp(outputPID, -12, 12));
    }

    // In the end of this command turn off the hood motor
    @Override
    public void end(boolean interrupted) {
        req_subsystem.setHoodMotor(0.0);
    }
    
}