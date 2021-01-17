package frc.robot.subsystems;

import static frc.robot.Constants.Shooter.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ShooterSubsystem extends SubsystemBase {

   // Instantiate the hardware
   private final WPI_TalonFX shooterMotor1 = new WPI_TalonFX(SHOOTER_MOTOR_1_ID);
   private final WPI_TalonFX shooterMotor2 = new WPI_TalonFX(SHOOTER_MOTOR_2_ID);
   private final Encoder shooterEncoder = new Encoder(SHOOTER_ENCODER_CHANNEL_B, SHOOTER_ENCODER_CHANNEL_A);

   public ShooterSubsystem() {
       // Set the setters 
       shooterEncoder.setDistancePerPulse(SHOOTER_ENCODER_DPR);
       shooterEncoder.setSamplesToAverage(100);
   }

   // Method to spin the shooter using percent
   public void spinShooterBP(double shooterPercent1, double shooterPercent2) {
        shooterMotor1.set(shooterPercent1);
        shooterMotor2.set(-1 * shooterPercent2);
   } 

   // Method to spin the shooter using voltages
   public void spinShooterBV(double shooterVoltage1, double shooterVoltage2) {
        shooterMotor1.setVoltage(shooterVoltage1);
        shooterMotor2.setVoltage(-1 * shooterVoltage2);
   }

   // Method to read the encoders values
   public double readShooterEncoder() {
        return shooterEncoder.getRate();
   }
}
