import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Kiwi extends Fruit{
    public Kiwi(double startX, double endX, double maxY) {

        super(startX, endX, maxY);
        setImage(new Image("images/kiwi.png"));
        setFitWidth(50);
        setFitHeight(50);
    }
    private Image leftHalf = new Image("images/left_Kiwi.png");
    private Image rightHalf = new Image("images/right_Kiwi.png");



    @Override
    public void cut() {
        bgShake();
        if(((TestFruitWorld) (getWorld())).getScore().getValue() + 10 < Integer.MAX_VALUE) {
            ((TestFruitWorld) (getWorld())).getScore().setValue(((TestFruitWorld) (getWorld())).getScore().getValue() + 10L);
        }
        ((TestFruitWorld)(getWorld())).setFruitsCut(((TestFruitWorld) (getWorld())).getFruitsCut() + 1);
        CutFruit right = new CutFruit( getX(), getY(), 2, 15, rightHalf);
        CutFruit left = new CutFruit( getX(), getY(), -2, 15, leftHalf);
        FruitSplat splat = new FruitSplat(getX(), getY(), new Image("images/kiwi_Splat.png"));
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
