package com.game.PhysicsEngine.PhysicsCalculations.Maths;

import javafx.beans.property.DoublePropertyBase;

public class MathLib {
    public static DoublePropertyBase convertDegreesToPositive(DoublePropertyBase angle) {
        while (angle.get() < 0.0) {
            angle.set(angle.get() + 360);
        }
    
        while (angle.get() >= 360.0) {
            angle.set(angle.get() - 360);
        }

        return angle;
    }
}
