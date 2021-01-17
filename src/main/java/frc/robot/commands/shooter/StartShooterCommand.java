/* 
* DO NOT RUN THIS COMMAND BY ITSELF
* DOING SO WILL RESULT IN THE SHOOTER NOT STOPPING
* YOU MUST RUN THE RUNENDSHOOTERCOMMAND AFTER THIS COMMAND
*/

package frc.robot.commands.shooter;

import static frc.robot.Constants.Shooter.*;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;

public class StartShooterCommand extends CommandBase {
    
    ShooterSubsystem req_subsystem;
    double setPoint;
    PIDController shooterPIDController = new PIDController(SHOOTER_ENCODER_KP, SHOOTER_ENCODER_KD, SHOOTER_ENCODER_KD);
    SimpleMotorFeedforward shooterFFController = new SimpleMotorFeedforward(SHOOTER_ENCODER_KS, SHOOTER_ENCODER_KV, SHOOTER_ENCODER_KA);

    public StartShooterCommand(ShooterSubsystem subsystem) {
        req_subsystem = subsystem;
        addRequirements(subsystem);
        shooterPIDController.setTolerance(100);
    }

    // In the execute method set the shooters motors according to the pid controllers calculations
    @Override
    public void execute() {
        setPoint = SmartDashboard.getNumber("Shooter-Setpoint", 4000);
        double outputPid = shooterPIDController.calculate(req_subsystem.readShooterEncoder(), setPoint);
        double outputFF = shooterFFController.calculate(setPoint);
        double outputVoltage = MathUtil.clamp(outputPid + outputFF, -12, 12);
        req_subsystem.spinShooterBV(outputVoltage, outputVoltage);
    }
     
    // If the shooter has reached its setpoint then set isFinished to true
    @Override
    public boolean isFinished() {
        return shooterPIDController.atSetpoint();
    }

}
