package entities;

public class Bloque {
	
	private int posX;
	
	private int posY;
	
	private boolean ocupado;
	
	private String dirImage;
	
	/**
	 * M�todo constructor de la clase Bloque
	 * @param img Posici�n en memoria de windows de la imagen del bloque
	 */
	public Bloque(String img) {
		posX=0;
		posY=0;
		ocupado=false;
		dirImage=img;
	}
	
	/**
	 * M�todo constructor de la clase Bloque cuando el juego aparece un tetrimino
	 * @param img Posici�n en memoria de windows de la imagen del bloque
	 * @param x posici�n en el eje X
	 * @param y posici�n en el eje Y
	 */
	public Bloque(String img, int x, int y) {
		posX=x;
		posY=y;
		ocupado=true;
		dirImage=img;
	}
	
	/**
	 * M�todo constructor de la clase Bloque cuando se rota un tetrimino
	 * @param img Posici�n en memoria de windows de la imagen del bloque
	 * @param x posici�n en el eje X
	 * @param y posici�n en el eje Y
	 * @param ocupado si el bloque est� ocupado
	 */
	public Bloque(String img, int x, int y, boolean o) {
		posX=x;
		posY=y;
		ocupado=o;
		dirImage=img;
	}

	/**
	 * Configura la posici�n del bloque en el eje X de la grilla
	 * @param x posici�n en el eje X
	 */
	public void setPosX(int x) {
		posX=x;
	}
	
	/**
	 * Configura la posici�n del bloque en el eje Y de la grilla
	 * @param y posici�n en el eje Y
	 */
	public void setPosY(int y) {
		posY=y;
	}
	
	/**
	 * Devuelve la posici�n del bloque en el eje Y de la grilla
	 * @return posY posici�n en el eje Y
	 */
	public int getPosY() {
		return posY;
	}
	
	/**
	 * Devuelve la posici�n del bloque en el eje X de la grilla
	 * @return posX posici�n en el eje X
	 */
	public int getPosX() {
		return posX;
	}
	
	/**
	 * Devuelve la direcci�n en memoria de Windows en que esta alojada la imagen
	 * @return dirImage direcci�n en memoria de Windows
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
