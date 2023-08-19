import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Game extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        BorderPane layout = new BorderPane();

        //Add world and set it to center
        BallWorld world = new BallWorld();
        layout.setCenter(world);


        world.start();
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setTitle("Fruit Ninja");
        stage.show();





        world.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.LEFT)
                    world.removeKeyCode(KeyCode.LEFT);
                if (keyEvent.getCode() == KeyCode.RIGHT)
                    world.removeKeyCode(KeyCode.RIGHT);
            }
        });
    }
}
