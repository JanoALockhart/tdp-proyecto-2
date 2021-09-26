package entities;

public class Bloque {
	
	private int posX;
	
	private int posY;
	
	private boolean ocupado;
	
	private String dirImage;
		
	/**
	 * M�todo constructor de la clase Bloque
	 * @param img Posici�n en el directorio de Windows de la imagen del bloque
	 * @param x posici�n en el eje X
	 * @param y posici�n en el eje Y
	 * @param o si el bloque est� ocupado
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
	 * Ocupa un bloque y configura que la imagen del bloque sea img para representar en GUI.
	 * @param img imagen que quiere ponerse al bloque
	 */
	public void ocupar(String img) {
		ocupado=true;
		dirImage=img;
	}
	
	/**
	 * Desocupa el bloque y configura que la imagen del bloque sea la de un bloque vacio para representar en GUI
	 */
	public void desocupar() {
		ocupado=false;
		dirImage="/images/bloqueVacio.png";
	}
	
	/**
	 * Configura que la imagen del bloque sea img.
	 * @param img imagen que quiere ponerse al bloque
	 */
	public void setDirImagen(String img) {
		dirImage=img;
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
	 * Devuelve la direcci�n en el directorio de Windows en que esta alojada la imagen
	 * @return dirImage direcci�n en memoria de Windows
	 */
	public String getDirImagen() {
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
