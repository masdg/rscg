import javafx.scene.image.*;
import javafx.scene.shape.*;

public class Asteroid extends Enemy {
  Asteroid (double x, double y, double xm, double ym, Image i, int health) {
    super(x, y, xm, ym, i, health);
  }
  //Asteroid enemy: moves slowly down the screen
  public void animate() {
    move(0,0.75);
    if (this.y >= 600) {
      this.status = 2;
    }
  }
}
