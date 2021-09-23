package entities;

public class Timer {
	
	private Juego miJuego;
	
	private int tiempoTranscurrido;
	
	private int velocidadTime;
	
	public Timer(Juego juego) {
		miJuego=juego;
		tiempoTranscurrido=0;
		velocidadTime=5000;
	}
	
	public void contadorSegundos() {
		while(true) {
			try {
				Thread.sleep(1000);
				tiempoTranscurrido++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void regularCaida() {
		int contador=0;
		while(true) {
			try {
				Thread.sleep(1000);
				if(Math.log10(tiempoTranscurrido)>contador) {
					contador++;
					velocidadTime=velocidadTime/2;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
