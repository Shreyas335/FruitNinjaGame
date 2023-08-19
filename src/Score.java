import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Score extends Text {
    private long value;

    public Score(){
        value = 0;
        try {
            setFont(Font.loadFont(new FileInputStream(new File("src/Font/Osake.otf")), 25));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        setFill(Color.WHITE);
        updateDisplay();
        setX(50);
        setY(120);
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
        updateDisplay();
    }

    void updateDisplay(){
        this.setText("Score: " + value);
    }
}