
public class ThreadedFibonacci {
	public static void main(String[] args) {
		System.out.println(fib(18));
		System.out.println(fibThread(18));
	}

	private static int fib(int n) {
		if (n == 1)  return 1;
		if (n == 2)  return 1;
		return fib(n - 1) + fib(n - 2);
	}


	private static int fibThread(int n) {
		if (n == 1)  return 1;
		if (n == 2)  return 1;

		int[] retval = { 0, 0 };
		Thread t1 = new Thread(() -> retval[0] = fibThread(n - 1) );
		Thread t2 = new Thread(() -> retval[1] = fibThread(n - 2) );

		try {
			t1.start();
			t2.start();
			t1.join();
			t2.join();

			Thread.sleep(500);
		} catch (Exception e) {
		}

		return retval[0] + retval[1];
	}
}
