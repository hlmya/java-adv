import java.util.Random;

public class Goat extends Thread {
	private Goat otherGoat;
	private Bridge bridge;
	private int minSpeed;
	private int maxSpeed;
	private Random rnd = new Random();
	private int number;
	private int pushAmount;

	private int position;

	public Goat(int number, Bridge bridge, int minSpeed, int maxSpeed, int pushAmount) {
		this.number = number;
		this.bridge = bridge;
		this.minSpeed = minSpeed;
		this.maxSpeed = maxSpeed;
		this.pushAmount = pushAmount;

		setPosition(bridge.getLength() / 2 + (isPushingUp() ? -1 : 0));
	}

	@Override
	public void run() {
		while (gameIsRunning()) {
			waitSomeTime();
			pushOtherGoat();
		}
		say("winner is: " + getWinner().number);
	}

	private void pushOtherGoat() {
		setPosition(getPosition() + pushAmount);
		otherGoat.setPosition(otherGoat.getPosition() + pushAmount);
	}

	private void waitSomeTime() {
		int rndTimeAmount = getRndBetween(minSpeed, maxSpeed);
		try {
			Thread.sleep(rndTimeAmount);
		} catch (InterruptedException e) {
		}
	}

	private int getRndBetween(int min, int max) {
		return min + rnd.nextInt(max - min);
	}

	private boolean gameIsRunning() {
		return (isPushingUp() ? bridge.isAtUpperEnd(getPosition()) : bridge.isAtLowerEnd(getPosition()));
	}

	private boolean isPushingUp() {
		return pushAmount > 0;
	}

	private Goat getWinner() {
		if (isPushingUp()) {
			if (bridge.isAtUpperEnd(getPosition()))
				return this;
			if (bridge.isAtLowerEnd(getPosition()))
				return otherGoat;
		} else {
			if (bridge.isAtUpperEnd(getPosition()))
				return otherGoat;
			if (bridge.isAtLowerEnd(getPosition()))
				return this;
		}
		return null;
	}

	private void say(String text) {
		System.out.println(number + ": " + text);
	}

	public void setOtherGoat(Goat otherGoat) {
		this.otherGoat = otherGoat;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
