import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

//Player controlled ship for space invaders
//Eric, Elliot, 05/31/2022

public class PlayerShip extends Rectangle {
  public static final int HEIGHT = 40; //height of ship
  public static final int WIDTH = 40; //width of ship
  public static final int YCOORD = 700; //constant y coordinate of ship
  public final int SPEED = 10; //speed the ship always moves at
  public int velocity; //speed of ship with direction
  public boolean shooting = false; //whether the user is holding shoot
  public long lastShot = -1000000000;
  public Projectile[] bullets = new Projectile[5];
  public boolean[] bulletUsed = new boolean[5];
  public Image img;
  
  //constructor, use rectangle constructor
  public PlayerShip(int x, int y) throws IOException {
    super(x, y, WIDTH, HEIGHT);
    velocity = 0;
    Arrays.fill(bulletUsed, false);
    for (int i = 0; i < 5; i++) {
      bullets[i] = new Projectile(-10, -10, 0, 0);
    }
    img = ImageIO.read(new File("C:\\Users\\334799608\\Downloads\\ship2.png"));
  }
  
  //check for controls being pressed
  public void keyPressed(KeyEvent e) {
    if (e.getKeyChar() == 'z') {
      velocity = SPEED * -1;
    } 
    if (e.getKeyChar() == 'x') {
      velocity = SPEED;
    }
    if (e.getKeyChar() == 'c') {
      shooting = true;
      shoot();
    }
  } 
  
  //find lowest unused bullet
  public int getBullet() {
    for (int i = 0; i < 5; i++) {
      if (!bulletUsed[i]) {
        return i;
      }
    }
    return 0;
  }
  
  //check for controls being released
  public void keyReleased(KeyEvent e) {
    if (e.getKeyChar() == 'z') {
      velocity = 0;
    } 
    if (e.getKeyChar() == 'x') {
      velocity = 0;
    }
    if (e.getKeyChar() == 'c') {
      shooting = false;
    }
  }
  
  //move the ship
  public void move() {
    x = x + velocity;
  }
  
  //set projectile velocity and starting position
  public void shoot() {
    long temp = System.nanoTime();
    if (temp - lastShot > 900000000) {
      lastShot = temp;
      int i = getBullet();
      bulletUsed[i] = true;
      bullets[i].x = this.x + 9;
      bullets[i].y = this.y - 2;
      bullets[i].setYVelocity(-10);
    }
  }
  
  //draw spaceship onto screen(make image)
  public void draw(Graphics g) {
    g.setColor(Color.white);
    //g.fillRect(x, y, WIDTH, HEIGHT);
    g.drawImage(img, x, y, 40, 40, null);
  }
}
