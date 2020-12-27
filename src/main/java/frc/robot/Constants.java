/*----------------------------------------------------------------------------*/
package frc.robot;

// All constants go here
public final class Constants {

    // All constants having to do with controllers go here
    public static class Controller {
        // Channels
        public static int CONTROLLER_CHANNEL = 0;
    }

    // All constants having to do with the spindexer go here
    public static class Spindexer {
        // Motors, Ports, and Channels
        public static int SPINDEXER_MOTOR_ID = 20;
        public static int SPINDEXER_ENCODER_PORT = 2;
        public static double SPINDEXER_ENCODER_DPR = 360/1;

        // For Tuning
        public static double SPINDEXER_ENCODER_KP = 0.1;
        public static double SPINDEXER_ENCODER_KI = 0.0;
        public static double SPINDEXER_ENCODER_KD = 0.001;

        // Other important spindexer constants
        public static double SPINDEXER_OFFSET_POSITION = 49; 
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
        public static int SHOOTER_ENCODER_CHANNEL_A = 0;
        public static int SHOOTER_ENCODER_CHANNEL_B = 1;

        // For Tuning
        public static double SHOOTER_ENCODER_KP = 0.1;
        public static double SHOOTER_ENCODER_KI = 0;
        public static double SHOOTER_ENCODER_KD = 0.0001;
        public static double SHOOTER_ENCODER_KS = 0.2;
        public static double SHOOTER_ENCODER_KV = 0.025;
        public static double SHOOTER_ENCODER_KA = 0.0037;
    }
}
/*----------------------------------------------------------------------------*/
