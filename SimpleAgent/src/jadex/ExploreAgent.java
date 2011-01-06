package jadex;

import robot.PioneerRsB;
import jadex.bridge.Argument;
import jadex.bridge.IArgument;
import jadex.micro.MicroAgent;
import jadex.micro.MicroAgentMetaInfo;

public class ExploreAgent extends MicroAgent {
	PioneerRsB pionRsB = null;

	public void agentCreated(){}

	public void executeBody()
	{
		System.out.println(getArgument("Starting up explore agent.."));
		try {
			pionRsB = new PioneerRsB("localhost", 6665, 1);
//			pionRG.setPlanner("localhost", 6685);
			// Planner
//			pionRG.setPosition(new Position(-28, 3, 90));
		} catch (Exception e) {
			// TODO why just JCC crashes here?
			e.printStackTrace();
			killAgent();
		}
	}
	public void agentKilled()
	{
		pionRsB.shutdown();
	}

	public static MicroAgentMetaInfo getMetaInfo()
	{
		return new MicroAgentMetaInfo("This agent starts up the Explorer agent.", 
				null, new IArgument[]{
				new Argument("welcome text", "This parameter is the argument given to the agent.", "String", "Hello world, this is a Jadex micro agent."),	
		}, null);
	}
}
