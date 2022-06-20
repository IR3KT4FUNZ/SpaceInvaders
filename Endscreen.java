import java.awt.*;
import java.awt.event.KeyEvent;
//End display screen for space invaders
//Eric Wang, Elliot Ngo, 6/14/2022

public class Endscreen {

  public static int GAME_WIDTH;//width of the window
  public static int GAME_HEIGHT;//height of the window
  public static boolean display = false; //whether screen is displaying

  //constructor establishes dimensions of game window
  public Endscreen(int w, int h){
    this.GAME_WIDTH = w;
    this.GAME_HEIGHT = h;
  }
  
  //check for restart input
  public void keyPressed(KeyEvent e){
	 if(e.getKeyChar() == 'r'){ // checks if r key is pressed to continue
	   Title.check = false;
   	   display = false;
	 }
  }
  
  //draw endscreen 
  public void draw(Graphics g){
     if (display) {
       g.setColor(Color.white);
       g.setFont(new Font("Consolas", Font.PLAIN, 100));
       g.drawString("GAME OVER", (int)(GAME_WIDTH*0.255), (int)(GAME_HEIGHT*0.4)); //setting location and text of title cards 
    
       g.setFont(new Font("Consolas", Font.PLAIN, 50));
       
       if(Score.score % 100 == 0) { // if more digits to score, center text differently
    	   g.drawString("Your score is " + Score.score + "!", (int)(GAME_WIDTH*0.27), (int)(GAME_HEIGHT*0.6));
       }
       else {
    	   g.drawString("Your score is " + Score.score + "!", (int)(GAME_WIDTH*0.29), (int)(GAME_HEIGHT*0.6));
       }
    
       g.setFont(new Font("Consolas", Font.PLAIN, 50));
       g.drawString("Press r to play again", (int)(GAME_WIDTH*0.23), (int)(GAME_HEIGHT*0.8));
    }
  }
}
