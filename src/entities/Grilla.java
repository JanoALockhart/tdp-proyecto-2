package entities;

import java.util.LinkedList;
import java.util.List;

public class Grilla {
	
	protected Juego miJuego;
	protected Tetrimino miTetriminoActual;
	protected Tetrimino miTetriminoSiguiente;
	protected Bloque misBloques[][];
	protected FabricaTetriminos miFabrica;
	
	public Grilla(Juego miJuego) {
		misBloques = new Bloque[10][21];
		for(int fila = 0; fila < 21; fila++) {
			for(int col = 0; col < 10; col++) {
				misBloques[col][fila] = new Bloque("/images/bloqueVacio.png",col,fila,false);				
			}
		}
		
		this.miJuego = miJuego;
		this.miFabrica = new FabricaTetriminos(this);
		miTetriminoActual = new Ele(this);
		miTetriminoActual.inicializarTetrimino();
		miTetriminoSiguiente = new Ele(this);
		miJuego.actualizarTetriSiguiente(miTetriminoSiguiente);
		this.miJuego.actualizarGUI(miTetriminoActual.getBloquesTetrimino());
	}
	
	public Bloque getBloque(int c, int f) {
		Bloque ret=null;
		if(0<=c && c<=9 && 0<=c && c<=20) {
			ret=misBloques[c][f];
		}
		return ret;
	}
	
	/**
	 * Primero verifica que el tetrimino se pueda mover para la izquierda,de ser asi
	 * mueve todos los bloques del tetrimino hacia la izquierda.
	 */
	public void moverIzq() {
		boolean sePuede = true;
		List<Bloque> bloquesIzquierdos;
		Iterable<Bloque> bloquesAnteriores;
		bloquesIzquierdos = miTetriminoActual.getBLoquesMasIzq();
		for (Bloque b : bloquesIzquierdos) {
			if(b.getPosX() == 0) {
				sePuede = false;
				break;
			}
			if(misBloques[b.getPosX()-1][b.getPosY()].isOcupado()) {
				sePuede = false;
				break;
			}
		}
		if(sePuede) {
			bloquesAnteriores=miTetriminoActual.moverIzquierda();	
			miJuego.actualizarGUI(bloquesAnteriores);
			miJuego.actualizarGUI(miTetriminoActual.getBloquesTetrimino());
		}
	}
	
	/**
	 * Primero verifica que el tetrimino se pueda mover para la derecha, de ser asi
	 * mueve todos los bloques del tetrimino hacia la derecha.
	 */
	public void moverDer() {
		boolean sePuede = true;
		List<Bloque> bloquesDerechos;
		bloquesDerechos = miTetriminoActual.getBLoquesMasDer();
		Iterable<Bloque> bloquesAnteriores;
		for (Bloque b : bloquesDerechos) {
			if(b.getPosX() == misBloques[0].length-1) {
				sePuede = false;
				break;
			}
			if(b.getPosX()==9 || misBloques[b.getPosX()+1][b.getPosY()].isOcupado()) {
				sePuede = false;
				break;
			}
		}
		if(sePuede) {
			bloquesAnteriores=miTetriminoActual.moverDerecha();			
			miJuego.actualizarGUI(bloquesAnteriores);
			miJuego.actualizarGUI(miTetriminoActual.getBloquesTetrimino());
		}
	}
	
	/**
	 * Primero verifica que cada bloque del tetrimino se pueda mover a la ubicación
	 * que le corresponde, de ser así el tetrimino rota.
	 */
	public void moverRotar() {
		boolean sePuede = true;
		List<Bloque> bloquesRotar;
		bloquesRotar = miTetriminoActual.getBloquesParaRotar();
		Iterable<Bloque> bloquesAnteriores;
		for (Bloque b : bloquesRotar) {
			if(b==null || misBloques[b.getPosX()][b.getPosY()].isOcupado()) {
				sePuede = false;
				break;
			}
		}
		if(sePuede) {
			bloquesAnteriores=miTetriminoActual.rotar();
			miJuego.actualizarGUI(bloquesAnteriores);
			miJuego.actualizarGUI(miTetriminoActual.getBloquesTetrimino());
		}
	}
	
	/**
	 * Verifica si el tetriminoactual puede caer, de ser asi todos los bloques del tetrimino
	 * se bajan una posición en la grilla, caso contrario el tetrimino se solidifica.
	 */
	public void bajarTetriminoActual() {
		boolean sePuede = true;
		List<Bloque> bloquesDeAbajo;
		Iterable<Bloque> bloquesAnteriores;
		bloquesDeAbajo = miTetriminoActual.getBLoquesAbajo();
		for (Bloque b : bloquesDeAbajo) {
			if(b.getPosY()==20 || misBloques[b.getPosX()][b.getPosY()+1].isOcupado()) {
				sePuede = false;
				break;
			}	
		}
		if(sePuede) {
			bloquesAnteriores=miTetriminoActual.caer();
			miJuego.actualizarGUI(bloquesAnteriores);
			miJuego.actualizarGUI(miTetriminoActual.getBloquesTetrimino());
		}
		else {			
			verificarLineas(miTetriminoActual);
			solidificarTetrimino();
		}
			
			
	}
	
	/**
	 * Este método se encarga de cambiar el tetrimino actual
	 * por el tetrimino siguiente y si este nuevo tetrimino
	 * puede ocupar sus posiciones. En caso de que no pueda, 
	 * el juego termina
	 */	
	private void solidificarTetrimino() {
		miTetriminoActual = miTetriminoSiguiente;
		if(puedeInicializar(miTetriminoActual)){
			miTetriminoActual.inicializarTetrimino();
			miJuego.actualizarGUI(miTetriminoActual.getBloquesTetrimino());
			
			miTetriminoSiguiente = new Ele(this);
			miJuego.actualizarTetriSiguiente(miTetriminoSiguiente);
		}else{
			System.out.println("perdiste");
			miJuego.perder();
		}
	}
	
	/**
	 * Verifica si la posicion que ocupa el tetrimino no inicializado
	 * puede ocupar los bloques a los que apunta
	 * @param tetri Es un tetrimino que todavia no esta incializado y 
	 * se quiere verificar que puede hacerlo.
	 * @return true si las posiciones a las que apunta estan libres,
	 * false en caso contrario
	 */
	boolean puedeInicializar(Tetrimino tetri){
		boolean puede = true;

		for(Bloque bloq : tetri.getBloquesTetrimino()){
			if(bloq.isOcupado()){
				puede = false;
				break;
			}
		}

		return puede;
	}
	
	/**
	 * Este método se encarga de ir verificando si la fila de cada bloque
	 * que conforma el tetrimino se puede romper
	 * @param tetri: es el tetrimino actual 
	 */
	private void verificarLineas(Tetrimino tetri) {
		boolean ocupado = true;
		int filasRotas=0;
		int max=0;
		Iterable<Bloque> bloquesDelTetri = tetri.getBloquesTetrimino();

		for (Bloque b : bloquesDelTetri) {		
			if(lineaLlena(b.getPosY())) {
				romperLineas(b.getPosY());
				filasRotas++;
				max=b.getPosY()>max?b.getPosY():max;
			}
			ocupado = true;
		}
		if(filasRotas>0) {
			miJuego.addScore(filasRotas);
			bajarLineas(max);
		}
	}
	
	/**
	 * Metodo que verifica si todas las celdas de una fila 
	 * estan ocupadas.
	 * @param fila Es el numero de la fila que se quiere verificar
	 * @return true si la linea esta llena, false
	 */
	private boolean lineaLlena(int fila) {
		boolean ocupado = true;
		
		for(int col = 0; col < misBloques.length && ocupado; col++) {
			if(!misBloques[col][fila].isOcupado()) {
				ocupado = false;
			}
		}
		
		return ocupado;
	}
	
	
	/**
	 * Este método se encarga de romper toda una fila,
	 * osea que recorre todos lo bloques de una fila y los pone en desocupados.
	 * @param fila: es la fila que tiene que romper
	 */
	private void  romperLineas(int fila) {
		List<Bloque> guardado = new LinkedList<Bloque>();
		//System.out.println("holaaaaaaaaaaaaaaaaaaaa");
		for(int col = 0; col < misBloques.length; col++) {
			guardado.add(misBloques[col][fila]);
			misBloques[col][fila].desocupar();
		}
		miJuego.actualizarGUI(guardado);
		System.out.println(fila);
	}
	/**
	 * Este método se encarga de ir bajando las filas, 
	 * una vez que se rompe la fila que le pasan por parámetro.
	 * @param filaRota: es la fila que se acaba de romper.
	 */
	private void bajarLineas(int filaRota) {
		List<Bloque> listaDeGuardado = new LinkedList<Bloque>();		
		for(int fila = filaRota; fila > 0; fila--) {
			for(int col = 0; col < misBloques.length; col++) {
				if(misBloques[col][fila-1].isOcupado()) {
					misBloques[col][fila].ocupar(misBloques[col][fila-1].getDirImagen());;
					misBloques[col][fila-1].desocupar();
					listaDeGuardado.add(misBloques[col][fila-1]);
					listaDeGuardado.add(misBloques[col][fila]);
				}
			}
		}
		miJuego.actualizarGUI(listaDeGuardado);
	}
	
	
}
