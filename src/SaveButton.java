import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

public class SaveButton extends Button{
    private int clickCount = 0;
    private File selectedFile;



    SavesWorld saves;
    public SaveButton(Image a) {
        super(a);
    }

    @Override
    public void act(long now) {

    }

    @Override
    public void onClick() {
        if(clickCount == 0){
            FileChooser fileChooser = new FileChooser();
            selectedFile = fileChooser.showOpenDialog(TestFruitNinja.getMainStage());
            System.out.println(selectedFile.getPath());
            saves = new SavesWorld(selectedFile);
            TestFruitNinja.getLayout().setCenter(saves);
            clickCount = 1;

        }
        if(saves != null){
            TestFruitNinja.getLayout().setCenter(saves);
        }
    }
    public File getFile() {
        return selectedFile;
    }

    public void setSaves(SavesWorld saves) {
        this.saves = saves;
    }
}
