import javax.swing.JPanel;
import java.awt.Graphics2D;

import java.awt.Graphics;

public class Renderer extends JPanel {

  // se aplica este override porque la ventana se dibujaba 2 veces
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Renderiza los elementos del juego
    if (SimonDice.simon != null) {
      SimonDice.simon.paint((Graphics2D) g);
    }
  }
}