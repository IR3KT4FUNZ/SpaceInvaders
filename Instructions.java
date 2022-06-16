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
  public void keyPressed(KeyEvent e){
	 if(e.getKeyChar() == 'i' && Title.check = false){ // checks if r key is pressed to continue
	   Title.check = true;
   	 display = true;
	 }
   if (e.getKeyChar() == 'o' && display = true;) {
     Title.check = false;
     display = false;
   }
  }
  
  //draw endscreen 
  public void draw(Graphics g){
     if (display) {
       g.setColor(Color.white);
       g.setFont(new Font("Consolas", Font.PLAIN, 100));
    }
  }
}
