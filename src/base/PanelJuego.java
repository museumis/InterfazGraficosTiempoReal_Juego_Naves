package base;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import pantallas.Pantalla;
import pantallas.PantallaBienvenida;

public class PanelJuego extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	// Pantalla inicio
	Pantalla pantallaActual;
	private Font fuente;

	/*
	 * Constructor
	 */
	public PanelJuego() {
		// Iniciar pantalla de bienvenida
		pantallaActual = new PantallaBienvenida(this);
		fuente = new Font("TimesRoman", Font.PLAIN, 80);
		listened();
		new Thread(this).start();

	}// Fin del constructor

	/**
	 * Metodo sobreescrito para pintar el componente, frames por segundo
	 */
	@Override
	protected void paintComponent(Graphics g) {
		pantallaActual.pintarPantalla(g);
	}

	/**
	 * Run del panel
	 */
	@Override
	public void run() {
		while (true) {
			// Repintar
			this.repaint();
			pantallaActual.ejecutarFrame();
		
		}

	}

	/**
	 * Metodo que incia todos los listenes del panel
	 */
	public void listened() {
		// Al presionar el raton
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pantallaActual.pulsarRaton(e);
			}
		});

		// El sprite de la nave se movéra conforme la posicion del raton
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				pantallaActual.moverRaton(e);

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				pantallaActual.moverRaton(e);

			}
		});

		// Reescala la imagen de fondo si se hace resize la ventana
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				pantallaActual.redimensionarPantalla();
			}
		});
	}

	/**
	 * Metodo para cambia la pantall
	 * @param pantallaActual
	 */
	public void setPantalla(Pantalla pantallaActual) {
		this.pantallaActual = pantallaActual;
	}

	public Font getFuente() {
		return fuente;
	}

	public void setFuente(Font fuente) {
		this.fuente = fuente;
	}
	



}
