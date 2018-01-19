package pantallas;

import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;

public interface Pantalla {

	/**
	 * Metodo para inciar la pantalla
	 */
	public void inicializarPantalla();
	/*
	 * Metodo para pintar la pantalla
	 */
	public void pintarPantalla(Graphics g);
	/**
	 * Acciones en cada frame
	 */
	public void ejecutarFrame();
	
	/**
	 * Acciones que llevara acabo el raton cuando se mueva
	 */
	public void moverRaton(MouseEvent e);
	/**
	 * Acciones que llevara acabo el raton cuando se pulse
	 */
	public void pulsarRaton(MouseEvent e);
	/**
	 * Acciones que llevara acabo el raton cuando se presiona el raton
	 */
	public void mantenerPulsadoRaton(MouseEvent e);
	/*
	 * Accion al redimensionar
	 */
	public void redimensionarPantalla();
	
	
	
}
