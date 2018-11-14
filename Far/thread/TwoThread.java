//Threading tasks
package thread;

public class TwoThread {

	// task 3
	public static void main(String[] args) {
    System.out.println(fibThread(8));
	}

	public static int fibThread(int n)  {
		if (n == 1)
			return 1;
		if (n == 2)
			return 1;
		int[] ret= {0,0};
		Thread thread1 = new Thread(() -> {
			ret[0] = fibThread(n-1);
		});
		Thread thread2 = new Thread(() -> {
			ret[1] = fibThread(n-2);
		});
		try {
		thread1.start();
		thread2.start();
		thread2.join();
		thread1.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}

		return ret[0]+ret[1];
	}

	// task 2
	public static void main2(String[] args) {
		int[] num = { 0 };
		Thread thread1 = new Thread(() -> {
			for (int i = 0; i < 100; i++)
				synchronized (System.out) {
					("A: " + ++num[0] + "\n").chars().mapToObj(ch -> (char) ch).forEach(System.out::print);
				}
		});
		Thread thread2 = new Thread(() -> {
			for (int i = 0; i < 100; i++)
				synchronized (System.out) {
					("B: " + ++num[0] + "\n").chars().mapToObj(ch -> (char) ch).forEach(System.out::print);
				}
		});

		thread1.start();
		thread2.start();
	}

	// task 1
	public static void main1(String[] args) {
		int[] num = { 0 };
		Thread thread1 = new Thread(() -> {
			for (int i = 0; i < 10000; i++)
				System.out.println("A: " + ++num[0]);
		});
		Thread thread2 = new Thread(() -> {
			for (int i = 0; i < 10000; i++)
				System.out.println("B: " + ++num[0]);
		});

		thread1.start();
		thread2.start();
	}
}
