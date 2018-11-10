
package solutionV1;

class Storage {
	int value;
	boolean isValuePresent = false;
}

public class WaitNotify2vs1 {
	public static void main(String[] args) {
		Storage storage = new Storage();

		// boolean badFinished = false;
		boolean isFinished[] = { false };
		int someValue = 532;
		final int[] someArray = { 0 };
		// someArray = new int[]{ 1 };
		someArray[0] = 5323;

		new Thread(() -> {
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

			// not final, not good
			// badFinished = true;
			isFinished[0] = true;

			// someArray[0] = 532532;

			// someValue = 532;

			// System.out.println(someValue);
		}).start();

		new Thread(() -> {
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

					System.out.println("Consumer 1 " + storage.value);
					storage.notifyAll();
					storage.isValuePresent = false;
				}
			}
		}).start();

		new Thread(() -> {
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

					System.out.println("Consumer 2 " + storage.value);
					storage.notifyAll();
					storage.isValuePresent = false;
				}
			}
		}).start();
	}
}
