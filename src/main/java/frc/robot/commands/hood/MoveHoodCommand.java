package frc.robot.commands.hood;

import static frc.robot.Constants.Hood.*;
import frc.robot.subsystems.HoodSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class MoveHoodCommand extends CommandBase {

    PIDController hoodPIDController = new PIDController(HOOD_ENCODER_KP, HOOD_ENCODER_KI, HOOD_ENCODER_KD);
    double hoodSetpoint;
    HoodSubsystem req_subsystem;
    
    public MoveHoodCommand(HoodSubsystem subsystem, double hoodSetpoint) {
        req_subsystem = subsystem;
        addRequirements(subsystem);
        this.hoodSetpoint = hoodSetpoint;
        hoodPIDController.setTolerance(3, 1);
    }

    

}