import engine.Actor;
import javafx.scene.image.Image;

public class MultiPlayerCursor extends Actor {
    private int dx = 5;

    public MultiPlayerCursor(){
        setImage(new Image("images/kunai.png"));
        setFitWidth(30);
        setFitHeight(getWidth() * 223/150);
    }
    @Override
    public void act(long now) {

    }
}
