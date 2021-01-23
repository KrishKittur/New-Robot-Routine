package frc.robot.subsystems;

import static frc.robot.Constants.Accelerator.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

public class AcceleratorSubsystem extends SubsystemBase {

    // Instantiate the hardware
    private final CANSparkMax acceleratorMotor = new CANSparkMax(ACCELERATOR_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANEncoder acceleratorEncoder = acceleratorMotor.getEncoder();

    // Method to set the accelerator motor by percent
    public void setAcceleratorMotorBP(double percent) {
        acceleratorMotor.set(-1 * percent);
    }

    // Method to set the accelerator motor by voltage
    public void setAcceleratorMotorBV(double voltage) {
        acceleratorMotor.setVoltage(-1 * voltage);
    }

    // Method to read the accelerator encoder
    public double readAcceleratorEncoder() {
        return -acceleratorEncoder.getVelocity();
    }

    // Method to get the current of the accelerator
    public double getAcceleratorCurrent() {
        return acceleratorMotor.getOutputCurrent();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Accelerator Speed", readAcceleratorEncoder());
    }

}
