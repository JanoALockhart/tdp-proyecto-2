package entities;

import java.util.LinkedList;
import java.util.List;

public class Grilla {
	
	protected Juego miJuego;
	protected Tetrimino miTetriminoActual;
	protected Tetrimino miTetriminoSiguiente;
	protected Bloque misBloques[][];
	protected FabricaTetriminos miFacbrica;
	
	public Grilla(Juego miJuego) {
		misBloques = new Bloque[21][10];
		for(int fila = 0; fila < 21; fila++) {
			for(int col = 0; col < 10; col++) {
				misBloques[fila][col] = new Bloque("/images/bloqueVacio.png",fila,col,false);				
			}
		}
		this.miJuego = miJuego; 
	}
	
	public Bloque getBloque(int f, int c) {
		return misBloques[f][c];
	}
	
	/**
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
	
	/**
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
	
	/**
	 * Primero verifica que cada bloque del tetrimino se pueda mover a la ubicación
	 * que le corresponde, de ser así el tetrimino rota.
	 */
	public void moverRotar() {
		boolean sePuede = true;
		List<Bloque> bloquesRotar;
		bloquesRotar = miTetriminoActual.getBloquesParaRotar();
		for (Bloque b : bloquesRotar) {
			if(misBloques[b.getPosX()+1][b.getPosY()].isOcupado()) {
				sePuede = false;
				break;
			}
		}
		if(sePuede)
			miTetriminoActual.rotar();
	}
	
	/**
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
		if(sePuede) {
			miTetriminoActual.caer();
		}
		else {
			solidificarTetrimino();
			VerificarLineas(miTetriminoActual);
		}
			
			
			
	}
	
	/**
	 * Este método se encarga de destruir el tetrimino actual,
	 * y solidificarlo con el resto de los bloques.
	 */	
	private void solidificarTetrimino() {
		generarNuevoTetrimino(misBloques.length);
		miTetriminoActual = miTetriminoSiguiente;
		miTetriminoSiguiente = miFacbrica.generarNuevoTetrimino();		
	}
	
	/**
	 * Este método se encarga de ir verificando si la fila de cada bloque
	 * que conforma el tetrimino se puede romper
	 * @param tetri: es el tetrimino actual 
	 */
	private void VerificarLineas(Tetrimino tetri) {
		boolean ocupado = true;
		for(int col = 0; col < misBloques[0].length; col++) {
			if(!misBloques[tetri.A.getPosY()][col].isOcupado()) {
				ocupado = false;
				break;
			}
		}
		if(ocupado)
			romperLineas(tetri.A.getPosY());
		ocupado = true;
		
		for(int col = 0; col < misBloques[0].length; col++) {
			if(!misBloques[tetri.B.getPosY()][col].isOcupado()) {
				ocupado = false;
				break;
			}
		}
		if(ocupado)
			romperLineas(tetri.B.getPosY());
		ocupado = true;
		
		for(int col = 0; col < misBloques[0].length; col++) {
			if(!misBloques[tetri.C.getPosY()][col].isOcupado()) {
				ocupado = false;
				break;
			}
		}
		if(ocupado)
			romperLineas(tetri.C.getPosY());
		ocupado = true;
		
		for(int col = 0; col < misBloques[0].length; col++) {
			if(!misBloques[tetri.pivote.getPosY()][col].isOcupado()) {
				ocupado = false;
				break;
			}
		}
		if(ocupado)
			romperLineas(tetri.pivote.getPosY());		
		
	}
	/**
	 * Este método se encarga de romper toda una fila,
	 * osea que recorre todos lo bloques de una fila y los pone en desocupados.
	 * @param fila: es la fila que tiene que romper
	 */
	private void  romperLineas(int fila) {
		for(int col = 0; col < misBloques[0].length; col++) {
			misBloques[fila][col].desocupar();
		}
		bajarLineas(fila);
	}
	/**
	 * Este método se encarga de ir bajando las filas, 
	 * una vez que se rompe la fila que le pasan por parámetro.
	 * @param filaRota: es la fila que se acaba de romper.
	 */
	private void bajarLineas(int filaRota) {
		List<Bloque> listaDeGuardado = new LinkedList<Bloque>();		
		for(int fila = filaRota; filaRota > 1; filaRota--) {
			for(int col = 0; col < misBloques[0].length; col++) {
				if(misBloques[fila--][col].isOcupado()) {
					misBloques[fila][col].isOcupado();
					misBloques[fila][col].setDirImagen(misBloques[fila--][col].getDirImagen());
					listaDeGuardado.add(misBloques[fila][col]);
				}
			}
		}		
	}
	
	/*
	 *Verifica que las posiciones que ocupara el nuevo tetrimino
	 *esten libres, si no estan libres, grilla llama a Juego.perder()
	 */
	private void generarNuevoTetrimino(int num) {
		for(int col = 0; col < misBloques[0].length; col++) {
			if(misBloques[num][col].isOcupado()) {
				miJuego.perder();
				break;
			}
		}
	}
}
