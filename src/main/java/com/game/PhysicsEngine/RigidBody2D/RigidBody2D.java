package com.game.PhysicsEngine.RigidBody2D;

//import com.game.PhysicsEngine.Geometry.Polygon2D;
import com.game.PhysicsEngine.PhysicsCalculations.Maths.Vector2D;

public abstract class RigidBody2D {
    private double mass, invMass, coefOfRestitution, inertia, invInertia, angularVelocity;
    private Vector2D linearVelocity;
    private Vector2D force;
    private Vector2D oldPosition;
    //private Vector2D position;

    public RigidBody2D (double mass, double Restitution) {
        this.mass = mass;
        this.invMass = mass == 0 ? 0 : 1 / mass;
        this.coefOfRestitution = Restitution;
        this.linearVelocity = new Vector2D();
        this.force = new Vector2D();
        this.oldPosition = new Vector2D();
        //this.position = new Vector2D();
    }

    public double getMass() {
        return mass;
    }

    public double getInvMass() {
        return invMass;
    }

    public double getInertia() {
        return inertia;
    }

    public double getInvInertia() {
        return invInertia;
    }

    public double getRestitution() {
        return coefOfRestitution;
    }

    public Vector2D getOldPosition() {
        return oldPosition;
    }

    // public Vector2D getPosition() {
    //     return position;
    // }

    public Vector2D getLinearVelocity() {
        return linearVelocity;
    }

    public double getAngularVelocity() {
        return angularVelocity;
    }

    // public void moveTo(Vector2D amount) {
    //     position.setVect(amount);
    // }

    public Vector2D applyForce(double dt) {
        Vector2D acceleration = new Vector2D(force); // F = m * a (need to add * dt)
        acceleration.multiplyVector2DConst(invMass);
        linearVelocity.addVect(linearVelocity.multiplyVector2DConst(dt));
        force.setToZero();
        return linearVelocity.multiplyVector2DConst(dt);
    } 

    // public void move(double dt) {
    //     Vector2D acceleration = new Vector2D(force); // F = m * a (need to add * dt)
    //     acceleration.multiplyVector2DConst(invMass);
    //     linearVelocity.addVect(acceleration.multiplyVector2DConst(dt));
    //     position.addVect(linearVelocity.multiplyVector2DConst(dt));
    //     //rotateBox2D(angularVelocity * dt);
    //     force.setToZero();
    // } 
    
    public void addForce(Vector2D force) {
        this.force.addVect(force);
    }

    // public void setPosition(Vector2D position, Polygon2D polygon) {
    //     this.oldPosition.setVect(this.position);
    //     this.position.setVect(position);

    //     polygon.updateVertices(new Vector2D(position.getXProperty().get() - oldPosition.getXProperty().get(),
    //     position.getYProperty().get() - oldPosition.getYProperty().get()));
    // }

    public void setLinearVelocity(Vector2D velocity) {
        this.linearVelocity.setVect(velocity);
    }

    public void setAngularVelocity(double angularVelocity) {
        this.angularVelocity = angularVelocity;
    }
}