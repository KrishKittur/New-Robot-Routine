package frc.robot.commands.shooter;

import static frc.robot.Constants.Shooter.*;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;

public class RunEndShooterCommand extends CommandBase {
    
    ShooterSubsystem req_subsystem;
    double setPoint = 4000;
    PIDController shooterPIDController = new PIDController(SHOOTER_ENCODER_KP, SHOOTER_ENCODER_KD, SHOOTER_ENCODER_KD);

    public RunEndShooterCommand(ShooterSubsystem subsystem) {
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
     
    // If this command ends then turn the shooter off
    @Override
    public void end(boolean interrupted) {
        req_subsystem.spinShooterBV(0, 0);
    }
}
