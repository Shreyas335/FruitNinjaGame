import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TestFruitNinja extends Application {
    private int counter = 0;
    private Media media;
    private MediaPlayer mediaPlayer;
    private static Stage mainStage;

    public static BorderPane getLayout() {
        return layout;
    }

    private static BorderPane layout;
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) {
        mainStage = stage;
        layout = new BorderPane();
        layout.setPrefWidth(1200);
        layout.setPrefHeight(700);



        //Add world and set it to center
        //TestFruitWorld world = new TestFruitWorld();
        //layout.setCenter(world);
        MainMenu menu = new MainMenu();
        menu.start();
        layout.setCenter(menu);
        stage.setResizable(false);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setTitle("Fruit");
        stage.show();

        media = new Media(getClass().getResource("Sound/Fruit-Ninja-Theme-Song.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.05);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();



    }

    public static Stage getMainStage(){
        return mainStage;
    }



}
