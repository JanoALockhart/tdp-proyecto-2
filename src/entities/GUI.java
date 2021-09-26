package entities;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;

public class GUI extends JFrame {
	
	private Juego juego;
	
	private JPanel contentPane;
	
	private JPanel tableroDeJuego;
	private JPanel tableroTetriSiguiente;
	
	private JLabel[][] matrizLabels;
	private JLabel[][] matrizTetriSig;
	private JLabel lblConTiempo;
	private JLabel lblConPuntuacion;
	
	private Font mainFont;
	
	private static final int CANT_FILAS = 21;
	private static final int CANT_COL = 10;
	private static final int TAM_CELDA = 30;
	
	private static final int FILAS_TETRISIG = 3;
	private static final int COL_TETRISIG = 6;
	
	private static final InputStream INPUT_STREAM_FUENTE = GUI.class.getResourceAsStream("../fonts/Early GameBoy.ttf");
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		try {
			mainFont = Font.createFont(Font.TRUETYPE_FONT, INPUT_STREAM_FUENTE);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}	
		mainFont=mainFont.deriveFont(18f);
		
		GroupLayout elementos = colocarElementosGraficos();
				
		tableroDeJuego.setLayout(new GridLayout(CANT_FILAS, CANT_COL, 0, 0));
		tableroTetriSiguiente.setLayout(new GridLayout(FILAS_TETRISIG, COL_TETRISIG, 0, 0));
		contentPane.setLayout(elementos);
		
//		//Crear Juego
//		juego = new Juego(this);
		
		inicializarCeldasTableroJuego();
		inicializarCeldasTableroTetriSiguiente();
		
		//Agregar oyente para teclas derecha, izquierda y rotar
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent teclaApretada) {
				switch (teclaApretada.getKeyCode()) {
					case KeyEvent.VK_D:{
						juego.operarJuego(Juego.MOVER_DERECHA);
						break;
					}
					case KeyEvent.VK_A:{
						juego.operarJuego(Juego.MOVER_IZQUIERDA);
						break;
					}
					case KeyEvent.VK_SPACE:{
						juego.operarJuego(Juego.ROTAR);
						break;
					}
				}
			}
		});
		
		//Crear Juego
		juego = new Juego(this);
		
	}
	/**
	 * Metodo que cambia la imagen de los labels correspondientes
	 * en el tablero de la GUI, segun los bloques pasados por parametro.
	 */
	public void actualizarLabels(Iterable<Bloque> bloquesActualizar) {
		ImageIcon icono;
		for(Bloque bloq : bloquesActualizar) {
			icono = reEscalar(bloq.getDirImagen());
			matrizLabels[bloq.getPosX()][bloq.getPosY()].setIcon(icono);
		}
	}
	
	/**
	 * Metodo que actualiza cual será el siguiente
	 * tetrimino.
	 */
	public void actualizarNuevoTetrimino() {
		
	}
	
	/**
	 * Metodo que actualiza la cantidad de puntos en el label
	 * correspondiente
	 * @param puntuacion Es la nueva puntuacion a mostrar.
	 */
	public void actualizarScore(int puntuacion) {
		lblConPuntuacion.setText(Integer.toString(puntuacion));
	}
	
	/**
	 * Metodo que actualiza el tiempo mostrado por pantalla.
	 * @param tiempo Es el nuevo tiempo a mostrar
	 */
	public void actualizarTimer(String tiempo) {
		lblConTiempo.setText(tiempo);
	}
	
	/**
	 * Metodo que devuelve un ImageIcon con los tamaños correspondientes
	 * para las celdas.
	 * @param dirImg Es el directorio en el que se encuantra la imagen.
	 * @return Un ImageIcon con el tamaño de la celda.
	 */
	private ImageIcon reEscalar(String dirImg) {
		ImageIcon img = new ImageIcon(GUI.class.getResource(dirImg));
		Image imgResized = img.getImage().getScaledInstance(TAM_CELDA, TAM_CELDA, Image.SCALE_SMOOTH);
		return new ImageIcon(imgResized);
	}
	
	/**
	 * Metodo que muestra la pantalla final del juego, la cual
	 * incluye la puntuacion obtenida y el tiempo transcurrido
	 */
	public void mostrarPantallaFinal() {
		JPanel endScreen = new JPanel();
		contentPane.setVisible(false);
		endScreen.setBackground(Color.BLACK);
		
		JLabel lblGameOver = new JLabel("GAME OVER");
		lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameOver.setForeground(Color.WHITE);
		lblGameOver.setFont(mainFont);
		
		JLabel lblPuntuacion = new JLabel("PUNTUACION");
		lblPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntuacion.setForeground(Color.WHITE);
		lblPuntuacion.setFont(mainFont);
		
		JLabel lblTiempo = new JLabel("TIEMPO");
		lblTiempo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiempo.setForeground(Color.WHITE);
		lblTiempo.setFont(mainFont);
		
		JLabel lblConPuntuacionES = new JLabel(lblConPuntuacion.getText());
		lblConPuntuacionES.setHorizontalAlignment(SwingConstants.CENTER);
		lblConPuntuacionES.setForeground(Color.WHITE);
		lblConPuntuacionES.setFont(mainFont);
		
		JLabel lblConTiempoES = new JLabel(lblConTiempo.getText());
		lblConTiempoES.setHorizontalAlignment(SwingConstants.CENTER);
		lblConTiempoES.setForeground(Color.WHITE);
		lblConTiempoES.setFont(mainFont);
		GroupLayout gl_endScreen = new GroupLayout(endScreen);
		gl_endScreen.setHorizontalGroup(
			gl_endScreen.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_endScreen.createSequentialGroup()
					.addGap(58)
					.addGroup(gl_endScreen.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGameOver, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_endScreen.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 10, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblTiempo, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
							.addGap(10))
						.addGroup(Alignment.TRAILING, gl_endScreen.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 10, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblConTiempoES, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
							.addGap(10))
						.addGroup(Alignment.TRAILING, gl_endScreen.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 10, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblConPuntuacionES, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
							.addGap(10))
						.addGroup(Alignment.TRAILING, gl_endScreen.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 10, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblPuntuacion, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
							.addGap(10)))
					.addGap(39))
		);
		gl_endScreen.setVerticalGroup(
			gl_endScreen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_endScreen.createSequentialGroup()
					.addGap(44)
					.addComponent(lblGameOver)
					.addGap(62)
					.addComponent(lblPuntuacion, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblConPuntuacionES, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(78)
					.addComponent(lblTiempo, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblConTiempoES, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(102, Short.MAX_VALUE))
		);
		endScreen.setLayout(gl_endScreen);
		
		setContentPane(endScreen);
	}
	
	/**
	 * Metodo que crea el tablero en el cual estara el tetrimino
	 * actual y los bloques solidificados, coloca los labels
	 * y el contenedor para mostrar el tetrimino siguientes
	 */
	private GroupLayout colocarElementosGraficos() {
				
		tableroDeJuego = new JPanel();
		tableroDeJuego.setBorder(new LineBorder(Color.BLACK));
		
		JLabel lblTiempo = new JLabel("TIEMPO");
		lblTiempo.setFont(mainFont);
		
		lblConTiempo = new JLabel("00:00");
		lblConTiempo.setFont(mainFont);
		
		JLabel lblPuntuacion = new JLabel("PUNTUACION\r\n");
		lblPuntuacion.setFont(mainFont);
		
		lblConPuntuacion = new JLabel("0000000");
		lblConPuntuacion.setFont(mainFont);
		
		JLabel lblSiguiente = new JLabel("SIGUIENTE");
		lblSiguiente.setFont(mainFont);
		
		tableroTetriSiguiente = new JPanel();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(tableroDeJuego, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTiempo, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
								.addComponent(lblConTiempo, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPuntuacion, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblConPuntuacion, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSiguiente, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
							.addGap(7))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(tableroTetriSiguiente, GroupLayout.PREFERRED_SIZE, COL_TETRISIG*TAM_CELDA, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTiempo)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblConTiempo, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(43)
							.addComponent(lblPuntuacion, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblConPuntuacion, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(57)
							.addComponent(lblSiguiente, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tableroTetriSiguiente, GroupLayout.PREFERRED_SIZE, FILAS_TETRISIG*TAM_CELDA, GroupLayout.PREFERRED_SIZE))
						.addComponent(tableroDeJuego, GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE))
					.addGap(13))
		);
		return gl_contentPane;
	}
	
	private void inicializarCeldasTableroJuego() {
		matrizLabels = new JLabel[CANT_COL][CANT_FILAS];
		//Ajustar tamaño magen bloque vacio al tamaño de la celda
		ImageIcon img = reEscalar("/images/bloqueVacio.png");
		JLabel celda;
		//Inicializar los labels con la imagen y agregarlos a la matriz y al panel grafico
		for(int fila=0; fila<CANT_FILAS; fila++) {
			for(int col=0; col<CANT_COL; col++) {
				celda = new JLabel();
				celda.setSize(new Dimension(TAM_CELDA,TAM_CELDA));
				celda.setIcon(img);
				matrizLabels[col][fila]=celda;
				tableroDeJuego.add(celda);
			}
		}	
	}
	
	private void inicializarCeldasTableroTetriSiguiente() {
		matrizTetriSig = new JLabel[COL_TETRISIG][FILAS_TETRISIG];
		
		ImageIcon img = reEscalar("/images/bloqueVacio.png");
		JLabel celda;
		
		//Inicializar los labels con la imagen y agregarlos a la matriz y al panel grafico
		for(int fila=0; fila<FILAS_TETRISIG; fila++) {
			for(int col=0; col<COL_TETRISIG; col++) {
				celda = new JLabel();
				celda.setSize(new Dimension(TAM_CELDA,TAM_CELDA));
				celda.setIcon(img);
				matrizTetriSig[col][fila]=celda;
				tableroTetriSiguiente.add(celda);
			}
		}
	}
}
