package JPanelBlanco;

import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						FramePrincipal ventana = new FramePrincipal();
						ventana.iniciar();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
	});

	}

}
