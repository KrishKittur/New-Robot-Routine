package frc.robot.subsystems;

import static frc.robot.Constants.Spindexer.*;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

public class SpindexerSubsystem extends SubsystemBase {

    // Instantiate the hardware
    private final CANSparkMax spindexerMotor = new CANSparkMax(SPINDEXER_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final DutyCycleEncoder spindexerEncoder = new DutyCycleEncoder(SPINDEXER_ENCODER_PORT);

    public SpindexerSubsystem() {
        // Set the setters
        spindexerMotor.setSmartCurrentLimit(14);
        spindexerMotor.setSecondaryCurrentLimit(16);
        spindexerEncoder.setDistancePerRotation(SPINDEXER_ENCODER_DPR);
    }

    // Method to set the spindexer motor by percent
    public void setSpindexerMotorBP(double speed) {
        spindexerMotor.set(speed);
    }

    // Method to set the spindexer motor by voltage
    public void setSpindexerMotorBV(double voltage) {
        spindexerMotor.setVoltage(voltage);
    }

    // Method to get the encoders readings
    public double readSpindexerEncoder() {
        return spindexerEncoder.getDistance();
    }

}
