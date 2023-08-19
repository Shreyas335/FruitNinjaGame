import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Orange extends Fruit{
    public Orange(double startX, double endX, double maxY) {

        super(startX, endX, maxY);
        setImage(new Image("images/orange.png"));
        setFitWidth(70);
        setFitHeight(70);
    }
    private Image leftHalf = new Image("images/left_Orange.png");
    private Image rightHalf = new Image("images/right_Orange.png");



    @Override
    public void cut() {
        bgShake();
        if(((TestFruitWorld) (getWorld())).getScore().getValue() + 4 < Integer.MAX_VALUE) {
            ((TestFruitWorld) (getWorld())).getScore().setValue(((TestFruitWorld) (getWorld())).getScore().getValue() + 4);
        }
        ((TestFruitWorld)(getWorld())).setFruitsCut(((TestFruitWorld) (getWorld())).getFruitsCut() + 1);

        CutFruit right = new CutFruit( getX(), getY(), 2, 15, rightHalf);
        CutFruit left = new CutFruit( getX(), getY(), -2, 15, leftHalf);
        FruitSplat splat = new FruitSplat(getX(), getY(), new Image("images/orange_Splat.png"));
        getWorld().add(right);
        getWorld().add(left);
        getWorld().add(splat);
        getWorld().remove(this);

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
    }
}
