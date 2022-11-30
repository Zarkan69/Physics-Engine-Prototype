package com.game.PhysicsEngine.RigidBody2D;

import com.game.PhysicsEngine.Geometry.Circle2D;

public class RigidCircle2D extends RigidBody2D {
    private Circle2D circle;

    public RigidCircle2D(double radius, double x, double y, double mass, double restitution) {
        super(mass, restitution);
        this.circle = new Circle2D(radius, x, y);
    }

    public Circle2D getCircle() {
        return circle;
    }

    public void setCircle(Circle2D circle) {
        this.circle = circle;
    }
}