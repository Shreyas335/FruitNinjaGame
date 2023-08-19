import engine.World;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class SavesWorld extends World {
    private File file;
    private Background1 background;
    private ArrayList<Integer> infiniteScores;
    public SavesWorld(File f){
        infiniteScores = new ArrayList<>();
        file = f;
        setWidth(1200);
        setHeight(700);
    }
    @Override
    public void act(long now) {

    }

    @Override
    public void onDimensionsInitialized()  {
        background = new Background1();
        background.setX(-40);
        background.setY(-40);
        add(background);
        background.begin();
        BackToMenuButton menuButton = new BackToMenuButton(new Image("images/back.png"));
        menuButton.setX(getWidth() - menuButton.getWidth());
        menuButton.setY(getHeight() - menuButton.getHeight());
        add(menuButton);
        String scores = "";
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNextInt()){
                infiniteScores.add(scan.nextInt());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int gameNum = 1;
        for(int a : infiniteScores){
            scores = scores + "Game " + gameNum + ": " + a + ", \n";
        }
        Text scoreText = new Text(scores);
        scoreText.setX(0);
        scoreText.setY(0);
        getChildren().add(scoreText);


    }

    public void addInfiniteScore(int score){
        infiniteScores.add(score);
    }

}
