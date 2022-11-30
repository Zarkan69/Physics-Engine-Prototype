package com.game.PhysicsEngine.PhysicsCalculations;

import java.util.ArrayList;

import com.game.Main;
import com.game.PhysicsEngine.Object.Entity;
import com.game.PhysicsEngine.PhysicsCalculations.Maths.Vector2D;
import com.game.PhysicsEngine.PhysicsCalculations.Maths.VectorMath;
import com.game.PhysicsEngine.RigidBody2D.RigidBody2D;

public class Physics2D {

    public static ArrayList<Entity> entities = new ArrayList<Entity>();
    public static ArrayList<CollisionManifold> collisions = new ArrayList<CollisionManifold>();
    public static int iterations = 50;

    public static void update(double dt) {
        playerMovement();
        for (int i = 0; i < iterations; i++) {
            for (Entity E : entities) {
                //E.getRigidBody().updateVertices();
            }
            //Interactions.findEntities();
            for (Entity E : entities) {
                //E.getRigidBody().move(dt / iterations);
                //E.getRigidBody().rotateBox2D(0.1);
            }
        }
    }

    public static void resolveCollision(CollisionManifold collision) {
        RigidBody2D rbA = collision.getBodyA();
        RigidBody2D rbB = collision.getBodyB();
        Vector2D normal = collision.getNormal();
        double depth = collision.getDepth();

        if (!(rbA.getInvMass() == 0) && !(rbB.getInvMass() == 0)) {
            rbA.addForce(normal.inverseVect().multiplyVector2DConst(depth / 2));
            rbB.addForce(normal.multiplyVector2DConst(depth / 2));
        } else if (rbA.getInvMass() == 0)
            rbB.addForce(normal.multiplyVector2DConst(depth / 2));
        else if (rbB.getInvMass() == 0)
            rbA.addForce(normal.inverseVect().multiplyVector2DConst(depth / 2));
        else if (rbA.getInvMass() == 0 && rbB.getInvMass() == 0)
            return;

        Vector2D relativeVelocity = new Vector2D(rbB.getLinearVelocity().subtractVector2DVect(rbA.getLinearVelocity()));

        if (VectorMath.dotProduct(relativeVelocity, normal) > 0)
            return;

        double e = Math.min(rbA.getRestitution(), rbB.getRestitution());
        double j = -(1 + e) * VectorMath.dotProduct(relativeVelocity, normal);
        j /= rbA.getInvMass() + rbB.getInvMass();

        Vector2D impulse = normal.multiplyVector2DConst(j);

        rbA.getLinearVelocity().subtractVect(impulse.multiplyVector2DConst(rbA.getInvMass()));
        rbB.getLinearVelocity().addVect(impulse.multiplyVector2DConst(rbB.getInvMass()));
        
        positionCorrection(rbA, rbB, depth, normal);
    }

    public static void positionCorrection(RigidBody2D rbA, RigidBody2D rbB, double depth, Vector2D normal) {
        double percent = 0.9; // usually 20% to 80%
        double slop = 0.02; // usually 0.01 to 0.1
        Vector2D correction = new Vector2D(normal.multiplyVector2DConst(percent * Math.max(depth - slop, 0) / (rbA.getInvMass() + rbB.getInvMass())));
        rbA.getPosition().subtractVect(correction.multiplyVector2DConst(rbA.getInvMass()));
        rbB.getPosition().addVect(correction.multiplyVector2DConst(rbB.getInvMass()));
    }

    public static void playerMovement() {
        double forceMagnitude = 5;
        double dx = 0;
        double dy = 0;
        if (Main.left) {
            dx -= 1;
        }
        if (Main.right) {
            dx += 1;
        }
        if (Main.up) {
            dy -= 5;
        }
        if (Main.down) {
            dy += 1;
        }
        if (Main.droite) {
            //Main.eA.getRigidBody().rotateBox2D(Main.eA.getRigidBody().getAngleProperty().get() + 0.002);
        }
        if (Main.gauche) {
            //Main.eA.getRigidBody().rotateBox2D(Main.eA.getRigidBody().getAngleProperty().get() - 0.002);
        }

        if (dx != 0 || dy != 0) {
            Vector2D forceDirection = new Vector2D(dx, dy);
            forceDirection.normalize();
            Vector2D force = new Vector2D(forceDirection.multiplyVector2DConst(forceMagnitude));
            //Main.eA.getRigidBody().addForce(force);
        }
    }

    public static double pythagoreanSolve(double a, double b) {
        return (double) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }
}