
// imports
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

public class SimonDice implements ActionListener, MouseListener { // Implementamos el Listener para que escuhe
  // este al tanto de los eventos del mouse
  
  public static SimonDice simon;

  public static Renderer renderer; // Instanciamos la clase renderer que es la encargada de renderizar la ventana

  public static final int ANCHO = 700, ALTO = 700; // usamos final para asignar las constantes de ancho y alto de la ventana

  // Definimos variables que utilizaremos en el programa
  public static int prendido = 0, ticks, apagado = 0, indexSecuencia = 0, velocidad = 100; 

  // Variable para saber si la secuencia está creada
  public static boolean crearSecuencia = true;

  // Declaramos el array que se rellenara con la secuencia
  public ArrayList<Integer> secuencia;

  // variable para saber si perdimos
  private boolean gameOver;

  // Declaramos la clase random
  public Random random;

  // Instanceamos la clase reproductor para poder acceder a los diferentes audios
  ReproducirAudio reproductor = new ReproducirAudio();

  // Constructor
  public SimonDice() {
  
    // Instanceamos la clase Jframe en frame que corresponde al espacio de la ventana
    JFrame frame = new JFrame("Simon Dice");

    Timer timer;
    
    renderer = new Renderer();

    // Seteamos caracteristicas de la ventana
    frame.setSize(ANCHO , ALTO); // Revisar esto, aumentar en la constante y no sumar ahora
    frame.setVisible(true); // visivilidad de la ventana
    frame.add(renderer); // le pasamos el componente renderer para indicarle que debemos renderizar
    frame.addMouseListener(this); // le agregamos el listener para que esté escuchando que ocurre en this, this hace referencia
    // a la propia clase SimonDice
    frame.setResizable(false); // desactivamos la reecalabilidad ya que nuestro programa no tiene diseño responsive
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Accedemos a la constante de EXIT de la clase JFrame para poder
    // cerrar la ventana
    
    start(); // Ejecutamos la función start para que se cree el arreglo con la secuencia

    // System.out.println(velocidad(10));
    // System.out.println(velocidad(20));
    // System.out.println(velocidad(100));

    
    timer = new Timer(1, this); // Instanciamos el timer que ejecutará de forma repetida (dependiendo el delay indicado)
    // el código
    timer.start(); // indicamos que inicie el timer
    
    
  }

  // Método que rellena la secuencia
  public void start() {
    random = new Random(); // se crea 

    secuencia = new ArrayList<Integer>();
    indexSecuencia = 0;
    apagado = 1;
    prendido = 0;
    ticks = 0;
  }

  // Clase de punto de entrada del programa
  public static void main(String[] args) throws IOException{
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
      g.setColor(Color.RED);
      g.setFont(new Font("Arial", 1, 30));
			g.drawString("GAME OVER", 257, 295);
      g.setColor(Color.WHITE);
      g.setFont(new Font("Arial", 1, 22));
      g.drawString("Niveles completados", 245, 330);
      g.setColor(Color.RED);
      g.setFont(new Font("Arial", 1, 40));
      g.drawString(""+(secuencia.size() - 1), ANCHO / 2 - 12, 380);
      g.drawImage(image, null, 325, 405);
		}
		else
		{
			g.drawString("Nivel: " + secuencia.size(), 275, 310);
		}
  
  }

  public int velocidad(int cantidadDeBotones) {
    //1/(LOG10(10*N^2))
    // double velocidadIncial = 1;
    
    // return velocidadIncial / Math.log10(10*(cantidadDeBotones*cantidadDeBotones));
    double velocidadIncial = 100;
    
    return (int)(velocidadIncial / Math.log10(10*(cantidadDeBotones*cantidadDeBotones)));
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
          velocidad = velocidad(secuencia.size()); // aumento la velocidad con la función de velocidad
        } else {
          // reproducimos el audio de GAMEOVER
          reproductor.reproGameOver();
          gameOver = true;
        }
      }
    }

    if (gameOver) {
      System.out.print("pos x :" + x);
      System.out.print("pos y :" + y);
      if (x > 314 && x < 385 && y > 424 && y < 491) {
        // System.out.print("Entro en el repetir");
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
