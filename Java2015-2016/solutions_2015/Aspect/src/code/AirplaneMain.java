package code;

public class AirplaneMain {
	public static void main(String[] args) {
		double distMontrealEdmonton = 2970.45;
		double loadedFuel = distMontrealEdmonton * Airplane.kmPerPounds;
		double properLoadedFuel = distMontrealEdmonton * Airplane.kmPerKg;

		System.out.println("The trip would require " + properLoadedFuel + " kgs of fuel");

		Airplane gimliGlider = new Airplane(loadedFuel);

		double maxFlightDistance = gimliGlider.getMaxFlightDistance();
		if (maxFlightDistance >= distMontrealEdmonton) {
			System.out.println("Starting flight, claiming max flight distance is " + maxFlightDistance + " km");
			gimliGlider.fly(distMontrealEdmonton);
		} else {
			System.out.println("Aborting flight, not enough fuel!");
		}
	}
}
