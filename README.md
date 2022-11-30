# Simon dice
“Simon” (léase SAIMON) es un juego electrónico de memorización creado por Ralph Baer y Howard J. Morrison en 1978. Fue programado por Lenny Cope, y tuvo mucha popularidad en la década del ‘80.

El dispositivo crea una secuencia de tonos y luces requiriendo que el jugador (haciendo uso de su memoria visual y auditiva) repita dicha secuencia en el orden en que fue generada. Mientras el jugador lo haga correctamente, el dispositivo continúa agregando luces a la secuencia, tornándola progresivamente más larga y difícil de memorizar. Cuando el jugador erra, el juego termina.

El dispositivo original tiene la forma de una corona circular dividida en cuatro cuadrantes. Cada cuadrante es un gran botón translúcido de un color diferente y tiene asociado un tono musical característico:

- Cuadrante inferior derecho (color Azul) nota musical mi4 (659.255 Hz)
- Cuadrante inferior izquierdo (color: Amarillo) nota musical do#4 (554.365 Hz)
- Cuadrante superior derecho (color: Rojo) nota musical la3 (440 Hz)
- Cuadrante superior izquierdo (color Verde) nota musical mi3 (329.628 Hz)

El juego comienza iluminando uno de los botones de colores y emitiendo el sonido asociado; luego hace una pausa, y habilita al jugador a presionar ese mismo botón. Si el jugador lo hace correctamente, entonces el dispositivo repite el color y el sonido generados inicialmente, agregando a continuación un nuevo elemento a la secuencia y vuelve a hacer una pausa esperando que el jugador repita la secuencia. El juego continúa de ese modo hasta que el participante comete un error.

Se pide construir una versión Java del juego Simon que corra bajo Windows con las siguientes consideraciones:

Interfaz Gráfica
El programa debe emplear algún tipo de interfaz gráfica, que muestre botones presionables por el usuario y que “se iluminen” (o “se destaquen” de algún modo) cada vez que se activan. Estos botones no necesariamente deben tener forma de corona circular, pudiendo ser rectangulares.

Velocidad creciente
Al iniciarse el juego, el programa mantendrá “iluminado” el primer botón de la secuencia por un segundo. A partir de allí, el tiempo durante el que se “ilumina” cada botón de la secuencia generada irá disminuyendo paulatinamente. En términos matemáticos, cuando la secuencia a reproducir conste de N botones, el tiempo (en segundos) durante el que se “iluminará” cada botón será 1/(LOG10(10*N^2)). Así, por ejemplo,
Para una secuencia de longitud 1, la duración de la luz será de 1.00 segundo
Para una secuencia de longitud 10, la duración de cada luz será de 0.33 segundos
Para una secuencia de longitud 20, la duración de cada luz será de 0.28 segundos
Para una secuencia de longitud 100, la duración de cada luz será de 0.20 segundos

Sonido
Mientras un botón está activado (ya sea porque está siendo presionado por el jugador, o porque es parte de la secuencia generada), el programa deberá reproducir la nota musical correspondiente. Ayuda: Explore la clase AudioFormat

Puntuación
Al finalizar el juego, el programa indicará la longitud de la máxima cadena que el jugador pudo reproducir correctamente.

Consultas
El alumno podrá dirigir sus consultas al docente tantas veces como necesite. Siempre y cuando sean para aclarar conceptos de la consigna del presente trabajo y no de la resolución propiamente dicha.

Fecha de Entrega
El trabajo debe ser subido al Campus Virtual conteniendo:
⦁	Nombre y Apellido del autor de la tarea
⦁	Código fuente con comentarios explicativos
⦁	Breve reseña exponiendo la estrategia de resolución
⦁	Capturas de pantalla de las pruebas de ejecución