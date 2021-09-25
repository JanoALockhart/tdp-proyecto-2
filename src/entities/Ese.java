package entities;

import java.util.List;

public class Ese extends Tetrimino{
	
	public Ese(Grilla miGrilla) {
		this.miGrilla = miGrilla;
		pivote = miGrilla.getBloque(3, 1);
		pivote.ocupar("/images/bloqueVerde.png");
		A =  miGrilla.getBloque(2, 1);
		pivote.ocupar("/images/bloqueVerde.png");
		B =  miGrilla.getBloque(4, 0);
		pivote.ocupar("/images/bloqueVerde.png");
		C =  miGrilla.getBloque(3, 0);
		pivote.ocupar("/images/bloqueVerde.png");
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
