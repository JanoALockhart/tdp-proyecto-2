package entities;

import java.util.LinkedList;
import java.util.List;

public class Grilla {
	
	protected Juego miJuego;
	protected Tetrimino miTetriminoActual;
	protected Tetrimino miTetriminoSiguiente;
	protected Bloque misBloques[][]; 
	
	public Grilla(Juego miJuego) {
		misBloques = new Bloque[21][10];
		for(int fila = 0; fila < 21; fila++) {
			for(int col = 0; col < 10; col++) {
				misBloques[fila][col] = new Bloque("/images/bloqueVacio.png",fila,col,false);				
			}
		}
		this.miJuego = miJuego; 
	}
	
	public void chequearFilas(Tetrimino tetri) {
		
	}
	
	public Bloque getBloque(int f, int c) {
		return misBloques[f][c];
	}
	
	/*
	 * Primero verifica que el tetrimino se pueda mover para la izquierda,de ser asi
	 * mueve todos los bloques del tetrimino hacia la izquierda.
	 */
	public void moverIzq() {
		boolean sePuede = true;
		List<Bloque> bloquesIzquierdos;
		bloquesIzquierdos = miTetriminoActual.getBLoquesMasIzq();
		for (Bloque b : bloquesIzquierdos) {
			if(misBloques[b.getPosX()-1][b.getPosY()].isOcupado()) {
				sePuede = false;
				break;
			}
		}
		if(sePuede)
			miTetriminoActual.moverIzquierda();
	}
	
	/*
	 * Primero verifica que el tetrimino se pueda mover para la derecha, de ser asi
	 * mueve todos los bloques del tetrimino hacia la derecha.
	 */
	public void moverDer() {
		boolean sePuede = true;
		List<Bloque> bloquesDerechos;
		bloquesDerechos = miTetriminoActual.getBLoquesMasDer();
		for (Bloque b : bloquesDerechos) {
			if(misBloques[b.getPosX()+1][b.getPosY()].isOcupado()) {
				sePuede = false;
				break;
			}
		}
		if(sePuede)
			miTetriminoActual.moverDerecha();
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
		boolean sePuede = true;
		List<Bloque> bloquesDeAbajo;
		bloquesDeAbajo = miTetriminoActual.getBLoquesMasDer();
		for (Bloque b : bloquesDeAbajo) {
			if(misBloques[b.getPosX()][b.getPosY()-1].isOcupado()) {
				sePuede = false;
				break;
			}
		}
		//if(sePuede)
			
			
			
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
