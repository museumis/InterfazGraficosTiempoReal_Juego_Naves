package pantallas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import base.PanelJuego;
import base.Sprite;

public class PantallaJuego implements Pantalla {
	// Panel sobre el que actuar
	PanelJuego panelJuego;

	// Sprite
	private ArrayList<Sprite> asteroides;
	private Sprite nave;
	private Sprite bala;
	private final int NUMERO_ASTEROIDES = 1;
	private final String RUTA_IMG_ASTEROIDE = "src//img//golden_star.gif";
	private final String RUTA_IMG_NAVE = "src//img//nave.png";
	private final String RUTA_IMG_FONDO = "src//img//heavy.jpg";
	// Acciones
	private boolean disparoActivo = true;
	// Propiedades
	private final int TAMANIO_ASTEROIDE = 40;
	// Elementos
	Image imgFondo = null;
	private File fondo;

	// Cronometro
	private float tiempoIncial;
	private float tiempoActual;
	private float tiempoJuego;

	/*
	 * Constructor
	 */
	public PantallaJuego(PanelJuego panelJuego) {
		this.panelJuego = panelJuego;
		inicializarPantalla();
		redimensionarPantalla();
	}

	@Override
	public void inicializarPantalla() {
		// Dibujar fondo
		cargarFondo();
		// Iniciar listado de sprites
		asteroides = new ArrayList<>();
		// Iniciar asteroides
		for (int i = 0; i < NUMERO_ASTEROIDES; i++) {
			asteroides.add(new Sprite(TAMANIO_ASTEROIDE, TAMANIO_ASTEROIDE, 10, 10, aleatorio(-15, 31),
					aleatorio(-15, 31), new Color(255, 100, 100), RUTA_IMG_ASTEROIDE));
		}
		// Iniciar Nave
		nave = new Sprite(45, 40, 300, 300, 0, 0, new Color(255, 200, 200), RUTA_IMG_NAVE);
		// Iniciar Cronometro
		tiempoIncial = System.nanoTime();
	}

	@Override
	public void pintarPantalla(Graphics g) {
		ocultarRaton();
		actualizarFondo(g);
		pintarAsteroide(g);
		pintarNave(g);
		actualizarTiempo();
		pintarTiempo(g);
		if (bala != null) {
			bala.pintarSprite(g);
		}
		if(quedanAsteroides()) {
			panelJuego.setPantalla(new PantallaVictoria(panelJuego,tiempoJuego));
		}
		//Colision de la nave
		for (int j = 0; j < asteroides.size(); j++) {
				if(nave.colisionaCon(asteroides.get(j))) {
			panelJuego.setPantalla(new PantallaGameOver(panelJuego,tiempoJuego));

		}
		}
	
		

	}

	@Override
	public void ejecutarFrame() {
		try {
			// Movimiento bala
			if (bala != null) {
				if (bala.colisionConBordePantalla(panelJuego.getHeight())) {
					bala = null;
					disparoActivo = true;
				} else {
					bala.moverSprite(panelJuego.getWidth(), panelJuego.getHeight());
				}

			}

			// Asteroides
			for (int i = 0; i < asteroides.size(); i++) {
				// Colision por Bala
				if (bala != null && bala.colisionaCon(asteroides.get(i))) {
					asteroides.remove(i);
					bala = null;
					disparoActivo = true;
				} else {
					// Movimiento del asteroide
					asteroides.get(i).moverSprite(panelJuego.getWidth(), panelJuego.getHeight());
				}

			
				
			}
		

			Thread.sleep(25);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean quedanAsteroides() {
		if(asteroides.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Dibuja el fondo del panel
	 */
	public void cargarFondo() {
		// Fondo
		fondo = new File(RUTA_IMG_FONDO);
		try {
			imgFondo = ImageIO.read(fondo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Rellenar fondo
	 * 
	 * @param grafico
	 */
	public void actualizarFondo(Graphics g) {
		g.drawImage(imgFondo, 0, 0, null);
	}

	/**
	 * Pinta el contador de destruccion de sprites
	 */
	public void pintarTiempo(Graphics g) {
		// Escribir en grafico
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		// Formato para darle dos decilames
		DecimalFormat format = new DecimalFormat("#.##");
		g.drawString("Time: " + (format.format(tiempoJuego / 1000000000)), 20, 20);

	}

	/**
	 * Pinta el grafico con los datos del sprite
	 * 
	 * @param grafico
	 */
	public void pintarAsteroide(Graphics g) {
		for (int i = 0; i < asteroides.size(); i++) {
			asteroides.get(i).pintarSprite(g);
		}
	}

	public void pintarNave(Graphics g) {
		nave.pintarSprite(g);
	}

	public void pintarBala(Graphics g) {
		bala.pintarSprite(g);

	}

	/**
	 * Cronometro
	 */
	public void actualizarTiempo() {
		tiempoActual = System.nanoTime();
		tiempoJuego = tiempoActual - tiempoIncial;
	}

	public void ocultarRaton() {
		// Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		// Create a new blank cursor.
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
		// Set the blank cursor to the JFrame.
		this.panelJuego.getRootPane().setCursor(cursor);
	}
	public void mostrarRaton() {
		// Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		// Create a new blank cursor.
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
		// Set the blank cursor to the JFrame.
		this.panelJuego.getRootPane().setCursor(cursor);
	}

	@Override
	public void moverRaton(MouseEvent e) {
		nave.setPosX(e.getX() - (nave.getAncho() / 2));
		nave.setPosY(e.getY() - (nave.getAlto() / 2));
	}

	@Override
	public void pulsarRaton(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e) && disparoActivo) {
			bala = new Sprite(16, 40, nave.getPosX() + ((nave.getAncho() / 2) - 4),
					nave.getPosY() + (nave.getAlto() / 2), 0, -10, Color.green, "");
			disparoActivo = false;
		}
	}

	@Override
	public void mantenerPulsadoRaton(MouseEvent e) {
		nave.setPosX(e.getX() - (nave.getAncho() / 2));
		nave.setPosY(e.getY() - (nave.getAlto() / 2));
	}

	@Override
	public void redimensionarPantalla() {
		imgFondo = imgFondo.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(),
				BufferedImage.SCALE_SMOOTH);
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
