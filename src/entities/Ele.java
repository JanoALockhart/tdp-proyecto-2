package entities;

import java.util.LinkedList;
import java.util.List;

public class Ele extends Tetrimino{
	
	public Ele(Grilla miGrilla) {
		this.miGrilla = miGrilla;
		pivote = miGrilla.getBloque(3, 2);
		pivote.ocupar("/images/bloqueRojo.png");
		A =  miGrilla.getBloque(3, 0);
		pivote.ocupar("/images/bloqueRojo.png");
		B =  miGrilla.getBloque(3, 1);
		pivote.ocupar("/images/bloqueRojo.png");
		C =  miGrilla.getBloque(4, 2);
		pivote.ocupar("/images/bloqueRojo.png");
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
			case 0 : 	lista.add(miGrilla.getBloque(pivote.getPosX()+1, pivote.getPosY()));
						lista.add(miGrilla.getBloque(pivote.getPosX()-1, pivote.getPosY()));
						lista.add(miGrilla.getBloque(pivote.getPosX()-1, pivote.getPosY()-1));
						break;
			case 90:	lista.add(miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()-1));
						lista.add(miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()+1));
						lista.add(miGrilla.getBloque(pivote.getPosX()-1, pivote.getPosY()-1));
						break;
			case 180:	lista.add(miGrilla.getBloque(pivote.getPosX()-1, pivote.getPosY()));
						lista.add(miGrilla.getBloque(pivote.getPosX()+1, pivote.getPosY()));
						lista.add(miGrilla.getBloque(pivote.getPosX()+1, pivote.getPosY()+1));
						break;
			case 270:	lista.add(miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()-1));
						lista.add(miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()+1));
						lista.add(miGrilla.getBloque(pivote.getPosX()+1, pivote.getPosY()+1));
						break;
		}
		return lista;
	}

	@Override
	public List<Bloque> getBLoquesMasDer() {
		List<Bloque> lista = new LinkedList<Bloque>();
		switch(angulo) {
			case 0 :	lista.add(A);
						lista.add(pivote);
						lista.add(C);
					  	break;
			case 90:	lista.add(A);
						break;
			case 180:	lista.add(A);
						lista.add(pivote);
						lista.add(B);
						break;
			case 270:   lista.add(B);
						lista.add(C);
						break;
		}
		return lista;
	}

	@Override
	public List<Bloque> getBLoquesMasIzq() {
		List<Bloque> lista = new LinkedList<Bloque>();
		switch(angulo) {
			case 0 :	lista.add(A);
						lista.add(B);
						lista.add(pivote);
					  	break;
			case 90:	lista.add(B);
						lista.add(C);
						break;
			case 180:	lista.add(A);
						lista.add(pivote);
						lista.add(C);
						break;
			case 270:   lista.add(A);
						lista.add(C);
						break;
		}
		return lista;
	}

	@Override
	public List<Bloque> getBLoquesAbajo() {
		List<Bloque> lista = new LinkedList<Bloque>();
		switch(angulo) {
			case 0 : 	lista.add(B);
						lista.add(C);
						break;
			case 90:	lista.add(A);
						lista.add(pivote);
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
	public void rotar() {
		switch(angulo) {
			case 0 :    A = moverBloqueAPos(A, pivote.getPosX()+1, pivote.getPosY());
						B = moverBloqueAPos(B, pivote.getPosX()-1, pivote.getPosY());
						C = moverBloqueAPos(C, pivote.getPosX()-1, pivote.getPosY()-1);
						angulo = 90;
						break;
			case 90:	A = moverBloqueAPos(A, pivote.getPosX(), pivote.getPosY()-1);
						B = moverBloqueAPos(B, pivote.getPosX(), pivote.getPosY()+1);
						C = moverBloqueAPos(C, pivote.getPosX()-1, pivote.getPosY()-1);
						angulo = 180;
						break;
			case 180:	A = moverBloqueAPos(A, pivote.getPosX()-1, pivote.getPosY());
						B = moverBloqueAPos(B, pivote.getPosX()+1, pivote.getPosY());
						C = moverBloqueAPos(C, pivote.getPosX()+1, pivote.getPosY()+1);
						angulo = 270;
						break;
			case 270:	A = moverBloqueAPos(A, pivote.getPosX(), pivote.getPosY()-1);
						B = moverBloqueAPos(B, pivote.getPosX(), pivote.getPosY()+1);
						C = moverBloqueAPos(C, pivote.getPosX()+1, pivote.getPosY()+1);
						angulo = 0;
						break;			
		}
	}	
}