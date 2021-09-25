/**
 * 
 */
package entities;

import java.util.Random;

/**
 *
 */
public class FabricaTetriminos {
	private Grilla miGrilla;
	
	
	/**
	 * Constructor clase Fabrica tetriminos
	 * @param miGrilla Es la grilla en la que se
	 *  generará el nuevo tetrimino
	 */
	public FabricaTetriminos(Grilla miGrilla) {
		this.miGrilla = miGrilla;
	}
	
	/**
	 * Crea un nuevo tetrimino aleatorio
	 * @return Un nuevo tetrimino
	 */
	public Tetrimino generarNuevoTetrimino() {
		Tetrimino tetri = null;
		Random randomizer = new Random();
		int numTetrimino;
		
		numTetrimino = randomizer.nextInt(7);
		
		switch(numTetrimino){
			case 0:{
				tetri = new Cuadrado(miGrilla);
				break;
			}
			case 1:{
				tetri = new Barra(miGrilla);
				break;
			}
			case 2:{
				tetri = new Jota(miGrilla);
				break;
			}
			case 3:{
				tetri = new Ele(miGrilla); 
				break;
			}
			case 4:{
				tetri = new Te(miGrilla);
				break;
			}
			case 5:{
				tetri = new Zeta(miGrilla);
				break;
			}
			case 6:{
				tetri = new Ese(miGrilla);
				break;
			}
			
		}
		return tetri;
	}
}
