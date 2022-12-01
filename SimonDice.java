
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
  // prendido indica la luz que está prendida siendo 0 ninguna, verde = 1, rojo = 2, amarillo = 3, azul = 4
  // apagado funcionaria con un indicador para apagar la luz
  // IndexSecuencia va a realizar el recorrido del arreglo random tanto de forma automatica como cuando se de click
  // veolocidad la cantidad de ticks que debe haber entre colores

  // Variable para saber si la secuencia está creada
  public static boolean ejecutandoSecuencia = true;

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

  // Se ejecutá continuamente a la velocidad indicada por el delay
  @Override
  public void actionPerformed(ActionEvent event) {

    ticks++; // Aumenta el contador de ticks
    
    if (ticks % velocidad == 0) { // cuando sea 0 y la velocidad se ejecutará este trozo de código
      prendido = 0; // apaga la luz
      if (apagado >= 0){
          apagado--; // cuando pasen los ticks y el apagado esté en 1 se restará
      }
    }
      if (ejecutandoSecuencia) { // Si la secuencia se encuentra en ejecución entra
        if(apagado <= 0) { // Si se encuentra apagada la luz
          if (indexSecuencia == secuencia.size()){ // Si el indice se encuentra en la última posición debemos agregar
            // un numero random más
            prendido = random.nextInt(4) + 1; // indicamos que el que se debe prender es el que agregamos
            secuencia.add(prendido);
            
            reproductor.reproLuz(prendido); // repoducimos el sonido del agregado
            System.out.println("arreglo de colores" + secuencia); // debug
            indexSecuencia = 0; // reseteamos el index para que empice devuelta
            ejecutandoSecuencia = false; // Indicamos que ahora la secuencia no se ejecuta para que el usuario haga el ingreso
          } else {
            // Si no agregamos ninguno más, iluminamos el correspondiente y vamos iluminando los demás
            prendido = secuencia.get(indexSecuencia);
            reproductor.reproLuz(prendido); // reproducimos el sonido del prendido
            indexSecuencia++; // aumentamos el indice para seguir recorriendo el arreglo
          }
          apagado = 1; // apagamos el color
        }
      } else if(indexSecuencia == secuencia.size()) { // Si nos encotnramos en la última posición y no se estaba ejecutando
        // lo cambiamos a true y reseteamos la secuencia indicando que se encuentra apagado
        ejecutandoSecuencia = true;
        indexSecuencia = 0;
        apagado = 1;
      }
    
    // renderiza constantemente 
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

    // Indicamos el color que se debe renderizar dependiendo sise encuentra apagado
    // o prendido, para los prendidos utilizamos el método birghter de Color que
    // aumenta el brillo del mismo
    if (prendido == 1) {
      g.setColor(verdeIluminado.brighter());
    } else {
      g.setColor(verdeApagado);
    }
    g.fillRect(0, 0, ANCHO / 2, ALTO / 2); // esta función indica la posición
    // en la que se va a renderizar el cuadrado

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

    // Indicamos el circulo negro de adelante
    g.setColor(grisTopo);
    g.fillRoundRect(225, 225, 250, 250, 250, 250);
   
    if (gameOver) // en el game over mostramos la puntuación y renderizamos la imagen para volver a empezar
		{
      // Agregamos la constante de la imagen de repetir
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
      g.drawImage(image, null, 325, 405); // renderizamos la imagen guardada en la constante
		}
		else
		{
      // en el caso de estar en juego renderizamos el nivel 
      g.setColor(Color.WHITE);
      g.setFont(new Font("Arial", 1, 40));
			g.drawString("Nivel: " + secuencia.size(), 275, (ALTO/2 +10));
		}
  
  }

  // Función para devolver la velocidad en base a la cantidad de botones prendidos
  public int velocidad(int cantidadDeBotones) {
    //1/(LOG10(10*N^2))
    // double velocidadIncial = 1;
    
    // return velocidadIncial / Math.log10(10*(cantidadDeBotones*cantidadDeBotones));
    double velocidadIncial = 100;
    
    // Hacemos un return de la velocidad inicial pasada por la función
    return (int)(velocidadIncial / Math.log10(10*(cantidadDeBotones*cantidadDeBotones)));
  }
  
  // Metodos para aplicar a la detección del mouse
  @Override
  public void mouseClicked(MouseEvent e) {

    // Estas variables nos devuelven la posición del mouse en el eje x e y cada
    // vez que clickeamos
    int x = e.getX(), y = e.getY();

    if (!ejecutandoSecuencia && !gameOver){ // Si no se encuentra ejecutando la secuencia
      // y no estamos en game Over ejecutadmoos lo siguiente

      // determinamos las hitbox de los colores en base al ancho y alto en los ejes x e y
      if (x > 0 && x < ANCHO / 2 && y > 0 && y < ALTO / 2) {
        prendido = 1; // prendemos el color correspondiente a la hitbox señalada
        ticks = 1; // reseteamos el tick a 0 para que no se apague al instante por la condición
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
        if (secuencia.get(indexSecuencia) == prendido) { // comprobamos que el indice en el que se encuentra la secuuencia
          // sea igual al color que prendimos, si es el mismo ejecutamos su sonido
          reproductor.reproLuz(prendido);
          // AudioRojo.play();
          indexSecuencia++; // aumentamos el indice para pasar al siguiente
          velocidad = velocidad(secuencia.size()); // aumento la velocidad con la función de velocidad
        } else { // si no es el mismo el indice que el que elegimos mostramos el game over
          // reproducimos el audio de GAMEOVER
          reproductor.reproGameOver();
          gameOver = true;
        }
      }
    }

    if (gameOver) {
      // Si estamos en game over solo podremos dar click en la hitbox de la imagen de repetir
      if (x > 314 && x < 385 && y > 424 && y < 491) {
        start(); // se volvera a resetear el valor del arraylist

        gameOver = false; // se vuelve el valor del gameover a 0
      }
      
    }
  }

  // Otros métodos para el evento del mouse
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
