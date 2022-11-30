package com.game.PhysicsEngine.PhysicsCalculations.Maths;

public class VectorMath {
    public static double vecLength(Vector2D v1) {
        return Math.sqrt(Math.pow(v1.getXProperty().getValue(), 2) + Math.pow(v1.getYProperty().getValue(), 2));
    }

    public static double distance(Vector2D v1, Vector2D v2) {
        return Math.sqrt(Math.pow(v1.getXProperty().getValue() - v2.getXProperty().getValue(), 2) + Math.pow(v1.getYProperty().getValue() - v2.getYProperty().getValue(), 2));
    }

    /**
     * <p>
     * return the result of v1.x * v2.x + v1.y * v2.y
     * @param v1 Vector2D
     * @param v2 Vector2D
     * @return double
     */
    public static double dotProduct(Vector2D... vn) {
        double result = 0;
        for (Vector2D v : vn) {
            result += v.getXProperty().getValue() * v.getYProperty().getValue();
        }
        return result;
    }

    /**
     * <p>
     * return the result of v1.x * v2.y - v1.y * v2.x
     * @param v1 Vector2D
     * @param v2 Vector2D
     * @return double
     */
    public static double crossProduct(Vector2D... vn) {
        double result = 0;
        for (int i = 0; i < vn.length; i++) {
            if (i == vn.length - 1) {
                result += vn[i].getXProperty().getValue() * vn[0].getYProperty().getValue() - vn[i].getYProperty().getValue() * vn[0].getXProperty().getValue();
            } else {
                result += vn[i].getXProperty().getValue() * vn[i + 1].getYProperty().getValue() - vn[i].getYProperty().getValue() * vn[i + 1].getXProperty().getValue();
            }
        }
        return result;
    }

    public static Vector2D crossProductVectConst(Vector2D v1, double c) {
        return new Vector2D(v1.getYProperty().getValue() * c, -v1.getXProperty().getValue() * c);
    }

    public static Vector2D crossProductConstVect(double c, Vector2D v1) {
        return new Vector2D(-v1.getYProperty().getValue() * c, v1.getXProperty().getValue() * c);
    }
}
