package com.game;

import com.game.PhysicsEngine.Object.Entity;
import com.game.PhysicsEngine.PhysicsCalculations.Interactions;
import com.game.PhysicsEngine.PhysicsCalculations.Physics2D;
import com.game.PhysicsEngine.PhysicsCalculations.Maths.Vector2D;
import com.game.PhysicsEngine.RigidBody2D.RigidBody2D;

import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

public class UpdateRender extends ScheduledService<Void> {
   public static double currentTime = 0;

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Platform.runLater(() -> {
                    /* Modify you GUI properties... */
                    for (Entity E : Physics2D.entities) {

                        RigidBody2D a = E.getRigidBody();
                        // if (a.getPosition().getXProperty().get() < 0 - a.getPosition().getXProperty().get() - a.getBox2D().getWidth() / 2) a.moveTo(new Vector2D(Main.sceneWidth + a.getBox2D().getWidth() / 2, a.getPosition().getYProperty().get()));
                        // if (a.getPosition().getXProperty().get() > Main.sceneWidth + a.getBox2D().getWidth() / 2) a.moveTo(new Vector2D(0 - a.getBox2D().getWidth() / 2 + 10, a.getPosition().getYProperty().get()));
                        // if (a.getPosition().getYProperty().get() < 0 - a.getPosition().getYProperty().get() - a.getBox2D().getHeight() / 2) a.moveTo(new Vector2D(a.getPosition().getXProperty().get(), Main.sceneHeight + a.getBox2D().getHeight() / 2));
                        // if (a.getPosition().getYProperty().get() > Main.sceneHeight + a.getBox2D().getHeight() / 2) a.moveTo(new Vector2D(a.getPosition().getXProperty().get(), 0 - a.getBox2D().getHeight() / 2 + 10));

                        // // Vector2D interpolate = new Vector2D(E.getOldPosition().multiplyVector2DConst(Main.alpha).addVector2DVect(new Vector2D(E.getRigidBody().getBox2D().getMinX(), E.getRigidBody().getBox2D().getMinY())).multiplyVector2DConst(1 - Main.alpha));
                        // // E.setOldPosition(new Vector2D(E.getRigidBody().getBox2D().getMinX(), E.getRigidBody().getBox2D().getMinY()));
                        // // E.getSprite().setTranslateX(interpolate.getXProperty().get());
                        // // E.getSprite().setTranslateY(interpolate.getYProperty().get());
                        
                        // //E.getSprite().setRotate(E.getSprite().getRotate() * Main.alpha + E.getRigidBody().getAngleProperty().get() * (1 - Main.alpha));

                        // E.getSprite().setTranslateX(E.getRigidBody().getBox2D().getMinX());
                        // E.getSprite().setTranslateY(E.getRigidBody().getBox2D().getMinY());
                        // E.getSprite().setRotate(E.getRigidBody().getAngleProperty().get());

        //                 Main.c.setTranslateX(Interactions.supportingPoints[0].getXProperty().get());
        // Main.c.setTranslateY(Interactions.supportingPoints[0].getYProperty().get());
        // Main.c2.setTranslateX(Interactions.supportingPoints[1].getXProperty().get());
        // Main.c2.setTranslateY(Interactions.supportingPoints[1].getYProperty().get());
                    }
                 });
                return null;
            }
        };
    }

    
}
