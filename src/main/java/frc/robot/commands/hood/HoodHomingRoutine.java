package frc.robot.commands.hood;

import static frc.robot.Constants.Hood.*;
import java.io.FileWriter;
import java.io.IOException;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HoodSubsystem;
import java.lang.String;

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
    }

    // In the execute method write the current data and time to a CSV file 
    @Override
    public void execute() {
        try {
            FileWriter hoodWriter = new FileWriter("data.csv", true);
            String hoodCurrent = String.valueOf(req_subsystem.getCurrent());
            String hoodTime = String.valueOf(RobotController.getFPGATime());
            hoodWriter.write(hoodCurrent + ", " + hoodTime);
            hoodWriter.close();
        } catch (IOException e) {
            System.out.println("An error occured! Please check the stacktrace: ");
            e.printStackTrace();
        }
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
    }


}