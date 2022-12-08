package com.game;

import com.game.PhysicsEngine.Object.Entity;
import com.game.PhysicsEngine.Object.SpriteObject;
import com.game.PhysicsEngine.PhysicsCalculations.Physics2D;
import com.game.PhysicsEngine.PhysicsCalculations.Maths.Vector2D;
import com.game.PhysicsEngine.RigidBody2D.RigidPolygon2D;

import javafx.application.Application;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    //public static Entity eA = new Entity(new RigidPolygon2D(32, 32, 32, 32, 10, 0.1));
    public static Entity eC = new Entity(new RigidPolygon2D(300, 350, 600, 32, 10, 0.1));
    public static SpriteObject eCSprite = new SpriteObject(eC);
    //public static Entity eB = new Entity(new RigidBody2D(32, 32, 10, 1, 0.5, 0));

    public static boolean up, down, left, right, droite, gauche, debug;
    public static double sceneWidth = 1200;
    public static double sceneHeight = 1200;
    public static double fps = 100, dt = 1 / fps, accumulator = 0, frameStart = 0, alpha = 0;
    public static Polyline showCollider = new Polyline();
    public static Pane root = new Pane();
    public static Polygon test = new Polygon();

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, sceneWidth, sceneHeight);

        eC.setColor(Color.RED);

        test.getPoints().addAll(new Double[] { 50.0, 50.0, 100.0, 50.0, 10.0, 10.0, 50.0, 10.0 });

        RigidPolygon2D rp = (RigidPolygon2D) eC.getRigidBody(); 
        //eC.getRigidBody().setPosition(new Vector2D(600, 700), rp.getPolygon2D());
        //eA.getRigidBody().rotateBox2D(35);

        Physics2D.entities.add(eC); // rouge

        //root.getChildren().add(eB.getSprite());
        root.getChildren().add(eCSprite.getPolygonSprite());
        root.getChildren().add(test);
        primaryStage.setScene(scene);
        primaryStage.show();

        var updateRender = new UpdateRender();
        updateRender.setPeriod(Duration.millis(30));
        updateRender.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                Physics2D.update(1);
                // double currentTime = System.nanoTime();

                // accumulator += currentTime - frameStart;

                // frameStart = currentTime;

                // if (accumulator > 0.2f)
                // accumulator = 0.2f;

                // while (accumulator > dt) {
                // Physics2D.update(dt);
                // accumulator -= dt;
                // }

                // alpha = accumulator / dt;
                // }
            }
        });
        // frameStart = System.nanoTime();
        updateRender.start();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case Z:
                        up = true;
                        break;
                    case S:
                        down = true;
                        break;
                    case D:
                        right = true;
                        break;
                    case Q:
                        left = true;
                        break;
                    case A:
                        droite = true;
                        break;
                    case E:
                        gauche = true;
                        break;
                    case F1:
                        if (debug) {
                            debug = false;
                        } else {
                            debug = true;
                        }
                        break;
                    default:
                        break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case Z:
                        up = false;
                        break;
                    case S:
                        down = false;
                        break;
                    case Q:
                        left = false;
                        break;
                    case D:
                        right = false;
                        break;
                    case A:
                        droite = false;
                        break;
                    case E:
                        gauche = false;
                        break;
                    default:
                        break;
                }
            }

        });

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Vector2D mouse = new Vector2D(event.getX(), event.getY());
                Entity e = new Entity(new RigidPolygon2D(0, 0, 32, 32, 10, 0.2));
                SpriteObject eSprite = new SpriteObject(e);
                Physics2D.entities.add(e);
                RigidPolygon2D rp = (RigidPolygon2D) e.getRigidBody();
                //e.getRigidBody().setPosition(mouse, rp.getPolygon2D());
                root.getChildren().add(eSprite.getPolygonSprite());
                eSprite.getPolygonSprite().setFill(Color.rgb((int) (Math.random() * 255) + 1, (int) (Math.random() * 255) + 1,
                        (int) (Math.random() * 255) + 1));
                //e.getRigidBody().setPosition(mouse, rp.getPolygon2D());
                //e.getRigidBody().rotateBox2D(Math.random() * 360);
                double forceMagnitude = 0.5;
                Vector2D forceDirection = new Vector2D(0, 3);
                Vector2D force = new Vector2D(forceDirection.multiplyVector2DConst(forceMagnitude));
                System.out.println("Entity Count: " + Physics2D.entities.size());
                // e.getRigidBody().addForce(force);
                // e.getRigidBody().rotateBox2D(45);
            }
        });
    }
}