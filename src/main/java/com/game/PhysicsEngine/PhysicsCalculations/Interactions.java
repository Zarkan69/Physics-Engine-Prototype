package com.game.PhysicsEngine.PhysicsCalculations;

import com.game.PhysicsEngine.Object.Entity;
import com.game.PhysicsEngine.PhysicsCalculations.Maths.Vector2D;
import com.game.PhysicsEngine.PhysicsCalculations.Maths.VectorMath;
import com.game.PhysicsEngine.RigidBody2D.RigidPolygon2D;

public class Interactions {
    public static boolean checking = true, isColliding = false;
    public static double depth = 0;
    public static Vector2D normal;
    public static Vector2D[] supportingPoints;

    public static void findEntities() {
        Physics2D.collisions.clear();

        for (int i = 0; i < Physics2D.entities.size(); i++) {
            Entity searchingEntity = Physics2D.entities.get(i);
            RigidPolygon2D searchingPolygon = (RigidPolygon2D) searchingEntity.getRigidBody();
            if (searchingPolygon.getPolygon2D().getVertices() == null || searchingPolygon.getPolygon2D().getVertices().length < 1)
                return;

            double searchingEntityMinX = searchingPolygon.getPolygon2D().getVertices()[0].getXProperty().get();
            double searchingEntityMaxX = searchingPolygon.getPolygon2D().getVertices()[0].getXProperty().get();
            double searchingEntityMinY = searchingPolygon.getPolygon2D().getVertices()[0].getYProperty().get();
            double searchingEntityMaxY = searchingPolygon.getPolygon2D().getVertices()[0].getYProperty().get();

            for (int k = 1; k < searchingPolygon.getPolygon2D().getVertices().length; k++) {
                if (searchingEntityMinX > searchingPolygon.getPolygon2D().getVertices()[k].getXProperty().get())
                    searchingEntityMinX = searchingPolygon.getPolygon2D().getVertices()[k].getXProperty().get();

                if (searchingEntityMaxX < searchingPolygon.getPolygon2D().getVertices()[k].getXProperty().get())
                    searchingEntityMaxX = searchingPolygon.getPolygon2D().getVertices()[k].getXProperty().get();

                if (searchingEntityMinY > searchingPolygon.getPolygon2D().getVertices()[k].getYProperty().get())
                    searchingEntityMinY = searchingPolygon.getPolygon2D().getVertices()[k].getYProperty().get();

                if (searchingEntityMaxY < searchingPolygon.getPolygon2D().getVertices()[k].getYProperty().get())
                    searchingEntityMaxY = searchingPolygon.getPolygon2D().getVertices()[k].getYProperty().get();
            }

            for (int j = i + 1; j < Physics2D.entities.size(); j++) {
                Entity nearEntity = Physics2D.entities.get(j);
                if (searchingPolygon.getPolygon2D().getVertices() == null || searchingPolygon.getPolygon2D().getVertices().length < 1)
                    return;

                double nearEntityMinX = searchingPolygon.getPolygon2D().getVertices()[0].getXProperty().get();
                double nearEntityMaxX = searchingPolygon.getPolygon2D().getVertices()[0].getXProperty().get();
                double nearEntityMinY = searchingPolygon.getPolygon2D().getVertices()[0].getYProperty().get();
                double nearEntityMaxY = searchingPolygon.getPolygon2D().getVertices()[0].getYProperty().get();

                for (int k = 1; k < searchingPolygon.getPolygon2D().getVertices().length; k++) {
                    if (nearEntityMinX > searchingPolygon.getPolygon2D().getVertices()[k].getXProperty().get())
                        nearEntityMinX = searchingPolygon.getPolygon2D().getVertices()[k].getXProperty().get();

                    if (nearEntityMaxX < searchingPolygon.getPolygon2D().getVertices()[k].getXProperty().get())
                        nearEntityMaxX = searchingPolygon.getPolygon2D().getVertices()[k].getXProperty().get();

                    if (nearEntityMinY > searchingPolygon.getPolygon2D().getVertices()[k].getYProperty().get())
                        nearEntityMinY = searchingPolygon.getPolygon2D().getVertices()[k].getYProperty().get();

                    if (nearEntityMaxY < searchingPolygon.getPolygon2D().getVertices()[k].getYProperty().get())
                        nearEntityMaxY = searchingPolygon.getPolygon2D().getVertices()[k].getYProperty().get();

                }
                // System.out.println("searchingEntityMinX: " + searchingEntityMinX + " searchingEntityMaxX: " + searchingEntityMaxX + " searchingEntityMinY: " + searchingEntityMinY + " searchingEntityMaxY: " + searchingEntityMaxY);
                // System.out.println("nearEntityMinX: " + nearEntityMinX + " nearEntityMaxX: " + nearEntityMaxX + " nearEntityMinY: " + nearEntityMinY + " nearEntityMaxY: " + nearEntityMaxY);

                if (nearEntityMinX - 32 < searchingEntityMaxX && 
                    nearEntityMaxX + 32 > searchingEntityMinX && 
                    nearEntityMinY - 32 < searchingEntityMaxY && 
                    nearEntityMaxY + 32 > searchingEntityMinY) {

                    if (searchingEntity.getNearEntities().size() != 0) {

                        for (int l = 0; l < searchingEntity.getNearEntities().size(); l++) {
                            if (searchingEntity.getNearEntities().get(l).equals(nearEntity)) {
                                checking = false;
                                if (isColliding = Intersects(searchingEntity, searchingEntity.getNearEntities().get(l))) {
                                    CollisionManifold collision = new CollisionManifold(searchingEntity.getRigidBody(), searchingEntity.getNearEntities().get(l).getRigidBody(), normal, depth, new Vector2D(), new Vector2D(), 0);
                                    Physics2D.collisions.add(collision);
                                    // Physics2D.resolveCollision(searchingEntity,
                                    //         searchingEntity.getNearEntities().get(l), depth, normal);
                                }
                                break;
                            }
                        }
                    }
                    if (checking) {
                        searchingEntity.getNearEntities().add(nearEntity);
                        // System.out.println("New entity added");
                        // if (isColliding = Intersects(searchingEntity, nearEntity))
                        // Physics2D.resolveCollision(searchingEntity, nearEntity, depth, normal);
                    }
                    checking = true;
                } else {
                    for (int l = 0; l < searchingEntity.getNearEntities().size(); l++) {
                        if (searchingEntity.getNearEntities().get(l).equals(nearEntity)) {
                            searchingEntity.getNearEntities().remove(l);
                            // System.out.println("Entity removed");
                            break;
                        }

                    }
                }
            }
        }

        for (int k = 0; k < Physics2D.collisions.size(); k++) {
            Physics2D.resolveCollision(Physics2D.collisions.get(k));
        }
    }

    private static boolean Intersects(Entity a, Entity b) {
        normal = new Vector2D(0, 0);
        depth = Double.MAX_VALUE;

        RigidPolygon2D aPolygonBody = (RigidPolygon2D) a.getRigidBody();
        RigidPolygon2D bPolygonBody = (RigidPolygon2D) b.getRigidBody();

        Vector2D[] verticesA = aPolygonBody.getPolygon2D().getVertices();
        Vector2D[] verticesB = bPolygonBody.getPolygon2D().getVertices();

        for (int i = 0; i < verticesA.length; i++) {
            Vector2D va = new Vector2D(verticesA[i]);
            Vector2D vb = new Vector2D(verticesA[(i + 1) % verticesA.length]);

            Vector2D edge = new Vector2D(vb.subtractVector2DVect(va)); 
            Vector2D axis = new Vector2D(-edge.getYProperty().get(), edge.getXProperty().get()); // normal
            axis.normalize();

            double[] projectionA = projectVertices(verticesA, axis);
            double[] projectionB = projectVertices(verticesB, axis);

            if (projectionA[0] >= projectionB[1] || projectionB[0] >= projectionA[1]) { // [0] min [1] max
                return false;
            }

            double axisDepth = Math.min(projectionB[1] - projectionA[0], projectionA[1] - projectionB[0]);

            if (axisDepth < depth) {
                depth = axisDepth;
                normal = axis;
            }
        }
        
        for (int i = 0; i < verticesB.length; i++) {
            Vector2D va = new Vector2D(verticesB[i]);
            Vector2D vb = new Vector2D(verticesB[(i + 1) % verticesB.length]);

            Vector2D side = new Vector2D(vb.subtractVector2DVect(va));
            Vector2D axis = new Vector2D(-side.getYProperty().get(), side.getXProperty().get());
            axis.normalize();

            double[] projectionA = projectVertices(verticesA, axis);
            double[] projectionB = projectVertices(verticesB, axis);

            if (projectionA[0] >= projectionB[1] || projectionB[0] >= projectionA[1]) {
                return false;
            }

            double axisDepth = Math.min(projectionB[1] - projectionA[0], projectionA[1] - projectionB[0]);

            if (axisDepth < depth) {
                depth = axisDepth;
                normal = axis;
            }
        }

        Vector2D direction = new Vector2D(b.getRigidBody().getPosition().subtractVector2DVect(a.getRigidBody().getPosition()));

        if (VectorMath.dotProduct(direction, normal) < 0) {
            normal.inverse();
        }

        return true;
    }
        
    // direction is inverse of the normal vector
    public static Vector2D[] getSupportPoints(Vector2D[] vertices, Vector2D direction) {
        double bestProjection = -Double.MAX_VALUE;
        Vector2D[] bestVertex = new Vector2D[2];

        for (Vector2D vertex : vertices) {
           Vector2D v = new Vector2D(vertex);
           double projection = VectorMath.dotProduct(v, direction);

           if (projection > bestProjection) {
               bestProjection = projection;
                bestVertex[0] = v;
           }
           else if (projection == bestProjection) {
               bestVertex[1] = v;
           }
        }
        return bestVertex;
    }

    private static double[] projectVertices(Vector2D[] vertices, Vector2D axis) { // project vertices onto normal axis
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        for (int i = 0; i < vertices.length; i++) {
            Vector2D v = new Vector2D(vertices[i]);
            double projection = VectorMath.dotProduct(v, axis);
            if (projection < min)
                min = projection;
            if (projection > max)
                max = projection;
        }
        return new double[] { min, max };
    }
}