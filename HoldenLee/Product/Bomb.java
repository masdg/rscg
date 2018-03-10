import javafx.scene.image.*;
import javafx.scene.shape.*;

public class Bomb extends Enemy {
  Bomb (double x, double y, double xm, double ym, Image i, int health) {
    super(x, y, xm, ym, i, health);
  }
  //Bomb enemy: moves straight down, relatively quickly
  public void animate () {
    move(0,1);
    if (this.y >= 600) {
      status = 2;
    }
  }
}
