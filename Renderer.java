import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.io.*;

import java.awt.Graphics;

public class Renderer extends JPanel {

  @Override
  protected void paintComponent(Graphics g) {
    try {
      super.paintComponent(g);
  
      // Renderiza los elementos del juego
      if (SimonDice.simon != null) {
        SimonDice.simon.paint((Graphics2D) g);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}