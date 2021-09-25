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
		List<Bloque> lista = new LinkedList<Bloque>();
		switch(angulo) {
			case 0 : 	lista.add(miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()-1));//A
						lista.add(miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()+1));//B
						lista.add(miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()+2));//C
						break;
			case 90:	lista.add(miGrilla.getBloque(pivote.getPosX()-2, pivote.getPosY()));//B
						lista.add(miGrilla.getBloque(pivote.getPosX()-1, pivote.getPosY()));//C
						lista.add(miGrilla.getBloque(pivote.getPosX()+1, pivote.getPosY()));//A
						break;
			case 180:	lista.add(miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()+1));//A
						lista.add(miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()-1));//C
						lista.add(miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()-2));//B
						break;
			case 270:	lista.add(miGrilla.getBloque(pivote.getPosX()-1, pivote.getPosY()));//A
						lista.add(miGrilla.getBloque(pivote.getPosX()+1, pivote.getPosY()));//B
						lista.add(miGrilla.getBloque(pivote.getPosX()+2, pivote.getPosY()));//C
						break;
		}
		return lista;
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
			case 90:	A.desocupar();
						A = miGrilla.getBloque(pivote.getPosX()+1, pivote.getPosY());
						A.ocupar(img);
						B.desocupar();
						B = miGrilla.getBloque(pivote.getPosX()-2, pivote.getPosY());
						C.desocupar();
						C = miGrilla.getBloque(pivote.getPosX()-1, pivote.getPosY());
						C.ocupar(img);
						angulo = 180;
						break;
			case 180:	A.desocupar();
						A = miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()+1);
						A.ocupar(img);
						B.desocupar();
						B = miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()-2);
						B.ocupar(img);
						C.desocupar();
						C = miGrilla.getBloque(pivote.getPosX(), pivote.getPosY()-1);
						C.ocupar(img);
						angulo = 270;
						break;
			case 270:	A.desocupar();
						A = miGrilla.getBloque(pivote.getPosX()-1, pivote.getPosY());
						A.ocupar(img);
						B.desocupar();
						B = miGrilla.getBloque(pivote.getPosX()+1, pivote.getPosY());
						B.ocupar(img);
						C.desocupar();
						C = miGrilla.getBloque(pivote.getPosX()+2, pivote.getPosY());
						C.ocupar(img);
						angulo = 0;
						break;
			
		}
	}

}
