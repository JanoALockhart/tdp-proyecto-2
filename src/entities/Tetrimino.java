package entities;
import java.util.*;


public abstract class Tetrimino {
	protected String img;
	protected Grilla miGrilla;
	protected Bloque pivote, A, B, C;
	protected int angulo;
	protected List<Bloque> misBloques; //TODO elegir lista o arreglo
	
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
	public abstract void rotar();
	
	/**
	 * Mueve el tetrimino una posicion a la derecha
	 */
	public void moverDerecha() {
		for(Bloque bloq : misBloques) {
			bloq.desocupar();
		}
		for(Bloque bloq : misBloques) {
			bloq = miGrilla.getBloque(bloq.getPosX()+1,bloq.getPosY());
		}
		for(Bloque bloq : misBloques) {
			bloq.ocupar(img);
		}
	}
	
	/**
	 * Mueve el tetrimino una posicion a la izquierda
	 */
	public void moverIzquierda() {
		for(Bloque bloq : misBloques) {
			bloq.desocupar();
		}
		for(Bloque bloq : misBloques) {
			bloq = miGrilla.getBloque(bloq.getPosX()-1,bloq.getPosY());
		}
		for(Bloque bloq : misBloques) {
			bloq.ocupar(img);
		}
	}
	
	/**
	 * Mueve el tetrimino una posicion abajo.
	 */
	public void caer() {
		for(Bloque bloq : misBloques) {
			bloq.desocupar();
		}
		for(Bloque bloq : misBloques) {
			bloq = miGrilla.getBloque(bloq.getPosX(),bloq.getPosY()+1);
			pivote = miGrilla.getBloque(pivote.getPosX(),pivote.getPosY()+1);
			A = miGrilla.getBloque(A.getPosX(),A.getPosY()+1);
			B = miGrilla.getBloque(B.getPosX(),B.getPosY()+1);
			C = miGrilla.getBloque(C.getPosX(),C.getPosY()+1);
			
		}		
		for(Bloque bloq : misBloques) {
			bloq.ocupar(img);
			pivote.ocupar(img);
			A.ocupar(img);
			B.ocupar(img);
			C.ocupar(img);
		}
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
	
	public Iterable<Bloque> getBloquesTetrimino(){
		return misBloques;
	}
}
