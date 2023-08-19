import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MultiPlayerButton extends Button{
    public MultiPlayerButton(Image a) {
        super(a);
    }

    @Override
    public void act(long now) {

    }

    @Override
    public void onClick() {
        TestFruitWorld world = new TestFruitWorld(3, ((GameSelectWorld)(getWorld())).getMain());
        world.start();
        TestFruitNinja.getLayout().setCenter(world);

    }

}
