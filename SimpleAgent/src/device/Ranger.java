package device;

import javaclient3.PlayerClient;
import javaclient3.PlayerException;
import javaclient3.RangerInterface;
import javaclient3.structures.PlayerConstants;

public class Ranger implements Runnable {

	protected RangerInterface rang = null;
	protected double[] ranges	= null;
	protected int count;
	protected final int SLEEPTIME = 100;

	// Every class of this type has it's own thread
	public Thread thread = new Thread ( this );

	public Ranger (PlayerClient host, int id) {
		try {
			this.rang = host.requestInterfaceRanger (0, PlayerConstants.PLAYER_OPEN_MODE);

			// Automatically start own thread in constructor
			this.thread.start();
			System.out.println("Running "
					+ this.toString()
					+ " in thread: "
					+ this.thread.getName()
					+ " of robot "
					+ id);

		} catch ( PlayerException e ) {
			System.err.println ("Ranger: > Error connecting to Player: ");
			System.err.println ("    [ " + e.toString() + " ]");
			System.exit (1);
		}
	}
	// Will check for new ranges
	// If not yet ready will put current thread to sleep
	protected void update() {
		// Wait for the laser readings
		if ( rang.isDataReady() ) {
			//				System.out.println("Laser data ready");
			if(rang.getData() != null) {
				this.count = rang.getData().getRanges_count();
				//					System.out.println("Count: " + count);
			}
			if (this.count > 0) {
				this.ranges = rang.getData().getRanges();
			}
		}
		try { Thread.sleep (this.SLEEPTIME); }
		catch (InterruptedException e) { this.thread.interrupt(); }
	}

	public double[] getRanges () {
		return this.ranges;
	}

	public int getCount () {
		return this.count;
	}

	@Override
	public void run() {
		while ( ! this.thread.isInterrupted()) {
			this.update();
		}
		System.out.println("Shutdown of " + this.toString());
	}
}
