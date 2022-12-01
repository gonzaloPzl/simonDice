public class ReproducirAudio {

  // Selecionamos los audios y los asignamos a los colores correspondientes 
  // Hacemos uso de la libreria de Audio

  Audio AudiVerde = new Audio("./audio/verde.wav");
  Audio AudioAzul = new Audio("./audio/azul.wav");
  Audio AudioRojo = new Audio("./audio/rojo.wav");
  Audio AudioAmarillo = new Audio("./audio/amarillo.wav");
  Audio gameOverAudio = new Audio("./audio/gameOver.wav");

  // Creamos el método que recibe la luz que está prendida y en base a eso reproduce
  // el audio correspondiente 
  public void reproLuz(int luzPrendida) {
    // Para selecionar el audio hacemos un switch
    switch(luzPrendida) {
      case 1:
        AudiVerde.play();
        break;
      case 2:
        AudioRojo.play();
        break;
      case 3:
        AudioAmarillo.play();
      case 4:
        AudioAzul.play();
      default:
        break;
    }
  }

  // Creamos otro método para reproducir unicamente el game over y separar las lógicas
  public void reproGameOver() {
    gameOverAudio.play();
  }
  
}
