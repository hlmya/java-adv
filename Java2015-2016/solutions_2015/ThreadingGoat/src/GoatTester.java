
public class GoatTester {
	public static void main(String[] args) throws InterruptedException {
		int bridgeLength = 10;
		int goat1MinSpeed = 250;
		int goat1MaxSpeed = 1000;
		int goat2MinSpeed = 600;
		int goat2MaxSpeed = 2000;

		Bridge bridge = new Bridge(bridgeLength);

		Goat goat1 = new Goat(1, bridge, goat1MinSpeed, goat1MaxSpeed, 1);
		Goat goat2 = new Goat(2, bridge, goat1MinSpeed, goat1MaxSpeed, -1);

		goat1.setOtherGoat(goat2);
		goat2.setOtherGoat(goat1);

		goat1.start();
		goat2.start();

		goat1.join();
		goat2.join();

		System.out.println("Goat game is done.");
	}
}
