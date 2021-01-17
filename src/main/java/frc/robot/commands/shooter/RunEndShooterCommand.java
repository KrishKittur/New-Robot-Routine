package frc.robot.commands.shooter;

import static frc.robot.Constants.Shooter.*;

import frc.robot.interpolation.InterpolationClass;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;

public class RunEndShooterCommand extends CommandBase {

    ShooterSubsystem req_subsystem;
    VisionSubsystem vision_subsystem;
    double setPoint;
    PIDController shooterPIDController = new PIDController(SHOOTER_ENCODER_KP, SHOOTER_ENCODER_KD, SHOOTER_ENCODER_KD);
    SimpleMotorFeedforward shooterFFController = new SimpleMotorFeedforward(SHOOTER_ENCODER_KS, SHOOTER_ENCODER_KV, SHOOTER_ENCODER_KA);

    public RunEndShooterCommand(ShooterSubsystem subsystem, VisionSubsystem vision_subsystem) {
        req_subsystem = subsystem;
        addRequirements(subsystem);
        shooterPIDController.setTolerance(100);

        this.vision_subsystem = vision_subsystem;
    }

    // In the execute method set the shooters motors according to the pid controllers calculations
    @Override
    public void execute() {
        double distance = vision_subsystem.getDistance();
        setPoint = InterpolationClass.getRPMFromDistance(distance);
        double outputPid = shooterPIDController.calculate(req_subsystem.readShooterEncoder(), setPoint);
        double outputFF = shooterFFController.calculate(setPoint);
        double outputVoltage = MathUtil.clamp(outputPid + outputFF, -12, 12);
        req_subsystem.spinShooterBV(outputVoltage, outputVoltage);
    }
     
    // If this command ends then turn the shooter off
    @Override
    public void end(boolean interrupted) {
        req_subsystem.spinShooterBV(0, 0);
    }
}
