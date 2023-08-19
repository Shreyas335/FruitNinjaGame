import com.sun.javafx.property.adapter.PropertyDescriptor;
import engine.World;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import java.util.Random;
import java.net.http.WebSocket;

public class BallWorld extends World {
    private Score score;

    public BallWorld() {
            setPrefHeight(500);
            setPrefWidth(500);
            setWidth(500);
            setHeight(500);

            //onDimensionsInitialized();

            //BROKEN CODE
            sceneProperty().addListener(new ChangeListener<Scene>() {
                @Override
                public void changed(ObservableValue<? extends Scene> observableValue, Scene scene, Scene t1) {
                    if (observableValue != null) {
                        requestFocus();
                    }
                }
            });

    }
    @Override
    public void act(long now) {

    }

    @Override
    public void onDimensionsInitialized() {
        Ball ball = new Ball();
        ball.setX(getWidth()/2);
        ball.setY(getHeight()/2);
        add(ball);

        Paddle paddle = new Paddle();
        paddle.setX(getWidth()/2);
        paddle.setY(getHeight()/5*4);
        add(paddle);

        Brick brci = new Brick();
        add(brci);
        brci.setX(200);
        brci.setY(200);

        Brick brci2 = new Brick();
        add(brci2);
        brci2.setX(100);
        brci2.setY(100);

        Brick brci3 = new Brick();
        add(brci3);
        brci3.setX(50);
        brci3.setY(150);

        Brick brci4 = new Brick();
        add(brci4);
        brci4.setX(250);
        brci4.setY(150);

        Brick brci5 = new Brick();
        add(brci5);
        brci5.setX(300);
        brci5.setY(150);

        Brick brci6 = new Brick();
        add(brci6);
        brci6.setX(350);
        brci6.setY(300);

        Brick brci7 = new Brick();
        add(brci7);
        brci7.setX(0);
        brci7.setY(100);

        Brick brci8 = new Brick();
        add(brci8);
        brci8.setX(400);
        brci8.setY(300);

        for (int i = 0; i < 20; i++) {
            Brick temp = new Brick();
            add(temp);
            temp.setX(new Random().nextInt(500));
            temp.setY(new Random().nextInt(350));
        }

        score = new Score();
        score.setX(getWidth()/2);
        score.setY(getHeight()-30);
        
        getChildren().add(score);

        //mousey tingy
        setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getX() >= paddle.getWidth() / 2 && mouseEvent.getX() <= getWidth() - paddle.getWidth() / 2)
                    paddle.setX(mouseEvent.getX() - paddle.getWidth() / 2);
            }
        });


    }


    public Score getScore(){
            return score;
    }
}