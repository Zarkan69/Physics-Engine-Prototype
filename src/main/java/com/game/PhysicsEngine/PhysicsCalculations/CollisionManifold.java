package com.game.PhysicsEngine.PhysicsCalculations;

import com.game.PhysicsEngine.PhysicsCalculations.Maths.Vector2D;
import com.game.PhysicsEngine.RigidBody2D.RigidBody2D;

public class CollisionManifold {
    private RigidBody2D bodyA, bodyB;
    private Vector2D normal;
    private double depth;
    private Vector2D FirstContactPoint;
    private Vector2D SecondContactPoint;
    private int contactCount;

    public CollisionManifold(RigidBody2D bodyA, RigidBody2D bodyB, Vector2D normal, double depth, Vector2D FirstContactPoint, Vector2D SecondContactPoint, int contactCount) {
        this.bodyA = bodyA;
        this.bodyB = bodyB;
        this.normal = normal;
        this.depth = depth;
        this.FirstContactPoint = FirstContactPoint;
        this.SecondContactPoint = SecondContactPoint;
        this.contactCount = contactCount;
    }

    public RigidBody2D getBodyA() {
        return bodyA;
    }

    public RigidBody2D getBodyB() {
        return bodyB;
    }

    public Vector2D getNormal() {
        return normal;
    }

    public double getDepth() {
        return depth;
    }

    public Vector2D getFirstContactPoint() {
        return FirstContactPoint;
    }

    public Vector2D getSecondContactPoint() {
        return SecondContactPoint;
    }

    public int getContactCount() {
        return contactCount;
    }
}
