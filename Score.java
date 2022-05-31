//Score class handles the game score
//child of Rectangle because it makes it easy to format and draw to the screen
import java.awt.*;

public class Score{

  public static int GAME_WIDTH;//width of the window
  public static int GAME_HEIGHT;//height of the window
  public int score;

  //constructor sets score to 0 and establishes dimensions of game window
  public Score(int w, int h){
    score = 0;
    Score.GAME_WIDTH = w;
    Score.GAME_HEIGHT = h;
  }

  //called frequently from GamePanel class
  //updates the current score and draws it to the screen
  public void draw(Graphics g){
    g.setColor(Color.white);
    g.setFont(new Font("Consolas", Font.PLAIN, 60));
    g.drawString(String.valueOf(score), (int)(GAME_WIDTH*0.01), (int)(GAME_HEIGHT*0.2)); //setting location of score to be the top left corner
  }
}
