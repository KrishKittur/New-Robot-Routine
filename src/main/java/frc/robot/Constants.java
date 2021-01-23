/*----------------------------------------------------------------------------*/
package frc.robot;

import edu.wpi.first.wpilibj.util.Units;

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

        // For Tuning
        public static double SPINDEXER_ENCODER_KP = 0.1;
        public static double SPINDEXER_ENCODER_KI = 0.0;
        public static double SPINDEXER_ENCODER_KD = 0.001;

        // Other important spindexer constants
        public static double SPINDEXER_OFFSET_POSITION = 10;
        public static double SPINDEXER_ENCODER_DPR = 360/1; 
    }

    // All constants having to do with the accelerator go here
    public static class Accelerator {
        // Motors, Ports, and Channels
        public static int ACCELERATOR_MOTOR_ID = 21;

        // For Tuning
        public static double ACCELERATOR_ENCODER_KP = 0.1;
        public static double ACCELERATOR_ENCODER_KI = 0.0;
        public static double ACCELERATOR_ENCODER_KD = 0.0;
        public static double ACCELERATOR_ENCODER_KS = 0.3;
        public static double ACCELERATOR_ENCODER_KV = 0.00211416;
        public static double ACCELERATOR_ENCODER_KA = 0.0;
        public static double ACCELERATOR_SETPOINT = 4500;
    }

    // All constants having to do with the shooter go here
    public static class Shooter {
        // Motors, Ports, and Channels
        public static int SHOOTER_MOTOR_1_ID = 22;
        public static int SHOOTER_MOTOR_2_ID = 23;
        public static int SHOOTER_ENCODER_CHANNEL_A = 0;
        public static int SHOOTER_ENCODER_CHANNEL_B = 1;

        // For Tuning
        public static double SHOOTER_ENCODER_KP = 0.01;
        public static double SHOOTER_ENCODER_KI = 0.0;
        public static double SHOOTER_ENCODER_KD = 0.0;
        public static double SHOOTER_ENCODER_KS = 0.5;
        public static double SHOOTER_ENCODER_KV = 0.00246865202;
        public static double SHOOTER_ENCODER_KA = 0.0;

        // Other important shooter constants
        public static double SHOOTER_ENCODER_DPR = 1.0/2048.0;
    }

    // All constants having to do with the hood go here
    public static class Hood {
        // Motors, Ports, and Channels
        public static int HOOD_MOTOR_ID = 24;
        public static int HOOD_ENCODER_ID = 3;

        // For tuning
        public static double HOOD_ENCODER_KP = 1.0;
        public static double HOOD_ENCODER_KI = 0.0;
        public static double HOOD_ENCODER_KD = 0.0;

        // Other important hood constants
        public static double HOOD_ENCODER_DPR = 24.0/1.0;
        public static double HOOD_CURRENT_THRESHOLD = 3.0;

    }

    // All constants having to do with the turret go here
    public static class Turret {
        // Motors, Ports, and Channels
        public static int TURRET_MOTOR_ID = 25;
        public static int TURRET_ENCODER_ID = 4;

        // For tuning
        public static double TURRET_ENCODER_KP = 0.025;
        public static double TURRET_ENCODER_KI = 0.0;
        public static double TURRET_ENCODER_KD = 0.001;

        // Other important turret constants
        public static double TURRET_ENCODER_DPR = 360.0/1.0;
        public static double TURRET_ENCODER_OFFSET = 131.0;

    }

    // All constants having to do with vision go here
    public static class Vision {
        // Motors, Ports, and Channels
        public static double CAMERA_HEIGHT = 1.2065;
        public static double TARGET_HEIGHT = 2.4892;
        public static double CAMERA_TILT = Units.degreesToRadians(23.0);
    }
}
/*----------------------------------------------------------------------------*/
