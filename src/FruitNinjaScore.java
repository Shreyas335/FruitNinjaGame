import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FruitNinjaScore extends Text {
    private int value;

    public FruitNinjaScore(){
        value = 0;
        setFont(new Font(25));
        updateDisplay();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        updateDisplay();
    }

    void updateDisplay(){
        this.setText("Score: " + value);
    }
}