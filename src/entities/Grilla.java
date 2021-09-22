package entities;

public class Grilla {
	
	//protected Juego miJuego();
	protected Tetrimino miTetriminoActual;
	protected Tetrimino miTetriminoSiguiente;
	protected Bloque misBloques; 
	
	public Grilla() {
		
	}
	
	public void chequearFilas(Tetrimino tetri) {
		
	}
	
	/*
	 * Primero verifica que el tetrimino se pueda mover para la izquierda,de ser asi
	 * mueve todos los bloques del tetrimino hacia la izquierda.
	 */
	public void moverIzq() {
		
	}
	
	/*
	 * Primero verifica que el tetrimino se pueda mover para la derecha, de ser asi
	 * mueve todos los bloques del tetrimino hacia la derecha.
	 */
	public void moverDer() {
		
	}
	
	/*
	 * Primero verifica que cada bloque del tetrimino se pueda mover a la ubicación
	 * que le corresponde, de ser así el tetrimino rota.
	 */
	public void moverRotar() {
		
	}
	
	/*
	 * Verifica si el tetriminoactual puede caer, de ser asi todos los bloques del tetrimino
	 * se bajan una posición en la grilla, caso contrario el tetrimino se solidifica. 
	 */
	public void bajarTetriminoActual() {
		
	}
	
	@SuppressWarnings("unused")
	private void solidificarTetrimino() {
		
	}
	
	/*
	 * Esto fue modificado respecto al diagrama 
	 */
	@SuppressWarnings("unused")
	private boolean VerificarLineas(Tetrimino tetri) {
		return false;
	}
	
	@SuppressWarnings("unused")
	private void  romperLineas(int filaAbajo, int cantfilas) {
		
	}
	
	@SuppressWarnings("unused")
	private void bajarLineas(int filaArriba , int cantFilas) {
		
	}
	
	@SuppressWarnings("unused")
	/*
	 *Verifica que las posiciones que ocupara el nuevo tetrimino
	 *esten libres, si no estan libres, grilla llama a Juego.perder()
	 */
	private void generarNuevoTetrimino(int num) {
		
	}
}
