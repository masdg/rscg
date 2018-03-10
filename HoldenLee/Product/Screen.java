import javafx.scene.*;

public class Screen {
	Scene scene;
	Group root;
	Screen (Scene s, Group g) {
		this.scene = s;
		this.root = g;
	}
	Screen () {

	}
	public Scene getScene () {
		return this.scene;
	}
	public void setScene (Scene s) {
		this.scene = s;
	}
	public Group getRoot () {
		return this.root;
	}
	public void setRoot (Group g) {
		this.root = g;
	}
	public void add (Node n) {
		this.getRoot().getChildren().add(n);
	}
	public void remove (Node n) {
		this.getRoot().getChildren().remove(n);
	}
}
