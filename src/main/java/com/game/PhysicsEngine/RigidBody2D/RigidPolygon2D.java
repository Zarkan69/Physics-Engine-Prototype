package com.game.PhysicsEngine.RigidBody2D;

import com.game.PhysicsEngine.Geometry.Polygon2D;
import com.game.PhysicsEngine.Geometry.Rectangle2D;
import com.game.PhysicsEngine.PhysicsCalculations.Maths.Vector2D;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class RigidPolygon2D extends RigidBody2D {
    private Rectangle2D polygon;
    
    public RigidPolygon2D(double minX, double minY, double width, double height, double mass, double restitution) {
        super(mass, restitution);
        this.polygon = new Rectangle2D(minX, minY, width, height);
        this.polygon.updateVertices(new Vector2D());
        setPosition(this.polygon.getCentroid(), polygon);

        getPosition().getXProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    getPolygon2D().setMinXProperty(getPosition().getXProperty().get() - width * 0.5);
            }
        });
        getPosition().getYProperty().addListener((new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    getPolygon2D().setMinYProperty(getPosition().getYProperty().get() - height * 0.5);
			}
        }));
    }

    public RigidPolygon2D(Vector2D[] vertices, double mass, double restitution) {
        super(mass, restitution);
        // TODO: Implement listener for polygons
    }

    public Polygon2D getPolygon2D() {
        return polygon;
    }

    public void setPolygon2D(Rectangle2D Polygon2D) {
        this.polygon = Polygon2D;
    }

    // public void updateVertices() {
    //     Vector2D[] polygonVertices = this.polygon.getVertices();
    //     Vector2D translatedPosition = new Vector2D(getPosition().getXProperty().get() - getOldPosition().getXProperty().get(), 
    //             getPosition().getYProperty().get() - getOldPosition().getYProperty().get());

    //     for (Vector2D vertice : polygonVertices) {
    //         vertice.addVector2DVect(translatedPosition);
    //     }
    // }
}
