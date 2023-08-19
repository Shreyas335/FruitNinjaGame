
import javafx.scene.image.Image;
import engine.*;

public class ThrownObject extends Actor {

    // INITIALIZED ALL TO IMAGE
    private Image wholeImage = new Image("images/pom1.png");

    public boolean isRising;
    private double maxHeight;
    private double startX;
    private double endX;
    private double speed = 5;

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    private double dx;
    private double dy;
    private boolean hasMoved = false;

    public double getGravity() {
        return gravity;
    }

    private double gravity = 0.3;

    public double getDyy() {
        return dyy;
    }

    public void setDyy(double dyy) {
        this.dyy = dyy;
    }

    private double dyy;
    private double tAir;


    private boolean mouseOutside = false;
    private boolean entered = false;
    private boolean exited = false;
    private double rotation = 3;
    private boolean frozen = false;


    private boolean slowed = false;
    public boolean isSlowed() {
        return slowed;
    }
    public void setSlowed(boolean x) {
        slowed = x;
    }
    public void halfSpeed() {
        dx /= 10;
        dy /= 10;
        speed /= 10;
    }
    public void doubleSpeed() {
        dx *= 10;
        dy *= 10;
        speed *= 10;
    }
    public boolean isMouseOutside() {
        return mouseOutside;
    }

    public boolean isEntered() {
        return entered;
    }

    public boolean isExited() {
        return exited;
    }

    public void setMouseOutside(boolean a) {
        mouseOutside = a;
    }

    public void setEntered(boolean a) {
        entered = a;
    }

    public void setExited(boolean a) {
        exited = a;
    }

    public ThrownObject(double startX, double endX, double maxY) {
        this.startX = startX;
        this.endX = endX;
        double yChange = maxY - 700;
        double xChange = endX - startX;
        dy = -Math.sqrt(-2 * gravity * yChange);
        double time = -2 * dy/ gravity;
        dx = (xChange) / time;

    }

    public void setWhole(Image image) {
        wholeImage = image;
    }

    public double getStartX() {
        return startX;
    }

    public double getEndX() {
        return endX;
    }

    public void setStartX(double x) {
        startX = x;
    }

    public void setEndX(double x ) {
        endX = x;
    }

    public void setMaxHeight(double h) {
        maxHeight = h;
    }

    public double getMaxHeight() {
        return maxHeight;
    }

    public void setSpeed(double x) { speed =x; }
    public double getSpeed() {return speed;}

    public void act(long now) {
        if(!frozen)
            setRotate(getRotate() + rotation);
        if(rotation >0.5)
            rotation = rotation - 0.01;

        dy = dy + gravity;
        move(dx, dy);

        if (mouseOutside && entered && exited) {

            cut();


        }
    }
    public void setGravity(double a){
        gravity = a;
    }

    public void cut() {


    }
    public void frozen(){
        frozen = true;
    }

    public void bgShake(){
        ((TestFruitWorld)(getWorld())).getBackground1().setSwitching(true);
    }

}
