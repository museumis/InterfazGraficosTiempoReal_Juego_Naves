package base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite {

	// Elementos
	private String ruta;
	private Image imagen, imgAux;
	private Graphics grafico;
	// Propiedades
	private Color color;
	private int ancho;
	private int alto;
	private int posX;
	private int posY;
	private int velocidadX;
	private int velocidadY;

	// Contructores
	public Sprite() {
	}

	// Constuctor nave
	public Sprite(int ancho, int alto, int posX, int posY, int velocidadX, int velocidadY, Color color, String ruta) {
		this.ancho = ancho;
		this.alto = alto;
		this.posX = posX;
		this.posY = posY;
		this.velocidadX = velocidadX;
		this.velocidadY = velocidadY;
		this.color = color;
		this.ruta = ruta;

		cargarSprite();
	}

	// Constuctor Bala
	public Sprite(int ancho, int alto, int posX, int posY, int velocidadX, int velocidadY, Color color) {
		this.ancho = ancho;
		this.alto = alto;
		this.posX = posX;
		this.posY = posY;
		this.velocidadX = velocidadX;
		this.velocidadY = velocidadY;
		this.color = color;

		cargarSprite();
	}

	/**
	 * Procedimiento para inciar el sprite
	 */
	public void cargarSprite() {
		// Preparativos
		imagen = new BufferedImage(this.ancho, this.alto, BufferedImage.TYPE_INT_ARGB);
		grafico = imagen.getGraphics();
		try {
			// Reescalado
			imgAux = ImageIO.read(new File(this.ruta));
			imgAux = imgAux.getScaledInstance(this.ancho, this.alto, BufferedImage.SCALE_SMOOTH);		
			// Dibujar
			grafico.drawImage(imgAux, 0, 0, null);
			//Si falla
		} catch (IOException e) {
			grafico.setColor(this.color);
			grafico.fillRect(0, 0, this.ancho, this.alto);
			grafico.dispose();
			// System.out.println("Imagen del Sprite no cargada.");
		}
	}

	/**
	 * Dibujar sprite
	 * 
	 * @param g
	 */
	public void pintarSprite(Graphics g) {
		g.drawImage(imagen, posX, posY, null);
	}

	public void moverSprite(int anchoPantalla, int altoPantalla) {
		if (posX >= anchoPantalla - ancho) {
			this.velocidadX = (-Math.abs(this.velocidadX));
		}
		if (posX <= 0) {
			this.velocidadX = (Math.abs(this.velocidadX));
		}
		if (posY >= altoPantalla - alto) {
			this.velocidadY = (-Math.abs(this.velocidadY));
		}
		if (posY <= 0) {
			this.velocidadY = (Math.abs(this.velocidadY));
		}

		this.posX = this.posX + this.velocidadX;
		this.posY = this.posY + this.velocidadY;
	}

	/**
	 * Funcion para saber si el sprite colisiona
	 * 
	 * @param otro
	 *            sprite
	 * @return true si colisiona con el sprite de entra
	 */
	public boolean colisionaCon(Sprite otro) {
		// Ver si comparten algun espacio a lo ancho:
		boolean colisionAncho = false;
		if (posX < otro.getPosX()) {
			if (ancho + posX > otro.getPosX()) {
				colisionAncho = true;
			}
		} else {
			if (otro.getAncho() + otro.getPosX() > posX) {
				colisionAncho = true;
			}
		}

		// Ver si comparten algun espacio a lo alto:
		boolean colisionAlto = false;
		if (posY < otro.getPosY()) {
			if (alto > otro.getPosY() - posY) {
				colisionAlto = true;
			}
		} else {
			if (otro.getAlto() > posY - otro.getPosY()) {
				colisionAlto = true;
			}
		}

		return colisionAncho && colisionAlto;
	}

	public boolean colisionConBordePantalla(int altoPantalla) {
		if (posY - (alto / 4) < 0) {
			return true;
		} else {
			return false;
		}

	}

	/*
	 * Get and Set
	 */
	public Image getBuffer() {
		return imagen;
	}

	public void setBuffer(Image buffer) {
		this.imagen = buffer;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(int velocidad) {
		this.velocidadX = velocidad;
	}

	public int getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}

}
