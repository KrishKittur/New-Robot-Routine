package frc.robot.commands.multisubs;

import static frc.robot.Constants.Shooter.*;

import java.io.FileWriter;
import java.io.IOException;

import static frc.robot.Constants.Accelerator.*;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.interpolation.InterpolationClass;
import frc.robot.subsystems.AcceleratorSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class StartAcceleratorShooterCommand extends CommandBase {

    // Insantiate the global variables for this command
    AcceleratorSubsystem acceleratorSubsystem;
    ShooterSubsystem shooterSubsystem;
    VisionSubsystem visionSubsystem;
    PIDController shooterPID = new PIDController(SHOOTER_ENCODER_KP, SHOOTER_ENCODER_KI, SHOOTER_ENCODER_KD);
    SimpleMotorFeedforward shooterFF = new SimpleMotorFeedforward(SHOOTER_ENCODER_KS, SHOOTER_ENCODER_KV, SHOOTER_ENCODER_KA);
    PIDController acceleratorPID = new PIDController(ACCELERATOR_ENCODER_KP, ACCELERATOR_ENCODER_KI, ACCELERATOR_ENCODER_KD);
    SimpleMotorFeedforward acceleratorFF = new SimpleMotorFeedforward(ACCELERATOR_ENCODER_KS, ACCELERATOR_ENCODER_KV, ACCELERATOR_ENCODER_KA);
    double shooterSetpoint;

    public StartAcceleratorShooterCommand(AcceleratorSubsystem acceleratorSubsystem, ShooterSubsystem shooterSubsystem, VisionSubsystem visionSubsystem) {
        // Add requuirements and set the tolerance for our PID Controller
        this.acceleratorSubsystem = acceleratorSubsystem;
        this.shooterSubsystem = shooterSubsystem;
        this.visionSubsystem = visionSubsystem;
        addRequirements(acceleratorSubsystem, shooterSubsystem);
        shooterPID.setTolerance(100);
        acceleratorPID.setTolerance(100);
    }

    // In the execute method set the motors based on the controllers outputs
    @Override
    public void execute() {
        double distance = visionSubsystem.getDistance();
        shooterSetpoint = InterpolationClass.getRPMFromDistance(distance);
        double shooterFFOutput = shooterFF.calculate(shooterSetpoint);
        double shooterPIDOutput = shooterPID.calculate(shooterSubsystem.readShooterEncoder(), shooterSetpoint);
        double shooterOutput = MathUtil.clamp(shooterFFOutput + shooterPIDOutput, -12, 12);
        shooterSubsystem.spinShooterBV(shooterOutput, shooterOutput);

        double acceleratorFFOutput = acceleratorFF.calculate(ACCELERATOR_SETPOINT);
        double acceleratorPIDOutput = acceleratorPID.calculate(acceleratorSubsystem.readAcceleratorEncoder(), ACCELERATOR_SETPOINT);
        double acceleratorOutput = MathUtil.clamp(acceleratorFFOutput + acceleratorPIDOutput, -12, 12);
        acceleratorSubsystem.setAcceleratorMotorBV(acceleratorOutput);
        try {
            FileWriter shooterWriter = new FileWriter(Filesystem.getOperatingDirectory() + "/shooteracceleratordata.csv", true);
            shooterWriter.write(shooterSubsystem.readShooterEncoder() + ", " + shooterSetpoint + ", " + acceleratorSubsystem.readAcceleratorEncoder() + ", " + ACCELERATOR_SETPOINT + ", " + acceleratorSubsystem.getAcceleratorCurrent() + ", " + RobotController.getFPGATime() * 1.0/1000000.0 + "\n");
            shooterWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // If the shooter and accelerator have reached their setpoint then end the command
    @Override
    public boolean isFinished() {
        return shooterPID.atSetpoint() && acceleratorPID.atSetpoint();
    }

}