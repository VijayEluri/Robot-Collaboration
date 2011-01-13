package robot;

import data.Position;
import device.RangerSonar;
import javaclient3.PlayerException;

final public class PioneerS extends Pioneer {

	public PioneerS(String name, int port, int id) throws Exception {
		super(name, port, id);
		try {
			this.sonar = new RangerSonar (roboClient, super.id);
		} catch (PlayerException e) {
			System.err.println (this.toString()
					+ " of robot "
					+ id
					+ ": > Error connecting to Player: ");
			System.err.println ("    [ " + e.toString() + " ]");
			throw new IllegalStateException();
		}
//		super.playerclient.runThreaded (-1, -1);
	}

	@Override
	public void shutdownDevices() {
		this.sonar.thread.interrupt();
		while(this.sonar.thread.isAlive());
	}

	@Override
	public void setGoal(Position goal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Position getGoal() {
		// TODO Auto-generated method stub
		return null;
	}

}
