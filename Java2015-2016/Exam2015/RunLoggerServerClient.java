package advjava.exam;

/**
 * Utility class. If you start it, it will run the logger server, the server and
 * the client, and stop when the client has received all the answers.
 */
public class RunLoggerServerClient {
	interface RunnableWithException {
		public void run() throws Exception;
	}

	public static void main(String[] args) throws InterruptedException {
		runAction(() -> { MoonLogServer.main(new String[]{}); });
		waitForAWhile();
		runAction(() -> { MoonServer.main(new String[]{}); });
		waitForAWhile();
		Thread clientThread = runAction(() -> { MoonClient.main(new String[]{}); });
		
		clientThread.join();

		waitForAWhile();

		System.out.println("Runner exiting...");
		
		// forced exit
		System.exit(0);
	}

	private static void waitForAWhile() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}
	}

	private static Thread runAction(RunnableWithException r) {
		Thread thread = new Thread(() -> {
			try {
				r.run();
			} catch (Exception e) {
				// should not happen
				e.printStackTrace();
			}
		});
		
		thread.start();
		
		return thread;
	}
}
