import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class Background{
  public Image img;
  public int width;
  public int height;
  
  //constructor, use rectangle constructor
  public Background(int x, int y) throws IOException {
	  width = x;
	  height = y;
    img = ImageIO.read(new File("C:\\Users\\334799608\\Downloads\\background.jpg"));
  }
  
  //draw spaceship onto screen(make image)
  public void draw(Graphics g) {
    g.setColor(Color.white);
    //g.fillRect(x, y, WIDTH, HEIGHT);
    g.drawImage(img, 0, 0, width, height, null);
  }
}
