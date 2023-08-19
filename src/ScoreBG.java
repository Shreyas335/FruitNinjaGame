import engine.Actor;
import javafx.scene.image.Image;

public class ScoreBG extends Actor {
    public ScoreBG(double width, double height){
        setImage(new Image("images/blk.png"));
        setFitWidth(width);
        setFitHeight(height);
        setOpacity(0.3);


    }

    @Override
    public void act(long now) {

    }
}
