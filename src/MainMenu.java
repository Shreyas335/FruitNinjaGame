import com.sun.tools.javac.Main;
import engine.World;
import javafx.scene.image.Image;

public class MainMenu extends World {
    private StartGameButton button;
    private Paper paper;
    private HowToPlayText text;
    private BackButton back;
    private SaveButton save;
    public MainMenu(){
        setWidth(1200);
        setHeight(700);
    }
    @Override
    public void act(long now) {

    }

    @Override
    public void onDimensionsInitialized() {
        Background1 bg = new Background1();
        bg.setFitWidth(getWidth());
        bg.setFitHeight(getHeight());
        bg.setX(-40);
        bg.setY(-40);
        add(bg);
        bg.begin();

        Logo logo = new Logo();
        add(logo);
        logo.setFitWidth(700);
        logo.setFitHeight(700 * 0.4340369393139842);
        logo.setX(getWidth() / 2 - logo.getWidth() / 2);
        logo.setY(0);


        button = new StartGameButton(new Image("images/start.png"));
        add(button);
        button.setWidth(getWidth() * 0.25);
        button.setX(getWidth() /2 - button.getWidth() / 2);
        button.setY(getHeight() /2 - button.getHeight() / 2);
        /*
        save = new SaveButton(new Image("images/save.png"));
        add(save);
        save.setWidth(getWidth() * 0.25);
        save.setX(getWidth() /2 - button.getWidth() / 2);
        save.setY(getHeight() /2 + button.getHeight() *  3 / 2);

         */

        About about = new About(new Image("images/howToPlay.png"));
        add(about);
        about.setWidth(getWidth() * 0.25);
        about.setX(getWidth() /2 - about.getWidth() / 2);
        about.setY(getHeight() /2 + about.getHeight() / 2);

        Paper paper1 = new Paper();
        add(paper1);
        paper1.setX(getWidth() / 2 - paper1.getWidth() / 2);
        paper1.setY(-getHeight() / 2 - getHeight() / 2 );
        paper = paper1;

        HowToPlayText text = new HowToPlayText();
        add(text);
        this.text = text;

        back = new BackButton(new Image("images/back.png"));
        add(back);
        back.setWidth(getWidth() * 0.15);






    }

    public Paper getPaper(){

        return this.paper;
    }
    public HowToPlayText getText(){
        return text;
    }

    public BackButton getBackButton(){return back;}

    public SaveButton getSaveButton(){
        return save;
    }


}
