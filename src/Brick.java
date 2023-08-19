import engine.Actor;
import javafx.scene.image.Image;

public class Brick extends Actor {
    @Override
    public void act(long now) {
        setImage(new Image("images/brick.png"));
    }
}
