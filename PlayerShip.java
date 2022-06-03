import java.awt.*;
import java.awt.event.*;

//Player controlled ship for space invaders
//Eric, Elliot, 05/31/2022

public class PlayerShip extends Rectangle {
  public static final int HEIGHT = 20; //height of ship
  public static final int WIDTH = 20; //width of ship
  public static final int YCOORD = 700; //constant y coordinate of ship
  public final int SPEED = 10; //speed the ship always moves at
  public int velocity; //speed of ship with direction
  public boolean shooting = false; //whether the user is holding shoot
  
  //constructor, use rectangle constructor
  public PlayerShip(int x, int y) {
    super(x, y, WIDTH, HEIGHT);
    velocity = 0;
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
    }
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
    
  }
  
  //draw spaceship onto screen(make image)
  public void draw(Graphics g) {
    g.setColor(Color.white);
    g.fillRect(x, y, WIDTH, HEIGHT);
  }
}
