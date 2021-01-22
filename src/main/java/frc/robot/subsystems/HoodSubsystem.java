package frc.robot.subsystems;

import static frc.robot.Constants.Hood.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HoodSubsystem extends SubsystemBase {
    
    // Insantiate the hardware
    CANSparkMax hoodMotor = new CANSparkMax(HOOD_MOTOR_ID, MotorType.kBrushless);
    DutyCycleEncoder hoodEncoder = new DutyCycleEncoder(HOOD_ENCODER_ID);
    
    // In the constructor set the setters
    public HoodSubsystem() {
        hoodMotor.setSmartCurrentLimit(13);
        hoodMotor.setSecondaryCurrentLimit(15);
        hoodEncoder.setDistancePerRotation(HOOD_ENCODER_DPR);
    }

    // Method to get the hoods distance
    public double getHoodDistance() {
        return hoodEncoder.getDistance() - 1;
    }

    // Method to reset the encoder
    public void resetHoodEncoder() {
        hoodEncoder.reset();
    }

    // Method to return the current of the motor
    public double getCurrent() {
        return hoodMotor.getOutputCurrent();
    }
    
    // Method to set the hood motor by voltage
    public void setHoodMotor(double voltage) {
        hoodMotor.setVoltage(-voltage);
    }

    // Method to get the hood velocity
    public double getHoodVelocity() {
        return hoodMotor.getEncoder().getVelocity() * 1.0/2048.0;
    }

    // In the periodic method show the current shooter speed
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Hood Distance", getHoodDistance());
    }

}