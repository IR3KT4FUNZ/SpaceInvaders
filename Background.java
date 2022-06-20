import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
//Background for space invaders
//Eric Wang, Elliot Ngo, 6/14/2022

public class Background{
  public Image img; //background image
  public int width; //width of background
  public int height; //height of background
  
  //constructor, use rectangle constructor
  public Background(int x, int y) throws IOException {
	  width = x;
	  height = y;
    img = ImageIO.read(new File("background.jpg"));
  }
  
  //draw background onto screen(make image)
  public void draw(Graphics g) {
    g.drawImage(img, 0, 0, width, height, null);
  }
}
