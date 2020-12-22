/* 
* DO NOT RUN THIS COMMAND BY ITSELF
* DOING SO WILL RESULT IN THE SHOOTER NOT STOPPING
* YOU MUST RUN THE RUNENDSHOOTERCOMMAND AFTER THIS COMMAND
*/

package frc.robot.commands.shooter;

import static frc.robot.Constants.Shooter.*;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;

public class StartShooterCommand extends CommandBase {
    
    ShooterSubsystem req_subsystem;
    double setPoint = 4000;
    PIDController shooterPIDController = new PIDController(SHOOTER_ENCODER_KP, SHOOTER_ENCODER_KD, SHOOTER_ENCODER_KD);

    public StartShooterCommand(ShooterSubsystem subsystem) {
        req_subsystem = subsystem;
        addRequirements(subsystem);
        shooterPIDController.setTolerance(10, 2);
    }

    // In the execute method set the shooters motors according to the pid controllers calculations
    @Override
    public void execute() {
        double outputPid = shooterPIDController.calculate(req_subsystem.readShooterEncoder(), setPoint);
        double outputVoltage = MathUtil.clamp(outputPid, -12, 12);
        req_subsystem.spinShooterBV(outputVoltage, outputVoltage);
    }
     
    // If the shooter has reached its setpoint then set isFinished to true
    @Override
    public boolean isFinished() {
        return shooterPIDController.atSetpoint();
    }
}
