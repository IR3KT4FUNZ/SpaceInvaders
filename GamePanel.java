import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

//Game panel class for space invaders
//Eric Wang, Elliot Ngo, 6/14/2022

public class GamePanel extends JPanel implements Runnable, KeyListener{

  public static final int GAME_WIDTH = 1000; //width of frame
  public static final int GAME_HEIGHT = 650; //height of frame
  
  public Thread gameThread; //thread the game runs in
  public Image image; //offscreen drawing image for the game
  public Graphics graphics; //graphics of the game
  public Alien[][] aliens = new Alien[5][11]; //array of aliens
  public PlayerShip ship; //player controlled ship
  public Title start; //title screen
  public static long startTime; //when game starts, set to the time
  public static long startTime2; //second start time used for different purpose
  public Score score; //player's score
  public boolean direction = true; //x direction 
  public boolean downwardmove = false; //y direction
  public double counter = 1; //counter for aliens
  public boolean alldead = true; //check if all aliens are dead
  public int x, y; // coordinates for setting each alien position
  public House[] houses = new House[3]; //array of houses
  public Background back; //background
  public Projectile[] alienBullets = new Projectile[5]; //pregenerated bullets for aliens
  public long prevAlienShot; //used to determine how often aliens can shoot
  public long timeDif2 = 2500000000l; //preset time difference
  public Hearts lives; //lives of player
  public Endscreen end; //end screen
  public Instructions instructions; //instruction screen
  
  public Music death = new Music(); // new music objects for each sfx
  public Music aliendeath = new Music();
  public static Music movement = new Music();
	
  //constructor initializes everything to proper values
  public GamePanel() throws IOException{
  
    //intialization of each object at correct position	  
    ship = new PlayerShip(GAME_WIDTH/2 - PlayerShip.WIDTH / 2, 550);
    start = new Title(GAME_WIDTH, GAME_HEIGHT);
    score = new Score(GAME_WIDTH, GAME_HEIGHT);
    back = new Background(GAME_WIDTH, GAME_HEIGHT);
    lives = new Hearts(GAME_WIDTH, GAME_HEIGHT);
    end = new Endscreen(GAME_WIDTH, GAME_HEIGHT);
    instructions = new Instructions(GAME_WIDTH, GAME_HEIGHT);
    prevAlienShot = System.nanoTime();
    
    death.musicCreate("explosion.wav"); // creats music files
    aliendeath.musicCreate("invaderkilled.wav");
    movement.musicCreate("moving.wav");
    
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
      alienBullets[i] = new Projectile(1000, 650, 0, 0); // creates each alien bullet
    }
  
    this.setFocusable(true); //make everything in this class appear on the screen
    this.addKeyListener(this); //start listening for keyboard input
    this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
    gameThread = new Thread(this);
    gameThread.start();
  }
  
  //paint function
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

  //draw necessary things to screen
  public void draw(Graphics g) throws IOException{
	back.draw(g); // draws background
    if(Title.check == false) { // checks whether to display start screen, instructions screen, end screen, or game
    	start.draw(g);
    }
    else if(Instructions.display == true) {
    	instructions.draw(g);
    }
    else if(Endscreen.display == true) {
    	end.draw(g);
    }
    else{
      lives.draw(g);
      for (int i = 0; i < 3; i++) {
    	  houses[i].draw(g);
      }
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
      for(int i = 0; i < 5; ++i) {
    	  alienBullets[i].draw(g);
      }
    }
  }
  
  //pick random aliens
  public int random() {
    return ((int) (Math.random() * 55.0));
  }
  
  public void alienShots() { //picks 5 aliens to shoot a projectile
	  int temp = random(), counter = 0;
	  while(counter != 5) {
	   	if(aliens[temp / 11][temp % 11].dead == false) {
	   		aliens[temp / 11][temp % 11].shoot(counter, alienBullets);
	   		++counter;
	   	}
	   	temp = random();
	  }
  }
  
  //resets all objects to their starting position for game restart
  public void reset() {
	  Hearts.lives = 3;
	  
	  ship.x = GAME_WIDTH/2 - PlayerShip.WIDTH / 2;
	  
	  timeDif2 = 2500000000l;
	  ship.timeDif = 1000000000;
	  
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
     
      Score.score = 0;
      
      counter = 1; // resets alien movement timing
      
      for(int i = 0; i < 3; ++i) {
         for (int j = 0; j < 15; j++) {
    	   for (int k = 0; k < 12; k++) {
    	     houses[i].alive[j][k] = true;
    	   }
    	 }
         
    	 houses[i].alive[0][0] = false;
    	 houses[i].alive[0][1] = false;
    	 houses[i].alive[0][2] = false;
    	 houses[i].alive[1][0] = false;
    	 houses[i].alive[1][1] = false;
    	 houses[i].alive[2][0] = false;
    	 houses[i].alive[12][0] = false;
    	 houses[i].alive[13][0] = false;
    	 houses[i].alive[13][1] = false;
    	 houses[i].alive[14][0] = false;
    	 houses[i].alive[14][1] = false;
    	 houses[i].alive[14][2] = false;
    	 
    	 for (int j = 5; j <= 9; j++) {
    	    houses[i].alive[j][7] = false; 
    	 }
    	 
    	 for (int j = 4; j <= 10; j++) {
    	    for (int k = 8; k <= 11; k++) {
    	       houses[i].alive[j][k] = false; 
    	    }
    	 }
      }
  }
  
  //moves everything on screen that should move
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
	  alienBullets[i].move();
    }
    temp = System.nanoTime();
    if (temp - prevAlienShot > timeDif2) {
      prevAlienShot = temp;
      alienShots();
    }
  }

  //checks for collisions between bullets and houses, ship, aliens
  public void checkCollision(){
	if(Title.switchs == true) {
		reset();
		Title.switchs = false;
	}
	  
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
                 aliendeath.play();
        		 aliens[i][j].dead = true; // sets alien to be dead
                 ship.bullets[k].x = 1000; // sets bullet offscreen
                 ship.bulletUsed[k] = false; // sets bullet as unused
                 if (Hearts.lives > 0){
                	 Score.score += 100; // increases score
		 }
		         if(timeDif2 >= 1400000000) { //decreases time between shots for both aliens and player
		        	 timeDif2 -= 20000000;
		         }
		         if(ship.timeDif >= 560000000) {
		        	 ship.timeDif -= 8000000;
		         }
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
          for(int k = 0; k < 5; ++k) { // cycles through and checks if each bullet hits each house
    		if(ship.bullets[k].x + 4 >= 65 + i * 8 && ship.bullets[k].x + 4 <= 65 + i * 8 + 8 && ship.bullets[k].y >= 425 + j * 8 && ship.bullets[k].y <= 425 + j * 8 + 8 && houses[0].alive[i][j]) {
    		   houses[0].alive[i][j] = false; //sets house part to dead
    		   ship.bullets[k].x = 1000; //sets bullet offscreen
    		   ship.bulletUsed[k] = false; //sets bullet as unused
    		}
    			   
    		if(ship.bullets[k].x + 4 >= 440 + i * 8 && ship.bullets[k].x + 4 <= 440 + i * 8 + 8 && ship.bullets[k].y >= 425 + j * 8 && ship.bullets[k].y <= 425 + j * 8 + 8 && houses[1].alive[i][j]) {
     		   houses[1].alive[i][j] = false;
     		   ship.bullets[k].x = 1000;
     		   ship.bulletUsed[k] = false;
     		}
    		
    		if(ship.bullets[k].x + 4 >= 815 + i * 8 && ship.bullets[k].x + 4 <= 815 + i * 8 + 8 && ship.bullets[k].y >= 425 + j * 8 && ship.bullets[k].y <= 425 + j * 8 + 8 && houses[2].alive[i][j]) {
     		   houses[2].alive[i][j] = false;
     		   ship.bullets[k].x = 1000;
     		   ship.bulletUsed[k] = false;
     		}
    		
    		if(alienBullets[k].x + 4 >= 65 + i * 8 && alienBullets[k].x + 4 <= 65 + i * 8 + 8 && alienBullets[k].y >= 425 + j * 8 && alienBullets[k].y <= 425 + j * 8 + 8 && houses[0].alive[i][j]) {
     		   houses[0].alive[i][j] = false;
     		   alienBullets[k].x = 1000;
     		}
     			   
    		if(alienBullets[k].x + 4 >= 440 + i * 8 && alienBullets[k].x + 4 <= 440 + i * 8 + 8 && alienBullets[k].y >= 425 + j * 8 && alienBullets[k].y <= 425 + j * 8 + 8 && houses[1].alive[i][j]) {
      		   houses[1].alive[i][j] = false;
      		   alienBullets[k].x = 1000;
      		}
     		
    		if(alienBullets[k].x + 4 >= 815 + i * 8 && alienBullets[k].x + 4 <= 815 + i * 8 + 8 && alienBullets[k].y >= 425 + j * 8 && alienBullets[k].y <= 425 + j * 8 + 8 && houses[2].alive[i][j]) {
      		   houses[2].alive[i][j] = false;
      		   alienBullets[k].x = 1000;
      		}
    	  }
       }
    }
    
    //checks if projectile hits player ship
    for(int i = 0; i < 5; ++i) {
    	if(ship.intersects(alienBullets[i])) {
    		Hearts.lives--; //decreases lives
    		
    		death.play(); //plays music
    		
		    ship.x = GAME_WIDTH / 2 - PlayerShip.WIDTH / 2; // sets ship to center of screen after hit
		
    		for(int j = 0; j < 5; ++j) {
				alienBullets[j].x = 1000; // moves alien bullets offscreen
			}
    		
    		if(Hearts.lives == 0) {
    			movement.stop(); //stops music
    			Endscreen.display = true; // sets endscreen to be displayed
    		}
    	}
    }
    
    
    //checks if some amount of time has passed, if so then moves alien(for staggered movement)
    
    if(System.nanoTime() - startTime >= 500000000/counter) {
    	if(direction) { // checks which direction its going
    		Alien.xspeed = 10;
    	}
    	else {
    		Alien.xspeed = -10;
    	}
    	startTime = System.nanoTime(); //resets time
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
    	Alien.yspeed = 25;
    }
    
    if(aliens[0][0].x <= 0) {
    	Alien.xspeed = 10;
    	counter += 0.5;
    	direction = true;
    	downwardmove = true;
    	startTime2 = System.nanoTime();
    	Alien.yspeed = 25;
    	/*
    	for(int i = 0; i < 5; ++i){
    	  for(int j = 0; j < 11; ++j){
    		  aliens[i][j].y += 10;
    	  }
    	}
    	*/
    }
    
    //moves alien down
    if(downwardmove) {
    	if(System.nanoTime() - startTime2 >= 10000) {
    		Alien.yspeed = 0;
    		downwardmove = false;
    	}
    }
    
    //add check that if all aliens dead, then resets wave(i.e. make aliens[i][j].dead == false and reset all the x y positions to the original position)
    //not sure if that goes in the check collision method
    
    //if aliens hit bottom, game ends
    if(aliens[0][10].y >= 550) {
    	Endscreen.display = true;
    }
  }
  
  //infinite loop of the game
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

  //check for keyboard input
  public void keyPressed(KeyEvent e){
    if(Title.check == false) { // checks if title has been displayed yet or not
      start.keyPressed(e);
    }
    else if(Endscreen.display == true) {
       end.keyPressed(e);
    }
    else if(Instructions.display == true) {
    	instructions.keyPressed(e);
    }
    else{
      ship.keyPressed(e);
    }
  }
  
  //check for keys being released
  public void keyReleased(KeyEvent e){
    ship.keyReleased(e);
  }

  //place holder(must include this class)
  public void keyTyped(KeyEvent e){

  }
}
