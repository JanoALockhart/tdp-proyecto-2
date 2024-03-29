package entities;

import java.util.*;

public class Te extends Tetrimino {

	public Te(Grilla miGrilla) {
		img = "/images/bloqueNaranja.png";
		this.miGrilla = miGrilla;
		pivote = miGrilla.getBloque(4, 1);
		A = miGrilla.getBloque(3, 1);
		B = miGrilla.getBloque(4, 0);
		C = miGrilla.getBloque(5, 1);
		angulo = 0;
		
	}
	@Override
	public List<Bloque> getBloquesParaRotar() {
		List<Bloque> lista = new LinkedList<Bloque>();
		switch(angulo) {
		case 0 : 	lista.add(miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()+1));
			 		break;
		case 90: 	lista.add(miGrilla.getBloque(pivote.getPosX()+1, pivote.getPosY()));
					break;
		case 180:	lista.add(miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()-1));
					break;
		case 270:	lista.add(miGrilla.getBloque(pivote.getPosX()-1, pivote.getPosY()));
					//lista.add(miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()-1));
					//lista.add(miGrilla.getBloque(pivote.getPosX()+1, pivote.getPosY()));
					break;
		}
		return lista;
		
	}

	@Override
	public List<Bloque> getBLoquesMasDer() {
		List<Bloque> lista = new LinkedList<Bloque>();
		switch(angulo) {
		case 0 : 	lista.add(B);
					lista.add(C);
			 		break;
		case 90: 	lista.add(B);
					lista.add(pivote);
					lista.add(C);
					break;
		case 180:	lista.add(B);
					lista.add(C);
					break;
		case 270:	lista.add(A);
					lista.add(B);
					lista.add(C);
					break;
		}
		return lista;
	}

	@Override
	public List<Bloque> getBLoquesMasIzq() {
		List<Bloque> lista = new LinkedList<Bloque>();
		switch(angulo) {
		case 0 : 	lista.add(B);
					lista.add(A);
			 		break;
		case 90: 	lista.add(B);
					lista.add(A);
					lista.add(C);
					break;
		case 180:	lista.add(A);
					lista.add(C);
					break;
		case 270:	lista.add(A);
					lista.add(pivote);
					lista.add(C);
					break;
		}
		return lista;
	}

	@Override
	public List<Bloque> getBLoquesAbajo() {
		List<Bloque> lista = new LinkedList<Bloque>();
		switch(angulo) {
		case 0 : 	lista.add(A);
					lista.add(pivote);
					lista.add(C);
			 		break;
		case 90: 	lista.add(C);
					lista.add(A);
					break;
		case 180:	lista.add(A);
					lista.add(C);
					lista.add(B);
					break;
		case 270:	lista.add(B);
					lista.add(C);
					break;
		}
		return lista;
	}

	@Override
	public Iterable<Bloque> rotar() {
		List<Bloque> listaAnteriores=new LinkedList<Bloque>();
		for(Bloque bloq : getBloquesTetrimino()) {
			listaAnteriores.add(miGrilla.getBloque(bloq.getPosX(),bloq.getPosY()));
		}
		switch(angulo) {
		case 0 :	C = moverBloqueAPos(C, pivote.getPosX(), pivote.getPosY()+1);
					angulo = 90;
					break;
		case 90:	B = moverBloqueAPos(B, pivote.getPosX()+1, pivote.getPosY());
					angulo = 180;
					break;
		case 180:	A = moverBloqueAPos(A, pivote.getPosX(), pivote.getPosY()-1);
					angulo = 270;
					break;
		case 270:	A = moverBloqueAPos(A, pivote.getPosX()-1, pivote.getPosY());
					B = moverBloqueAPos(B, pivote.getPosX(), pivote.getPosY()-1);
					C = moverBloqueAPos(C, pivote.getPosX()+1, pivote.getPosY());	
					angulo = 0;
					break;
		
		}
		return listaAnteriores;
	}

}
