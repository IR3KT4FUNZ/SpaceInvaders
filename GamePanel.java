import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener{

  public static final int GAME_WIDTH = 1000;
  public static final int GAME_HEIGHT = 650;
  
  public Thread gameThread;
  public Image image;
  public Graphics graphics;
  public Alien[][] aliens = new Alien[5][11];
  public PlayerShip ship;
  public Title start;
  public static long startTime; //when game starts, set starttime to that time
  public static long startTime2;
  public Score score;
  public boolean direction = true;
  public boolean downwardmove = false;
  public double counter = 1;
  public boolean alldead = true;
  public int x, y; // coordinates for setting each alien position
  public House[] houses = new House[3];
  public Background back;
  public Projectile[] alienBullets = new Projectile[5];
  public long prevAlienShot;
  
  public GamePanel() throws IOException{
  
    ship = new PlayerShip(GAME_WIDTH/2 - PlayerShip.WIDTH / 2, 550);
    start = new Title(GAME_WIDTH, GAME_HEIGHT);
    score = new Score(GAME_WIDTH, GAME_HEIGHT);
    back = new Background(GAME_WIDTH, GAME_HEIGHT);
    prevAlienShot = System.nanoTime();
    y = 40;
    
    for(int i = 0; i < 5; ++i){
      x = 100; // change to the first alien position later(make it so its centered)
    
      for(int j = 0; j < 11; ++j){
        //intialization of each alien object
        aliens[i][j] = new Alien(x, y);
        x += 40;
      }
      
      y += 45; // next row of aliens
      houses[0] = new House(65, 425);
      houses[1] = new House(440, 425);
      houses[2] = new House(815, 425);
    }
    for (int i = 0; i < 5; i++) {
      alientBullets[i] = new Projectile(-10, -10, 0, 0);
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
    try {
		draw(graphics);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    g.drawImage(image, 0, 0, this);

  }

  public void draw(Graphics g) throws IOException{
	back.draw(g);
    if(Title.check == false) { // checks if title has been displayed yet or not
    	start.draw(g);
    }
    else{
      for(int i = 0; i < 5; ++i){
        for(int j = 0; j < 11; ++j){
          if(aliens[i][j].dead == false){
            aliens[i][j].draw(g);
          }
        }
      }
      ship.draw(g);
      for (int i = 0; i < 5; i++) {
    	  ship.bullets[i].draw(g);
      }
      score.draw(g);
      for (int i = 0; i < 3; i++) {
    	  houses[i].draw(g);
      }
    }
  }
  
//pick random aliens(changes start)
  public int random() {
    return ((int) (Math.random() * 55.0));
  }
  
  public void alienShots() {
    int temp = random();
    for (int i = 0; i < 5; i++) {
	aliens[temp / 11][temp % 11].shoot(i);
    }
  }
  //insert end

  public void move(){
    long temp;
    ship.move();
    for(int i = 0; i < 5; ++i){
        for(int j = 0; j < 11; ++j){
        	aliens[i][j].move();
        }
    }
    for (int i = 0; i < 5; i++) {
  	  ship.bullets[i].move();
	  panel.alienBullets[i].move();
    }
    temp = System.nanoTime();
    if (temp - prevAlienShot > 2500000000) {
      prevAlienShot = temp;
      alienShots();
    }
  }

  public void checkCollision(){
    //forces ship to remain on screen
    if(ship.x<= 0){
      ship.x = 0;
    }
    
    if(ship.x >= GAME_WIDTH - PlayerShip.WIDTH){
      ship.x = GAME_WIDTH - PlayerShip.WIDTH;
    }
  
    alldead = true; // checks if all aliens have been killed
    
    //checks if projectile hits alien, then makes alien[i][j].dead = true, increase score
    for(int i = 0; i < 5; ++i){
        for(int j = 0; j < 11; ++j){
          for (int k = 0; k < 5; k++) {
        	  if(aliens[i][j].intersects(ship.bullets[k]) && aliens[i][j].dead == false){ //change the projectile name to the name of the projectile object later
                 aliens[i][j].dead = true;
                 ship.bullets[k].x = -10;
                 ship.bulletUsed[k] = false;
                 score.score += 100;
              }
        	  
        	  if(aliens[i][j].dead == false) {
        		  alldead = false; // if any alien is alive then sets to false
        	  }
          }
        }
      }
    
    if(alldead == true) { // if all aliens are dead, resets wave of aliens
       y = 40;
    	
       for(int i = 0; i < 5; ++i){
    	 x = 100; // change to the first alien position later(make it so its centered)
    	    
    	 for(int j = 0; j < 11; ++j){
    	 //resets each alien object
    	    aliens[i][j].x = x;
    	    aliens[i][j].y = y;
    	    x += 40;
    	    aliens[i][j].dead = false;
    	 }
    	      
    	 y += 45; // next row of aliens
       }
       
       counter = 1; // resets alien movement timing
    }
      
    //checks if projectile hits house/wall
    for (int i = 0; i < 15; i++) {
       for (int j = 0; j < 12; j++) {
          for(int k = 0; k < 5; ++k) {
    		if(ship.bullets[k].x + 4 >= 65 + i * 8 && ship.bullets[k].x + 4 <= 65 + i * 8 + 8 && ship.bullets[k].y >= 425 + j * 8 && ship.bullets[k].y <= 425 + j * 8 + 8 && houses[0].alive[i][j]) {
    		   houses[0].alive[i][j] = false;
    		   ship.bullets[k].x = -10;
    		   ship.bulletUsed[k] = false;
    		}
    			   
    		if(ship.bullets[k].x + 4 >= 440 + i * 8 && ship.bullets[k].x + 4 <= 440 + i * 8 + 8 && ship.bullets[k].y >= 425 + j * 8 && ship.bullets[k].y <= 425 + j * 8 + 8 && houses[1].alive[i][j]) {
     		   houses[1].alive[i][j] = false;
     		   ship.bullets[k].x = -10;
     		   ship.bulletUsed[k] = false;
     		}
    		
    		if(ship.bullets[k].x + 4 >= 815 + i * 8 && ship.bullets[k].x + 4 <= 815 + i * 8 + 8 && ship.bullets[k].y >= 425 + j * 8 && ship.bullets[k].y <= 425 + j * 8 + 8 && houses[2].alive[i][j]) {
     		   houses[2].alive[i][j] = false;
     		   ship.bullets[k].x = -10;
     		   ship.bulletUsed[k] = false;
     		}
    	  }
       }
    }
    
    //checks if projectile hits player ship
    ///if(projectile.intersects(ship)){ //change the projectile name to the name of the projectile object later
       //add code here that decreases number of lives
       //also add code that if number of lives = 0, then display the end screen and stop game
    //}
    
    
    //checks if at one second has passed, if so then moves alien(for staggered movement)
    
    if(System.nanoTime() - startTime >= 500000000/counter) { // note to increase movement, just change the 1000000000 number to a smaller number as time goes on
    	if(direction) {
    		Alien.xspeed = 10;
    	}
    	else {
    		Alien.xspeed = -10;
    	}
    	startTime = System.nanoTime();
    }
    else {
    	Alien.xspeed = 0;
    }
    
    
    //checks if farthest alien to one side hits wall, then reverses the x direction and moves down
    
    if(aliens[0][10].x >= GAME_WIDTH - Alien.ALIEN_WIDTH) {
    	Alien.xspeed = -10;
    	counter += 0.5;
    	direction = false;
    	downwardmove = true;
    	startTime2 = System.nanoTime();
    	Alien.yspeed = 30;
    }
    
    if(aliens[0][0].x <= 0) {
    	Alien.xspeed = 10;
    	counter += 0.5;
    	direction = true;
    	downwardmove = true;
    	startTime2 = System.nanoTime();
    	Alien.yspeed = 30;
    	/*
    	for(int i = 0; i < 5; ++i){
    	  for(int j = 0; j < 11; ++j){
    		  aliens[i][j].y += 10;
    	  }
    	}
    	*/
    }
    
    //supposed to make alien move down a bit
    if(downwardmove) {
    	if(System.nanoTime() - startTime2 >= 10000) {
    		Alien.yspeed = 0;
    		downwardmove = false;
    	}
    }
    
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
