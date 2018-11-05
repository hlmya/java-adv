package pr;

public class SynchronizedSample {

	// volatile is not helpful in this case
	private static int count = 0;
	
	// synchronized guarantees ONLY ONE thread running at a time.
	public synchronized static void increment() {
		count++;
	}
	
	public static void main(String[] args) {
	
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment();
				}
				
			}
		});
		
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment();
				}
				
			}
		});
		
		thread1.start();
		thread2.start();
		
		//join() means "wait for this thread to finish"
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// this code is implemented when thread1 and thread 2 finish
		System.out.println("Count is: " + count);
	}

}
