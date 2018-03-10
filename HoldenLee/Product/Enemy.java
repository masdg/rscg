import javafx.scene.image.*;
import javafx.scene.shape.*;

public class Enemy {
	Image sprite;
	ImageView spriteHandler = new ImageView();
	double x, y;
	Ellipse hitbox = new Ellipse(); //hitbox is abstract; doesn't get added to the scene, just for the purpose of detection
	int health;
	double xMove;
	double yMove;
	int moveNum = 0;
	int status = 0;
	Enemy (double x, double y, double xm, double ym, Image i, int health) {
		this.sprite = i;
		this.spriteHandler.setImage(sprite);
		this.x = x;
		this.spriteHandler.setX(this.x);
		this.y = y;
		this.spriteHandler.setY(this.y);
		this.hitbox.setRadiusX(i.getWidth() / 2);
		this.hitbox.setRadiusY(i.getHeight() / 2);
		this.hitbox.setCenterX(this.x + this.hitbox.getRadiusX());
		this.hitbox.setCenterY(this.y + this.hitbox.getRadiusY());
		this.health = health;
		this.xMove = xm;
		this.yMove = ym;
	}
	Enemy () {

	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public void setX(double x) {
		this.x = x;
		this.getIV().setX(x);
		this.hitbox.setCenterX(x);
	}
	public void setY(double y) {
		this.y = y;
		this.getIV().setY(y);
		this.hitbox.setCenterY(y);
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
		if (health == 0) {
			status = 1;
		}
	}
	public int getStatus () {
		return this.status;
	}
	//basis for animation methods in subclasses
	public void move(double x, double y) {
		this.x += x;
		this.getIV().setX(this.x);
		this.hitbox.setCenterX(this.x);
		this.y += y;
		this.getIV().setY(this.y);
		this.hitbox.setCenterY(this.y);
	}
}
