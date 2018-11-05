package pr;

public class FibThread {

	public static void main(String[] args) {
		System.out.println("Normal Fib: " + fib(6));
		System.out.println("Thread fib: " + fibThread(6));
	}
	
	private static int fib(int n) {
		if(n == 1) return 1;
		if(n == 2) return 1;
		return fib(n-1) + fib(n-2);
	}
	
	private static int fibThread(int n) {
		if(n == 1) return 1;
		if(n == 2) return 1;
		
		int[] retval = {0,0};
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				retval[0] = fibThread(n-1);
			}
			
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				retval[1] = fibThread(n-2);
			}
			
		});
//		Thread t1 = new Thread(() -> retval[0] = fibThread(n - 1) );
//		Thread t2 = new Thread(() -> retval[1] = fibThread(n - 2) );
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
			
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return retval[0] + retval[1];
	}

}
