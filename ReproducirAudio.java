public class ReproducirAudio {

  Audio AudiVerde = new Audio("./audio/verde.wav");
  Audio AudioAzul = new Audio("./audio/azul.wav");
  Audio AudioRojo = new Audio("./audio/rojo.wav");
  Audio AudioAmarillo = new Audio("./audio/amarillo.wav");
  Audio gameOverAudio = new Audio("./audio/gameOver.wav");

  public void reproLuz(int luzPrendida) {
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

  public void reproGameOver() {
    gameOverAudio.play();
  }
  
}
