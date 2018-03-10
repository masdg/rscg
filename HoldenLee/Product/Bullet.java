import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
public class Bullet {
	double x, y;
	double xDirection, yDirection;
	Ellipse hitbox = new Ellipse();
	Image sprite = new Image("file:Bullet.png");
	ImageView spriteHandler = new ImageView(sprite);
	Bullet () {
		this.spriteHandler.setX(-50);
		this.spriteHandler.setY(-50);
	}
	Bullet (double x, double y, double xDirection, double yDirection) {
		this.x = x;
		this.y = y;
		this.xDirection = xDirection;
		this.yDirection = yDirection;
		this.spriteHandler.setX(x);
		this.spriteHandler.setY(y);
		this.hitbox.setRadiusX(sprite.getWidth() / 2);
		this.hitbox.setRadiusY(sprite.getHeight() / 2);
		this.hitbox.setCenterX(this.x + this.hitbox.getRadiusX());
		this.hitbox.setCenterY(this.y + this.hitbox.getRadiusY());
	}
	public void animate() {
		this.x += xDirection;
		this.y += yDirection;
		this.spriteHandler.setX(x);
		this.spriteHandler.setY(y);
		this.hitbox.setCenterX(this.x + this.hitbox.getRadiusX());
		this.hitbox.setCenterY(this.y + this.hitbox.getRadiusY());
	}
	public ImageView getIV() {
		return this.spriteHandler;
	}
}
