import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
//Class for aliens in game
//Eric Wang, Elliot Ngo, 6/14/2022

public class Alien extends Rectangle{

  public static int yspeed; //move speed in vertical direction
  public static int xspeed; //move speed in horizontal direction
  public static final int ALIEN_WIDTH = 27; //width of Alien
  public static final int ALIEN_HEIGHT = 20; //height of alien
  public boolean dead = false; //whether alien has been killed
  public Image img; //image of alien
  
  //constructor creates the Alien
  public Alien(int x, int y) throws IOException{
    super(x, y, ALIEN_WIDTH, ALIEN_HEIGHT);
    yspeed = 0;
    xspeed = 0;
    img = ImageIO.read(new File("alien.png"));
  }

  //update the position of the ball
  public void move(){
    x = x + xspeed;
    y = y + yspeed;
  }
  
  //make the alien shoot a bullet at the player
  public void shoot(int i, Projectile[] alienBullets) {
    alienBullets[i].x = this.x + 13;
    alienBullets[i].y = this.y + 20;
    alienBullets[i].setYVelocity(8);
  }

  //draw the alien to the screen
  public void draw(Graphics g) {
    g.drawImage(img, x, y, 27, 20, null);
  }

}
