import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener{

  public static final int GAME_WIDTH = 1000;
  public static final int GAME_HEIGHT = 750;
  
  public Thread gameThread;
  public Image image;
  public Graphics graphics;
  public Alien[][] aliens = new Alien[11][5];
  public PlayerShip ship;
  public Menu start;
  public long startTime(); //when game starts, set starttime to that time
  public Score score;
  
  public GamePanel(){
    int x, y = 20; // coordinates for setting each alien position
  
    ship = new PlayerShip(GAME_WIDTH/2 - PlayerShip.WIDTH / 2, PlayerShip.YCOORD);
    start = new Menu(GAME_WIDTH, GAME_HEIGHT);
    score = new Score(GAME_WIDTH, GAME_HEIGHT);
    
    for(int i = 0; i < 11; ++i){
      x = 20; // change to the first alien position later(make it so its centered)
    
      for(int j = 0; j < 5; ++j){
        //intialization of each alien object
        aliens[i][j] = new Alien(x, y);
        x += 50;
      }
      
      y += 40; // next row of aliens
    }
  
    this.setFocusable(true); //make everything in this class appear on the screen
    this.addKeyListener(this); //start listening for keyboard input
    this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
    gameThread = new Thread(this);
    gameThread.start();
  }
  
  public void paint(Graphics g){
    image = createImage(GAME_WIDTH, GAME_HEIGHT);
    graphics = image.getGraphics();
    draw(graphics);
    g.drawImage(image, 0, 0, this);

  }

  public void draw(Graphics g){
    if(Title.check == false) { // checks if title has been displayed yet or not
    	start.draw(g);
    }
    else{
      for(int i = 0; i < 11; ++i){
        for(int j = 0; j < 5; ++j){
          if(aliens[i][j].dead == false){
            aliens[i][j].draw(g);
          }
        }
      }
      ship.draw();
    }
  }

  public void move(){
    ship.move();
  }

  public void checkCollision(){
    //forces ship to remain on screen
    if(ship.x<= 0){
      ship.x = 0;
    }
    
    if(ship.x >= GAME_WIDTH - PlayerShip.WIDTH){
      ship.x = GAME_WIDTH - PlayerShip.WIDTH;
    }
  
  
    //checks if projectile hits alien, then makes alien[i][j].dead = true, increase score
    for(int i = 0; i < 11; ++i){
        for(int j = 0; j < 5; ++j){
          if(aliens[i][j].intersects(projectile)){ //change the projectile name to the name of the projectile object later
            aliens[i][j].dead = true;
            score.score += 100;
          }
        }
      }
      
    //checks if projectile hits house/wall
    
    //checks if projectile hits player ship
    if(projectile.intersects(ship)){ //change the projectile name to the name of the projectile object later
       //add code here that decreases number of lives
       //also add code that if number of lives = 0, then display the end screen and stop game
    }
    
    //checks if farthest alien to one side hits wall, then reverses the x direction and moves all aliens down, as aliens reach more row, speed increases
    
    //add check that if all aliens dead, then resets wave(i.e. make aliens[i][j].dead == false and reset all the x y positions to the original position)
    //not sure if that goes in the check collision method
  }

  public void run(){
    long lastTime = System.nanoTime();
    double amountOfTicks = 60;
    double ns = 1000000000/amountOfTicks;
    double delta = 0;
    long now;

    while(true){ 
      now = System.nanoTime();
      delta = delta + (now-lastTime)/ns;
      lastTime = now;
      
      if(delta >= 1){
        move();
        checkCollision();
        repaint();
        delta--;
      }
    }
  }

  public void keyPressed(KeyEvent e){
    if(Title.check == false) { // checks if title has been displayed yet or not
    	start.keyPressed(e);
    }
    else{
      ship.keyPressed(e);
    }
  }

  public void keyReleased(KeyEvent e){
    ship.keyReleased(e);
  }

  public void keyTyped(KeyEvent e){

  }
}
