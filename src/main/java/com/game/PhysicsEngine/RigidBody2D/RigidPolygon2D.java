package com.game.PhysicsEngine.RigidBody2D;

import com.game.PhysicsEngine.Geometry.Polygon2D;
import com.game.PhysicsEngine.Geometry.Rectangle2D;
import com.game.PhysicsEngine.PhysicsCalculations.Maths.Vector2D;

public class RigidPolygon2D extends RigidBody2D {
    private Polygon2D polygon;
    
    public RigidPolygon2D(double minX, double minY, double width, double height, double mass, double restitution) {
        super(mass, restitution);
        this.polygon = new Rectangle2D(minX, minY, width, height);
        this.polygon.updateVertices(new Vector2D()); // first calulation of the centroid
    }

    public RigidPolygon2D(Vector2D[] vertices, double mass, double restitution) {
        super(mass, restitution);
        this.polygon = new Polygon2D(vertices);
        this.polygon.updateVertices(new Vector2D()); // first calulation of the centroid
    }

    public Polygon2D getPolygon2D() {
        return polygon;
    }

    public void setPolygon2D(Polygon2D Polygon2D) {
        this.polygon = Polygon2D;
    }

    /**
     * <p>Take the actual position of the object and add it the movement</p>
     * @param movement The movement to be applied to the object
     */
    public void moveRigidPolygon2D(Vector2D... movement) {
        this.polygon.updateVertices(applyForce(1));
        if (movement.length > 0) {
            for (Vector2D v : movement) {
                this.polygon.updateVertices(v);
            }
        }
    }
}
