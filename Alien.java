/* Alien class defines behaviours for the Alien

Child of Rectangle because that makes it easy to draw and check for collision

In 2D GUI, basically everything is a rectangle even if it doesn't look like it!
*/ 
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Alien extends Rectangle{

  public static int yspeed; //move speed in vertical direction
  public static int xspeed; //move speed in horizontal direction
  public static final int ALIEN_WIDTH = 27; //size of Alien
  public static final int ALIEN_HEIGHT = 20;
  public boolean dead = false;
  public Image img;
  
  //constructor creates the Alien
  public Alien(int x, int y) throws IOException{
    super(x, y, ALIEN_WIDTH, ALIEN_HEIGHT);
    yspeed = 0;
    xspeed = 0;
    img = ImageIO.read(new File("C:\\Users\\334799608\\Downloads\\alien.png"));
  }

  //update the position of the ball (called from GamePanel frequently)
  public void move(){
    x = x + xspeed;
    y = y + yspeed;
  }

  //draw the obstacle to the screen (called from GamePanel frequently)
  //Replace ball with image of alien later?
  public void draw(Graphics g) {
    g.setColor(Color.white); //set the colour
    //g.fillOval(x, y, ALIEN_WIDTH, ALIEN_HEIGHT); //draw with given position and dimensions
    g.drawImage(img, x, y, 27, 20, null);
  }

}
