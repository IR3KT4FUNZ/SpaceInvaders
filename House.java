import java.awt.*;

public class House extends Rectangle {
  public static int width = 60; //width of the house
  public static int height = 48; //height of the house
  public boolean[][] alive; //array for squares of the house
  
  //constructor, defines walls
  public House(int x, int y) {
    super(x, y, width, height);
    alive = new boolean[15][12];
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 8; j++) {
        alive[i][j] = true;
      }
    }
    alive[0][0] = false;
    alive[0][1] = false;
    alive[0][2] = false;
    alive[1][0] = false;
    alive[1][1] = false;
    alive[2][0] = false;
    alive[12][0] = false;
    alive[13][0] = false;
    alive[13][1] = false;
    alive[14][0] = false;
    alive[14][1] = false;
    alive[14][2] = false;
    for (int i = 5; i <= 9; i++) {
      alive[i][7] = false; 
    }
    for (int i = 4; i <= 10; i++) {
      for (int j = 8; j <= 11; j++) {
        alive[i][j] = false; 
      }
    }
  }
  
  //display the alive parts of the defensive house
  public void draw(Graphics g) {
    for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 12; j++) {
        if (alive[i][j]) {
          g.setColor(Color.red);
          g.fillRectangle(x + i * 4, y + j * 4, 4, 4);
        }
      }
    }
  }
  
  //helper method
  public void setDead(int i, int j) {
    alive[i][j] = false;
  }
  
  
} 
