package frc.robot.interpolation;


// Class to pass as keys into the tree map
public class InterpolatableValue {

    // Declare the RPM and hood angle
    private final double RPM;
    private final double hoodAngle;

    // Instantiate the RPM and hood angle
    public InterpolatableValue(double RPM, double hoodAngle) {
        this.RPM = RPM;
        this.hoodAngle = hoodAngle;
    }

    // Getter for RPM
    public double getRPM() {
        return RPM;
    }

    // Getter for hood angle
    public double getHoodAngle() {
        return hoodAngle;
    }
    
} 