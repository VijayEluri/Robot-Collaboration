package core;

import device.Device;

// TODO wrap message service
public class Logger {
	/**
	 * Log an event.
	 * @param isError
	 * @param event
	 * @param objName
	 * @param id
	 * @param thread
	 */
	public static synchronized void logActivity(boolean isError, String event, String objName, int id, String thread) {
		
		int otherId = -1;
		final String otherThread = "no thread";
		String threadName = null;

		if (id >= 0) { otherId = id; }
		if (thread == null) {
			threadName = otherThread;
		} else {
			threadName = thread;
		}
		String logMsg = event + " of " + objName + " of device " + otherId + " in " + threadName;

		if (isError == true) {
			System.err.println(logMsg);
		} else {
			System.out.println(logMsg);
		}
	}
	/**
	 * Log an event a given device object is taken part in.
	 * @param isError
	 * @param event
	 * @param objName
	 */
	public static synchronized void logDeviceActivity (boolean isError, String event, Device objName) {
		if (event != null && objName != null) {
			String threadName = objName.getThreadName();
			if (threadName == null) {
				threadName = new String ("no thread");
			}
				
			String logMsg = event + " of "
			+ objName.toString()
			+ " @(" + objName.getHost() + "," + objName.getPort() + "," + objName.getDeviceNumber() + ")"
			+ " in " + threadName;

			if (isError == true) {
				System.err.println(logMsg);
			} else {
				System.out.println(logMsg);
			}
		}
	}
}
