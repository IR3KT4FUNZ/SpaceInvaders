/* Alien class defines behaviours for the Alien

Child of Rectangle because that makes it easy to draw and check for collision

In 2D GUI, basically everything is a rectangle even if it doesn't look like it!
*/ 
import java.awt.*;

public class Alien extends Rectangle{

  public static int yspeed; //move speed in vertical direction
  public static int xspeed; //move speed in horizontal direction
  public static final int ALIEN_WIDTH = 27; //size of Alien
  public static final int ALIEN_HEIGHT = 20;
  public boolean dead = false;
  
  //constructor creates the Alien
  public Alien(int x, int y){
    super(x, y, ALIEN_WIDTH, ALIEN_HEIGHT);
    yspeed = 0;
    xspeed = 0;
  }

  //update the position of the ball (called from GamePanel frequently)
  public void move(){
    x = x + xspeed;
    y = y + yspeed;
  }
  
  public void shoot(){
    //calls upon projectile object to move to alien position 
    //also changes projectile y velocity to go down
  }

  //draw the obstacle to the screen (called from GamePanel frequently)
  //Replace ball with image of alien later?
  public void draw(Graphics g){
    g.setColor(Color.white); //set the colour
    g.fillOval(x, y, ALIEN_WIDTH, ALIEN_HEIGHT); //draw with given position and dimensions
  }

}
