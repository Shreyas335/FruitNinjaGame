import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Bomb extends ThrownObject{
    public Bomb(double startX, double endX, double maxY) {

        super(startX, endX, maxY);
        setImage(new Image("images/bomb.png"));
    }

    @Override
    public void cut() {
        /*
        String musicFile = "StayTheNight.mp3";     // For example

        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        x
         */
        ((TestFruitWorld) (getWorld())).getScore().setValue(((TestFruitWorld) (getWorld())).getScore().getValue() /2);

        FruitSplat splat = new FruitSplat(getX(), getY(), new Image("images/explosion.png"));

        getWorld().add(splat);
        getWorld().remove(this);

        Media media = new Media(getClass().getResource("Sound/blade-dragon-impact-2.wav").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
