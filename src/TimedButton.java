import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class TimedButton extends Button{
    public TimedButton(Image a) {
        super(a);
    }

    @Override
    public void act(long now) {

    }

    @Override
    public void onClick() {
        TestFruitWorld world = new TestFruitWorld(1, ((GameSelectWorld)(getWorld())).getMain());
        world.start();
        TestFruitNinja.getLayout().setCenter(world);

    }
}
