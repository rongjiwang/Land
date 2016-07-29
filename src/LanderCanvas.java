import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LanderCanvas extends JPanel {
	public static double dropTime = 0.01;
	Rectangle dr;
	BufferedImage offscreen;
	public static int x = 0;
	public static int y = 0;
	public static LanderFrame frame;

	int[] groundXS = { 0, 30, 40, 100, 140, 160, 180, 200, 220, 230, 300, 310, 330, 350, 360, 400, 410, 435, 460, 465,
			500, 545, 560, 575, 580, 600, 600, 0 };
	int[] groundYS = { 500, 450, 480, 510, 350, 400, 395, 480, 490, 480, 480, 520, 515, 520, 515, 550, 400, 350, 360,
			400, 410, 480, 455, 465, 480, 500, 600, 600 };
	int[] landerXS = { 11, 13, 27, 29, 30, 26, 37, 40, 40, 30, 30, 33, 24, 21, 24, 16, 19, 16, 7, 0, 0, 10, 10, 3, 14,
			10 };
	int[] landerYS = { 5, 0, 0, 5, 20, 20, 35, 35, 40, 40, 35, 35, 20, 20, 25, 25, 20, 20, 35, 35, 40, 40, 35, 35, 20,
			20 };
	private boolean screen;
	public static int fuel = 100;
	public static boolean showFire;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

	}

	@Override
	public Image createImage(int x, int y) {
		return super.createImage(x, y);
	}

	@Override
	public Dimension getPreferredSize() {

		return new Dimension(600, 600);
	}

	public void gravity() {
		LanderCanvas.y = (int) ((LanderCanvas.y + 1) * (1 + (dropTime += 0.001)));

	}

	/**
	 * draw fire after lander
	 */
	public void fire() {
		this.showFire = true;
		dropTime = 0.01;
		LanderCanvas.y = (int) ((LanderCanvas.y - 1) / (1 + dropTime));

	}

	public void bufferdraw() {
		screen = true;
		offscreen = (BufferedImage) createImage(600, 600);
		Graphics offgc = offscreen.getGraphics();
		offgc.setColor(Color.red);
		offgc.fillRect(0, 0, 1000, 1000);
	}

	private void thrust(int fuel2, Graphics g) {
		g.drawRect(450, 20, 100, 20);
		g.drawString("Fuel", 420, 35);
		g.setColor(Color.GREEN);
		g.fillRect(450, 20, fuel2, 20);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);// set background color
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.gray); // set land and lander color
		// set a polygon ground and set with color
		Polygon ground = new Polygon(groundXS, groundYS, groundYS.length);
		g.fillPolygon(ground);
		thrust(fuel, g);

		// graphcis pixel changing
		g.translate(x, y);
		// set a polygong lander and set with color
		Polygon lander = new Polygon(landerXS, landerYS, landerYS.length);
		g.fillPolygon(lander);
		// Surround with rectangle for collision testing
		Rectangle dr = lander.getBoundingBox();
		// renew the rectangle position
		dr.translate(x, y);
		// if (showFire) {
		// g.setColor(Color.white);
		// g.fillRoundRect((int)(dr.getMinX()),(int)( dr.getMaxY()), dr.width/2,
		// dr.height/2, 30, 10);
		// }
		if (ground.intersects(dr)) {
			for (int i = 0; i < landerXS.length; i++) {
				if (groundXS[i] <= dr.getMinX() && groundXS[i + 1] >= dr.getMaxX()) {
					if (((dr.getMaxY()) >= groundYS[i]) && (Math.abs(groundYS[i] - groundYS[i + 1])) <= 10) {
						// player win the game
						frame.wonTheGame();
						//return;
					} else {
						// lost the game
						frame.lostTheGame();
						//return;
					}
				}

			}
			// lost the game
			frame.lostTheGame();
			//return;
		}
		// if(screen){
		// g.drawImage(offscreen,0,0,this);
		// }

	}

}
