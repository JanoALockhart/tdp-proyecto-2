package entities;

import java.util.*;

public class Cuadrado extends Tetrimino{

	public Cuadrado(Grilla miGrilla) {
		img = "/images/bloqueAmarillo.png";
		this.miGrilla = miGrilla;
		pivote = miGrilla.getBloque(4,0);
		A = miGrilla.getBloque(5,0);
		B = miGrilla.getBloque(4,1);
		C = miGrilla.getBloque(5,1);
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
	public Iterable<Bloque> rotar() {
		//El cuadrado no rota xd
		List<Bloque> listaAnteriores=new LinkedList<Bloque>();
		return listaAnteriores;
	}

}
