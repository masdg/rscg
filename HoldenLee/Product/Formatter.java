import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Formatter {
	Formatter() {

	}
	//formats the text in an input Label, then returns it
	public Label textFormat (Label l) {
		Label temp = l;
		temp.setFont(new Font("Verdana", 20));
		temp.setTextFill(Color.YELLOW);
		temp.setAlignment(Pos.CENTER);
		return temp;
	}
}
