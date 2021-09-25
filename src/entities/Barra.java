package entities;

import java.util.*;

public class Barra extends Tetrimino {
	
	public Barra(Grilla miGrilla) {
		img = "/images/bloqueAzul.png";
		this.miGrilla = miGrilla;
		pivote = miGrilla.getBloque(4, 0);
		pivote.ocupar(img);
		A =  miGrilla.getBloque(3, 0);
		A.ocupar(img);
		B =  miGrilla.getBloque(5, 0);
		B.ocupar(img);
		C =  miGrilla.getBloque(6, 0);
		B.ocupar(img);
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
		List<Bloque> lista = new LinkedList<Bloque>();
		switch(angulo) {
			case 0 :	lista.add(C);
					  	break;
			case 90:	lista.add(C);
						lista.add(B);
						lista.add(pivote);
						lista.add(A);
						break;
			case 180:	lista.add(A);
						break;
			case 270:   lista.add(A);
						lista.add(pivote);
						lista.add(C);
						lista.add(B);
						break;
		}
		return lista;
	}

	@Override
	public List<Bloque> getBLoquesMasIzq() {
		List<Bloque> lista = new LinkedList<Bloque>();
		switch(angulo) {
			case 0 :	lista.add(A);
					  	break;
			case 90:	lista.add(C);
						lista.add(B);
						lista.add(pivote);
						lista.add(A);
						break;
			case 180:	lista.add(C);
						break;
			case 270:   lista.add(A);
						lista.add(pivote);
						lista.add(C);
						lista.add(B);
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
						lista.add(C);
						break;
			case 90:	lista.add(A);
						break;
			case 180:	lista.add(B);
						lista.add(C);
						lista.add(pivote);
						lista.add(A);
						break;
			case 270:   lista.add(B);
			  			break;
		}
		return lista;
	}

	@Override
	public void rotar() {
		switch(angulo) {
			case 0 :	A.desocupar();
						A = miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()-1);
						A.ocupar(img);
						B.desocupar();
						B = miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()+1);
						B.ocupar(img);
						C.desocupar();
						C = miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()+2);
						C.ocupar(img);
						angulo = 90;
						break;
			case 0 :	A.setPosX(pivote.getPosX());
						A.setPosY(pivote.getPosY()-1);
						B.setPosX(pivote.getPosX());
						B.setPosY(pivote.getPosY()+1);
						C.setPosX(pivote.getPosX());
						C.setPosY(pivote.getPosY()+2);
						angulo = 90;
						break;
			case 90:	A.setPosX(pivote.getPosX()+1);
						A.setPosY(pivote.getPosY());
						B.setPosX(pivote.getPosX()-2);
						B.setPosY(pivote.getPosY());
						C.setPosX(pivote.getPosX()-1);
						C.setPosY(pivote.getPosY());
						angulo = 180;
						break;
			case 180:	A.setPosX(pivote.getPosX());
						A.setPosY(pivote.getPosY()+1);
						B.setPosX(pivote.getPosX());
						B.setPosY(pivote.getPosY()-2);
						C.setPosX(pivote.getPosX());
						C.setPosY(pivote.getPosY()-1);
						angulo = 270;
						break;
			case 270:	A.setPosX(pivote.getPosX()-1);
						A.setPosY(pivote.getPosY());
						B.setPosX(pivote.getPosX()+1);
						B.setPosY(pivote.getPosY());
						C.setPosX(pivote.getPosX()+2);
						C.setPosY(pivote.getPosY());
						angulo = 0;
						break;
			
		}
	}

}
