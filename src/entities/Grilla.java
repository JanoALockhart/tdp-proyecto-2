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
				misBloques[fila][col] = new Bloque("/images/bloqueVerde.png",fila,col,false);				
			}
		}
		//test/////////////////////////////////////
		misBloques[0][0].setDirImagen("/images/bloqueVerde.png");
		misBloques[0][1].setDirImagen("/images/bloqueAzul.png");
		misBloques[1][0].setDirImagen("/images/bloqueRojo.png");
		misBloques[2][0].setDirImagen("/images/bloqueGris.png");
		
		List<Bloque> test = new LinkedList<Bloque>();
		test.add(misBloques[0][0]);
		test.add(misBloques[0][1]);
		test.add(misBloques[1][0]);
		test.add(misBloques[2][0]);
		
		miJuego.actualizarGUI(test);
		
		///////////////////////////////
		
		this.miJuego = miJuego;
		this.miFacbrica = new FabricaTetriminos(this);
		miTetriminoActual = new Ele(this);
//		miTetriminoActual = miFacbrica.generarNuevoTetrimino();
		//miTetriminoSiguiente = miFacbrica.generarNuevoTetrimino();
		this.miJuego.actualizarGUI(miTetriminoActual.getBloquesTetrimino());
	}
	
	public Bloque getBloque(int c, int f) {
		return misBloques[c][f];
	}
	
	/**
	 * Primero verifica que el tetrimino se pueda mover para la izquierda,de ser asi
	 * mueve todos los bloques del tetrimino hacia la izquierda.
	 */
	public void moverIzq() {
		List<Bloque> guardado = new LinkedList<Bloque>();
		boolean sePuede = true;
		List<Bloque> bloquesIzquierdos;
		bloquesIzquierdos = miTetriminoActual.getBLoquesMasIzq();
		for (Bloque b : bloquesIzquierdos) {
			if(b.getPosX() == 0) {
				sePuede = false;
				break;
			}
			guardado.add(b);
			if(misBloques[b.getPosX()-1][b.getPosY()].isOcupado()) {
				sePuede = false;
				break;
			}
			guardado.add(misBloques[b.getPosX()-1][b.getPosY()]);
		}
		if(sePuede) {
			miTetriminoActual.moverIzquierda();
			miJuego.actualizarGUI(guardado);
		}
	}
	
	/**
	 * Primero verifica que el tetrimino se pueda mover para la derecha, de ser asi
	 * mueve todos los bloques del tetrimino hacia la derecha.
	 */
	public void moverDer() {
		boolean sePuede = true;
		List<Bloque> guardado = new LinkedList<Bloque>();
		List<Bloque> bloquesDerechos;
		bloquesDerechos = miTetriminoActual.getBLoquesMasDer();
		for (Bloque b : bloquesDerechos) {
			if(b.getPosX() == misBloques[0].length) {
				sePuede = false;
				break;
			}
			guardado.add(b);
			if(misBloques[b.getPosX()+1][b.getPosY()].isOcupado()) {
				sePuede = false;
				break;
			}
			guardado.add(misBloques[b.getPosX()+1][b.getPosY()]);
		}
		if(sePuede) {
			miTetriminoActual.moverDerecha();
			miJuego.actualizarGUI(guardado);
		}
	}
	
	/**
	 * Primero verifica que cada bloque del tetrimino se pueda mover a la ubicación
	 * que le corresponde, de ser así el tetrimino rota.
	 */
	public void moverRotar() {
		boolean sePuede = true;
		List<Bloque> guardado = new LinkedList<Bloque>();
		List<Bloque> bloquesRotar;
		bloquesRotar = miTetriminoActual.getBloquesParaRotar();
		for (Bloque b : bloquesRotar) {
			guardado.add(b);
			if(misBloques[b.getPosX()][b.getPosY()].isOcupado()) {
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
		List<Bloque> guardado = new LinkedList<Bloque>();
		List<Bloque> bloquesDeAbajo;
		bloquesDeAbajo = miTetriminoActual.getBLoquesAbajo();
		for (Bloque b : bloquesDeAbajo) {
			
			System.out.println(misBloques[3][3].isOcupado());
			
			guardado.add(b);
			
			System.out.println(b.getPosX()+" "+(b.getPosY()+1));
			
			if(misBloques[b.getPosX()][b.getPosY()+1].isOcupado()) {
				sePuede = false;
				break;
			}
			guardado.add(misBloques[b.getPosX()][b.getPosY()+1]);			
		}
		if(sePuede) {
			miJuego.actualizarGUI(miTetriminoActual.getBloquesTetrimino());
			miTetriminoActual.caer();
			miJuego.actualizarGUI(guardado);
		}
		else {
			System.out.println("holaaa");
//			solidificarTetrimino();
//			VerificarLineas(miTetriminoActual);
		}
			
			
			
	}
	
	/**
	 * Este método se encarga de destruir el tetrimino actual,
	 * y solidificarlo con el resto de los bloques.
	 */	
	private void solidificarTetrimino() {
		generarNuevoTetrimino(misBloques[0].length);
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
					misBloques[fila][col].ocupar(misBloques[fila--][col].getDirImagen());;
					misBloques[fila--][col].desocupar();
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
			if(misBloques[col][num].isOcupado()) {
				miJuego.perder();
				break;
			}
		}
	}
}
