//Menu class for displaying title
import java.awt.*;
import java.awt.event.KeyEvent;

public class EndScreen {

  public static int GAME_WIDTH;//width of the window
  public static int GAME_HEIGHT;//height of the window
  public static boolean display = false; //whether screen is displaying

  //constructor establishes dimensions of game window
  public EndScreen(int w, int h){
    Title.GAME_WIDTH = w;
    Title.GAME_HEIGHT = h;
  }
  
  public void keyPressed(KeyEvent e){
	 if(e.getKeyChar() == 'r' && display){ // checks if g key is pressed to continue
	   Title.check = false;
     display = false;
	 }
  }
  
  public void draw(Graphics g){
     if (display) {
       g.setColor(Color.white);
       g.setFont(new Font("Consolas", Font.PLAIN, 100));
       g.drawString("GAME OVER", (int)(GAME_WIDTH*0.16), (int)(GAME_HEIGHT*0.4)); //setting location and text of title cards 
    
       g.setFont(new Font("Consolas", Font.PLAIN, 50));
       g.drawString("Your score is score.score!", (int)(GAME_WIDTH*0.20), (int)(GAME_HEIGHT*0.6));
    
       g.setFont(new Font("Consolas", Font.PLAIN, 50));
       g.drawString("Press r to play again", (int)(GAME_WIDTH*0.29), (int)(GAME_HEIGHT*0.8));
    }
  }
}
