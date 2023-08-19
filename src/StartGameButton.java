import javafx.scene.image.Image;

public class StartGameButton extends Button{
    GameSelectWorld world;
    public StartGameButton(Image a ){
        super(a);
    }

    @Override
    public void act(long now) {

    }

    @Override
    public void onClick() {
        /*
        world = new TestFruitWorld();
        world.start();
        TestFruitNinja.getLayout().setCenter(world);
        world.setPrefWidth(300);
        world.setPrefHeight(500);

         */
        world = new GameSelectWorld((MainMenu) getWorld());
        world.start();
        TestFruitNinja.getLayout().setCenter(world);

        world.setPrefWidth(getWorld().getWidth());

        world.setPrefHeight(getWorld().getHeight());

    }

}
