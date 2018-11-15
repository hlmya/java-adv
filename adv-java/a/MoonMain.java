package advjava.exam;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoonMain {
	public static void main(String[] args) {
		showAllPhaseNamesWithGetter();
		showAllPhaseNamesWithToString();

		showMoonPhase("Full Moon");
		showMoonPhase("New Moon");
		showMoonPhase("No Moon");
		showMoonPhase("Unknown");

		Supplier<MoonPhase> supplier = MoonPhase.PHASE11.getSupplier(new int[]{3, 6, 7});
		List<MoonPhase> phasesAfter11 = Stream.generate(supplier).limit(10).collect(Collectors.toList());
		System.out.println(phasesAfter11);
	}

	private static void showMoonPhase(String name) {
		Optional<MoonPhase> phase = MoonPhase.getPhase(name);
		System.out.printf("Phase for %s: %s%n", name, phase);
	}

	private static void showAllPhaseNamesWithGetter() {
		for (MoonPhase phase : MoonPhase.values()) {
			System.out.println(phase.getPhaseName());
		}
	}

	private static void showAllPhaseNamesWithToString() {
		for (MoonPhase phase : MoonPhase.values()) {
			System.out.println(phase.getPhaseName());
		}
	}
}
