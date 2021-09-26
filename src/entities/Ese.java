package entities;

import java.util.LinkedList;
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
		misBloques = new LinkedList<Bloque>();
		misBloques.add(pivote);
		misBloques.add(A);
		misBloques.add(B);
		misBloques.add(C);
		angulo = 0;
	}

	@Override
	public List<Bloque> getBloquesParaRotar() {
		List<Bloque> lista = new LinkedList<Bloque>();
		switch(angulo) {
			case 0 : 	lista.add(miGrilla.getBloque(pivote.getPosX()+1, pivote.getPosY()+1));
						lista.add(miGrilla.getBloque(pivote.getPosX()+1, pivote.getPosY()));
						lista.add(miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()+1));
						break;
			case 90:	lista.add(miGrilla.getBloque(pivote.getPosX()+1, pivote.getPosY()));
						lista.add(miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()+1));
						lista.add(miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()+1));
						break;
			case 180:	lista.add(miGrilla.getBloque(pivote.getPosX()-1, pivote.getPosY()-1));
						lista.add(miGrilla.getBloque(pivote.getPosX()-1, pivote.getPosY()));
						lista.add(miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()-1));
						break;
			case 270:	lista.add(miGrilla.getBloque(pivote.getPosX()+1, pivote.getPosY()-1));
						lista.add(miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()-1));
						lista.add(miGrilla.getBloque(pivote.getPosX()-1, pivote.getPosY()));
						break;
		}
		return lista;
	}

	@Override
	public List<Bloque> getBLoquesMasDer() {
		List<Bloque> lista = new LinkedList<Bloque>();
		switch(angulo) {
			case 0 :	lista.add(B);
						lista.add(pivote);
					  	break;
			case 90:	lista.add(A);
						lista.add(B);
						lista.add(C);	
						break;
			case 180:	lista.add(A);
						lista.add(C);
						break;
			case 270:   lista.add(A);
						lista.add(B);
						lista.add(pivote);
						break;
		}
		return lista;
	}

	@Override
	public List<Bloque> getBLoquesMasIzq() {
		List<Bloque> lista = new LinkedList<Bloque>();
		switch(angulo) {
			case 0 :	lista.add(A);
						lista.add(C);
					  	break;
			case 90:	lista.add(A);
						lista.add(B);
						lista.add(pivote);
						break;
			case 180:	lista.add(B);
						lista.add(pivote);
						break;
			case 270:   lista.add(A);
						lista.add(B);
						lista.add(C);	
						break;
		}
		return lista;
	}

	@Override
	public List<Bloque> getBLoquesAbajo() {
		List<Bloque> lista = new LinkedList<Bloque>();
		switch(angulo) {
			case 0 :	lista.add(A);
						lista.add(pivote);
						lista.add(B);
					  	break;
			case 90:	lista.add(B);
						lista.add(pivote);
						break;
			case 180:	lista.add(A);
						lista.add(B);
						lista.add(C);
						break;
			case 270:   lista.add(A);
						lista.add(C);	
						break;
		}
		return lista;
	}

	@Override
	public void rotar() {
		switch(angulo) {
			case 0 :    B = moverBloqueAPos(B, pivote.getPosX()+1, pivote.getPosY()+1);
						C = moverBloqueAPos(C, pivote.getPosX()+1, pivote.getPosY());
						A = moverBloqueAPos(A, pivote.getPosX(), pivote.getPosY()+1);
						angulo = 90;
						break;
			case 90:	B = moverBloqueAPos(B, pivote.getPosX()+1, pivote.getPosY());
						C = moverBloqueAPos(C, pivote.getPosX(), pivote.getPosY()+1);
						A = moverBloqueAPos(A, pivote.getPosX(), pivote.getPosY()+1);
						angulo = 180;
						break;
			case 180:	B = moverBloqueAPos(B, pivote.getPosX()-1, pivote.getPosY()-1);
						C = moverBloqueAPos(C, pivote.getPosX()-1, pivote.getPosY());
						A = moverBloqueAPos(A, pivote.getPosX(), pivote.getPosY()-1);
						angulo = 270;
						break;
			case 270:	B = moverBloqueAPos(B, pivote.getPosX()+1, pivote.getPosY()-1);
						C = moverBloqueAPos(C, pivote.getPosX(), pivote.getPosY()-1);
						A = moverBloqueAPos(A, pivote.getPosX()-1, pivote.getPosY());
						angulo = 0;
						break;			
		}
	}
	
}
