package code;

public class Airplane {
	// measured in pounds
	private double fuel;

	public double getFuel() {
		return fuel;
	}

	public void setFuel(double fuel) {
		this.fuel = fuel;
	}

	public static final double kmPerPounds = 3.4;
	public static final double kmPerKg = 7.5;

	public Airplane(double fuelInPounds) {
		this.setFuel(fuelInPounds);

		System.out.println("Loaded fuel " + getFuel());
	}

	double getMaxFlightDistance() {
		return getFuel() / kmPerPounds;
	}

	public void fly(double distance) {
		double necessaryFuel = distance / kmPerKg;
		if (distance > necessaryFuel) {
			System.err.println("The plane has crashed.");
			return;
		}

		setFuel(getFuel() - necessaryFuel);
		System.out.println("We have arrived.");
	}
}
