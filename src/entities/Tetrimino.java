package entities;
import java.util.*;


public abstract class Tetrimino {
	protected Bloque pivote, A, B, C;
	protected int angulo;
	protected List<Bloque> misBloques; //TODO elegir lista o arreglo
	
	public abstract List<Bloque> getBloquesParaRotar();
	
	public abstract List<Bloque> getBLoquesMasDer();
	
	public abstract List<Bloque> getBLoquesMasIzq();
	
	public abstract List<Bloque> getBLoquesAbajo();
	
	public abstract void rotar();
	
	public void moverDerecha() {
		for(Bloque bloq : misBloques) {
			bloq.setPosX(bloq.getPosX()+1);
		}
	}
	
	public void moverIzquierda() {
		for(Bloque bloq : misBloques) {
			bloq.setPosX(bloq.getPosX()-1);
		}
	}
	
	public void caer() {
		for(Bloque bloq : misBloques) {
			bloq.setPosY(bloq.getPosY()-1);
		}
	}
	

}
