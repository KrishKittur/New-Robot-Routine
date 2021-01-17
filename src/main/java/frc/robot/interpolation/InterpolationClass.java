package frc.robot.interpolation;

import static java.util.Map.entry;
import java.util.Map;
import java.util.TreeMap;

// Class to get RPMS and Hood Angles
public final class InterpolationClass {

    // Create the treemap 
    private final static TreeMap<Double, InterpolatableValue> valueMap = new TreeMap<>(
        Map.ofEntries(
            entry(0.0, new InterpolatableValue(1000, 0.0)),
            entry(1.0, new InterpolatableValue(1250, 7.5)),
            entry(2.0, new InterpolatableValue(1500, 15.0)),
            entry(3.0, new InterpolatableValue(1750, 22.5)),
            entry(4.0, new InterpolatableValue(2000, 30.0)),
            entry(5.0, new InterpolatableValue(2250, 37.5)),
            entry(6.0, new InterpolatableValue(2500, 45.0)),
            entry(7.0, new InterpolatableValue(2750, 52.5)),
            entry(8.0, new InterpolatableValue(3000, 60.0))
        )
    );

    // Unaccesible constructor because this is a utility class
    private InterpolationClass() {
        throw new UnsupportedOperationException();
    }

    // Method to get rpm when given distance
    public static double getRPMFromDistance(double distance) {
        if (valueMap.ceilingKey(distance) == null) {
            return valueMap.get(10.0).getRPM();
        }
        if (valueMap.floorKey(distance) == null) {
            return valueMap.get(0.0).getRPM();
        }
        double floorKey = valueMap.floorKey(distance);
        double ceilingKey = valueMap.ceilingKey(distance);
        if (ceilingKey == floorKey) {
            return valueMap.get(distance).getRPM();
        }
        double slope = ((valueMap.get(ceilingKey).getRPM() - valueMap.get(floorKey).getRPM())/(ceilingKey - floorKey));
        double returnRPM = valueMap.get(floorKey).getRPM() + slope * (distance - floorKey);
        return returnRPM;
    }

    // Method to get hood angle when given distance
    public static double getHoodAngleFromDistance(double distance) {
        if (valueMap.ceilingKey(distance) == null) {
            return valueMap.get(10.0).getHoodAngle();
        }
        if (valueMap.floorKey(distance) == null) {
            return valueMap.get(0.0).getHoodAngle();
        }
        double floorKey = valueMap.floorKey(distance);
        double ceilingKey = valueMap.ceilingKey(distance);
        if (ceilingKey == floorKey) {
            return valueMap.get(distance).getHoodAngle();
        }
        double slope = ((valueMap.get(ceilingKey).getHoodAngle() - valueMap.get(floorKey).getHoodAngle())/(ceilingKey - floorKey));
        double returnRPM = valueMap.get(floorKey).getHoodAngle() + slope * (distance - floorKey);
        return returnRPM;

    }

}