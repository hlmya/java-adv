
public class ThreadDifferentWays {
	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello world");
			}
		};

		// the current thread runs it
		runnable.run();

		// a newly created thread runs it
		new Thread(runnable).start();

		// lambda (anon function)
		Runnable runnable2 = () -> {
		};
		new Thread(runnable2).start();

		Object lock = new Object();

		new Thread(() -> {
			synchronized (lock) {
				for (int i = 0; i < 100000; i++) {
					System.out.println("aaaaaaaaaaaaaa");
				}
			}
		}).start();

		new Thread(() -> {
			synchronized (lock) {
				for (int i = 0; i < 100000; i++) {
					System.out.println("vvvvvvvvvvvvvv");
				}
			}
		}).start();

	}
}

class MyThread extends Thread {
	@Override
	public void run() {
		// do something
	}
}

class MyThread2 implements Runnable {
	@Override
	public void run() {
		// do something
	}
}
