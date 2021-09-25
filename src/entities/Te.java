package entities;

import java.util.List;

public class Te extends Tetrimino {

	public Te(Grilla miGrilla) {
		img = "images/bloqueNaranja.png";
		this.miGrilla = miGrilla;
		pivote = miGrilla.getBloque(4, 1);
		pivote.ocupar(img);
		A = miGrilla.getBloque(3, 1);
		A.ocupar(img);
		B = miGrilla.getBloque(4, 0);
		B.ocupar(img);
		C = miGrilla.getBloque(5, 1);
		C.ocupar(img);
		misBloques.add(pivote);
		misBloques.add(A);
		misBloques.add(B);
		misBloques.add(C);
		angulo = 0;
		
	}
	@Override
	public List<Bloque> getBloquesParaRotar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bloque> getBLoquesMasDer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bloque> getBLoquesMasIzq() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bloque> getBLoquesAbajo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rotar() {
		// TODO Auto-generated method stub

	}

}
