import java.awt.*;
import java.io.IOException;
import javax.swing.*;

//Game frame for space invaders, base code taken from Mr. Anthony pong sample code
//Eric Wang, Elliot Ngo, 6/14/2022
public class GameFrame extends JFrame{

  GamePanel panel; //panel of game

  public GameFrame() throws IOException{
    panel = new GamePanel(); //run GamePanel constructor
    this.add(panel);
    this.setTitle("Space Invaders"); //set title for frame
    this.setResizable(false); //frame can't change size
    this.setBackground(Color.black);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //X button will stop program execution
    this.pack();//makes components fit in window - don't need to set JFrame size, as it will adjust accordingly
    this.setVisible(true); //makes window visible to user
    this.setLocationRelativeTo(null);//set window in middle of screen
  }
  
}
