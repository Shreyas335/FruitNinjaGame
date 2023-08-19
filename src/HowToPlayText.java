import com.sun.tools.javac.Main;
import engine.Actor;
import javafx.scene.image.Image;

public class HowToPlayText extends Actor {
    private Actor paper;
    private boolean makeVisible = false;
    public HowToPlayText(){

        setImage(new Image("images/instructions.png"));
        setFitWidth(800);
        setFitHeight(600);
        setOpacity(0);

    }
    public void setMakeVisible(boolean a){
        makeVisible = a;
        if(a)
            ((MainMenu)(getWorld())).getBackButton().appear();
        else
            ((MainMenu)(getWorld())).getBackButton().disappear();
    }



    @Override
    public void act(long now) {
        if(paper == null)
            paper = ((MainMenu)(getWorld())).getPaper();
        setX(paper.getX() + paper.getWidth() / 2 - this.getWidth() / 2);
        setY(paper.getY() + paper.getHeight() / 2 - this.getHeight() / 2);
        if(makeVisible && getOpacity() < 1){
            setOpacity(getOpacity() + 0.01);
        }
        if(!makeVisible && getOpacity() > 0){
            setOpacity(getOpacity() - 0.01);
        }
    }
}
