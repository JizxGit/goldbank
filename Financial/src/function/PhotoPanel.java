package function;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

class PhotoPanel extends JPanel {
	public PhotoPanel(Image inputPhoto) {
		photo=inputPhoto;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(photo, 0, 0, null);
	}

	private Image photo ;
}