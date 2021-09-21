/**
 * 
 */
package entities;

/**
 *
 */
public class Juego {
	private int score;
	private GUI interfazGrafica;
	//private Grilla miGrilla;
	//private Timer temporizador;
	
	//TODO ver que pasarle a la grilla, recibir gui?
	/**
	 * Constructor clase juego
	 */
	public Juego() {
		temporizador = new Timer(this);
		miGrilla = new Grilla();
		//interfazGrafica = ?
	}
	
	/**
	 * Metodo que suma una cantidad de puntos, pasado por
	 * parametro, a los puntos ya almacenados.
	 * @param cant Es la cantidad de puntos que se quieren sumar.
	 */
	public void addScore(int cant) {
		score+=cant;
	}
	
	/**
	 * Metodo que provoca que la grilla haga
	 * que su tetrimino actual se mueva un bloque
	 * hacia abajo
	 */
	public void hacerCaer() {
		miGrilla.bajarTetriminoActual();
	}
	
	/**
	 * Metodo que provoca que la grilla haga que su tetrimino
	 * actual se mueva a la izquierda en un bloque
	 */
	public void avisarIzq() {
		miGrilla.moverIzq();
	}
	
	/**
	 * Metodo que provoca que la grilla haga que su
	 * tetrimino actual se mueva a la derecha en un boque
	 */
	public void avisarDer() {
		miGrilla.moverDer();
	}
	
	/**
	 * Metodo que provoca que la grilla haga que su tetrimino
	 * actual rote
	 */
	public void avisarRotar() {
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
	 * Metodo que avisa a la gui para actualizar
	 * los labels mostrados por pantalla
	 */
	public void actualizarGUI() {
		interfazGrafica.actualizarLabels();
	}
	
	/**
	 * Metodo que llama a los metodos correspondientes de 
	 * otras clases cuando el jugador pierde
	 */
	public void perder() {
		interfazGrafica.mostrarPantallaFinal();	
	}
	
	
}
