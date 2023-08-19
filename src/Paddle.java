import engine.Actor;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Paddle extends Actor {

    public Paddle() {
        setImage(new Image("images/paddle.png"));
    }
    @Override
    public void act(long now) {
        //Keyboard events
        getWorld().requestFocus();
        //getWorld().sceneProperty().addListener();
        if (getWorld().isKeyCodeDown(KeyCode.LEFT)){
            if (getX() >= 0) {
                setX(getX() - 10);
            }

            //getWorld().addKeyCode(KeyCode.LEFT);
        }

        if (getWorld().isKeyCodeDown(KeyCode.RIGHT)) {
            if (getX() <= getWorld().getWidth()){
                setX(getX() + 10);
            }

            //getWorld().addKeyCode(KeyCode.RIGHT);
        }
        /*
        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                /*
                if (keyEvent.getCode() == KeyCode.LEFT)
                    world.addKeyCode(KeyCode.LEFT);
                if (keyEvent.getCode() == KeyCode.RIGHT)



                if (getWorld().isKeyCodeDown(KeyCode.LEFT)){
                    setX(getX() - 10);
                    //getWorld().addKeyCode(KeyCode.LEFT);
                }

                if (getWorld().isKeyCodeDown(KeyCode.RIGHT)) {
                    setX(getX() + 10);
                    //getWorld().addKeyCode(KeyCode.RIGHT);
                }
            }
        });
        */

    }


}
