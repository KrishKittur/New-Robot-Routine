package frc.robot;

import static frc.robot.Constants.Controller.*;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.accelerator.*;
import frc.robot.commands.hood.*;
import frc.robot.commands.shooter.*;
import frc.robot.commands.spindexer.*;
import frc.robot.commands.turret.*;
import frc.robot.subsystems.*;
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
              new SpinAcceleratorCommand(accelerator_subsystem, -0.1)
          ),
          new ParallelDeadlineGroup(
            new StartShooterCommand(shooter_subsystem), 
            new StartAcceleratorCommand(accelerator_subsystem, 1)
          ),
          new ParallelDeadlineGroup(
            new SequentialCommandGroup(
              new SpinSpindexerCommand(spindexer_subsystem),
              new WaitCommand(0.2)
            ),
            new RunEndShooterCommand(shooter_subsystem),
            new RunEndAcceleratorCommand(accelerator_subsystem)
          )
        ),
        new ParallelCommandGroup(
          new TurnToAngleVision(turret_subsystem, vision_subsystem),
          new HoodToAngle(hood_subsystem)
        )
      )
    );

  }


  public void getAutonomousCommand() {
    // Add auto command here
  }

}
