package com.game.PhysicsEngine.Geometry;

import com.game.PhysicsEngine.PhysicsCalculations.Maths.Vector2D;

public class Circle2D {
   private double radius;
   private Vector2D center;

    public Circle2D(double radius, double x, double y) {
         this.radius = radius;
         this.center = new Vector2D(x, y);
    }

    public double getRadius() {
         return radius;
    }

    public void setRadius(double radius) {
         this.radius = radius;
    }

    public Vector2D getCenter() {
         return center;
    }

}
