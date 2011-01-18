package device;

import core.Logger;
import javaclient3.PlayerException;
import javaclient3.RangerInterface;
import javaclient3.structures.PlayerConstants;

public class Ranger extends Device {

	protected RangerInterface rang = null;
	protected double[] ranges	= null;
	protected int count;
//	protected final static int SLEEPTIME = 100;

	// Every class of this type has it's own thread
//	public Thread thread = new Thread ( this );

	protected Ranger (int id) {
		super(id);
	}
	public Ranger (RobotClient roboClient, int id, int device) {
		super(id);
		try {
			rang = roboClient.getClient().requestInterfaceRanger(device, PlayerConstants.PLAYER_OPEN_MODE);
			
			// Automatically start own thread in constructor
//			this.thread.start();
//			Logger.logActivity(false, "Running", this.toString(), id, thread.getName());

		} catch ( PlayerException e ) {
//			System.err.println ("    [ " + e.toString() + " ]");
			Logger.logActivity(true, "Connecting", this.toString(), id, thread.getName());
			throw new IllegalStateException();
		}
	}
	public Ranger(RobotClient roboClient, Device device) {
		this(roboClient, device.getHost(),device.getDeviceNumber());
		host = device.getHost();
		name = device.getName();
		deviceNumber = device.getDeviceNumber();
		port = device.getPort();
	}
	/**
	 * Will check for new ranges
	 * If not yet ready will put current thread to sleep
	 */
	protected void update() {
		if ( ! rang.isDataReady() ) {
//			try { Thread.sleep (SLEEPTIME); }
//			catch (InterruptedException e) { this.thread.interrupt(); }
		} else {
			count = rang.getData().getRanges_count();
			ranges = rang.getData().getRanges();
		}
	}

	public double[] getRanges () {
		return ranges;
	}

	public int getCount () {
		return count;
	}

//	@Override
//	public void run() {
//		while ( ! thread.isInterrupted()) {
//			update();
//		}
//		Logger.logActivity(false, "Shutdown", this.toString(), id, thread.getName());
//	}
}
