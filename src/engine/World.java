package engine;

import engine.Actor;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;

public abstract class World extends Pane implements javafx.css.Styleable, javafx.event.EventTarget {
    HashSet<KeyCode> set;
    private AnimationTimer timer;
    private boolean isRunning;
    private double heightInitialized = -1;
    private double widthInitialized = -1;
    private boolean started = false;
    public World() {
        set = new HashSet<KeyCode>();
        isRunning = false;
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                act(l);
                    for (Actor i : getObjects(Actor.class)) {
                        if(i != null) {

                            i.act(l);

                        }
                    }
                    //isRunning = true;
            }
        };

        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                set.add(keyEvent.getCode());
            }
        });
        setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                set.remove(keyEvent.getCode());
            }
        });

        /*
        Notes to reader:
            ChangeListener is assigned to a property, in this case with the width, height, or scene.
            Upon a change to that property like a screen resize, the listener should be triggered
            The observable value is the thing being changed (width/height), second value is the old value
            and the third (t1) is the new value.
         */
        widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if (number.doubleValue() == 0 && t1.doubleValue() != 0) widthInitialized = t1.doubleValue();
                if (widthInitialized != -1 && heightInitialized != -1 && !started) {
                    onDimensionsInitialized();
                    started = true;
                }
            }
        });
        heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if (number.doubleValue() == 0 && t1.doubleValue() != 0) heightInitialized = t1.doubleValue();
                if (widthInitialized != -1 && heightInitialized != -1 && !started) {
                    onDimensionsInitialized();
                    started = true;
                }
            }
        });
        sceneProperty().addListener(new ChangeListener<Scene>() {
            @Override
            public void changed(ObservableValue<? extends Scene> observableValue, Scene scene, Scene t1) {
                if (t1 != null) requestFocus();
            }
        });

    }
    public abstract void act(long now);

    public <A extends Actor> List<A> getObjects(java.lang.Class<A> cls){
        ArrayList<A> list = new ArrayList<A>();
//        for(Node i : getChildren()){
//            if(i.getClass().equals(cls.getClass())){
//                list.add((A)(i));
//            }
//        }
        for (int i = 0; i < getChildren().toArray().length; i++) {
            if(getChildren().get(i) instanceof Actor) {
                A actor = (A) getChildren().get(i);
                if (cls.isInstance(actor)) {
                    list.add(actor);
                }
            }
        }
        return list;


    }
    public void add(Actor actor){
        getChildren().add(actor);
        actor.addedToWorld();
    }

    public void remove(Actor actor){
        getChildren().remove(actor);
    }

    public void start(){
        timer.start();
        isRunning = true;

    }
    public void stop(){
        timer.stop();
        isRunning = false;
    }

    public void addKeyCode(KeyCode code) {
        set.add(code);
    }

    public void removeKeyCode(KeyCode code) {
        set.remove(code);
    }

    public boolean isKeyPressed (KeyCode code) {
        return set.contains(code);
    }

    public boolean isKeyCodeDown(KeyCode code) {
        return set.contains(code);
    }

    public abstract void onDimensionsInitialized();

    public boolean isStopped() {
        return isRunning;
    }

    public <A extends engine.Actor> java.util.List<A> getObjectsAt(double x, double y, java.lang.Class<A> cls) {
        ArrayList<A> list = new ArrayList<A>();
//        for(Node i : getChildren()){
//            if(i.getClass().equals(cls.getClass())){
//                list.add((A)(i));
//            }
//        }
        List<A>  temp = getObjects(cls);
        for (A n : temp) {
            if (n.getX() <= x && n.getX() + n.getWidth() >= x && n.getY() <= y && n.getY() + n.getHeight() >= y) {
                list.add(n);
            }
        }

        return list;
    }

}