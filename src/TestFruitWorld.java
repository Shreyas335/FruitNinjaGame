
import com.sun.tools.javac.Main;
import engine.*;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestFruitWorld extends World{
    private Score score;
    private int gamemode;
    private boolean canCut = false;
    private Point2D prevPoint;
    private Point2D currPoint;
    private ArrayList<Line> list = new ArrayList<>();
    private int counter = 0;
    private AnimationTimer timer;
    private Long oldTime;
    private int minutes = 3;
    private int seconds = 0;
    private Text time;
    private int timesInc = 0;

    private boolean shake = false;
    private int counter1 = 0;
    private Background1 background;
    private boolean timerInit = false;
    private int strikes = 0;
    private MultiPlayerCursor cursor;
    private int delay = 2000000000;
    private boolean play = true;
    private boolean zeroChecked = false;
    private int mpFruitDelay = 30;
    private int bombDelay = 100;
    private BackToMenuButton toMenuButton;
    private AnimationTimer timer1;
    private boolean canPom = true;
    private Text strikeText;
    private x2 rep;
    private boolean fade = false;
    private MainMenu main;





    public int getFruitsCut() {
        return fruitsCut;
    }

    public void setFruitsCut(int fruitsCut) {
        this.fruitsCut = fruitsCut;
    }

    private int fruitsCut = 0;

    public boolean getShake(){return shake;}
    public void setShake(boolean Shake){shake = Shake;}


    public TestFruitWorld(int gamemode, MainMenu main){
        this.main = main;
        this.gamemode = gamemode;//1 = timed, 2 = infinite, 3 = mp
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                if(oldTime == null){
                    oldTime = l;
                }
                if(l - oldTime >= 1000000000){

                    updateTime();
                    oldTime = l;
                }

            }
        };

        setWidth(1200);
        setHeight(700);

    }

    public Point2D getCurrPoint(){
        return currPoint;
    }
    public void setCurrPoint(Point2D a){
        currPoint = a;
    }
    public boolean slow = false;
    public void setSlow(boolean x) {
        slow = x;
    }
    public Point2D getPrevPoint(){
        return prevPoint;
    }
    public void setPrevPoint(Point2D a){
        prevPoint = a;
    }

    public void setPom(boolean pom, Pome a) {
        if(pom) {
            timer1 = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    if (oldTime == null) {
                        oldTime = l;
                    }
                    if (l - oldTime >= 3000000000L) {
                        pomeOver(a);
                    }
                }
            };
            timer1.start();
        }
    }


    @Override
    public void onDimensionsInitialized() {
        background = new Background1();
        background.setX(-40);
        background.setY(-40);
        add(background);
        background.begin();



        score = new Score();
        getChildren().add(score);





        setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                List<ThrownObject> arr = getObjects(ThrownObject.class);
                List<ThrownObject> atMouse = getObjectsAt(mouseEvent.getX(), mouseEvent.getY(), ThrownObject.class);
                for (ThrownObject to : arr) {
                    if (!atMouse.contains(to)) {
                        if (!to.isEntered()) {
                            to.setMouseOutside(true);
                        } else {
                            if (to.isMouseOutside()){
                                to.setExited(true);
                            } else {
                                to.setEntered(false);
                            }
                        }
                    } else {
                        to.setEntered(true);
                    }
                }



                if(counter == 1) {
                    if (getCurrPoint() == null) {
                        setPrevPoint(new Point2D(mouseEvent.getX(), mouseEvent.getY()));
                        setCurrPoint(new Point2D(mouseEvent.getX(), mouseEvent.getY()));
                    } else {
                        setPrevPoint(getCurrPoint());
                        setCurrPoint(new Point2D(mouseEvent.getX(), mouseEvent.getY()));
                    }
                    knifeMoving();
                    counter = 0;
                }else{
                    counter++;
                }


            }
        });




    }

    double prev = 0;
    double slowTimer = 0;
    double timmy = 0;
    @Override
    public void act(long now) {
        if(fade){
            rep.setOpacity(rep.getOpacity() - 0.01);
            if(getOpacity() < 0.1){
                remove(rep);
                fade = false;
            }
        }
        timmy++;



        //knife
        if (counter == 1) {
            for (Line line : list) {
                line.setStrokeWidth(line.getStrokeWidth() / 1.2);
                if (line.getStrokeWidth() < 0.2) {
                    getChildren().remove(line);
                    line = null;
                }
            }
            counter = 0;
        } else {
            counter++;
        }


        if (slow) {
            slowTimer = now;
            if (slowTimer + 2000000000 < now) setSlow(false);
            for (ThrownObject x : getObjects(ThrownObject.class)) {
                if (!x.isSlowed()) {
                    x.setSlowed(true);
                    x.halfSpeed();
                }
            }
        } else {

            for (ThrownObject x : getObjects(ThrownObject.class)) {
                if (x.isSlowed()) {
                    x.setSlowed(false);
                    x.doubleSpeed();
                }
            }
        }


        if(gamemode == 1 && play) {
            if(time == null){
                time = new Text("");

                try {
                    File font = new File("src/Font/Osake.otf");
                    Font osake = Font.loadFont(new FileInputStream(font), 70);
                    time.setFont(osake);

                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }



                time.setFill(Color.WHITE);
                time.setX(50);
                time.setY(70);
                getChildren().add(time);
            }
            if(!timerInit) {
                timer.start();
                timerInit = true;
            }

            if (now > prev + 2000000000 ) {

                prev = now;
                Random r = new Random();
                int numSpawn = r.nextInt(10) + 1;
                for (int i = 0; i < numSpawn; i++) {
                    int obj = r.nextInt(250);
                    double startX = r.nextDouble(getWidth() * 7 / 8) + getWidth() /8;
                    double endX = r.nextDouble(getWidth() / 1.5) + getWidth() / 8;
                    double endY = r.nextDouble(getHeight() / 3) + 200;
                     if (obj < 40) {

                        ThrownObject f = new Watermelon(startX, endX, endY);
                        add(f);
                        f.setX(f.getStartX());
                        f.setY(getHeight());
                        f.setImage(new Image("images/watermelon.png"));
                    } else if (obj < 80) {
                        ThrownObject f = new Kiwi(startX, endX, endY);
                        add(f);
                        f.setX(f.getStartX());
                        f.setY(getHeight());
                        f.setImage(new Image("images/kiwi.png"));
                    } else if (obj < 120) {
                        ThrownObject f = new Banana(startX, endX, endY);
                        add(f);
                        f.setX(f.getStartX());
                        f.setY(getHeight());
                        f.setImage(new Image("images/banana.png"));
                    } else if (obj < 160) {
                        ThrownObject f = new Apple(startX, endX, endY);
                        add(f);
                        f.setX(f.getStartX());
                        f.setY(getHeight());
                        f.setImage(new Image("images/apple.png"));
                    } else if (obj < 200) {
                        ThrownObject f = new Orange(startX, endX, endY);
                        add(f);
                        f.setX(f.getStartX());
                        f.setY(getHeight());
                        f.setImage(new Image("images/orange.png"));
                    }  else {

                        int temp = r.nextInt(2);
                        if (temp == 0) {
                            ThrownObject f = new x2(startX, endX, endY, false);
                            add(f);
                            f.setX(f.getStartX());
                            f.setY(getHeight());
                            f.setImage(new Image("images/x2.png"));
                        } else {
                            ThrownObject f = new Freeze(startX, endX, endY);
                            add(f);
                            f.setX(f.getStartX());
                            f.setY(getHeight());
                            f.setImage(new Image("images/Freeze.png"));
                        }

                    }

                }
            }
            if(time.getText().equals("0:0")){
                System.out.println("over cause time");
                play = false;
                timer.stop();
                results();
            }
            if(score.getValue() > 0){
                zeroChecked = true;
            }
            if(score.getValue() < 0 && zeroChecked){
                play = false;
                timer.stop();

            }
        }else if(gamemode == 2 && play ){
            if(strikeText == null){
                strikeText = new Text("Strikes: " + strikes);
                try {
                    File font = new File("src/Font/Osake.otf");
                    Font osake = Font.loadFont(new FileInputStream(font), 40);
                    strikeText.setFont(osake);


                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                strikeText.setX(50);
                strikeText.setY(60);
                getChildren().add(strikeText);

            }
            if (now > prev + delay ) {

                prev = now;
                Random r = new Random();
                int numSpawn = r.nextInt(4) + 1;
                for (int i = 0; i < numSpawn; i++) {
                    int obj = r.nextInt(250);
                    double startX = r.nextDouble(getWidth() * 7 / 8) + getWidth() /8;
                    double endX = r.nextDouble(getWidth() / 1.5) + getWidth() / 8;
                    double endY = r.nextDouble(getHeight() / 3) + 200;
                    if (obj < 250 && canPom) {
                        ThrownObject f = new Pome(startX, endX, endY);
                        canPom = false;

                        add(f);
                        f.setX(startX);
                        f.setY(getHeight());
                    } else if (obj < 40) {

                        ThrownObject f = new Watermelon(startX, endX, endY);
                        add(f);
                        f.setX(startX);
                        f.setY(getHeight());
                        f.setImage(new Image("images/watermelon.png"));
                    } else if (obj < 80) {
                        ThrownObject f = new Kiwi(startX, endX, endY);
                        add(f);
                        f.setX(startX);
                        f.setY(getHeight());
                        f.setImage(new Image("images/kiwi.png"));
                    } else if (obj < 120) {
                        ThrownObject f = new Banana(startX, endX, endY);
                        add(f);
                        f.setX(startX);
                        f.setY(getHeight());
                        f.setImage(new Image("images/banana.png"));
                    } else if (obj < 160) {
                        ThrownObject f = new Apple(startX, endX, endY);
                        add(f);
                        f.setX(startX);
                        f.setY(getHeight());
                        f.setImage(new Image("images/apple.png"));
                    } else if (obj < 200) {
                        ThrownObject f = new Orange(startX, endX, endY);
                        add(f);
                        f.setX(startX);
                        f.setY(getHeight());
                        f.setImage(new Image("images/orange.png"));
                    } else if (obj < 210) {
                        ThrownObject f = new Bomb(startX, endX, endY);
                        add(f);
                        f.setX(startX);
                        f.setY(getHeight());
                        f.setImage(new Image("images/bomb.png"));
                    } else {

                        int temp = r.nextInt(2);
                        if (temp == 0) {
                            ThrownObject f = new x2(startX, endX, endY, false);
                            add(f);
                            f.setX(f.getStartX());
                            f.setY(getHeight());
                            f.setImage(new Image("images/x2.png"));
                        } else {
                            ThrownObject f = new Freeze(startX, endX, endY);
                            add(f);
                            f.setX(f.getStartX());
                            f.setY(getHeight());
                            f.setImage(new Image("images/Freeze.png"));

                            double sX = r.nextDouble(getWidth() * 7 / 8) + getWidth() /8;
                            double eX = r.nextDouble(getWidth() / 1.5) + getWidth() / 8;
                            double eY = r.nextDouble(getHeight() / 3) + 200;
                            ThrownObject a = new Orange(sX, eX, eY);
                            add(f);
                            f.setX(sX);
                            f.setY(getHeight());
                            f.setImage(new Image("images/orange.png"));

                            double sX1 = r.nextDouble(getWidth() * 7 / 8) + getWidth() /8;
                            double eX1 = r.nextDouble(getWidth() / 1.5) + getWidth() / 8;
                            double eY1= r.nextDouble(getHeight() / 3) + 200;
                            ThrownObject b = new Orange(sX1, eX1, eY1);
                            add(f);
                            f.setX(sX1);
                            f.setY(getHeight());
                            f.setImage(new Image("images/orange.png"));
                        }

                    }

                }
            }

            if(getFruitsCut() >= 30 && timesInc == 0){
                timesInc = 1;
                delay = delay /2;
            }else if(getFruitsCut() >= 60 && timesInc == 1){
                timesInc = 2;
                delay = delay /2;
            }else if(getFruitsCut() >= 90 && timesInc == 2){
                timesInc = 3;
                delay = delay /2;

            }
            for(Fruit a : getObjects(Fruit.class)){
                if(a.getY() >= getHeight() * 1.1){
                    strikes++;
                    System.out.println(a.getClass());
                    System.out.println("Start: " + a.getStartX());
                    System.out.println("End: " + a.getEndX());
                    System.out.println("Actual X: " + a.getX());
                    System.out.println("Actual Y: " + a.getY());
                    System.out.println("Strikes: " + strikes);
                    strikeText.setText("Strikes: " + strikes);
                    remove(a);

                }
            }
            if(strikes == 3){
                play = false;
                results();
            }
        }else if(gamemode == 3){
            if(prevPoint != null && currPoint != null) {
                if (getObjectsAt(getPrevPoint().getX(), getPrevPoint().getY(), BackToMenuButton.class).size() > 0 || getObjectsAt(getCurrPoint().getX(), getCurrPoint().getY(), BackToMenuButton.class).size() > 0 && toMenuButton != null) {
                    toMenuButton.requestFocus();
                }
            }
            if(mpFruitDelay < 30)
                mpFruitDelay++;
            if(bombDelay < 100)
                bombDelay++;
            if(cursor == null) {
                toMenuButton = new BackToMenuButton(new Image("images/back.png"));
                toMenuButton.setWidth(getWidth() * 0.25);
                toMenuButton.setX(getWidth() - toMenuButton.getWidth());
                toMenuButton.setY(0);
                add(toMenuButton);
                cursor = new MultiPlayerCursor();
                cursor.setX(getWidth() / 2 - cursor.getWidth() / 2);
                cursor.setY(getHeight() - cursor.getHeight());
                add(cursor);
            }
            if(isKeyPressed(KeyCode.A) && cursor.getX() > 0){
                cursor.setX(cursor.getX() - 10);
            }
            if(isKeyPressed(KeyCode.D) && cursor.getX() < getWidth() - cursor.getWidth() ){
                cursor.setX(cursor.getX() + 10);
            }
            if(isKeyPressed(KeyCode.SPACE) && mpFruitDelay >= 30){
                mpFruitDelay = 0;
                Random r = new Random();
                int numSpawn = r.nextInt(4) + 1;
                for (int i = 0; i < numSpawn; i++) {
                    int obj = r.nextInt(250);
                    double endX = r.nextDouble(getWidth() / 1.5) + getWidth() / 8;
                    double endY = r.nextDouble(getHeight() / 3) + 200;
                    if (obj < 4 && canPom) {
                        ThrownObject f = new Pome(cursor.getX(), endX, endY);
                        canPom = false;
                        add(f);
                        f.setX(f.getStartX());
                        f.setY(getHeight());
                    } else if (obj < 40) {

                        ThrownObject f = new Watermelon(cursor.getX(), endX, endY);
                        add(f);
                        f.setX(f.getStartX());
                        f.setY(getHeight());
                        f.setImage(new Image("images/watermelon.png"));
                    } else if (obj < 80) {
                        ThrownObject f = new Kiwi(cursor.getX(), endX, endY);
                        add(f);
                        f.setX(f.getStartX());
                        f.setY(getHeight());
                        f.setImage(new Image("images/kiwi.png"));
                    } else if (obj < 120) {
                        ThrownObject f = new Banana(cursor.getX(), endX, endY);
                        add(f);
                        f.setX(f.getStartX());
                        f.setY(getHeight());
                        f.setImage(new Image("images/banana.png"));
                    } else if (obj < 160) {
                        ThrownObject f = new Apple(cursor.getX(), endX, endY);
                        add(f);
                        f.setX(f.getStartX());
                        f.setY(getHeight());
                        f.setImage(new Image("images/apple.png"));
                    } else if (obj < 200) {
                        ThrownObject f = new Orange(cursor.getX(), endX, endY);
                        add(f);
                        f.setX(f.getStartX());
                        f.setY(getHeight());
                        f.setImage(new Image("images/orange.png"));
                    }  else {

                        int temp = r.nextInt(2);
                        if (temp == 0) {
                            ThrownObject f = new x2(cursor.getX(), endX, endY, false);
                            add(f);
                            f.setX(f.getStartX());
                            f.setY(getHeight());
                            f.setImage(new Image("images/x2.png"));
                        } else {
                            ThrownObject f = new Freeze(cursor.getX(), endX, endY);
                            add(f);
                            f.setX(f.getStartX());
                            f.setY(getHeight());
                            f.setImage(new Image("images/Freeze.png"));
                        }

                    }
                }
            }

            if(isKeyPressed(KeyCode.SHIFT)&& bombDelay >= 100){
                bombDelay = 0;
                Random r = new Random();
                int numSpawn = r.nextInt(2) + 1;
                double endX = r.nextDouble(getWidth() / 1.5) + getWidth() / 8;
                double endY = r.nextDouble(getHeight() * 2 / 4) + 200;
                ThrownObject f = new Bomb(cursor.getX(), endX, endY);
                add(f);
                f.setX(f.getStartX());
                f.setY(getHeight());
                f.setImage(new Image("images/bomb.png"));
            }
        }





    }
    public void knifeMoving(){
        if(currPoint != null && prevPoint != null){
            Line line = new Line(currPoint.getX(), currPoint.getY(), prevPoint.getX(), prevPoint.getY());
            line.setStroke(Color.WHITE);
            line.setStrokeWidth(15);
            getChildren().add(line);
            list.add(line);
            Glow glo = new Glow();
            glo.setLevel(10);
            Lighting light = new Lighting();
            light.setDiffuseConstant(100);
            light.setSurfaceScale(22);
            line.setEffect(light);
            //line.setEffect(glo);
            Bloom bb = new Bloom();
            bb.setThreshold(1000);
            line.setEffect(bb);
            GaussianBlur blur = new GaussianBlur();
            //blur.setRadius();
            //line.setEffect(blur);
        }
    }

    public Score getScore(){
        return score;
    }
    public Background1 getBackground1(){
        return background;
    }

    public void updateTime(){
        if(seconds != 0){
            seconds--;
        }else{
            seconds = 59;
            minutes--;
        }
        if(minutes == 0 && seconds == 0){
            timer.stop();
        }
        if(seconds < 10){
            time.setText(minutes + ":0" + seconds);
        }
        if (seconds >= 10) {
            time.setText(minutes + ":" + seconds);
        } else {
            time.setText(minutes + ":0" + seconds);
        }

    }

    public void results(){
        Text scoreLabel = new Text("Final Score: " + score.getValue());
        Text fruitsCutLabel = new Text("Fruits Cut: " + getFruitsCut());
        try {
            File font = new File("src/Font/Osake.otf");
            Font osake = Font.loadFont(new FileInputStream(font), 70);
            scoreLabel.setFont(osake);
            fruitsCutLabel.setFont(osake);


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        scoreLabel.setX(350);
        scoreLabel.setY(400);
        fruitsCutLabel.setX(350);
        fruitsCutLabel.setY(450);
        getChildren().add(scoreLabel);
        getChildren().add(fruitsCutLabel);


        if(gamemode == 1){
            Text timeLabel = new Text("Time Left: " + time.getText());
            try {
                File font = new File("src/Font/Osake.otf");
                Font osake = Font.loadFont(new FileInputStream(font), 70);
                timeLabel.setFont(osake);

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
            timeLabel.setX(350);
            timeLabel.setY(350);
            getChildren().add(timeLabel);




        }

        if(gamemode != 3){
            BackToMenuButton toMenuButton = new BackToMenuButton(new Image("images/back.png"));
            toMenuButton.setWidth(getWidth() * 0.25);
            toMenuButton.setX(getWidth() - toMenuButton.getWidth());
            toMenuButton.setY(getHeight() - toMenuButton.getHeight());
            toMenuButton.requestFocus();
            add(toMenuButton);
        }
        if(gamemode == 2 ){
            if(main.getSaveButton().getFile() != null){
                System.out.println("hello");
                try {
                    FileWriter fw = new FileWriter(main.getSaveButton().getFile());
                    fw.write(score.getValue() + "\n");
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public void pomeOver(Pome a){
        timer1.stop();
        CutFruit rFruit = new CutFruit(a.getX(), a.getY(), 2, 15, new Image("images/pom1.png"));
        CutFruit lFruit = new CutFruit(a.getX(), a.getY(), -2, 15, new Image("images/pom1.png"));
        //FruitSplat(double x, double y, Image image)
        FruitSplat splat = new FruitSplat(a.getX(), a.getY(), new Image("images/watermelon_Splat.png"));

        remove(a);
        Media media = new Media(getClass().getResource("Sound/pome-burst.wav").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        add(rFruit);
        add(lFruit);
        add(splat);


        //public CutFruit(double x, double y, int dx, int dy, Image a)

    }

    public void draw2x(){
        rep = new x2(50, 50, 300, true);
        rep.setDx(0);
        rep.setDy(0);
        rep.setGravity(0);
        rep.setX(50);
        rep.setY(170);
        add(rep);
        fade = true;
    }



}
