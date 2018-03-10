import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import java.util.*;
import javafx.animation.*;
import javafx.util.*;
import javafx.scene.shape.Shape.*;
import java.lang.Math.*;
import javafx.scene.text.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import java.lang.Math.*;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.animation.AnimationTimer;


public class Main extends Application {
	public Level[] levels = new Level[6];
	Group winRoot = new Group();
	public Screen win = new Screen(new Scene(winRoot, 500, 600, Color.DARKBLUE), winRoot);
	Group loseRoot = new Group();
	public Screen lose = new Screen(new Scene(loseRoot, 500, 600, Color.DARKBLUE), loseRoot);
	Formatter formatter = new Formatter();
	double moveInterval = 10;
	Player p = new Player(250, 500, new Image (getClass().getResourceAsStream("Player.png")));
	public Bullet[] bullets = new Bullet[100];
	int k = 0;
	Asteroid[] a = new Asteroid[5];
	Spaceship[] s = new Spaceship[2];
	Zigzag[] z = new Zigzag[2];
	Bomb[] b = new Bomb[3];
	Asteroid[] resetA = new Asteroid[5];
	Spaceship[] resetS = new Spaceship[5];
	Bomb[] resetB = new Bomb[5];
	Zigzag[] resetZ = new Zigzag[5];
	int currentLevel = 0;
	public static void main(String[] args) {
		launch(args);
	}

	public void init () {
		//create enemies, initialize levels, and create bullets (offscreen)
		fillLists();
		formatScenes(levels);
		createEnemies();
		setLevels();
		for (int i = 0; i < bullets.length; i++) {
			bullets[i] = new Bullet(-50, -50, 0, 0);
		}
	}
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Europa");
		stage.setResizable(false);
		stage.show();
		//set up the first level
		levelSetup(currentLevel, stage);
		//animate everything: this is where the player interacts with the game
		new AnimationTimer() {

			public void handle(long now) {
				move(levels[currentLevel].getScene(), levels[currentLevel].getRoot());
				for (int i = 0; i < bullets.length; i++) {
					bullets[i].animate();
				}
				for (int i = 0; i < levels[currentLevel].aList.length; i++) {
					levels[currentLevel].aList[i].animate();
				}
				for (int i = 0; i < levels[currentLevel].sList.length; i++) {
					levels[currentLevel].sList[i].animate();
				}
				for (int i = 0; i < levels[currentLevel].zList.length; i++) {
					levels[currentLevel].zList[i].animate();
				}
				for (int i = 0; i < levels[currentLevel].bList.length; i++) {
					levels[currentLevel].bList[i].animate();
				}
				for (int i = 0; i < bullets.length; i++) {
					for (int j = 0; j < levels[currentLevel].aList.length; j++) {
						if (collision(levels[currentLevel].aList[j].hitbox, bullets[i].hitbox)) {
							levels[currentLevel].remove(bullets[i].getIV());
							bullets[i] = new Bullet();
							levels[currentLevel].aList[j].hit();
							if (levels[currentLevel].aList[j].status == 1) {
								levels[currentLevel].remove(levels[currentLevel].aList[j].getIV());
								levels[currentLevel].aList[j] = resetA[j];
							}
						}
					}
					for (int j = 0; j < levels[currentLevel].sList.length; j++) {
						if (collision(levels[currentLevel].sList[j].hitbox, bullets[i].hitbox)) {
							levels[currentLevel].remove(bullets[i].getIV());
							levels[currentLevel].sList[j].hit();
							if (levels[currentLevel].sList[j].status == 1) {
								levels[currentLevel].remove(levels[currentLevel].sList[j].getIV());
								levels[currentLevel].sList[j] = resetS[j];
							}
						}
					}
					for (int j = 0; j < levels[currentLevel].zList.length; j++) {
						if (collision(levels[currentLevel].zList[j].hitbox, bullets[i].hitbox)) {
							levels[currentLevel].remove(bullets[i].getIV());
							levels[currentLevel].zList[j].hit();
							if (levels[currentLevel].zList[j].status == 1) {
								levels[currentLevel].remove(levels[currentLevel].zList[j].getIV());
								levels[currentLevel].zList[j] = resetZ[j];
							}
						}
					}
					for (int j = 0; j < levels[currentLevel].bList.length; j++) {
						if (collision(levels[currentLevel].bList[j].hitbox, bullets[i].hitbox)) {
							levels[currentLevel].remove(bullets[i].getIV());
							levels[currentLevel].bList[j].hit();
							if (levels[currentLevel].bList[j].status == 1) {
								levels[currentLevel].remove(levels[currentLevel].bList[j].getIV());
								levels[currentLevel].bList[j] = resetB[j];
							}
						}
					}
				}
				int condition = levels[currentLevel].condition();
				if (condition == 1) {
					currentLevel++;
					System.out.println(currentLevel);
					createEnemies();
					levelSetup(currentLevel, stage);
					if (currentLevel == 6) {
						this.stop();
					}
				} else if (condition == 2) {
					stage.setScene(lose.getScene());
				}
			}
		}.start();
	}
	//determines how the player will move, depending on key pressed
	public void move (final Scene scene, Group root) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				String temp = ke.getText();
				switch (temp) {
				case "w":
					p.moveY(moveInterval);
					break;
				case "a":
					p.moveX(moveInterval * -1);
					break;
				case "s":
					p.moveY(moveInterval * -1);
					break;
				case "d":
					p.moveX(moveInterval);
					break;
				case " ":
					Bullet b = new Bullet(p.x + (p.sprite.getWidth() / 2) - 3, p.y, 0, -2);
					bullets[k] = b;
					k++;
					k = k % bullets.length;
					root.getChildren().add(b.getIV());
				default:
					break;
				}
			}

		});
	}
	//for detecting collisions between a bullet/Player and an enemy
	//this method relies on the "equation" method underneath it for calculations
	public boolean collision (Ellipse oval, Ellipse circle) {
		double[] ovalConstants = equation(oval);
		double[] circleConstants = equation(circle);
		double distance = Math.sqrt(Math.pow(ovalConstants[2] - circleConstants[2], 2) + Math.pow(ovalConstants[3] - circleConstants[3],2));
		double angle = Math.acos((ovalConstants[2]-circleConstants[2])/distance);
		double ovalRadius = (ovalConstants[1] * Math.pow(Math.sin(angle), 2) + (ovalConstants[0] * Math.pow(Math.cos(angle), 2)));
		double threshold = (ovalRadius) + circleConstants[0];
		if (distance <= threshold) {
			return true;
		} else {
			return false;
		}
	}
	//needed to return values for "collision" method
	public double[] equation (Ellipse e) {
		double[] constants = new double[4];
		constants[0] = e.getRadiusX();
		constants[1] = e.getRadiusY();
		constants[2] = e.getCenterX();
		constants[3] = e.getCenterY();
		return constants;
	}
	//set up size and color of scenes
	public void formatScenes (Level[] s) {
		for (int i = 0; i < s.length; i++) {
			s[i].setRoot(new Group());
			s[i].setScene(new Scene(s[i].getRoot(), 500, 600, Color.DARKBLUE));
		}
	}
	//create objects for arrays to reference
	public void fillLists () {
		for (int i = 0; i < levels.length; i++) {
			levels[i] = new Level();
		}
	}
	//create each enemy
	public void createEnemies () {
		for (int i = 0; i < a.length; i++) {
			a[i] = new Asteroid (-50, 0, 0, 0, new Image (getClass().getResourceAsStream("Enemy4.png")), 2);
			resetA[i] = a[i];
			if (i < s.length) {
				s[i] = new Spaceship (-50, 0, 0, 0, new Image (getClass().getResourceAsStream("Enemy1.png")), 2);
				resetS[i] = s[i];
			}
			if (i < z.length) {
				z[i] = new Zigzag (-50, 0, 0, 0, new Image (getClass().getResourceAsStream("Enemy2.png")), 3);
				resetZ[i] = z[i];
			}
			if (i < b.length) {
				b[i] = new Bomb (-50, 0, 0, 0, new Image (getClass().getResourceAsStream("Enemy3.png")), 1);
				resetB[i] = b[i];
			}
		}
	}
	//format all levels and add enemies, player
	public void setLevels() {
		Label newL = new Label("You win!");
		newL = formatter.textFormat(newL);
		win.add(newL);
		newL = new Label("You Lose!");
		newL = formatter.textFormat(newL);
		lose.add(newL);
	}
	//tells each level what enemies and how many they contain
	public void levelSetup (int i, Stage stage) {
		if (i == 0) {
			levels[0].set(p, a, s, z, b, "3000", "aaa");
			stage.setScene(levels[0].getScene());
		} else if (i == 1) {
			levels[1].set(p, a, s, z, b, "2200", "assa");
			stage.setScene(levels[1].getScene());
		} else if (i == 2) {
			levels[2].set(p, a, s, z, b, "0010", "z");
			stage.setScene(levels[2].getScene());
		} else if (i == 3) {
			levels[3].set(p, a, s, z, b, "1020", "zaz");
			stage.setScene(levels[3].getScene());
		} else if (i == 4) {
			levels[4].set(p, a, s, z, b, "0003", "bbb");
			stage.setScene(levels[4].getScene());
		} else if (i == 5) {
			levels[5].set(p, a, s, z, b, "2212", "abszsba");
			stage.setScene(levels[5].getScene());
		} else if (i == 6) {
			stage.setScene(win.getScene());
		} else {
			stage.setScene(lose.getScene());
		}
	}
	//used for removing bullets from screen
	public void resetBullets() {
		for (int i = 0; i < bullets.length; i++) {
			bullets[i] = new Bullet();
		}
	}
}
