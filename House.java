import java.awt.*;

public class House extends Rectangle {
  public static int width = 20; //width of the house
  public static int height = 16; //height of the house
  public boolean[][] alive; //array for squares of the house
  
  //constructor, defines walls
  public House(int x, int y) {
    super(x, y, width, height);
    alive = new boolean[10][8];
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 8; j++) {
        alive[i][j] = true;
      }
    }
    alive[0][0] = false;
    alive[0][1] = false;
    alive[1][0] = false;
    alive[8][0] = false;
    alive[9][0] = false;
    alive[9][1] = false;
    alive[4][4] = false;
    alive[5][4] = false;
    for (int i = 3; i <= 6; i++) {
      for (int j = 5; j <= 7; j++) {
        alive[i][j] = false; 
      }
    }
  }
  
  //display the alive parts of the defensive house
  public void draw(Graphics g) {
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 8; j++) {
        if (alive[i][j]) {
          g.setColor(Color.red);
          g.fillRectangle(x + i * 2, y + j * 2, 2, 2);
        }
      }
    }
  }
  
  //helper method
  public void setDead(int i, int j) {
    alive[i][j] = false;
  }
  
  
} 
