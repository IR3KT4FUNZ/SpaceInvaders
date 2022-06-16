//Menu class for displaying title
import java.awt.*;
import java.awt.event.KeyEvent;

public class Title{

  public static int GAME_WIDTH;//width of the window
  public static int GAME_HEIGHT;//height of the window
  public static boolean check = false; // checks if title has been displayed yet
  public static boolean switchs = false;

  //constructor establishes dimensions of game window
  public Title(int w, int h){
    Title.GAME_WIDTH = w;
    Title.GAME_HEIGHT = h;
  }
  
  public void keyPressed(KeyEvent e){
	 if(e.getKeyChar() == 'v'){ // checks if v key is pressed to continue
	   check = true; // indicates title has been displayed
	   switchs = true;
	   GamePanel.startTime = System.nanoTime();
	 }
	 
	 if(e.getKeyChar() == 'i') {
		 Instructions.display = true;
		 check = true;
	 }
  }
  
  //called frequently from GamePanel class
  //updates the current score and draws it to the screen
  public void draw(Graphics g){
     g.setColor(Color.white);
     g.setFont(new Font("Consolas", Font.PLAIN, 100));
     g.drawString("SPACE INVADERS", (int)(GAME_WIDTH*0.14), (int)(GAME_HEIGHT*0.4)); //setting location and text of title cards 
    
     g.setFont(new Font("Consolas", Font.PLAIN, 50));
     g.drawString("Press V to start", (int)(GAME_WIDTH*0.29), (int)(GAME_HEIGHT*0.6));
    
     g.setFont(new Font("Consolas", Font.PLAIN, 50));
     g.drawString("Press I for instructions", (int)(GAME_WIDTH*0.20), (int)(GAME_HEIGHT*0.8));
  }
}
