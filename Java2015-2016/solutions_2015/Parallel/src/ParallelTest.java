public class ParallelTest {
	static Object lock = new Object();

	synchronized void f() {
		// code
	}

	void fSync() {
		synchronized (this) {
			// code
		}
	}

	synchronized static void fStatic() {
		// code
	}

	static void fStaticSync() {
		synchronized (ParallelTest.class) {
			// code
		}
	}

	private synchronized static void myPrintln(String text) {
		synchronized (lock) {
			for (char c : text.toCharArray()) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// new Thread(new MyPrinterRunnable("aaaaaaaaaaaaaaaaaaa")).start();
		Thread t1 = new MyPrinterThread("aaaaaaaaaaaaaaaaaaa", lock);
		Thread t2 = new MyPrinterThread("bbbbbbbbbbbbbbbbbbb", lock);

		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					myPrintln("ccccccccccccccccccc");
				}
			}
		});

		Thread t4 = new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				myPrintln("ddddddddddddddddddd");
			}
		});

		t1.start();
		t2.start();
		t3.start();
		t4.start();

		t1.join();
		t2.join();
		t3.join();
		t4.join();

		System.out.println("Done.");
	}
}

class MyPrinterRunnable implements Runnable {
	private String string;

	public MyPrinterRunnable(String string) {
		this.string = string;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			synchronized (this) {
				System.out.println(string);
			}
		}
	}

}

class MyPrinterThread extends Thread {
	private String string;
	private Object lock;

	public MyPrinterThread(String string, Object lock) {
		this.string = string;
		this.lock = lock;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			synchronized (lock) {
				System.out.println(string);
			}
		}
	}

}