import javafx.scene.image.*;
import javafx.scene.shape.*;

public class Zigzag extends Enemy {
  boolean goLeft = true;
  Zigzag (double x, double y, double xm, double ym, Image i, int health) {
    super(x, y, xm, ym, i, health);
  }
  //Zigzag enemy: moves in a zigzag pattern down the screen
  public void animate() {
    if (this.x < 30) {
      goLeft = false;
    } else if (this.x > 470) {
      goLeft = true;
    }
    if (goLeft) {
      move(-1.5, 0.2);
    } else {
      move(1.5, 0.2);
    }
    if (this.y >= 600) {
      this.status = 2;
    }
  }
}
