import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class Hearts extends Rectangle {
  public Image img;
  public final WIDTH = 30;
  public final HEIGHT = 30;
  boolean alive;

  public Hearts(int x, int y) throws IOException {
    super(x, y, WIDTH, HEIGHT);
    img = ImageIO.read(new File("C:\\Users\\334799608\\Downloads\\ship2.png");
    alive = true;
  }
  
  public void setDead() {
    alive = false;
  }
  
  public void draw(Graphics g) {
    if (alive) {
      g.drawImage(img, x, y, WIDTH, HEIGHT, null);
    }
  }
}
