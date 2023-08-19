import engine.Actor;
import javafx.scene.image.Image;

public class Paper extends Actor {
    private int move = 0;
    public Paper(){
        setImage(new Image("images/paper.png"));
        setFitWidth(1000);
        setFitHeight(650);
    }

    @Override
    public void act(long now) {
        if(move == 1 && getY() <=getWorld().getHeight() / 2 - getHeight() / 2 ){
            setY(getY() + 20);
        }
        if(move == -1 && getY() >= -getWorld().getHeight() / 2 - getHeight() / 2){
            setY(getY() - 20);
        }
    }
    public void appear(){
        move = 1;
    }
    public void disappear(){
        move = -1;
    }
}
