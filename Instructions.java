import java.awt.*;
import java.awt.event.KeyEvent;
//instructions screen for space invaders
//Eric Wang, Elliot Ngo, 6/14/2022

public class Instructions {

  public static int GAME_WIDTH;//width of the window
  public static int GAME_HEIGHT;//height of the window
  public static boolean display = false; //whether screen is displaying

  //constructor establishes dimensions of game window
  public Instructions(int w, int h){
    this.GAME_WIDTH = w;
    this.GAME_HEIGHT = h;
  }
  
  //check for restart input
  public void keyPressed(KeyEvent e) {
     if (e.getKeyChar() == 'o') {
       Title.check = false;
       display = false;
     }
  }
  
  //draw endscreen 
  public void draw(Graphics g){
	  g.setColor(Color.white);
	  g.setFont(new Font("Consolas", Font.PLAIN, 100));
	  g.drawString("INSTRUCTIONS", (int)(GAME_WIDTH*0.16), (int)(GAME_HEIGHT*0.2)); //setting location and text of title cards 
	    
	  g.setFont(new Font("Consolas", Font.PLAIN, 50));
	  g.drawString("Use ZX to move the ship", (int)(GAME_WIDTH*0.18), (int)(GAME_HEIGHT*0.4));
	    
	  g.setFont(new Font("Consolas", Font.PLAIN, 50));
	  g.drawString("Press C to shoot", (int)(GAME_WIDTH*0.26), (int)(GAME_HEIGHT*0.6));
	  
	  g.setFont(new Font("Consolas", Font.PLAIN, 50));
	  g.drawString("Press O to return to menu", (int)(GAME_WIDTH*0.15), (int)(GAME_HEIGHT*0.8));
  }
}
