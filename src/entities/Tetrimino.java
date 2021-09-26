package entities;
import java.util.*;


public abstract class Tetrimino {
	protected String img;
	protected Grilla miGrilla;
	protected Bloque pivote, A, B, C;
	protected int angulo;
	
	/**
	 * Retorna una lista iterable con los bloques que seran cambiados de posicion
	 * asi pueden ser chequeado por la grilla
	 * @return List<Bloque> iterable
	 */
	public abstract List<Bloque> getBloquesParaRotar();
	
	/**
	 * *Retorna una lista iterable con los bloques que seran cambiados de posicion
	 * asi pueden ser chequeado por la grilla
	 * @return List<Bloque> iterable
	 */
	public abstract List<Bloque> getBLoquesMasDer();
	
	/**
	 * Retorna una lista iterable con los bloques que seran cambiados de posicion
	 * asi pueden ser chequeado por la grilla
	 * @return List<Bloque> iterable
	 */
	public abstract List<Bloque> getBLoquesMasIzq();
	
	/**
	* Retorna una lista iterable con los bloques que seran cambiados de posicion
	 * asi pueden ser chequeado por la grilla
	 * @return List<Bloque> iterable
	 */
	public abstract List<Bloque> getBLoquesAbajo();
	
	/**
	 * Rota el tetrimino
	 */
	public abstract Iterable<Bloque> rotar();
	
	/**
	 * Mueve el tetrimino una posicion a la derecha
	 */
	public Iterable<Bloque> moverDerecha() {
		List<Bloque> listaAnteriores=new LinkedList<Bloque>();
		for(Bloque bloq : getBloquesTetrimino()) {
			listaAnteriores.add(miGrilla.getBloque(bloq.getPosX(),bloq.getPosY()));
			bloq.desocupar();
		}
		A = miGrilla.getBloque(A.getPosX()+1,A.getPosY());
		B = miGrilla.getBloque(B.getPosX()+1,B.getPosY());
		C = miGrilla.getBloque(C.getPosX()+1,C.getPosY());
		pivote = miGrilla.getBloque(pivote.getPosX()+1,pivote.getPosY());
		A.ocupar(img);
		B.ocupar(img);
		C.ocupar(img);
		pivote.ocupar(img);
		return listaAnteriores;
	}
	
	/**
	 * Mueve el tetrimino una posicion a la izquierda
	 */
	public Iterable<Bloque> moverIzquierda() {
		List<Bloque> listaAnteriores=new LinkedList<Bloque>();
		for(Bloque bloq : getBloquesTetrimino()) {
			listaAnteriores.add(miGrilla.getBloque(bloq.getPosX(),bloq.getPosY()));
			bloq.desocupar();
		}
		A = miGrilla.getBloque(A.getPosX()-1,A.getPosY());
		B = miGrilla.getBloque(B.getPosX()-1,B.getPosY());
		C = miGrilla.getBloque(C.getPosX()-1,C.getPosY());
		pivote = miGrilla.getBloque(pivote.getPosX()-1,pivote.getPosY());
		A.ocupar(img);
		B.ocupar(img);
		C.ocupar(img);
		pivote.ocupar(img);
		return listaAnteriores;
	}
	
	/**
	 * Mueve el tetrimino una posicion abajo.
	 */
	public Iterable<Bloque> caer() {
		// lista de bloques anteriores
		List<Bloque> listaAnteriores=new LinkedList<Bloque>();
		for(Bloque bloq : getBloquesTetrimino()) {
			// pone en una lista el bloque con get bloque
			listaAnteriores.add(miGrilla.getBloque(bloq.getPosX(),bloq.getPosY()));
			bloq.desocupar();
		}
		
		pivote = miGrilla.getBloque(pivote.getPosX(),pivote.getPosY()+1);
		A = miGrilla.getBloque(A.getPosX(),A.getPosY()+1);
		B = miGrilla.getBloque(B.getPosX(),B.getPosY()+1);
		C = miGrilla.getBloque(C.getPosX(),C.getPosY()+1);
				
		pivote.ocupar(img);
		A.ocupar(img);
		B.ocupar(img);
		C.ocupar(img);
		return listaAnteriores;
	}
	
	/**
	 * Metodo Protegido, factoriza las operaciones de desocupar, asignar pos y ocupar.
	 * @param bloque Boque que va a ser afectado
	 * @param x Posicion en X que va a ocupar
	 * @param y Posicion en Y que va a ocupar
	 * @return Bloque que va a ser afectado
	 */
	protected Bloque moverBloqueAPos(Bloque bloque, int x, int y) {
		bloque.desocupar();
		bloque = miGrilla.getBloque(x, y);
		bloque.ocupar(img);
		return bloque;
	}
	
	/**
	 * Devuelve una coleccion de los bloques a los que apunta
	 * los atributos del tetrimino
	 * @return Una coleccion con los bloques actuales del tetrimino
	 */
	public Iterable<Bloque> getBloquesTetrimino(){
		List<Bloque> misBloques=new LinkedList<Bloque>();
		misBloques.add(A);
		misBloques.add(B);
		misBloques.add(C);
		misBloques.add(pivote);
		return misBloques;
	}
	
	/**
	 * Este metodo hace efectivo los bloques
	 * que ocupa el tetrimino
	 */
	public void inicializarTetrimino() {
		pivote.ocupar(img);
		A.ocupar(img);
		B.ocupar(img);
		C.ocupar(img);
	}
	
	/**
	 * Metodo que devuelve la imagen que corresponde
	 * a los bloques del tetrimino.
	 * @return
	 */
	public String getImage() {
		return img;
	}
	
}
