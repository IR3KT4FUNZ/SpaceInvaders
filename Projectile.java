import java.awt.*;

public class Projectile extends Rectangle {

  public int xVelocity; //horizontal velocity of projectile
  public int yVelocity; //vertical velocity of projectile
  public static final int SIDELENGTH = 4; //dimension of square projectile
  
  //constructor creates projectile as a 2x2 square with velocity
  public Projectile(int x, int y, int xVel, int yVel) {
    super(x, y, SIDELENGTH, SIDELENGTH);
    xVelocity = xVel;
    yVelocity = yVel;
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
    g.fillRect(x, y, SIDELENGTH, SIDELENGTH);
  }
} 
