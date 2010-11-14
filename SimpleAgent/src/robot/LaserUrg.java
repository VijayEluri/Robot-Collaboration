package robot;

import javaclient3.PlayerClient;
import javaclient3.PlayerException;
import javaclient3.RangerInterface;
import javaclient3.structures.PlayerConstants;

public class LaserUrg {
	
	protected RangerInterface	rang = null;
	protected double[] ranges	= null;
	protected int count;
	
	public LaserUrg (PlayerClient host) {
		try {
			this.rang = host.requestInterfaceRanger (0, PlayerConstants.PLAYER_OPEN_MODE);
			//this.count = rang.getData().getRanges_count();
			this.count = 682;
		} catch ( PlayerException e ) {
			System.err.println ("LaserUrg: > Error connecting to Player: ");
			System.err.println ("    [ " + e.toString() + " ]");
			System.exit (1);
		}
	}
	
	// Only to be called @~10Hz
	public void updateRanges () {
		// Wait for the laser readings
		while (!rang.isDataReady());
		this.ranges = rang.getData().getRanges();
	}
	
	public double[] getRanges () {
		return this.ranges;
	}

}