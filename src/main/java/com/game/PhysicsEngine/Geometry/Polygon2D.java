package com.game.PhysicsEngine.Geometry;

import com.game.PhysicsEngine.PhysicsCalculations.Maths.MathLib;
import com.game.PhysicsEngine.PhysicsCalculations.Maths.Vector2D;
import com.game.PhysicsEngine.PhysicsCalculations.Maths.VectorMath;

import javafx.beans.property.DoublePropertyBase;

public class Polygon2D {
    private Vector2D[] vertices;
    private Vector2D centroid;
    private DoublePropertyBase angle;
    
    public Polygon2D(Vector2D[] vertices) {
        this.vertices = vertices;
        this.angle = new DoublePropertyBase(0) {
            @Override
            public Object getBean() {
                return Polygon2D.this;
            }

            @Override
            public String getName() {
                return "angle";
            }
        };
        //calculateCentroid();
    }

    public Polygon2D(Vector2D[] vertices, double angle) {
        this.vertices = vertices;
        this.angle = new DoublePropertyBase(angle) {
            @Override
            public Object getBean() {
                return Polygon2D.this;
            }

            @Override
            public String getName() {
                return "angle";
            }
        };
        rotatePolygon2D(angle);
        calculateCentroid();
    }

    public Vector2D[] getVertices() {
        return vertices;
    }

    public int getVertexCount() {
        return vertices.length;
    }

    public Vector2D getVertex(int index) {
        return vertices[index];
    }

    public Vector2D getCentroid() {
        return centroid;
    }

    public DoublePropertyBase getAngleProperty() {
        return angle;
    }

    public void setVertex(int index, Vector2D vertex) {
        vertices[index] = vertex;
    }

    public void setVertices(Vector2D[] vertices) {
        this.vertices = vertices;
    }

    public void setAngleProperty(double angle) {
        resetAngle();
        rotatePolygon2D(angle);
    }

    public void rotatePolygon2D(double angle) {
        this.angle.set(this.angle.get() + angle);
        MathLib.convertDegreesToPositive(this.angle);

        for (int i = 0; i < this.vertices.length; i++) {
            this.vertices[i].rotateDegrees(this.centroid, angle);
        }
    }

    private void resetAngle() {
        while (this.getAngleProperty().get() < 0) {
            this.getAngleProperty().set(getAngleProperty().get() + 360);
        }
        rotatePolygon2D(-this.getAngleProperty().get());
    }

    private int calculateArea() {
        int sum = 0;

        for (int i = 0; i < this.vertices.length; i++) {
            sum += this.vertices[i].getXProperty().getValue() * this.vertices[(i + 1) % this.vertices.length].getYProperty().getValue();
            sum -= this.vertices[i].getYProperty().getValue() * this.vertices[(i + 1) % this.vertices.length].getXProperty().getValue();
        }
        return sum / 2;
    }

    private void calculateCentroid() {
        int cx = 0;
        int cy = 0;
        for (int i = 0; i < this.vertices.length - 1; i++) {
            cx += (this.vertices[i].getXProperty().get() + this.vertices[i + 1].getXProperty().get())
                    * (VectorMath.crossProduct(this.vertices[i], this.vertices[i + 1]));
            cy += (this.vertices[i].getYProperty().get() + this.vertices[i + 1].getYProperty().get())
                    * (VectorMath.crossProduct(this.vertices[i], this.vertices[i + 1]));
        }

        this.centroid = new Vector2D(cx / (6 * calculateArea()), cy / (6 * calculateArea()));
    }

    public void updateVertices(Vector2D movement) {
        for (int i = 0; i < this.vertices.length; i++) {
            this.vertices[i].addVect(movement);
        }
        calculateCentroid();
    }
}
