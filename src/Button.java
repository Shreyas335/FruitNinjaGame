import engine.Actor;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public abstract class Button extends Actor {

    public Button(Image a){
        setImage(a);
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                onClick();
            }
        });

    }
    public void setWidth(double width){
        setFitWidth(width);
        setFitHeight(width * 0.2993630573248408);
    }

    public abstract void act(long now);

    public abstract void onClick();

    public void setText(String text){
        Label label = new Label(text);
        //label.setFont(Font.)
        label.setMaxWidth(getWidth() / 1.5);
        label.setMaxHeight(getHeight() / 1.5);
        label.setLayoutX(getX() + getWidth() / 2 - label.getWidth() / 2);
        label.setLayoutY(getY() + getHeight() / 2 - label.getHeight() / 2);
        getWorld().getChildren().add(label);
        System.out.println("done");
    }
}
