
package solutionV2;

import java.util.function.Function;

class Storage {
	int value;
	boolean isValuePresent = false;
}

public class WaitNotify2vs1Better {
	public static void main(String[] args) {
		Storage storage = new Storage();

		// boolean badFinished = false;
		boolean isFinished[] = { false };
		int someValue = 532;
		final int[] someArray = { 0 };
		someArray[0] = 5323;

		Function<String, Thread> makeConsumer = consumerFactory(storage, isFinished);

		producerThread(storage, isFinished).start();
		makeConsumer.apply("Consumer 1").start();
		makeConsumer.apply("Consumer 2").start();
	}

	private static Function<String, Thread> consumerFactory(Storage storage, boolean[] isFinished) {
		return name -> new Thread(() -> {
			while (!isFinished[0]) {
				synchronized (storage) {
					while (!isFinished[0] && !storage.isValuePresent) {
						try {
							storage.wait();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					System.out.println(name + " " + storage.value);
					storage.notifyAll();
					storage.isValuePresent = false;
				}
			}
		});
	}

	private static Thread producerThread(Storage storage, boolean[] isFinished) {
		return new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				synchronized (storage) {
					while (storage.isValuePresent) {
						try {
							storage.wait();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					storage.value = i;
					storage.isValuePresent = true;
					System.out.println("Producer " + i);
					storage.notifyAll();
				}
			}

			isFinished[0] = true;
		});
	}
}
