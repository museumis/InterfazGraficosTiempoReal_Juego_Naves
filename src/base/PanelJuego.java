package base;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import pantallas.Pantalla;
import pantallas.PantallaBienvenida;

public class PanelJuego extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	// Pantalla inicio
	ArrayList<Pantalla> pantallas;
	/*
	 * Constructor
	 */
	public PanelJuego() {
		//Iniciar pantalla de bienvenida
		pantallas= new ArrayList<>();
		pantallas.add(new PantallaBienvenida(this));
		new Thread(this).start();
	}// Fin del constructor

	/**
	 * Metodo sobreescrito para pintar el componente, frames por segundo
	 */
	@Override
	protected void paintComponent(Graphics g) {
		pantallas.get(0).pintarPantalla(g);
	}

	/**
	 * Run del panel
	 */
	@Override
	public void run() {
		while (true) {
			//Repintar
			this.repaint();

			pantallas.get(0).ejecutarFrame();
			//interfaz. efecutarFrame
			//Dormir
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
			}
		});

		// El sprite de la nave se movéra conforme la posicion del raton
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
			}

			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});

		// Reescala la imagen de fondo si se hace resize la ventana
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
			}
		});
	}



}
