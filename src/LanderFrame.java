import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class LanderFrame extends JFrame implements KeyListener {
	LanderCanvas canvas;

	public LanderFrame() {
		super("Moon Lander");
		canvas = new LanderCanvas();
		LanderCanvas.frame = this;
		this.setLayout(new BorderLayout());
		this.add(canvas, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		this.setResizable(false);
		this.setVisible(true);
		this.addKeyListener(this);
		// canvas.repaint();
		// Timer time = new Timer();
		// time.start();
		// canvas.repaint();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT) {
			if (LanderCanvas.x < this.getWidth() && LanderCanvas.fuel != 0) {
				// canvas.bufferdraw();
				LanderCanvas.x += 5;
				LanderCanvas.fuel--;
			}
		} else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) {
			if (LanderCanvas.x > 0 && LanderCanvas.fuel != 0) {
				LanderCanvas.x -= 5;
				LanderCanvas.fuel--;
			}
		} else if (code == KeyEvent.VK_UP || code == KeyEvent.VK_KP_UP) {
			if (LanderCanvas.y > 0 && LanderCanvas.fuel != 0) {
				canvas.fire();
				LanderCanvas.fuel--;

			}
		} else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_KP_DOWN) {
			if (LanderCanvas.y < this.getHeight() && LanderCanvas.fuel != 0) {
				LanderCanvas.y += 5;
				LanderCanvas.fuel--;

			}
		}

	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		LanderCanvas.showFire = false;
	}

	@Override
	public void keyTyped(KeyEvent k) {
		// TODO Auto-generated method stub

	}

	public void wonTheGame() {
		Timer.GAME_STATUS = true;
		int r = JOptionPane.showConfirmDialog(this, new JLabel("You Won the game!"), "Won!",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
	}

	public void lostTheGame() {
		Timer.GAME_STATUS = true;

		int r = JOptionPane.showConfirmDialog(this, new JLabel("You Lost the game!"), "Lost!",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
	}

}
