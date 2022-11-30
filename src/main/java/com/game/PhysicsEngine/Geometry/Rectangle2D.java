package com.game.PhysicsEngine.Geometry;

import com.game.PhysicsEngine.PhysicsCalculations.Maths.Vector2D;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;

public class Rectangle2D extends Polygon2D {
    private DoubleProperty minX;
    private DoubleProperty minY;
    private DoubleProperty width;
    private DoubleProperty height;

    /**
     * 
     * @param vertices the vertices of the polygon
     * <p><font color="red">
     * Warning: the vertices must be in clockwise order
     * </font>
     * </p>
     */
    public Rectangle2D(Vector2D[] vertices) {
        super(vertices);
    }

    /**
     * <p>
     * The {@code Rectangle2D} is a contructor which allow to create a rectangle
     * a polygon shape without configuring the vertices.
     * </p>
     * 
     * @param minX   Double x value of the upper-left corner of the rectangle
     * @param minY   Double y value of the upper-left corner of the rectangle
     * @param width  Double width of the rectangle
     * @param height Double height of the rectangle
     * @author Mathis
     */

    public Rectangle2D(double minX, double minY, double width, double height) {
        super(new Vector2D[] { new Vector2D(minX, minY), new Vector2D(minX + width, minY),
                new Vector2D(minX + width, minY + height), new Vector2D(minX, minY + height) });
        this.minX = new DoublePropertyBase(minX) {
            @Override
            public Object getBean() {
                return Rectangle2D.this;
            }

            @Override
            public String getName() {
                return "minX";
            }
        };
        this.minY = new DoublePropertyBase(minY) {
            @Override
            public Object getBean() {
                return Rectangle2D.this;
            }

            @Override
            public String getName() {
                return "minY";
            }
        };
        this.width = new DoublePropertyBase(width) {
            @Override
            public Object getBean() {
                return Rectangle2D.this;
            }

            @Override
            public String getName() {
                return "width";
            }
        };
        this.height = new DoublePropertyBase(height) {
            @Override
            public Object getBean() {
                return Rectangle2D.this;
            }

            @Override
            public String getName() {
                return "height";
            }
        };
    }

    public void setMinXProperty(Double minX) {
        this.minX.setValue(minX);
    }

    public void setMinYProperty(Double minY) {
        this.minY.setValue(minY);
    }

    public void setWidth(Double width) {
        this.width.setValue(width);
    }

    public void setHeight(Double height) {
        this.height.setValue(height);
    }

    public Double getMinX() {
        return minX.get();
    }

    public Double getMinY() {
        return minY.get();
    }

    public Double getWidth() {
        return width.get();
    }

    public Double getHeight() {
        return height.get();
    }

    public double getMaxX() {
        return minX.get() + width.get();
    }

    public double getMaxY() {
        return minY.get() + height.get();
    }

    public DoubleProperty getMinXProperty() {
        return minX;
    }

    public DoubleProperty getMinYProperty() {
        return minY;
    }

    public DoubleProperty getWidthProperty() {
        return width;
    }

    public DoubleProperty getHeightProperty() {
        return height;
    }
}