/* GameFrame class establishes the frame (window) for the game
It is a child of JFrame because JFrame manages frames
Runs the constructor in GamePanel class

*/ 

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

public class GameFrame extends JFrame{

  GamePanel panel;

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
