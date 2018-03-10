import javafx.scene.image.*;
import javafx.scene.shape.*;
public class Player {
	Image sprite;
	ImageView spriteHandler = new ImageView();
	double x, y;
	Ellipse hitbox = new Ellipse(); //hitbox is abstract; doesn't get added to the scene, just for the purpose of detection
	int health;
	Player (double x, double y, Image i) {
		this.sprite = i;
		this.spriteHandler.setImage(i);
		this.x = x;
		this.spriteHandler.setX(this.x);
		this.y = y;
		this.spriteHandler.setY(this.y);
		this.hitbox.setRadiusX(i.getWidth() / 2);
		this.hitbox.setRadiusY(i.getHeight() / 2);
		this.hitbox.setCenterX(this.x + this.hitbox.getRadiusX());
		this.hitbox.setCenterY(this.y + this.hitbox.getRadiusY());
	}
	Player () {

	}

	public void moveX (double x) {
		double temp = this.x + x;
		if (temp + this.sprite.getWidth() / 2 + 3> 500 || temp < 0) {

		} else {
			this.x += x;
			this.spriteHandler.setX(this.x);
			this.hitbox.setCenterX(this.x + this.hitbox.getRadiusX());
		}

	}
	public void moveY (double y) {
		double temp = this.y - y;
		if (temp + this.sprite.getHeight() / 2 + 3> 600 || temp < 0) {

		} else {
			this.y -= y;
			this.spriteHandler.setY(this.y);
			this.hitbox.setCenterY(this.y + this.hitbox.getRadiusY());
		}
	}
	public String location () {
		return String.valueOf(this.x) + ", " + String.valueOf(this.y);
	}
	public ImageView getIV () {
		return this.spriteHandler;
	}
	public Ellipse getHitBox () {
		return this.hitbox;
	}
	public void hit () {
		this.health -= 1;
	}
	public void hit(int i) {
		this.health -= i;
	}
}
