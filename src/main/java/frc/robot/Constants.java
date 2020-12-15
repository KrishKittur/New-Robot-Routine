/*----------------------------------------------------------------------------*/
package frc.robot;

import java.lang.Math;

// All constants go here
public final class Constants {

    // All constants having to do with the spindexer go here
    public static class Spindexer {
        // Motors, Ports, and Channels
        public static int SPINDEXER_MOTOR_ID = 20;
        public static int SPINDEXER_ENCODER_PORT = 0;
        public static double SPINDEXER_ENCODER_DPR = Math.PI * 2/1;

        // For Tuning
        public static int SPINDEXER_MOTOR_KP = 1;
        public static int SPINDEXER_MOTOR_KI = 0;
        public static int SPINDEXER_MOTOR_KD = 0;
    }

    // All constants having to do with the accelerator go here
    public static class Accelerator {
        // Motors, Ports, and Channels
        public static int ACCELERATOR_MOTOR_ID = 21;
    }

    // All constants having to do with the shooter go here
    public static class Shooter {
        // Motors, Ports, and Channels
        public static int SHOOTER_MOTOR_1_ID = 22;
        public static int SHOOTER_MOTOR_2_ID = 23;
        public static int SHOOTER_ENCODER_CHANNEL_A = 1;
        public static int SHOOTER_ENCODER_CHANNEL_B = 2;
    }
}
/*----------------------------------------------------------------------------*/
