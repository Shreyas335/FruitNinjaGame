import javafx.scene.image.Image;

public class BackButton extends Button{
    private Paper paper;
    private boolean visible = false;
    public BackButton(Image a) {
        super(a);
        setOpacity(0);

    }
    @Override
    public void onClick(){
        paper.disappear();
        ((MainMenu) (getWorld())).getText().setMakeVisible(false);
    }

    @Override
    public void act(long now){
        if(paper == null) {
            paper = ((MainMenu) (getWorld())).getPaper();
            setX(paper.getX() + paper.getWidth() / 1.6);
        }
        setY(paper.getY() + paper.getHeight() / 1.2);
        if(visible && getOpacity() < 1){
            setOpacity(getOpacity() + 0.005);
        }


        if(!visible && getOpacity() > 0){
            setOpacity(getOpacity() - 0.005);
        }
    }

    public void appear(){ visible = true;}
    public void disappear(){visible = false;}
}
