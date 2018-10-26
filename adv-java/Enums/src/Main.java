import java.util.stream.Stream;

enum GardeningTools {
	SPADES
}

enum Planet {
	EARTH(45236745), NEPTUNE(463872673);

	private int mass;

	Planet(int mass) {
		this.setMass(mass);

	}

	public int getMass() {
		return mass;
	}

	public void setMass(int mass) {
		this.mass = mass;
	}
}

enum CardSuits {
	SPADES(13), DIAMONDS;

	private int value;

	CardSuits() {
		this(0);
	}

	CardSuits(int value) {
		this.value = value;
	}

	public String toString() {
		return "foo";
	}
}

public class Main {
	public static final int SP1 = 0;
	public static final int SP2 = 0;

	public static void main(String[] args) {
//		new CardSuits(54);

		System.out.println(GardeningTools.SPADES);
		System.out.println(CardSuits.SPADES);
		System.out.println(SP1 == SP1);
//		System.out.println(CardSuits.SPADES == GardeningTools.SPADES);

		System.out.println(Planet.NEPTUNE.getMass());
		Planet.NEPTUNE.setMass(0);
		System.out.println(Planet.NEPTUNE.getMass());
		System.out.println(Planet.NEPTUNE.ordinal());

		for (Planet planet : Planet.values()) {
			System.out.println(planet + " " + planet.getMass());
		}

		Stream.of(Planet.values())
			.forEach(System.out::println);
	
	}
}
