import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.io.*;

import java.awt.Graphics;

public class Renderer extends JPanel {

  // se aplica este override porque la ventana se dibujaba 2 veces
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

//   try {
//     BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));    
//     String userInput;    
//     while ((userInput = stdIn.readLine()) != null) {
//         System.out.println(userInput);
//     }
// } catch(IOException ie) {
//     ie.printStackTrace();
// }   
}