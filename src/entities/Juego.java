/**
 * 
 */
package entities;

/**
 *
 */
public class Juego {
	private int score;
	private Grilla miGrilla;
	private GUI interfazGrafica;
	private Timer temporizador;
	private static final int[] SCOREVALUES={100,200,500,800};
	
	//constantes para el metodo sincronizado operarJuego
	public static final int MOVER_ABAJO = 1;
	public static final int MOVER_IZQUIERDA = 2;
	public static final int MOVER_DERECHA = 3;
	public static final int ROTAR = 4;
	/**
	 * Constructor clase juego
	 */
	public Juego(GUI gui) {
		temporizador = new Timer(this);
		interfazGrafica = gui;
		miGrilla = new Grilla(this);
		score = 0;
	}
	
	/**
	 * Metodo que suma una cantidad de puntos, pasado por
	 * parametro, a los puntos ya almacenados.
	 * @param cant Es la cantidad de puntos que se quieren sumar.
	 */
	public void addScore(int cantFilas) {
		score+=SCOREVALUES[cantFilas-1];
		interfazGrafica.actualizarScore(score);
	}
	
	/**
	 * Metodo que provoca que la grilla haga
	 * que su tetrimino actual se mueva un bloque
	 * hacia abajo
	 */
	private void hacerCaer() {
		miGrilla.bajarTetriminoActual();
	}
	
	/**
	 * Metodo que provoca que la grilla haga que su tetrimino
	 * actual se mueva a la izquierda en un bloque
	 */
	private void avisarIzq() {
		miGrilla.moverIzq();
	}
	
	/**
	 * Metodo que provoca que la grilla haga que su
	 * tetrimino actual se mueva a la derecha en un boque
	 */
	private void avisarDer() {
		miGrilla.moverDer();
	}
	
	/**
	 * Metodo que provoca que la grilla haga que su tetrimino
	 * actual rote
	 */
	private void avisarRotar() {
		miGrilla.moverRotar();
	}
	
	
	/**
	 * Metodo que provoca que la interfaz gráfica actualice 
	 * su cronometro.
	 * @param segundos Es un string que representa el tiempo
	 * mm:ss que se colocará en la GUI.
	 */
	public void actualizarTimerGui(String segundos) {
		interfazGrafica.actualizarTimer(segundos);
	}
	
	/**
	 * Metodo que envia a la gui los bloques que fueron modificados
	 * para que la gui cambie los correspondientes labels.
	 */
	public void actualizarGUI(Iterable<Bloque> bloquesModificados) {
		interfazGrafica.actualizarLabels(bloquesModificados);
	}
	
	/**
	 * Metodo que le pide a la gui que actualice el tablero que
	 * muestra el tetrimino siguiente.
	 * @param siguiente Es el siguiente tetrimino a mostrar
	 */
	public void actualizarTetriSiguiente(Tetrimino sigTetri) {
		interfazGrafica.actualizarNuevoTetrimino(sigTetri);
	}
	
	public synchronized void operarJuego(int operacion) {
		switch(operacion) {
			case MOVER_ABAJO: {hacerCaer(); break;}
			case MOVER_IZQUIERDA: {avisarIzq(); break;}
			case MOVER_DERECHA: {avisarDer(); break;}
			case ROTAR: {avisarRotar(); break;}
		}
	}
	
	/**
	 * Metodo que llama a los metodos correspondientes de 
	 * otras clases cuando el jugador pierde
	 */
	public void perder() {		
		
		temporizador.detener();
		interfazGrafica.mostrarPantallaFinal();	
	}
	
	
}
