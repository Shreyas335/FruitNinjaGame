import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Pome extends Fruit{
    private boolean hasCut = false;
    public Pome(double startX, double endX, double maxY) {

        super(startX, endX, maxY);
        setImage(new Image("images/Pom.png"));
    }
    //private Image leftHalf = new Image("images/left_Apple.png");
    //private Image rightHalf = new Image("images/right_Apple.png");



    @Override
    public void cut() {

        bgShake();
        if(((TestFruitWorld) (getWorld())).getScore().getValue() + 1 < Integer.MAX_VALUE) {
            ((TestFruitWorld) (getWorld())).getScore().setValue(((TestFruitWorld) (getWorld())).getScore().getValue() + 1);
        }
        if(!hasCut) {
            ((TestFruitWorld) (getWorld())).setPom(true, this);
            hasCut = true;
        }
        setDx(0);
        setDy(0);
        setGravity(0);
        String[] audi = {
                "Sound/pome-slice-1.wav",
                "Sound/pome-slice-2.wav",
                "Sound/pome-slice-3.wav",
                "Sound/pome-lp.wav"
        };
        int ran = (int) (Math.random() * 4);
        String s = audi[ran];
        Media media = new Media(getClass().getResource(s).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();




    }
}
