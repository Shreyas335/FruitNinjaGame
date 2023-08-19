import javafx.scene.image.Image;

public class About extends Button {
    public About(Image a) {
        super(a);


    }

    @Override
    public void act(long now) {

    }

    @Override
    public void onClick() {
        System.out.println(((MainMenu)(getWorld())).getPaper().hashCode());
        ((MainMenu)(getWorld())).getPaper().appear();
        ((MainMenu)(getWorld())).getText().setMakeVisible(true);
    }
}