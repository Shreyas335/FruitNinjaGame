import javafx.scene.image.Image;

//Copy these imports
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import java.io.File;
public class Watermelon extends Fruit{
    public Watermelon(double startX, double endX, double maxY) {

        super(startX, endX, maxY);
        setImage(new Image("images/watermelon.png"));
        setFitWidth(120);
        setFitHeight(120);
    }
    private Image leftHalf = new Image("images/left_Watermelon.png");
    private Image rightHalf = new Image("images/right_Watermelon.png");



    @Override
    public void cut() {
        bgShake();
        if(((TestFruitWorld) (getWorld())).getScore().getValue() + 1 < Integer.MAX_VALUE) {
            ((TestFruitWorld) (getWorld())).getScore().setValue(((TestFruitWorld) (getWorld())).getScore().getValue() + 1);
        }
        String path = "Sound/Clean-Slice-1.wav";

        String[] audi = {
                "Sound/combo-1.wav",
                "Sound/combo-2.wav",
                "Sound/combo-4.wav",
                "Sound/Combo-8.wav"


        };
        int ran = (int) (Math.random() * 4);
        String s = audi[ran];
        Media media = new Media(getClass().getResource(s).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        ((TestFruitWorld)(getWorld())).setFruitsCut(((TestFruitWorld) (getWorld())).getFruitsCut() + 1);

        CutFruit right = new CutFruit( getX(), getY(), 2, 15, new Image("images/right_Watermelon.png"));
        CutFruit left = new CutFruit( getX(), getY(), -2, 15, new Image("images/left_Watermelon.png"));
        FruitSplat splat = new FruitSplat(getX(), getY(), new Image("images/watermelon_Splat.png"));
        getWorld().add(right);
        getWorld().add(left);
        getWorld().add(splat);
        getWorld().remove(this);


    }


}
