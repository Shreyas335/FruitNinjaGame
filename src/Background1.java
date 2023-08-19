import engine.Actor;
import javafx.scene.image.Image;

public class Background1 extends Actor {
    private int count;
    private boolean switching;
    private Image x;

    public Background1(){

        x = new Image("images/background.png");
        setImage(x);

        switching = false;
        count = 0;
    }
    @Override
    public void act(long now) {
        //setRotate((Math.random() * 5) - 2.5);
        //count = 0;
        //switching = true;
        if (switching) {
            setRotate((Math.random() * 5) - 2.5);
            count++;
        }

        if (count == 10) {
            count = 0;
            switching = false;
            setRotate(0);
        }
    }


    public boolean isSwitching() {
        return switching;
    }

    public void setSwitching(boolean switching) {
        this.switching = switching;
    }



    public void begin(){

        this.setFitHeight(getWorld().getHeight()* 1.25) ;
        this.setFitWidth(getWorld().getWidth()* 1.25);
    }
}