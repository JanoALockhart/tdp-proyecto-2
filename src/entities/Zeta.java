/**
 * 
 */
package entities;

import java.util.*;

/**
 *
 */
public class Zeta extends Tetrimino{
	
	/**
	 * Constructor tetrimino zeta
	 * @param miGrilla Es la grilla que contiene al tetrimino
	 */
	public Zeta(Grilla miGrilla) {
		this.miGrilla = miGrilla;
		img = "/images/bloqueMagenta.png";
		pivote = this.miGrilla.getBloque(5, 1);
		pivote.ocupar(img);
		A = this.miGrilla.getBloque(4, 0);
		A.ocupar(img);
		B = this.miGrilla.getBloque(5, 0);
		B.ocupar(img);
		C = this.miGrilla.getBloque(6, 1);
		C.ocupar(img);
		
		angulo=0;	
	}
	
	/**
	 * Metodo que devuelve los bloques de la grilla
	 * que serían ocupados en caso de que se ejecute la rotacion
	 */
	public List<Bloque> getBloquesParaRotar(){
		List<Bloque> listaBloques = new LinkedList<Bloque>();
		
		switch(angulo) {
			case 0:{
				listaBloques.add(miGrilla.getBloque(pivote.getPosX()-1, pivote.getPosY()));  
				listaBloques.add(miGrilla.getBloque(pivote.getPosX()-1, pivote.getPosY()+1));
				break;
			}
			case 90:{
				listaBloques.add(miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()+1));
				listaBloques.add(miGrilla.getBloque(pivote.getPosX()+1, pivote.getPosY()+1));
				break;
			}
			case 180:{
				listaBloques.add(miGrilla.getBloque(pivote.getPosX()+1, pivote.getPosY()));
				listaBloques.add(miGrilla.getBloque(pivote.getPosX()+1, pivote.getPosY()-1));
				break;
			}
			case 270:{
				listaBloques.add(miGrilla.getBloque(pivote.getPosX()-1, pivote.getPosY()-1));
				listaBloques.add(miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()-1));
				break;
			}
			
		}
		
		return listaBloques;
	}
	
	/**
	 * Metodo que retorna los bloques que podrian colisionar si el
	 * tetrimino se mueve a la derecha
	 * @return Un iterable con los bloques necesarios para corroborar
	 * si se puede mover a la derecha.
	 */
	public List<Bloque> getBLoquesMasDer(){
		List<Bloque> listaBloques = new LinkedList<Bloque>();
		
		switch(angulo) {
			case 0:{
				listaBloques.add(C);
				listaBloques.add(B);
				break;
			}
			case 90:{
				listaBloques.add(pivote);
				listaBloques.add(B);
				listaBloques.add(C);
				break;
			}
			case 180:{
				listaBloques.add(pivote);
				listaBloques.add(C);
				break;
			}
			case 270:{
				listaBloques.add(A);
				listaBloques.add(B);
				listaBloques.add(C);
				break;
			}
			
		}
		
		return listaBloques;	
	}
	
	/**
	 * Metodo que devuelve una lista con los bloques que pueden colisionar
	 * si se intenta mover a la izquierda.
	 * @return Una lista con los bloques necesarios para corroborar que se pueda
	 * mover a izquierda
	 */
	public List<Bloque> getBLoquesMasIzq(){
		List<Bloque> listaBloques = new LinkedList<Bloque>();
		
		switch(angulo) {
			case 0:{
				listaBloques.add(pivote);
				listaBloques.add(A);
				break;
			}
			case 90:{
				listaBloques.add(C);
				listaBloques.add(B);
				listaBloques.add(A);
				break;
			}
			case 180:{
				listaBloques.add(A);
				listaBloques.add(B);
				break;
			}
			case 270:{
				listaBloques.add(A);
				listaBloques.add(pivote);
				listaBloques.add(B);
				break;
			}
		}
		
		return listaBloques;
	}
	
	/**
	 * Metodo que retorna los bloques que podrian tener colision
	 * cuando se intente mover el tetrimino hacia abajo
	 * @return Una lista con los bloques necesarios para corroborar
	 * que se pueda mover hacia abajo
	 */
	public List<Bloque> getBLoquesAbajo(){
		List<Bloque> listBloq = new LinkedList<Bloque>();
		
		switch(angulo) {
			case 0:{
				listBloq.add(pivote);
				listBloq.add(A);
				listBloq.add(C);
				break;
			}
			case 90:{
				listBloq.add(pivote);
				listBloq.add(C);
				break;
			}
			case 180:{
				listBloq.add(B);
				listBloq.add(A);
				listBloq.add(C);
			}
			case 270:{
				listBloq.add(B);
				listBloq.add(C);
			}
		}
		
		return listBloq;
	}
	
	/**
	 * Metodo que cambia las referencias de los atributos de 
	 * tetrimino para que rote. De
	 */
	public void rotar() {
		switch(angulo) {
			case 0:{
				A = moverBloqueAPos(A, pivote.getPosX()-1, pivote.getPosY());
				C = moverBloqueAPos(C, pivote.getPosX()-1, pivote.getPosY()+1);
				angulo = 90;
				break;
			}
			case 90:{
				B = moverBloqueAPos(B, pivote.getPosX(), pivote.getPosY()+1);
				C = moverBloqueAPos(C, pivote.getPosX()+1, pivote.getPosY()+1);
				angulo = 180;
				break;
			}
			case 180:{
				A = moverBloqueAPos(A, pivote.getPosX()+1, pivote.getPosY()-1);
				C = moverBloqueAPos(C, pivote.getPosX()+1, pivote.getPosY());
				angulo = 270;
				break;
			}
			case 270:{
				A = moverBloqueAPos(A, pivote.getPosX()-1, pivote.getPosY()-1);
				B = moverBloqueAPos(B, pivote.getPosX(), pivote.getPosY()-1);
				angulo = 0;
				break;
			}
		}
	}
}
