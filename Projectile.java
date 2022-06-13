import java.awt.*;

public class Projectile extends Rectangle {

  public int xVelocity; //horizontal velocity of projectile
  public int yVelocity; //vertical velocity of projectile
  public static final int SIDELENGTH = 4; //dimension of square projectile
  public Image bullet1;
  public Image bullet2;
  
  //constructor creates projectile as a 2x2 square with velocity
  public Projectile(int x, int y, int xVel, int yVel) {
    super(x, y, SIDELENGTH, SIDELENGTH);
    xVelocity = xVel;
    yVelocity = yVel;
    bullet1 = ImageIO.read(new File("C:\\Users\\334799608\\Downloads\\bullet1.png"));
    bullet2 = ImageIO.read(new File("C:\\Users\\334799608\\Downloads\\bullet2.png"));
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
      g.drawImage(bullet1, x, y, 27, 20, null);
    } else {
      g.drawImage(bullet2, x, y, 27, 20, null);
    }    
  }
} 
