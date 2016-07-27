import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class LanderFrame extends JFrame implements KeyListener {
	LanderCanvas canvas;
	public LanderFrame() {
		super("Moon Lander");
		 canvas = new LanderCanvas();
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
			if (LanderCanvas.x > this.getWidth()){
				canvas.bufferdraw();
				
			}
				LanderCanvas.x+=5;
		} else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) {
			if (LanderCanvas.x > 0)
				LanderCanvas.x-=5;
		} else if (code == KeyEvent.VK_UP || code == KeyEvent.VK_KP_UP) {
			if (LanderCanvas.y > 0)
				canvas.fire();
		} else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_KP_DOWN) {
			if (LanderCanvas.y < this.getHeight())
				LanderCanvas.y+=5;
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

}
