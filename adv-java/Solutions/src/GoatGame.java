import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class GoatGame {
	static int bridgeLength = 10;

	// option 1
	static int doubleGoatPosition = bridgeLength / 2;

	// option 2
	static int goatPosition1 = bridgeLength / 2;
	static int goatPosition2 = bridgeLength / 2 + 1;

	public static void main(String[] args) {
		BiFunction<Integer, Integer, Runnable> goatCreator = (goatWaitTime, pushAmount) -> () -> {
			while (areGoatsOnBridge()) {
				waitABit(goatWaitTime);
				pushOtherGoat(pushAmount);
			}
			System.out.printf("Goat finished (%d)%n", pushAmount);
		};

		Thread goat1 = new Thread(goatCreator.apply(200, 1));
		Thread goat2 = new Thread(goatCreator.apply(300, -1));

		Stream.of(goat1, goat2).forEach(Thread::start);
		Stream.of(goat1, goat2).forEach(GoatGame::goatJoin);

		System.out.println("Champion goat: " + doubleGoatPosition);
	}


	static boolean areGoatsOnBridge() {
		return 0 <= doubleGoatPosition &&
			   doubleGoatPosition <= bridgeLength;
	}

	static void waitABit(int goatWaitTime) {
		try {
			Thread.sleep(goatWaitTime);
		} catch (InterruptedException e) {
		}
	}

	static void pushOtherGoat(int pushAmount) {
		if (areGoatsOnBridge()) {
			System.out.printf("Push by %d at position %d%n", pushAmount, doubleGoatPosition);
			doubleGoatPosition += pushAmount;
		}
	}

	static void goatJoin(Thread goatThread) {
		try {
			goatThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
