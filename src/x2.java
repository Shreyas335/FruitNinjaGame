import javafx.scene.image.Image;

public class x2 extends ThrownObject{
    private boolean cutOnce = false;
    private boolean stationary;
    public x2(double startX, double endX, double maxY, boolean stationary) {
        super(startX, endX, maxY);
        this.stationary = stationary;

        setImage(new Image("images/x2.png"));
    }

    @Override
    public void cut() {

        if(!stationary) {
            if(((TestFruitWorld) (getWorld())).getScore().getValue() * 2< Integer.MAX_VALUE) {
                ((TestFruitWorld) (getWorld())).getScore().setValue(((TestFruitWorld) (getWorld())).getScore().getValue() * 2);
            }
            ((TestFruitWorld) (getWorld())).draw2x();

            getWorld().remove(this);
        }

    }
}
