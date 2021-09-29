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
		miTetriminoActual = miFabrica.generarNuevoTetrimino(); //TODO cambiar a un tetri especifico para testear
		miTetriminoActual.inicializarTetrimino();
		miTetriminoSiguiente = miFabrica.generarNuevoTetrimino();///TODO cambiar a un tetri especifico para testear
		miJuego.actualizarTetriSiguiente(miTetriminoSiguiente);
		this.miJuego.actualizarGUI(miTetriminoActual.getBloquesTetrimino());
	}
	
	/**
	 * Metodo que devuelve el bloque de la grilla que 
	 * corresponde a la columa y fila indicada por parametro
	 * @param c Es la columna en la que esta el bloque
	 * @param f Es la fila en la que esta el bloque
	 * @return El bloque que esta en la columna c y en la fila f
	 */
	public Bloque getBloque(int c, int f) {
		Bloque ret=null;
		if(0<=c && c<=9 && 0<=f && f<=20) {
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
	 * Primero verifica que cada bloque del tetrimino se pueda mover a la ubicaci�n
	 * que le corresponde, de ser as� el tetrimino rota.
	 */
	public void moverRotar() {
		boolean sePuede = true;
		List<Bloque> bloquesRotar;
		bloquesRotar = miTetriminoActual.getBloquesParaRotar();
		Iterable<Bloque> bloquesAnteriores;
		for (Bloque b : bloquesRotar) {
			if(b==null || (misBloques[b.getPosX()][b.getPosY()].isOcupado())) {
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
	 * se bajan una posici�n en la grilla, caso contrario el tetrimino se solidifica.
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
	 * Este m�todo se encarga de cambiar el tetrimino actual
	 * por el tetrimino siguiente y si este nuevo tetrimino
	 * puede ocupar sus posiciones. En caso de que no pueda, 
	 * el juego termina
	 */	
	private void solidificarTetrimino() {
		miTetriminoActual = miTetriminoSiguiente;
		if(puedeInicializar(miTetriminoActual)){
			miTetriminoActual.inicializarTetrimino();
			miJuego.actualizarGUI(miTetriminoActual.getBloquesTetrimino());
			
			miTetriminoSiguiente = miFabrica.generarNuevoTetrimino();//TODO cambiar a un tetri especifico para testear
			miJuego.actualizarTetriSiguiente(miTetriminoSiguiente);
		}else{
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
	private boolean puedeInicializar(Tetrimino tetri){
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
	 * Este m�todo se encarga de ir verificando si la fila de cada bloque
	 * que conforma el tetrimino se puede romper
	 * @param tetri: es el tetrimino actual 
	 */
	private void verificarLineas(Tetrimino tetri) {
		int filasRotas=0;
		int max=0; //es el numero de la fila mas baja rota hasta el momento
		Iterable<Bloque> bloquesDelTetri = tetri.getBloquesTetrimino();

		for (Bloque b : bloquesDelTetri) {		
			if(lineaLlena(b.getPosY())) {
				romperLineas(b.getPosY());
				filasRotas++;
				max = b.getPosY()>max ? b.getPosY():max;
				
			}
		}
		if(filasRotas>0) {
			miJuego.addScore(filasRotas);
			bajarLineasConAlgo();
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
	 * Este m�todo se encarga de romper toda una fila,
	 * osea que recorre todos lo bloques de una fila y los pone en desocupados.
	 * @param fila: es la fila que tiene que romper
	 */
	private void  romperLineas(int fila) {
		List<Bloque> guardado = new LinkedList<Bloque>();
		for(int col = 0; col < misBloques.length; col++) {
			misBloques[col][fila].desocupar();
			guardado.add(misBloques[col][fila]);
		}
		miJuego.actualizarGUI(guardado);
	}
	
	/**
	 * Este metodo se encarga de que por cada linea vacia que se encuentre
	 * se bajen todos los bloques que hay por encima de esa fila un bloque
	 * hacia abajo
	 */
	private void bajarLineasConAlgo() {
		List<Bloque> guardado = new LinkedList<Bloque>();
		int fila=misBloques[0].length-1;
		
		while(fila>0) {
			if(!hayEnFila(fila) && quedanBloquesEncima(fila)) {
				bajarTodoAFila(fila,guardado);
			}else {
				fila--;
			}
		}
		miJuego.actualizarGUI(guardado);
	}
	
	/**
	 * Verifica si por encima de la fila indicada, quedan bloques ocupados
	 * @param filaAnalizada Es la fila a partir de la cual se analiza por encima
	 * si hay bloques
	 * @return true si quedan bloques por encima de la fila, false si no queda ninguno.
	 */
	private boolean quedanBloquesEncima(int filaAnalizada) {
		boolean quedan = false;
		
		for(int fila = filaAnalizada-1; fila>=0 && !quedan; fila--) {
			quedan = hayEnFila(fila);
		}
		
		return quedan;
	}
	
	/**
	 * Este metodo baja todos los bloques por encima de la fila y se agregan
	 * en una lista para que sean modificados por la gui
	 * @param filaVacia Es la fila a partir de la cual se bajan bloques
	 * @param modificar Es la lista de bloques a modificar en la gui
	 */
	private void bajarTodoAFila(int filaVacia, List<Bloque> modificar) {
		Bloque origen, destino;
		for(int fila=filaVacia; fila>0; fila--) {
			for(int col=0; col<misBloques.length; col++) {
				origen = misBloques[col][fila-1];
				destino = misBloques[col][fila];
				
				if(origen.isOcupado()){
					destino.ocupar(origen.getDirImagen());
					origen.desocupar();
					modificar.add(origen);
					modificar.add(destino);
				}
			}
		}
	}
	
	/**
	 * Metodo que verifica si la fila indicada por par�metro
	 * esta vacia o no
	 * @param fila Es el numero de fila que se quiere verificar
	 * @return true si la fila tiene algun bloque, false si esta vacia.
	 */
	private boolean hayEnFila(int fila){
		boolean hay = false;

		for(int col=0; col<misBloques.length && !hay; col++){
			hay = misBloques[col][fila].isOcupado();
		}

		return hay;
	}
	
	
}
