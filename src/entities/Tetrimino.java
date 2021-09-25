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
			bloq = miGrilla.getBloque(bloq.getPosX(),bloq.getPosY()-1);
		}
		for(Bloque bloq : misBloques) {
			bloq.ocupar(img);
		}
	}
	
	protected Bloque moverBloqueAPos(Bloque bloque, int x, int y) {
		bloque.desocupar();
		bloque = miGrilla.getBloque(x, y);
		bloque.ocupar(img);
		return bloque;
	}
	/**
	 * -setear la imagen
	 * -setear que esta ocupado
	 * -
	 */
	
	
}
