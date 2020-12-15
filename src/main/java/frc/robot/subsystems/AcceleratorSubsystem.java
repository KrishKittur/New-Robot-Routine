package frc.robot.subsystems;

import static frc.robot.Constants.Accelerator.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

public class AcceleratorSubsystem extends SubsystemBase {

    // Instantiate the hardware
    private final CANSparkMax acceleratorMotor = new CANSparkMax(ACCELERATOR_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);

    // Method to set the accelerator motor by percent
    public void setAcceleratorMotorBP(double percent) {
        acceleratorMotor.set(-1 * percent);
    }

    // Method to set the accelerator motor by voltage
    public void setAcceleratorMotorBV(double voltage) {
        acceleratorMotor.set(-1 * voltage);
    }

}
