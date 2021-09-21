package entities;

import java.util.*;

public class Cuadrado extends Tetrimino{

	public Cuadrado() {
		pivote = new Bloque("/images/bloqueVacio.png");
		A = new Bloque("/images/bloqueVacio.png");
		B = new Bloque("/images/bloqueVacio.png");
		C = new Bloque("/images/bloqueVacio.png");
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
