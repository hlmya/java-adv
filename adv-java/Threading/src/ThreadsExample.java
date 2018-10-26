import java.util.function.BiFunction;
import java.util.function.Function;

public class ThreadsExample {
	public static void main3(String[] args) {
		Function<Long, Thread> t = stepsToTake -> new Thread(() -> {
			long sum = 0;
			for (long i = 0; i < stepsToTake; i++) {
				sum += i;
			}
			System.out.println(sum);
		});

		t.apply(100000000000L).start();
		t.apply(100000000000L).start();
	}



	public static void main(String[] args) throws InterruptedException {
		Object lock = new Object();

		int[] num = { 0 };
		Thread thread = new Thread(() -> {
			for (int i = 0; i < 100000; i++) {
				println("AAAAAAAAAAAAAAAAAAAAAAAAAA", lock);
			}
		});
		Thread thread2 = new Thread(() -> {
			for (int i = 0; i < 100000; i++) {
				println("BBBBBBBBBBBBBBBBBBBBBBBBBB", lock);
			}
		});
		thread.start();
		thread2.start();
	}

	static void println(String text, Object lock) {
//		synchronized (text) {
//		synchronized (lock) {
//		synchronized (ThreadsExample.class) {
		synchronized (System.out) {
			for (char c : text.toCharArray()) {
				System.out.print(c);
			}
			System.out.println();
		}
	}


	public static void main5(String[] args) throws InterruptedException {
		Object lock = new Object();
		Object lock2 = new Object();

		int[] num = { 0 };
		Thread thread = new Thread(() -> {
			for (int i = 0; i < 100000; i++) {
				synchronized (lock) {
					++num[0];
				}
				System.out.println("A" + num[0]);
			}
		});
		Thread thread2 = new Thread(() -> {
			for (int i = 0; i < 100000; i++) {
				synchronized (lock2) {
					++num[0];
				}
				System.out.println("B" + num[0]);
			}
		});
		thread.start();
		thread2.start();

//		System.out.println(num[0]);
	}

	public static void main4(String[] args) throws InterruptedException {
		BiFunction<Integer, String, Thread> timeFun = (time, text) -> new Thread(() -> {
			System.out.println(text);

			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
			}

			System.out.println(text);
		});

		Thread thread500 = timeFun.apply(15000, "Hello");
		Thread thread1000 = timeFun.apply(30000, "world");

		thread1000.setName("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

//		thread500.run();
//		thread1000.run();
		thread500.start();
		thread1000.start();

		thread500.join();
		thread1000.join();

		System.out.println("Done.");
	}
}
