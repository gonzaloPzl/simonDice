
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

import javax.swing.JFrame;

public class SimonDice implements ActionListener, MouseListener {
  
  public static SimonDice simon;

  public static Renderer renderer;

  public static final int ANCHO = 700, ALTO = 700;

  // Variable para definir si está prendida la prendido
  public static int prendido = 0, ticks, apagado = 0, indexSecuencia = 0, velocidad = 50; 

  // Variable para saber si la secuencia está creada
  public static boolean crearSecuencia = true;

  // Array con la secuencia random
  public ArrayList<Integer> secuencia;

  // variable para saber si perdimos
  private boolean gameOver;

  // Creamos 
  public Random random;

  // Creamos los objetos de audio de los diferentes colores
  ReproducirAudio reproductor = new ReproducirAudio();



  public SimonDice() {
  
    JFrame frame = new JFrame("Simon Dice");
    // Timer timer = new Timer(1000, this);
    Timer timer;
    
    renderer = new Renderer();

    // Seteamos caracteristicas de la ventana
    frame.setSize(ANCHO , ALTO); // Revisar esto, aumentar en la constante y no sumar ahora
    frame.setVisible(true);
    frame.add(renderer);
    frame.addMouseListener(this);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    
    start();

    
    timer = new Timer(20, this);
    timer.start();
    
    
  }

  // Método que rellena la secuencia
  public void start() {
    random = new Random();

    secuencia = new ArrayList<Integer>();
    indexSecuencia = 0;
    apagado = 1;
    prendido = 0;
    ticks = 0;
  }

  public static void main(String[] args){
    simon = new SimonDice();
  }

  // Sección que se ejecuta continuamente cada vez que se lo indica al timer
  @Override
  public void actionPerformed(ActionEvent event) {

    ticks++;
    // System.out.println(ticks);

    // Contabiliza
    if (ticks % velocidad == 0) {
      prendido = 0;
      if (apagado >= 0){
          apagado--;
      }
    }
      if (crearSecuencia) {
        if(apagado <= 0) {
          if (indexSecuencia == secuencia.size()){
            prendido = random.nextInt(4) + 1;
            secuencia.add(prendido);
            // ACÁ VA AUDIO
            
            reproductor.reproLuz(prendido);
            System.out.println("arreglo de colores" + secuencia);
            indexSecuencia = 0;
            crearSecuencia = false;
          } else {
            // Si no agregamos ninguno más, iluminamos el correspondiente y vamos iluminando los demás
            prendido = secuencia.get(indexSecuencia);
            // ACÁ VA AUDIO
            // AudioRojo.play();
            reproductor.reproLuz(prendido);
            indexSecuencia++;
          }
          apagado = 1;
        }
      } else if(indexSecuencia == secuencia.size()) {
        crearSecuencia = true;
        indexSecuencia = 0;
        apagado = 1;
      }
    

    // Si el último número coincide con el el ingresado por el usuario se añade uno más
    // Al arreglo


    // pinta cada frame del juego de forma continua
    renderer.repaint();
  }



  // vamos a realizar los renderizados
  public void paint(Graphics2D g)  throws IOException {
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

    // Le decimos al programa que si la prendido es 1 entonces la pinte de un verde 
    // más claro
    if (prendido == 1) {
      g.setColor(verdeIluminado.brighter());
    } else {
      g.setColor(verdeApagado);
    }
    g.fillRect(0, 0, ANCHO / 2, ALTO / 2);

    if (prendido == 2) {
      g.setColor(rojoIluminado.brighter());
    } else {
      g.setColor(rojoApagado);
    }
    g.fillRect(ANCHO / 2, 0, ANCHO / 2, ALTO / 2);

    if (prendido == 3) {
      g.setColor(amarilloIluminado.brighter());
    } else {
      g.setColor(amarilloApagado);
    }
    g.fillRect(0, ALTO / 2, ANCHO / 2, ALTO / 2);

    if (prendido == 4) {
      g.setColor(azulIluminado.brighter());
    } else {
      g.setColor(azulApagado);
    }
    g.fillRect(ANCHO / 2, ALTO / 2, ANCHO / 2, ALTO / 2);

    g.setColor(grisTopo);
    g.fillRoundRect(225, 225, 250, 250, 250, 250);

    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", 1, 40));


    if (gameOver)
		{
      final BufferedImage image = ImageIO.read(new File("./imagen/repetir.png"));
      g.setColor(Color.WHITE);
      g.setFont(new Font("Arial", 1, 50));
			g.drawString("GAME OVER", 300, 310);
      g.drawImage(image, null, 300, 310);
		}
		else
		{
			g.drawString("Nivel: " + secuencia.size(), 275, 310);
		}

  
  }


  
  // Metodos para aplicar a la detección del mouse
  @Override
  public void mouseClicked(MouseEvent e) {
    // g.fillRect(0, 0, ANCHO / 2, ALTO / 2);
    // g.fillRect(ANCHO / 2, 0, ANCHO / 2, ALTO / 2);
    // g.fillRect(0, ALTO / 2, ANCHO / 2, ALTO / 2);
    // g.fillRect(ANCHO / 2, ALTO / 2, ANCHO / 2, ALTO / 2);

    // Estas variables nos entregan la posición del mouse en el eje x e y
    int x = e.getX(), y = e.getY();

    if (!crearSecuencia && !gameOver){
      if (x > 0 && x < ANCHO / 2 && y > 0 && y < ALTO / 2) {
        prendido = 1;
        ticks = 1;
      } else if (x > ANCHO / 2 && x < ANCHO && y > 0 && y < ALTO / 2) {
        prendido = 2;
        ticks = 1;
      } else if (x > 0 && x < ANCHO / 2 && y > ALTO / 2 && y < ALTO) {
        prendido = 3;
        ticks = 1;
      } else if (x > ANCHO / 2 && x < ANCHO && y > ALTO / 2 && y < ALTO) {
        prendido = 4;
        ticks = 1;
      }

      if (prendido != 0) {
        if (secuencia.get(indexSecuencia) == prendido) {
          // ACÁ VA AUDIO
          reproductor.reproLuz(prendido);
          // AudioRojo.play();
          indexSecuencia++;
        } else {
          // reproducimos el audio de GAMEOVER
          reproductor.reproGameOver();
          gameOver = true;
        }
      } else if (gameOver) {
        start();
        gameOver = false;
      }
    }
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
