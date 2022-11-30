
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.Timer;

import javax.swing.JFrame;

public class SimonDice implements ActionListener, MouseListener {
  
  public static SimonDice simon;

  public static Renderer renderer;

  public static final int WIDTH = 700, HEIGHT = 700;

  // Variable para definir si está prendida la luz
  public static int luz = 3; 

  public SimonDice() {
  
    JFrame frame = new JFrame("Simon Dice");
    Timer timer = new Timer(20, this);
    
    renderer = new Renderer();

    // Seteamos caracteristicas de la ventana
    frame.setSize(WIDTH , HEIGHT); // Revisar esto, aumentar en la constante y no sumar ahora
    frame.setVisible(true);
    frame.add(renderer);
    frame.addMouseListener(this);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    timer.start();
    
  }

  public static void main(String[] args) {
    simon = new SimonDice();
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    // pinta cada frame del juego
    renderer.repaint();
  }

  // vamos a realizar los renderizados
  public void paint(Graphics2D g) {
    // Creamos colores personalizados
    Color verdeIluminado = new Color(57, 202, 73);
    Color verdeApagado = new Color(30, 107, 39);
    Color rojoIluminado = new Color(252, 67, 62);
    Color rojoApagado = new Color(163, 43, 40);
    Color amarilloIluminado = new Color(255, 218, 49);
    Color amarilloApagado = new Color(161, 137, 31);
    Color azulIluminado = new Color(19, 119, 215);
    Color azulApagado = new Color(10, 62, 112);
    Color grisTopo = new Color(26, 26, 26);

    // Le decimos al programa que si la luz es 1 entonces la pinte de un verde 
    // más claro
    if (luz == 1) {
      g.setColor(verdeIluminado.brighter());
    } else {
      g.setColor(verdeApagado);
    }
    g.fillRect(0, 0, WIDTH / 2, HEIGHT / 2);

    if (luz == 2) {
      g.setColor(rojoIluminado.brighter());
    } else {
      g.setColor(rojoApagado);
    }
    g.fillRect(WIDTH / 2, 0, WIDTH / 2, HEIGHT / 2);

    if (luz == 3) {
      g.setColor(amarilloIluminado.brighter());
    } else {
      g.setColor(amarilloApagado);
    }
    g.fillRect(0, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);

    if (luz == 4) {
      g.setColor(azulIluminado.brighter());
    } else {
      g.setColor(azulApagado);
    }
    g.fillRect(WIDTH / 2, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);

    g.setColor(grisTopo);
    g.fillRoundRect(225, 225, 250, 250, 250, 250);
  
  }

  // Metodos para aplicar a la detección del mouse
  @Override
  public void mouseClicked(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }
  @Override
  public void mouseExited(MouseEvent e){

  }
}
