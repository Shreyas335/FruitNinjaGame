import engine.World;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GameSelectWorld extends World {


    private MainMenu main;
    public GameSelectWorld(MainMenu m){
        main = m;
        setWidth(1200);
        setHeight(700);
    }
    @Override
    public void act(long now) {

    }

    @Override
    public void onDimensionsInitialized() {
        Background1 bg = new Background1();
        bg.setFitWidth(getWidth());
        bg.setFitHeight(getHeight());
        bg.setX(-40);
        bg.setY(-40);
        add(bg);
        bg.begin();


        InfiniteButton inf = new InfiniteButton(new Image("images/infinite.png"));
        inf.setWidth(getWidth() * 0.25);
        inf.setY(getHeight() / 2);
        inf.setX(getWidth() / 6 - inf.getWidth() / 2);

        MultiPlayerButton mp = new MultiPlayerButton(new Image("images/multiplayer.png"));
        mp.setWidth(getWidth() * 0.25);
        mp.setY(getHeight() / 2);
        mp.setX(getWidth() / 2 - inf.getWidth() / 2);

        TimedButton timed = new TimedButton(new Image("images/timed.png"));
        timed.setWidth(getWidth() * 0.25);
        timed.setY(getHeight() / 2);
        timed.setX(getWidth() * 5/ 6 - inf.getWidth() / 2);

        add(inf);
        add(mp);
        add(timed);


    }
    public MainMenu getMain() {
        return main;
    }
}
