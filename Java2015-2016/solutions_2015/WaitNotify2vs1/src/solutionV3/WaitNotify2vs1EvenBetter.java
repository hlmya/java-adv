
package solutionV3;

import java.util.Optional;
import java.util.function.Function;

class Storage {
	Optional<Integer> value = Optional.empty();
}

public class WaitNotify2vs1EvenBetter {
	public static void main(String[] args) {
		Storage storage = new Storage();

		// boolean badFinished = false;
		boolean isFinished[] = { false };
		final int[] someArray = { 0 };
		someArray[0] = 5323;

		Function<String, Thread> makeConsumer = consumerFactory(storage, isFinished);

		producerThread(storage, isFinished).start();
		for (int i = 0; i < 100; i++) {
			makeConsumer.apply("Consumer " + i).start();
		}
	}

	private static Function<String, Thread> consumerFactory(Storage storage, boolean[] isFinished) {
		return name -> new Thread(() -> {
			while (!isFinished[0]) {
				synchronized (storage) {
					while (!isFinished[0] && !storage.value.isPresent()) {
						try {
							storage.wait();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (storage.value.isPresent()) {
						System.out.println(name + " " + storage.value.get());
						storage.notifyAll();
						storage.value = Optional.empty();
					}
				}
			}
		});
	}

	private static Thread producerThread(Storage storage, boolean[] isFinished) {
		return new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				synchronized (storage) {
					while (storage.value.isPresent()) {
						try {
							storage.wait();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					storage.value = Optional.of(i);
					System.out.println("Producer " + i);
					storage.notifyAll();
				}
			}

			isFinished[0] = true;
		});
	}
}
