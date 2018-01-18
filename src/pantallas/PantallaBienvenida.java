package pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

import base.PanelJuego;

public class PantallaBienvenida implements Pantalla {

	// Panel sobre el que actuar
	PanelJuego panelJuego;

	private Font fuente = new Font("TimesRoman", Font.PLAIN, 80);
	private Color colorInicio;

	/*
	 * Constructor
	 */
	public PantallaBienvenida(PanelJuego panelJuego) {
		colorInicio = Color.CYAN;
		this.panelJuego = panelJuego;
		inicializarPantalla();

	}

	@Override
	public void inicializarPantalla() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pintarPantalla(Graphics g) {
		// Escribir en grafico
		g.setColor(Color.BLACK);
		g.setFont(fuente);
		g.drawString("NAVITOM", panelJuego.getWidth() / 3, panelJuego.getHeight() / 2);
		g.setColor(colorInicio);
		g.drawLine((panelJuego.getHeight() / 2) + 120, (panelJuego.getHeight()/2)+20, (panelJuego.getWidth() / 2) + 120, (panelJuego.getHeight()/2)+20);
	}

	@Override
	public void ejecutarFrame() {

		// color de Inicio
		colorInicio = new Color(aleatorio(0,255), aleatorio(0,255),aleatorio(0,255));
	}

	@Override
	public void moverRaton(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pulsarRaton(MouseEvent e) {
		//iniciarPartida();

	}

	@Override
	public void redimensionarPantalla(ComponentEvent e) {
	}
	@Override
	public void mantenerPulsadoRaton(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Obtiene un aleatorio
	 * 
	 * @param minimo
	 * @param maximo
	 * @return aleatorio entre minimo y maximo
	 */
	public int aleatorio(int minimo, int cantidad) {
		Random r = new Random();
		int aleatorio = r.nextInt(cantidad) + minimo;
		return aleatorio;
	}


}
