package pantallas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Random;

import base.PanelJuego;

public class PantallaBienvenida implements Pantalla {

	// Panel sobre el que actuar
	PanelJuego panelJuego;	
	private Color colorInicio;

	/*
	 * Constructor
	 */
	public PantallaBienvenida(PanelJuego panelJuego) {
		colorInicio = Color.CYAN;
		this.panelJuego = panelJuego;
		inicializarPantalla();
		redimensionarPantalla();

	}

	@Override
	public void inicializarPantalla() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pintarPantalla(Graphics g) {
		// Escribir en grafico
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, panelJuego.getWidth(), panelJuego.getHeight());
		g.setColor(Color.BLACK);
		g.setFont(panelJuego.getFuente());
		g.drawString("NAVITOM", panelJuego.getWidth() / 3, panelJuego.getHeight() / 2);
		g.setColor(colorInicio);
		g.drawLine((panelJuego.getHeight() / 2) + 120, (panelJuego.getHeight() / 2) + 20,
				(panelJuego.getWidth() / 2) + 120, (panelJuego.getHeight() / 2) + 20);
	}

	@Override
	public void ejecutarFrame() {
		try {
			// color de Inicio
			colorInicio = new Color(aleatorio(0, 255), aleatorio(0, 255), aleatorio(0, 255));
			Thread.sleep(225);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void moverRaton(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pulsarRaton(MouseEvent e) {
		panelJuego.setPantalla(new PantallaJuego(panelJuego));

	}

	@Override
	public void redimensionarPantalla() {
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
