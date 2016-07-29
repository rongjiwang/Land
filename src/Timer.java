
public class Timer extends Thread {
	private static LanderFrame frame = new LanderFrame();
	public static boolean GAME_STATUS = false;

	LanderCanvas myCanvas = new LanderCanvas();;

	public void run() {
		while (!GAME_STATUS) {
			try {
				Thread.sleep(100);// 0.1sec delay
				myCanvas.gravity();
				frame.repaint();

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
