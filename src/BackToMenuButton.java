import javafx.scene.image.Image;

public class BackToMenuButton extends Button{
    public BackToMenuButton(Image a) {
        super(a);
    }

    @Override
    public void act(long now) {

    }

    @Override
    public void onClick() {
        MainMenu menu = new MainMenu();
        menu.start();
        TestFruitNinja.getLayout().setCenter(menu);
        menu.setPrefWidth(getWorld().getWidth());
        menu.setPrefHeight(getWorld().getHeight());
    }
}
