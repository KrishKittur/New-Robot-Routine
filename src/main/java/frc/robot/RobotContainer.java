package frc.robot;

import static frc.robot.Constants.Controller.*;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.accelerator.RunEndAcceleratorCommand;
import frc.robot.commands.accelerator.SpinAcceleratorCommand;
import frc.robot.commands.accelerator.StartAcceleratorCommand;
import frc.robot.commands.hood.HoodToVisionAngleCommand;
import frc.robot.commands.shooter.RunEndShooterCommand;
import frc.robot.commands.shooter.StartShooterCommand;
import frc.robot.commands.spindexer.SpinSpindexerCommand;
import frc.robot.commands.spindexer.TurnSpindexerCommand;
import frc.robot.commands.multisubs.RunEndAcceleratorShooterCommand;
import frc.robot.commands.multisubs.StartAcceleratorShooterCommand;
import frc.robot.commands.turret.TurnToAngleVision;
import frc.robot.subsystems.AcceleratorSubsystem;
import frc.robot.subsystems.HoodSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SpindexerSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class RobotContainer {

  // Instantiate the subsystems and controllers
  private final ShooterSubsystem shooter_subsystem = new ShooterSubsystem();
  public final SpindexerSubsystem spindexer_subsystem = new SpindexerSubsystem();
  private final AcceleratorSubsystem accelerator_subsystem = new AcceleratorSubsystem();
  public final HoodSubsystem hood_subsystem = new HoodSubsystem();
  private final VisionSubsystem vision_subsystem = new VisionSubsystem();
  private final TurretSubsystem turret_subsystem = new TurretSubsystem();
  private final XboxController controller = new XboxController(CONTROLLER_CHANNEL);


  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    // If the "A" button is pressed then run the routine
    new JoystickButton(controller, Button.kA.value).whenPressed(
      new ParallelDeadlineGroup(
        new SequentialCommandGroup(
          new ParallelDeadlineGroup(
              new TurnSpindexerCommand(spindexer_subsystem),
              new SpinAcceleratorCommand(accelerator_subsystem, -0.1),
              new StartShooterCommand(shooter_subsystem, vision_subsystem)
          ),
          new StartAcceleratorShooterCommand(
            accelerator_subsystem, shooter_subsystem, vision_subsystem
          ),
          new ParallelDeadlineGroup(
            new SequentialCommandGroup(
              new SpinSpindexerCommand(spindexer_subsystem),
              new WaitCommand(0.2)
            ),
            new RunEndAcceleratorShooterCommand(
              accelerator_subsystem, shooter_subsystem, vision_subsystem
            )
          )
        ),
        new ParallelCommandGroup(
          new TurnToAngleVision(turret_subsystem, vision_subsystem),
          new HoodToVisionAngleCommand(hood_subsystem, vision_subsystem)
        )
      )
    );

  }


  public void getAutonomousCommand() {
    // Add auto command here
  }

}
