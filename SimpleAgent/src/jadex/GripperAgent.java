package jadex;

import jadex.bridge.*;
import jadex.micro.MicroAgentMetaInfo;
import data.Position;
import device.DeviceNode;
import robot.GripperRobot;

public class GripperAgent extends PlayerAgent
{
//	protected final static String[] playerCmd={"/usr/local/bin/player","/Users/sebastian/robotcolla/SimpleAgent/player/planner2.cfg"};

	DeviceNode devices = null;
	GripperRobot gripper = null;
	Position curPos = null;
	//TODO start planner
	@Override
	public void agentCreated()
	{
		super.agentCreated();
		try {
			devices = new DeviceNode("localhost", port);
			devices.runThreaded();
			
			gripper = new GripperRobot(devices);
			gripper.runThreaded();
			gripper.setPosition(new Position(-28, 3, 90));
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}
	
	@Override
	public void executeBody()
	{
		super.executeBody();
		if (gripper == null) {
			killAgent();
		}

		final IComponentStep step = new IComponentStep()
		{			
			public Object execute(IInternalAccess args)
			{
				curPos = gripper.getPosition();

				waitFor(1000, this);
				return null;
			}
		};
		waitForTick(step);
	}
	@Override
	public void agentKilled() {
		
		super.agentKilled();
	}
	public static MicroAgentMetaInfo getMetaInfo()
	{
		IArgument[] args = {
				new Argument("requires player", "dummy", "Boolean", new Boolean(false)),
				new Argument("player path", "dummy", "String", playerPath),
				new Argument("player port", "dummy", "Integer", new Integer(port)),	
				new Argument("player config", "dummy", "String", "/Users/sebastian/robotcolla/SimpleAgent/player/planner2.cfg")};
		
		return new MicroAgentMetaInfo("This agent starts up a Player agent.", null, args, null);
	}
}
