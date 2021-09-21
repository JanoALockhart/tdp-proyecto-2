package entities;

public class Bloque {
	
	private int posX;
	
	private int posY;
	
	private boolean ocupado;
	
	private String dirImage;
	
	/**
	 * Método constructor de la clase Bloque
	 * @param img Posición en memoria de windows de la imagen del bloque
	 */
	public Bloque(String img) {
		posX=0;
		posY=0;
		ocupado=false;
		dirImage=img;
	}
	
	/**
	 * Método constructor de la clase Bloque cuando el juego aparece un tetrimino
	 * @param img Posición en memoria de windows de la imagen del bloque
	 * @param x posición en el eje X
	 * @param y posición en el eje Y
	 */
	public Bloque(String img, int x, int y) {
		posX=x;
		posY=y;
		ocupado=false;
		dirImage=img;
	}

	/**
	 * Configura la posición del bloque en el eje X de la grilla
	 * @param x posición en el eje X
	 */
	public void setPosX(int x) {
		posX=x;
	}
	
	/**
	 * Configura la posición del bloque en el eje Y de la grilla
	 * @param y posición en el eje Y
	 */
	public void setPosY(int y) {
		posY=y;
	}
	
	/**
	 * Devuelve la posición del bloque en el eje Y de la grilla
	 * @return posY posición en el eje Y
	 */
	public int getPosY() {
		return posY;
	}
	
	/**
	 * Devuelve la posición del bloque en el eje X de la grilla
	 * @return posX posición en el eje X
	 */
	public int getPosX() {
		return posX;
	}
	
	/**
	 * Devuelve la dirección en memoria de Windows en que esta alojada la imagen
	 * @return dirImage dirección en memoria de Windows
	 */
	public String getDirImage() {
		return dirImage;
	}
	
	/**
	 * Devuelve si es que el bloque es usado en la grilla para representar un bloque
	 * @return ocupado Si esta ocupado true
	 *         		   Si no esta ocupado false
	 */
	public boolean isOcupado() {
		return ocupado;
	}
}
