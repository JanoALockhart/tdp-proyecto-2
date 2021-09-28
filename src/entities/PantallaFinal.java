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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;

public class PantallaFinal extends JFrame {

	private JPanel endScreen;
	/**
	 * Create the frame.
	 */
	public PantallaFinal(String puntuacion, String tiempo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 720);
		endScreen = new JPanel();
		endScreen.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(endScreen);
		
		endScreen.setBackground(Color.BLACK);
		
		JLabel lblGameOver = new JLabel("GAME OVER");
		lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameOver.setForeground(Color.WHITE);
		lblGameOver.setFont(new Font("Showcard Gothic", Font.PLAIN, 60));
		
		JLabel lblPuntuacion = new JLabel("PUNTUACION");
		lblPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntuacion.setForeground(Color.WHITE);
		lblPuntuacion.setFont(new Font("Showcard Gothic", Font.PLAIN, 45));
		
		JLabel lblTiempo = new JLabel("TIEMPO");
		lblTiempo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiempo.setForeground(Color.WHITE);
		lblTiempo.setFont(new Font("Showcard Gothic", Font.PLAIN, 45));
		
		JLabel lblConPuntuacionES = new JLabel(puntuacion);
		lblConPuntuacionES.setHorizontalAlignment(SwingConstants.CENTER);
		lblConPuntuacionES.setForeground(Color.WHITE);
		lblConPuntuacionES.setFont(new Font("Showcard Gothic", Font.PLAIN, 45));
		
		JLabel lblConTiempoES = new JLabel(tiempo);
		lblConTiempoES.setHorizontalAlignment(SwingConstants.CENTER);
		lblConTiempoES.setForeground(Color.WHITE);
		lblConTiempoES.setFont(new Font("Showcard Gothic", Font.PLAIN, 45));
		GroupLayout gl_endScreen = new GroupLayout(endScreen);
		gl_endScreen.setHorizontalGroup(
			gl_endScreen.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_endScreen.createSequentialGroup()
					.addGap(59)
					.addGroup(gl_endScreen.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblGameOver, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
						.addGroup(gl_endScreen.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 10, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblTiempo, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
							.addGap(10))
						.addGroup(gl_endScreen.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 10, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblConTiempoES, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
							.addGap(10))
						.addGroup(gl_endScreen.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 10, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblConPuntuacionES, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
							.addGap(10))
						.addGroup(gl_endScreen.createSequentialGroup()
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
	}

}
