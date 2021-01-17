package frc.robot.subsystems;

import static frc.robot.Constants.Hood.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class HoodSubsystem extends SubsystemBase {

    // Instantiate the hardware
    private final CANSparkMax hoodMotor = new CANSparkMax(HOOD_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final DutyCycleEncoder hoodEncoder = new DutyCycleEncoder(HOOD_ENCODER_ID);

    public HoodSubsystem() {
        // Set the setters
        hoodEncoder.setDistancePerRotation(HOOD_ENCODER_DPR);
        hoodMotor.setSmartCurrentLimit(5);
    }

    // Method to read the hood encoders values
    public double readHoodEncoder() {
        return hoodEncoder.getDistance() - HOOD_ENCODER_OFFSET;
    }

    // Method to move the hood
    public void setHoodMotor(double voltage) {
        if ((readHoodEncoder() >= 60.0 && voltage < 0.0) || (readHoodEncoder() <= 2.0 && voltage > 0.0) || (2.0 < readHoodEncoder() && readHoodEncoder() < 60.0)) {
            hoodMotor.setVoltage(-voltage);
        }
    }

    // In the periodic method, print out the value of the hood encoder
    @Override
    public void periodic() {
        System.out.println(readHoodEncoder());
    }


    

}
