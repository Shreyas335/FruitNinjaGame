import javafx.scene.image.Image;

public class Freeze extends ThrownObject{
    public Freeze(double startX, double endX, double maxY) {

        super(startX, endX, maxY);
        setImage(new Image("images/Freeze.png"));
    }

    @Override
    public void cut() {

        //((TestFruitWorld) (getWorld())).getScore().setValue(((TestFruitWorld) (getWorld())).getScore().getValue() + 20);
        for(Fruit a : getWorld().getObjects(Fruit.class)){
            a.setDx(0);
            a.setDy(0);
            a.setGravity(0);
            a.frozen();

        }
        //((TestFruitWorld) (getWorld())).setSlow(true);
        getWorld().remove(this);

    }
}
