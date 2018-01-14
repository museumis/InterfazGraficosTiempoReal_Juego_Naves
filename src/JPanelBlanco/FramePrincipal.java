package JPanelBlanco;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class FramePrincipal {

	private JFrame frame;
	private PanelJuego juego;

	public FramePrincipal() {
		frame = new JFrame("");
		frame.setBounds(200, 200, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void iniciar() {
		iniciarComponentes();
		iniciarListened();
		frame.setVisible(true);
	}

	public void iniciarComponentes() {

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(false);
		frame.setLayout(new GridLayout());

		juego = new PanelJuego();

		frame.add(juego);
		ocultarRaton();

	}

	public void ocultarRaton() {
		// Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");

		// Set the blank cursor to the JFrame.
		frame.getContentPane().setCursor(cursor);
	}

	public void iniciarListened() {
	}

}
