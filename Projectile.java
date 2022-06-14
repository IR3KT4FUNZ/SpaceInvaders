import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Projectile extends Rectangle {

  public int xVelocity; //horizontal velocity of projectile
  public int yVelocity; //vertical velocity of projectile
  public static final int LENGTH = 10; //dimension of square projectile
  public static final int WIDTH = 25; //dimension of square projectile
  public Image bullet3;
  public Image bullet4;
  
  //constructor creates projectile as a 2x2 square with velocity
  public Projectile(int x, int y, int xVel, int yVel) throws IOException {
    super(x, y, LENGTH, WIDTH);
    xVelocity = xVel;
    yVelocity = yVel;
    bullet3 = ImageIO.read(new File("bullet3.png"));
    bullet4 = ImageIO.read(new File("bullet4.png"));
  }
  
  //helper method
  public void setYVelocity(int yVel) {
    yVelocity = yVel;
  }
  
  //helper method
  public void setXVelocity(int xVel) {
    xVelocity = xVel;
  }
  
  //move the projectile
  public void move() {
    x = x + xVelocity;
    y = y + yVelocity;
  }
  
  //draw the projectile onto the screen
  public void draw(Graphics g) {
    g.setColor(Color.white);
    if (((int) (y / 40)) % 2 == 0) {
      g.drawImage(bullet3, x, y, 10, 25, null);
    } else {
      g.drawImage(bullet4, x, y, 10, 25, null);
    }    
  }
} 
