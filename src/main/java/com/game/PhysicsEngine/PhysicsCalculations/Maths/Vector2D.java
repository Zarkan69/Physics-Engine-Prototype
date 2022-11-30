package com.game.PhysicsEngine.PhysicsCalculations.Maths;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
/**
     * <p> The 
     * {@code Vector2D} class is a simple class that represents a 2D vector.
     * It can be used to represent coordinates, velocities, forces, etc.
     * It use DoubleProperty to make it easier to bind to JavaFX properties.
     * </p>
     * @param x double
     * @param y double
     * @author Mathis
     */
public class Vector2D {
    private DoubleProperty x;
    private DoubleProperty y;

    public Vector2D(Vector2D v) {
        this.x = new DoublePropertyBase(v.getXProperty().getValue()) {
            @Override
            public Object getBean() {
                return Vector2D.this;
            }

            @Override
            public String getName() {
                return "x";
            }
        };
        this.y = new DoublePropertyBase(v.getYProperty().getValue()) {
            @Override
            public Object getBean() {
                return Vector2D.this;
            }

            @Override
            public String getName() {
                return "y";
            }
        };
    }

    public Vector2D(double x, double y) {
        this.x = new DoublePropertyBase(x) {
            @Override
            public Object getBean() {
                return Vector2D.this;
            }

            @Override
            public String getName() {
                return "x";
            }
        };
        this.y = new DoublePropertyBase(y) {
            @Override
            public Object getBean() {
                return Vector2D.this;
            }

            @Override
            public String getName() {
                return "y";
            }
        };
    }

    public Vector2D() {
        this.x = new DoublePropertyBase(0) {
            @Override
            public Object getBean() {
                return Vector2D.this;
            }

            @Override
            public String getName() {
                return "x";
            }
        };
        this.y = new DoublePropertyBase(0) {
            @Override
            public Object getBean() {
                return Vector2D.this;
            }

            @Override
            public String getName() {
                return "y";
            }
        };
    }


    public DoubleProperty getXProperty() {
        return x;
    }

    public DoubleProperty getYProperty() {
        return y;
    }
    /**
    * <p>
    * Return a new Vector2D which is the result of the sum of the Vector2D of the current object and the Vector2D of the parameter.
    * </p>
    * @param v1 the new Vector2D which will be added to the current Vector2D
    * @return new Vector2D;
    */
    public Vector2D addVector2DVect(Vector2D v1, Vector2D... vn) {
        double x = v1.getXProperty().getValue();
        double y = v1.getYProperty().getValue();
        
        for (Vector2D v : vn) {
            x += v.getXProperty().getValue();
            y += v.getYProperty().getValue();
        }
        return new Vector2D(x, y);
    }

    public void addVect(Vector2D... vn) {
        for (Vector2D v : vn) {
            this.x.setValue(this.x.getValue() + v.getXProperty().getValue());
            this.y.setValue(this.y.getValue() + v.getYProperty().getValue());
        }
    }

    public void addConst(double... dn) {
        for (double d : dn) {
            this.x.setValue(this.x.getValue() + d);
            this.y.setValue(this.y.getValue() + d);
        }
    }

    public Vector2D subtractConstVect(double... dn) {
        double x = this.x.getValue();
        double y = this.y.getValue();
        for (double d : dn) {
            x -= d;
            y -= d;
        }
        return new Vector2D(x, y);
    }

    public Vector2D subtractVector2DVect(Vector2D... vn) {
        double x = this.x.getValue();
        double y = this.y.getValue();
        
        for (Vector2D v : vn) {
            x -= v.getXProperty().getValue();
            y -= v.getYProperty().getValue();
        }
        return new Vector2D(x, y);
    }

    public Vector2D subtractVector2DConst(double... dn) {
        double x = this.x.getValue();
        double y = this.y.getValue();
        
        for (double d : dn) {
            x -= d;
            y -= d;
        }
        return new Vector2D(x, y);
    }

    public void subtractVect(Vector2D... vn) {
        for (Vector2D v : vn) {
            this.x.setValue(this.x.getValue() - v.getXProperty().getValue());
            this.y.setValue(this.y.getValue() - v.getYProperty().getValue());
        }
    }
    
    public void inverse() {
        this.x.setValue(-this.x.getValue());
        this.y.setValue(-this.y.getValue());
    }

    public Vector2D inverseVect() {
        return new Vector2D(-this.getXProperty().getValue(), -this.getYProperty().getValue());
    }

    public Vector2D normalizeVector2D(Vector2D v) {
        double length = VectorMath.vecLength(v);
        return new Vector2D(v.getXProperty().getValue() / length, v.getYProperty().getValue() / length);
    }

    public void normalize() {
        double length = Math.sqrt(Math.pow(this.getXProperty().getValue(), 2) + Math.pow(this.getYProperty().getValue(), 2));
        this.x.setValue(this.x.getValue() / length);
        this.y.setValue(this.y.getValue() / length);
    }

    public Vector2D getNormalVector(Vector2D v1, Vector2D v2) {
        Vector2D vector = addVector2DVect(v1, v2);
        Vector2D normalizedVector = normalizeVector2D(vector);
        return new Vector2D(-normalizedVector.getYProperty().getValue(), normalizedVector.getXProperty().getValue());
    }

    public void multiplyConst(double... dn) {
        for (double d : dn) {
            this.x.setValue(this.x.getValue() * d);
            this.y.setValue(this.y.getValue() * d);
        }
    }

    public Vector2D multiplyVector2DConst(double... dn) {
        double x = this.x.getValue();
        double y = this.y.getValue();
        
        for (double d : dn) {
            x *= d;
            y *= d;
        }
        return new Vector2D(x, y);
    }

    public Vector2D multiplyVector2DVect(Vector2D... vn) {
        double x = this.x.getValue();
        double y = this.y.getValue();
        
        for (Vector2D v : vn) {
            x *= v.getXProperty().getValue();
            y *= v.getYProperty().getValue();
        }
        return new Vector2D(x, y);
    }

    public Vector2D divide(double... dn) {
        double x = this.x.getValue();
        double y = this.y.getValue();
        
        for (double d : dn) {
            x /= d;
            y /= d;
        }
        return new Vector2D(x, y);
    }

    public Vector2D rotate(Vector2D pivot, double radians) {
        double x2 = pivot.getXProperty().get() + (x.get() - pivot.getXProperty().get()) * Math.cos(radians) - (y.get() - pivot.getYProperty().get()) * Math.sin(radians);
        double y2 = pivot.getYProperty().get() + (x.get() - pivot.getXProperty().get()) * Math.sin(radians) + (y.get() - pivot.getYProperty().get()) * Math.cos(radians);
        return new Vector2D(x2, y2);
    }

    public Vector2D rotateDegrees(Vector2D pivot, double angle) {
        return rotate(pivot, Math.toRadians(angle));
    }

    /**
     * <p>
     * Set the value of the current Vector2D to the value of the Vector2D passed in parameter.
     * </p>
     * @param v1 Vector2D to be set to the current Vector2D
     * @return void
     */
    public void setVector2D(Vector2D v1) {
        this.x.setValue(v1.getXProperty().getValue());
        this.y.setValue(v1.getYProperty().getValue());
    }

    public void setValues(double x, double y) {
        this.x.setValue(x);
        this.y.setValue(y);
    }

    public void setVect(Vector2D v1) {
        this.x.setValue(v1.getXProperty().getValue());
        this.y.setValue(v1.getYProperty().getValue());
    }

    public void setToZero() {
        this.x.setValue(0);
        this.y.setValue(0);
    }

    public void setXProperty (double x) { // = set x
        this.x.setValue(x);
    }

    public void setYProperty (double y) { // = set y
        this.y.setValue(y);
    }

}
