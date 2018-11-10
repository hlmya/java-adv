
// An incorrect implementation of a producer and consumer.
class Storage {
	int n;

	synchronized int get() {
		System.out.println("Got: " + n);
		return n;
	}

	synchronized void put(int n) {
		this.n = n;
		System.out.println("Put: " + n);
	}
}

class Producer implements Runnable {
	Storage q;

	Producer(Storage q) {
		this.q = q;
		new Thread(this, "Producer").start();
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			q.put(i);
		}
	}
}

class Consumer implements Runnable {
	Storage q;

	Consumer(Storage q) {
		this.q = q;
		new Thread(this, "Consumer").start();
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			q.get();
		}
	}
}

class PC {
	public static void main(String args[]) {
		Storage q = new Storage();
		new Producer(q);
		new Consumer(q);
		System.out.println("Press Control-C to stop.");
	}
}
