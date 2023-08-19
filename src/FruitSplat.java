import engine.Actor;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class FruitSplat extends Actor {

    public FruitSplat(double x, double y, Image image) {
        String[] splatty = {"Sound/Splatter-Medium-1.wav", "Sound/Splatter-Medium-2.wav", "Sound/Splatter-Small-1.wav", "Sound/Splatter-Small-2.wav"};

        Media mediac = new Media(getClass().getResource(splatty[(int)(Math.random() * 4)]).toExternalForm());
        MediaPlayer mediaPlayerr = new MediaPlayer(mediac);
        mediaPlayerr.play();
        setX(x);
        setY(y);
        setImage(image);
    }
    double prev = 0;
    public void act(long now) {
        if (prev == 0) prev = now;
        if (prev + 1000000000 < now) getWorld().remove(this);

    }
}
