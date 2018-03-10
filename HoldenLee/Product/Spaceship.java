import javafx.scene.image.*;
import javafx.scene.shape.*;

public class Spaceship extends Enemy {
  String direction = "";
  Spaceship (double x, double y, double xm, double ym, Image i, int health) {
    super(x, y, xm, ym, i, health);
  }
  //Spaceship enemy: moves in a downwards diagonal, away from the side of the screen it is closest to at initialization
  public void animate() {
    if (direction.equals("")) {
      if (this.x < 250) {
        direction = "right";
      } else {
        direction = "left";
      }
    }
    if (direction.equals("right")) {
      move(0.375, .9);
    } else {
      move(-0.375, .9);
    }
    if (this.y >= 600) {
      this.status = 2;
    }
  }
}
