
import engine.Actor;
import javafx.scene.image.Image;

public class CutFruit extends Actor {
    private int dx;
    private int dy;

    public CutFruit(double x, double y, int dx, int dy, Image a) {
        this.dx = dx;
        this.dy = dy;
        setX(x);
        setY(y);

        setImage(a);
    }

    @Override
    public void act(long now) {
        move(dx, dy);
        if (getY() > getWorld().getHeight()) getWorld().remove(this);
    }

}



