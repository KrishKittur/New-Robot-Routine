/*----------------------------------------------------------------------------*/
package frc.robot;

import java.lang.Math;

// Create the "wrapper" constants class
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
}
/*----------------------------------------------------------------------------*/
