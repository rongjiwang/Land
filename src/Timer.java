
public class Timer extends Thread {
	private static final LanderFrame frame = new LanderFrame();
	LanderCanvas myCanvas = new LanderCanvas();;

	public void run() {
		while (LanderCanvas.GAME_STATUS == false) {
			try {
				Thread.sleep(100);// 0.1sec delay
				myCanvas.gravity();
				frame.repaint();
				// myCanvas.repaint();

			}

			catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.start();
		
	}

}
