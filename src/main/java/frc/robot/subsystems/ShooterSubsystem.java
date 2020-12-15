package frc.robot.subsystems;

import static frc.robot.Constants.Shooter.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ShooterSubsystem extends SubsystemBase {

   // Instantiate the hardware
   private final CANSparkMax shooterMotor1 = new CANSparkMax(SHOOTER_MOTOR_1_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
   private final CANSparkMax shooterMotor2 = new CANSparkMax(SHOOTER_MOTOR_2_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
   private final Encoder shooterEncoder = new Encoder(SHOOTER_ENCODER_CHANNEL_A, SHOOTER_ENCODER_CHANNEL_B);

   public ShooterSubsystem() {
       // Set the setters 
       shooterEncoder.setDistancePerPulse(Math.PI * 2/8192);
   }

   // Method to spin the shooter using percent
   public void spinShooterBP(double shooterPercent1, double shooterPercent2) {
        shooterMotor1.set(-1 * shooterPercent1);
        shooterMotor2.set(shooterPercent2);
   } 

   // Method to spin the shooter using voltages
   public void spinShooterBV(double shooterVoltage1, double shooterVoltage2) {
        shooterMotor1.setVoltage(shooterVoltage1);
        shooterMotor2.setVoltage(shooterVoltage2);
   }

   // Method to read the encoders values
   public double readShooterEncoder() {
        return shooterEncoder.getRate();
   }

}
