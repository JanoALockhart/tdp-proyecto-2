package entities;

import java.util.*;

public class Cuadrado extends Tetrimino{

	public Cuadrado(Grilla miGrilla) {
		img = "/images/bloqueAmarillo.png";
		this.miGrilla = miGrilla;
		pivote = miGrilla.getBloque(4,0);
		pivote.ocupar(img);
		A = miGrilla.getBloque(5,0);
		A.ocupar(img);
		B = miGrilla.getBloque(4,1);
		B.ocupar(img);
		C = miGrilla.getBloque(5,1);
		C.ocupar(img);
		misBloques.add(pivote);
		misBloques.add(A);
		misBloques.add(B);
		misBloques.add(C);
		angulo = 0;
	}
	
	@Override
	public List<Bloque> getBloquesParaRotar() {
		List<Bloque> lista = new LinkedList<Bloque>();
		return lista;
	}

	@Override
	public List<Bloque> getBLoquesMasDer() {
		List<Bloque> lista = new LinkedList<Bloque>();
		lista.add(A);
		lista.add(C);
		return lista;
	}

	@Override
	public List<Bloque> getBLoquesMasIzq() {
		List<Bloque> lista = new LinkedList<Bloque>();
		lista.add(pivote);
		lista.add(B);
		return lista;
	}

	@Override
	public List<Bloque> getBLoquesAbajo() {
		List<Bloque> lista = new LinkedList<Bloque>();
		lista.add(B);
		lista.add(C);
		return lista;
	}

	@Override
	public void rotar() {
		//El cuadrado no rota xd
	}

}
