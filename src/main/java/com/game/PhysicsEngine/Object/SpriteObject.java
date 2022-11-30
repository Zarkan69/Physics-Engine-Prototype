package com.game.PhysicsEngine.Object;

import com.game.PhysicsEngine.RigidBody2D.RigidPolygon2D;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class SpriteObject {
    private Polygon polygonSprite;
    private Circle circleSprite;
    private Entity entity;
    private Double[] points;

    public SpriteObject(Entity entity) {
        this.entity = entity;
        if (this.entity.getBodyType() == Entity.BodyType.POLYGON) {
            this.polygonSprite = new Polygon();
            RigidPolygon2D rp = (RigidPolygon2D) this.entity.getRigidBody();
            this.points = new Double[rp.getPolygon2D().getVertices().length];

            for (int i = 0; i < rp.getPolygon2D().getVertices().length - 1; i++) {
                points[i] = rp.getPolygon2D().getVertices()[i].getXProperty().getValue();
                points[i + 1] = rp.getPolygon2D().getVertices()[i].getYProperty().getValue();
            }
            this.polygonSprite.getPoints().addAll(points);

        } else {
            this.circleSprite = new Circle(); // TODO: Create a circle sprite
        }
    }

    public Polygon getPolygonSprite() {
        if (polygonSprite != null)
            return polygonSprite;
        else 
            return null;
    }

    public void setPolygonSprite(Polygon polygonSprite) {
        this.polygonSprite = polygonSprite;
    }

    public Circle getCircleSprite() {
        if (circleSprite != null)
            return circleSprite;
        else 
            return null;
    }

    public void setCircleSprite(Circle circleSprite) {
        this.circleSprite = circleSprite;
    }
}
