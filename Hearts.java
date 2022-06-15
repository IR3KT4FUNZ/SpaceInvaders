import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

//Lives display for space invaders
//Eric Wang, Elliot Ngo, 6/14/2022

public class Hearts extends Rectangle {
  public Image img; //display image for hearts
  public static final int WIDTH = 30; //width of image
  public static final int HEIGHT = 30; //height of image
  public static int lives = 3; //number of lvies

  //constructor, initialized \ at x, y coordinates
  public Hearts(int x, int y) throws IOException {
    super(x, y, WIDTH, HEIGHT);
    img = ImageIO.read(new File("ship2.png"));
  }
  
  //display as many lives as the player has
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
