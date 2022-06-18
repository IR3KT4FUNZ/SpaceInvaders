import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

//Player controlled ship for space invaders
//Eric Wang, Elliot Ngo, 6/14/2022

public class PlayerShip extends Rectangle {
  public static final int HEIGHT = 40; //height of ship
  public static final int WIDTH = 40; //width of ship
  public static final int YCOORD = 550; //constant y coordinate of ship
  public final int SPEED = 10; //speed the ship always moves at
  public int velocity; //speed of ship with direction
  public boolean shooting = false; //whether the user is holding shoot
  public long lastShot = -1000000000; //used to set how often the user can shoot
  public Projectile[] bullets = new Projectile[5]; //preinitialized bullets
  public boolean[] bulletUsed = new boolean[5]; //check which bullets are in use
  public Image img; //image for the ship
  public Image shootingImg; //image for ship when it shoots
  public long timeDif = 1000000000; //revert

  
  public static Music shootsound = new Music(); //music object
  
  //constructor, use rectangle constructor
  public PlayerShip(int x, int y) throws IOException {
    super(x, y, WIDTH, HEIGHT);
    velocity = 0;
    Arrays.fill(bulletUsed, false);
    for (int i = 0; i < 5; i++) {
      bullets[i] = new Projectile(1000, 650, 0, 0);
    }
    img = ImageIO.read(new File("ship2.png"));
    shootingImg = ImageIO.read(new File("shootingship.png"));
    shootsound.musicCreate("shoot.wav"); // sets music object to read the sound file name
  }
  
  //check for controls being pressed/used
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
    for(int i = 0; i < 5; ++i) {
    	if(bullets[i].y < 0) {
    		bulletUsed[i] = false;
    	}
    }
  }
  
  //set projectile velocity and starting position
  public void shoot() {
    long temp = System.nanoTime();
    if (temp - lastShot > timeDif) {
      shootsound.play(); //plays sound effect
      lastShot = temp;
      int i = getBullet();
      bulletUsed[i] = true;
      bullets[i].x = this.x + 15;
      bullets[i].y = this.y - 2;
      bullets[i].setYVelocity(-8);
    }
  }
  
  //draw spaceship onto screen(make image)
  public void draw(Graphics g) {
    if (System.nanoTime() - lastShot > 100000000) {
      g.drawImage(img, x, y, 40, 40, null);
    } else {
      g.drawImage(shootingImg, x, y, 40, 40, null);
    }
  }
}
