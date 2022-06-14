import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class Hearts extends Rectangle {
  public Image img;
  public static final int WIDTH = 30;
  public static final int HEIGHT = 30;
  public static int lives = 3;

  public Hearts(int x, int y) throws IOException {
    super(x, y, WIDTH, HEIGHT);
    img = ImageIO.read(new File("C:\\Users\\334799608\\Downloads\\ship2.png"));
  }
  
  public void draw(Graphics g) {
    if(lives == 3) {
      g.drawImage(img, 900, 10, WIDTH, HEIGHT, null);
      g.drawImage(img, 930, 10, WIDTH, HEIGHT, null);
      g.drawImage(img, 960, 10, WIDTH, HEIGHT, null);
    }
    if(lives == 2) {
      g.drawImage(img, 930, 10, WIDTH, HEIGHT, null);
      g.drawImage(img, 960, 10, WIDTH, HEIGHT, null);
    }
    if(lives == 1) {
      g.drawImage(img, 960, 10, WIDTH, HEIGHT, null);
    }
  }
}
