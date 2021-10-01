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
import java.awt.Toolkit;

public class GUI extends JFrame {
	
	private Juego juego;
	
	private JPanel contentPane;
	
	private JPanel tableroDeJuego;
	private JPanel tableroTetriSiguiente;
	
	private JLabel[][] matrizLabels;
	private JLabel[][] matrizTetriSig;
	private JLabel lblConTiempo;
	private JLabel lblConPuntuacion;
	JLabel lblFinPartida;
	
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
		setTitle("Tetris Comision 9");
		setBackground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/images/bloqueRojo.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
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
	 * en el tablero grande de la GUI, segun los bloques pasados por parametro.
	 */
	public void actualizarLabels(Iterable<Bloque> bloquesActualizar) {
		ImageIcon icono;
		for(Bloque bloq : bloquesActualizar) {
			icono = reEscalar(bloq.getDirImagen());
			matrizLabels[bloq.getPosX()][bloq.getPosY()].setIcon(icono);
		}
	}
	
	/**
	 * Metodo que actualiza el tablero peque�o que muestra
	 * cual ser� el siguiente tetrimino.
	 */
	public void actualizarNuevoTetrimino(Tetrimino sigTetri) {
		//Vaciar labels de tetrimino siguiente
		for(int fila=0; fila<FILAS_TETRISIG; fila++) {
			for(int col=0; col<COL_TETRISIG; col++) {
				matrizTetriSig[col][fila].setIcon(reEscalar("/images/bloqueVacio.png"));
			}
		}
		
		//imprimir el tetrimino siguiente
		ImageIcon icono = reEscalar(sigTetri.getImage());
		for(Bloque bloq : sigTetri.getBloquesTetrimino()) {
			matrizTetriSig[bloq.getPosX()-1][bloq.getPosY()].setIcon(icono);
		}
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
	 * Metodo que devuelve un ImageIcon con los tama�os correspondientes
	 * para las celdas.
	 * @param dirImg Es el directorio en el que se encuantra la imagen.
	 * @return Un ImageIcon con el tama�o de la celda.
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
		lblFinPartida.setText("GAME OVER");
		PantallaFinal endScreen = new PantallaFinal(lblConPuntuacion.getText(),lblConTiempo.getText());
		endScreen.setVisible(true);
		dispose();
	}
	
	/**
	 * Metodo que crea y ubica los distintos labels que se muestran
	 * por pantalla.
	 */
	private GroupLayout colocarElementosGraficos() {
				
		tableroDeJuego = new JPanel();
		tableroDeJuego.setBorder(new LineBorder(Color.BLACK));
		
		JLabel lblTiempo = new JLabel("TIEMPO");
		lblTiempo.setForeground(Color.WHITE);
		lblTiempo.setFont(mainFont);
		
		lblConTiempo = new JLabel("00:00");
		lblConTiempo.setForeground(Color.WHITE);
		lblConTiempo.setFont(mainFont);
		
		JLabel lblPuntuacion = new JLabel("PUNTUACION");
		lblPuntuacion.setForeground(Color.WHITE);
		lblPuntuacion.setFont(mainFont);
		
		lblConPuntuacion = new JLabel("0");
		lblConPuntuacion.setForeground(Color.WHITE);
		lblConPuntuacion.setFont(mainFont);
		
		JLabel lblSiguiente = new JLabel("SIGUIENTE");
		lblSiguiente.setForeground(Color.WHITE);
		lblSiguiente.setFont(mainFont);
		
		tableroTetriSiguiente = new JPanel();
		
		lblFinPartida = new JLabel("");
		lblFinPartida.setFont(mainFont);
		lblFinPartida.setForeground(Color.RED);
		
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
								.addComponent(lblConPuntuacion, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSiguiente, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPuntuacion))
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblFinPartida, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(tableroTetriSiguiente, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
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
							.addComponent(tableroTetriSiguiente, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblFinPartida, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
						.addComponent(tableroDeJuego, GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE))
					.addGap(13))
		);
		return gl_contentPane;
	}
	
	/**
	 * Metodo que se encarga de crear todas las celdas del
	 * tablero en el que se llevara a cabo el juego.
	 */
	private void inicializarCeldasTableroJuego() {
		matrizLabels = new JLabel[CANT_COL][CANT_FILAS];
		//Ajustar tama�o magen bloque vacio al tama�o de la celda
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
	
	/**
	 * Metodo que se encarga de crear e insertar las celdas
	 * del tablero peque�o que muestra el tetrimino siguiente
	 */
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
