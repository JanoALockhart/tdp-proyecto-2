package entities;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Image;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI extends JFrame {

	private JPanel contentPane;
	private Juego juego;
	private JLabel[][] matrizLabels;
	private JLabel lblConTiempo;
	private JLabel lblConPuntuacion;
	
	private static final int CANT_FILAS = 21;
	private static final int CANT_COL = 10;
	private static final int TAM_CELDA = 30;
	
	
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
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLACK));
		
		JLabel lblTiempo = new JLabel("TIEMPO");
		lblTiempo.setFont(new Font("Showcard Gothic", Font.PLAIN, 25));
		
		lblConTiempo = new JLabel("00:00");
		lblConTiempo.setFont(new Font("Showcard Gothic", Font.PLAIN, 25));
		
		JLabel lblPuntuacion = new JLabel("PUNTUACION\r\n");
		lblPuntuacion.setFont(new Font("Showcard Gothic", Font.PLAIN, 25));
		
		lblConPuntuacion = new JLabel("0000000");
		lblConPuntuacion.setFont(new Font("Showcard Gothic", Font.PLAIN, 25));
		
		JLabel lblSiguiente = new JLabel("SIGUIENTE");
		lblSiguiente.setFont(new Font("Showcard Gothic", Font.PLAIN, 25));
		
		JLabel lblConSiguiente = new JLabel("");
		lblConSiguiente.setBorder(new LineBorder(Color.BLACK));
		lblConSiguiente.setFont(new Font("Showcard Gothic", Font.PLAIN, 25));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTiempo, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
						.addComponent(lblConTiempo, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPuntuacion, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblConPuntuacion, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSiguiente, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblConSiguiente, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
					.addGap(7))
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
							.addGap(10)
							.addComponent(lblConSiguiente, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE))
					.addGap(13))
		);
		panel.setLayout(new GridLayout(CANT_FILAS, CANT_COL, 0, 0));
		contentPane.setLayout(gl_contentPane);
		
		//Crear Juego
		juego = new Juego();
		
		matrizLabels = new JLabel[CANT_FILAS][CANT_COL];
		//Ajustar tamaño magen bloque vacio al tamaño de la celda
		ImageIcon img = new ImageIcon(pruebaLayouts.class.getResource("/images/bloqueVacio.png"));
		Image imgResized = img.getImage().getScaledInstance(TAM_CELDA, TAM_CELDA, Image.SCALE_SMOOTH);
		img = new ImageIcon(imgResized);
		JLabel celda;
		
		//Inicializar los labels con la imagen y agregarlos a la matriz y al panel grafico
		for(int fila=0; fila<CANT_FILAS; fila++) {
			for(int col=0; col<CANT_COL; col++) {
				celda = new JLabel();
				celda.setSize(new Dimension(TAM_CELDA,TAM_CELDA));
				celda.setIcon(img);
				matrizLabels[fila][col]=celda;
				panel.add(celda);
			}
		}	
		
		//Agregar oyente
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent teclaApretada) {
				switch (teclaApretada.getKeyCode()) {
					case KeyEvent.VK_D:{
						juego.avisarDer();
					}
					case KeyEvent.VK_A:{
						juego.avisarIzq();
					}
					case KeyEvent.VK_SPACE:{
						juego.avisarRotar();
					}
				}
			}
		});
	}
	
	//TODO que recibe? propuesta: coleccion de bloques
	/**
	 * Metodo que cambia la imagen de los labels correspondientes
	 * en el tablero de la GUI, segun los bloques pasados por parametro.
	 */
	public void actualizarLabels() {
		
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
	 * Metodo que muestra la pantalla final del juego, la cual
	 * incluye la puntuacion obtenida y el tiempo transcurrido
	 */
	public void mostrarPantallaFinal() {

	}

}
