import java.io.IOException;

import simulator.Simulator;

import core.OSCommand;



public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
//		System.out.println("SimulationAgent" + OSCommand.run("ls -l"));
//		String[] playerCmd={"/usr/bin/nohup","/usr/local/bin/player","/Users/sebastian/robotcolla/SimpleAgent/player/uhh1.cfg","&"};
////		System.out.println(OSCommand.run(playerCmd));
////		System.err.println(OSCommand.run(playerCmd));
////		String[] testCmd={"open","/Applications/Chess.app"};
////		System.err.println(OSCommand.run(testCmd));
//		OSCommand testCmd = new OSCommand(playerCmd);
//		try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		testCmd.terminate();
////		while(true);
//		try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Simulator simu = Simulator.getInstance("localhost", 6600);
	}

}
