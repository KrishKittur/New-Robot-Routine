package frc.robot;

import static frc.robot.Constants.Controller.*;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.accelerator.RunEndAcceleratorCommand;
import frc.robot.commands.accelerator.SpinAcceleratorCommand;
import frc.robot.commands.accelerator.StartAcceleratorCommand;
import frc.robot.commands.shooter.RunEndShooterCommand;
import frc.robot.commands.shooter.StartShooterCommand;
import frc.robot.commands.spindexer.SpinSpindexerCommand;
import frc.robot.commands.spindexer.TurnSpindexerCommand;
import frc.robot.subsystems.AcceleratorSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SpindexerSubsystem;

public class RobotContainer {

  private final ShooterSubsystem shooter_subsystem = new ShooterSubsystem();
  public final SpindexerSubsystem spindexer_subsystem = new SpindexerSubsystem();
  private final AcceleratorSubsystem accelerator_subsystem = new AcceleratorSubsystem();
  private final XboxController controller = new XboxController(CONTROLLER_CHANNEL);


  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  
  private void configureButtonBindings() {
    // If the "A" button is pressed then run the routine
    new JoystickButton(controller, Button.kA.value).whenPressed(

      new ParallelDeadlineGroup(
          new TurnSpindexerCommand(spindexer_subsystem),
          new SpinAcceleratorCommand(accelerator_subsystem, -0.2)
      )

    );
  }


  public void getAutonomousCommand() {
    // Add auto command here
  }

}
