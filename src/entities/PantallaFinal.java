package entities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;

public class PantallaFinal extends JFrame {

	private JPanel endScreen;
	private Font mainFont;
	private static final InputStream INPUT_STREAM_FUENTE = GUI.class.getResourceAsStream("../fonts/Early GameBoy.ttf");
	/**
	 * Create the frame.
	 */
	public PantallaFinal(String puntuacion, String tiempo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 720);
		endScreen = new JPanel();
		endScreen.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(endScreen);
		
		try {
			mainFont = Font.createFont(Font.TRUETYPE_FONT, INPUT_STREAM_FUENTE);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}	
		mainFont=mainFont.deriveFont(36f);
		
		endScreen.setBackground(Color.BLACK);
		
		JLabel lblGameOver = new JLabel("GAME OVER");
		lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameOver.setForeground(Color.WHITE);
		lblGameOver.setFont(mainFont);
		lblGameOver.setForeground(Color.RED);
		
		JLabel lblPuntuacion = new JLabel("PUNTUACION");
		lblPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntuacion.setForeground(Color.WHITE);
		lblPuntuacion.setFont(mainFont);
		
		JLabel lblTiempo = new JLabel("TIEMPO");
		lblTiempo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiempo.setForeground(Color.WHITE);
		lblTiempo.setFont(mainFont);
		
		JLabel lblConPuntuacionES = new JLabel(puntuacion);
		lblConPuntuacionES.setHorizontalAlignment(SwingConstants.CENTER);
		lblConPuntuacionES.setForeground(Color.WHITE);
		lblConPuntuacionES.setFont(mainFont);
		
		JLabel lblConTiempoES = new JLabel(tiempo);
		lblConTiempoES.setHorizontalAlignment(SwingConstants.CENTER);
		lblConTiempoES.setForeground(Color.WHITE);
		lblConTiempoES.setFont(mainFont);
		
		GroupLayout gl_endScreen = new GroupLayout(endScreen);
		gl_endScreen.setHorizontalGroup(
			gl_endScreen.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_endScreen.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPuntuacion, GroupLayout.PREFERRED_SIZE, 398, GroupLayout.PREFERRED_SIZE)
					.addGap(39))
				.addGroup(Alignment.LEADING, gl_endScreen.createSequentialGroup()
					.addGap(49)
					.addComponent(lblGameOver, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
					.addGap(49))
				.addGroup(Alignment.LEADING, gl_endScreen.createSequentialGroup()
					.addGap(59)
					.addComponent(lblConPuntuacionES, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(68, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_endScreen.createSequentialGroup()
					.addGap(59)
					.addComponent(lblTiempo, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(68, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_endScreen.createSequentialGroup()
					.addGap(59)
					.addComponent(lblConTiempoES, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(68, Short.MAX_VALUE))
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
					.addContainerGap(131, Short.MAX_VALUE))
		);
		endScreen.setLayout(gl_endScreen);
	}

}
