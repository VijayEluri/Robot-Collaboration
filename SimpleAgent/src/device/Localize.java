package device;

import data.Position;
import javaclient3.structures.PlayerPose;
import javaclient3.structures.localize.PlayerLocalizeHypoth;
import javaclient3.structures.localize.PlayerLocalizeSetPose;

public class Localize extends PlayerDevice
{
	// TODO move to localize IF
	// initial values for the covariance matrix (c&p example from playernav)
	protected double cov[] = { 0.5*0.5, 0.5*0.5, (Math.PI/6.0)*(Math.PI/6.0), 0, 0, 0 };

	private Position getPosition;
	PlayerLocalizeSetPose setPosition;

	private boolean isNewPose = false;

	public Localize(RobotClient roboClient, Device device) {
		super(roboClient, device);

		// set the initial guessed pose for localization (AMCL)
		setPosition = new PlayerLocalizeSetPose ();
		// set the mean values to 0,0,0
		setPosition.setMean (new PlayerPose ());
		setPosition.setCov (cov);
		
	}
	// Only to be called @~10Hz
	@Override
	protected void update () {
		// TODO check if position is on map
		if (((javaclient3.LocalizeInterface) device).isDataReady()) {
			// set position belief
			// has to be before over writing curPosition!
			if(isNewPose) {
				isNewPose = false;
				((javaclient3.LocalizeInterface) device).setPose(setPosition);
			} else { // Read the current mean position
			// Update current position belief
				PlayerLocalizeHypoth[] hypList = ((javaclient3.LocalizeInterface) device).getData().getHypoths();
				if (hypList.length > 0) {
					// Only first hypothesis
					PlayerPose curPos = hypList[0].getMean();
					getPosition.setX(curPos.getPx());
					getPosition.setY(curPos.getPy());
					getPosition.setYaw(curPos.getPa());
				}
			}
		}
	}

	public synchronized void setPose(Position position) {
		setPosition.setMean(new PlayerPose(position.getX(),position.getY(),position.getYaw()));
		getPosition = position;
		isNewPose = true;
	}
	public synchronized Position getPose() {
		return getPosition;
	}
}
