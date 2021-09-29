package entities;

public class Timer {
	
	private Juego miJuego;
	private int tiempoTranscurrido;
	private int velocidadTime;
	private boolean activo;
	
	private Thread cronometro;
	private Thread avisadorCaer;
	
	/**
	 * Constructor de la clase Timer
	 * @param juego Es el juego al que hay que avisarle
	 * el paso del tiempo de los segundos y hacer caer
	 * el tetrimino
	 */
	public Timer(Juego juego) {
		miJuego=juego;
		tiempoTranscurrido=0;
		velocidadTime=500;
		activo = true;
		
		//Inciar hilos para contar el tiempo y avisar para hacer caer
		cronometro = new Thread(new Runnable() {
			public void run() {
				contadorSegundos();
			}
		});
		cronometro.start();
		
		avisadorCaer = new Thread (new Runnable() {
			public void run() {
				regularCaida();
			}
		});
		avisadorCaer.start();
	}
	
	/**
	 * Metodo que va contando la cantidad de segundos que van pasando
	 * y avisa al juego para que se vaya actualizando.
	 */
	public void contadorSegundos() {
		while(activo) {
			try {
				Thread.sleep(1000);
				tiempoTranscurrido++;
				miJuego.actualizarTimerGui(tiempoFormateado());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metodo que avisa, cada cierto tiempo, que el tetrimino actual
	 * baje en uno. El tiempo entre avisos se va disminuyendo a medida
	 * que pasa el tiempo.
	 */
	public void regularCaida() {
		int contador=0;
		while(activo) {
			try {
				Thread.sleep(velocidadTime);
				miJuego.operarJuego(Juego.MOVER_ABAJO);
				if(tiempoTranscurrido/15>contador && velocidadTime>100) {
					contador++;
					velocidadTime=velocidadTime-80;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metodo que detiene el cronometro y el avisadorCaer
	 */
	public void detener() {
		activo = false;
	}
	
	public Thread getCronometro() {
		return cronometro;
	}
	
	public Thread getAvisadorCaer() {
		return avisadorCaer;
	}
	
	/**
	 * Metodo que retorna un string con el tiempo foromateado.
	 * @return Un string con el tiempo formateado.
	 */
	private String tiempoFormateado() {
		String segStr, minStr;
		int segundos, minutos;
		
		segundos = tiempoTranscurrido % 60;
		if(segundos<10) {
			segStr = "0"+segundos;
		}else {
			segStr = ""+segundos;
		}
		
		minutos =  tiempoTranscurrido / 60;
		if(minutos<10) {
			minStr = "0"+minutos;
		}else {
			minStr = ""+minutos;
		}
		
		return minStr+":"+segStr;
	}
}
