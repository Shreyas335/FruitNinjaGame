
import javafx.scene.image.Image;
import engine.*;

public class Fruit extends ThrownObject {
    /*
    @Override
    public double getStartX() {
        return startX;
    }

    @Override
    public void setStartX(double startX) {
        this.startX = startX;
    }

    @Override
    public double getEndX() {
        return endX;
    }

    @Override
    public void setEndX(double endX) {
        this.endX = endX;
    }

    public double getMaxY() {
        return maxY;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }
    */
    private double startX;
    private double endX;
    private double maxY;
    public Fruit(double startX, double endX, double maxY) {
        super(startX, endX, maxY);
    }
    public void split(){};

}

