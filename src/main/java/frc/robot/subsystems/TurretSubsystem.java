package frc.robot.subsystems;

import static frc.robot.Constants.Turret.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretSubsystem extends SubsystemBase {

    // Instantiate the hardware
    private final CANSparkMax turretMotor = new CANSparkMax(TURRET_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final DutyCycleEncoder turretEncoder = new DutyCycleEncoder(TURRET_ENCODER_ID);

    public TurretSubsystem() {
        // Set the setters
        turretMotor.setSmartCurrentLimit(25);
        turretEncoder.setDistancePerRotation(TURRET_ENCODER_DPR);
    }

    // Method to read the turret encoders values
    public double readTurretEncoder() {
        return turretEncoder.getDistance() - TURRET_ENCODER_OFFSET;
    }

    // Method to set the turret motors speed 
    public void setTurretMotor(double voltage) {
        turretMotor.setVoltage(voltage);
    }

    // Add turret's encoder readings to SmartDashboard
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Turret Angle", readTurretEncoder());
    }
}