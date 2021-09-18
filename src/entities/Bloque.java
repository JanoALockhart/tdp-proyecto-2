package entities;

public class Bloque {
	
	private int posX;
	
	private int posY;
	
	private boolean ocupado;
	
	private String dirImage;
	
	//private Grilla grilla;
	
	//private Tetrimino tetrimino;
	
	public Bloque(String img) {
		posX=0;
		posY=0;
		ocupado=false;
		dirImage=img;
	}
	
	public void setPosX(int x) {
		posX=x;
	}
	
	public void setPosY(int y) {
		posY=y;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public String getDirImage() {
		return dirImage;
	}
	
	public boolean isOcupado() {
		return ocupado;
	}
	
	/*public Grilla miGrilla() {
		return grilla;
	}
	
	public Tetrimino miTetrimino() {
		return tetrimino;
	}*/
}
