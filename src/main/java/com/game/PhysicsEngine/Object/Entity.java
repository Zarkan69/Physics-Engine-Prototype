package com.game.PhysicsEngine.Object;

import java.util.ArrayList;

import com.game.PhysicsEngine.RigidBody2D.RigidBody2D;
import com.game.PhysicsEngine.RigidBody2D.RigidCircle2D;
import com.game.PhysicsEngine.RigidBody2D.RigidPolygon2D;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class Entity {
    private Polygon polygonSprite;
    private Circle circleSprite;
    private RigidBody2D rigidBody;
    private ArrayList<Entity> nearEntities;
    private BodyType bodyType;

    public enum BodyType {
        POLYGON, CIRCLE
    }

    public Entity(RigidPolygon2D rigidBodyPolygon) {
        this.rigidBody = rigidBodyPolygon;
        this.polygonSprite = new Polygon(); // TODO: Create a polygon sprite
        this.nearEntities = new ArrayList<Entity>();
        this.bodyType = BodyType.POLYGON;
    }

    public Entity(RigidCircle2D rigidBodyCircle) {
        this.rigidBody = rigidBodyCircle;
        this.circleSprite = new Circle(rigidBodyCircle.getCircle().getCenter().getXProperty().get(), 
                                    rigidBodyCircle.getCircle().getCenter().getYProperty().get(), 
                                    rigidBodyCircle.getCircle().getRadius());
        this.nearEntities = new ArrayList<Entity>();
        this.bodyType = BodyType.CIRCLE;
    }

    public ArrayList<Entity> getNearEntities() {
        return nearEntities;
    }

    public RigidBody2D getRigidBody() {
        return rigidBody;
    }

    public void setRigidBody(RigidBody2D rb) {
        this.rigidBody = rb;
    }

    public void setColor(Color color) {
        if (bodyType == BodyType.POLYGON) {
            polygonSprite.setFill(color);
        } else if (bodyType == BodyType.CIRCLE) {
            circleSprite.setFill(color);
        }
    }

    public BodyType getBodyType() {
        return bodyType;
    }
}