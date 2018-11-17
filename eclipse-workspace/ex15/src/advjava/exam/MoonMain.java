package advjava.exam;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoonMain {

	public static void main(String[] args) {
		//showAllPhaseNamesWithGetter();//test
		
		showMoonPhase("Full Moon");
		showMoonPhase("New Moon");
		showMoonPhase("Phase 1");
		showMoonPhase("Unknown");
		
		Supplier<MoonPhase> supplier = MoonPhase.PHASE11.getSupplier(new int[] {3,6,7});
		List<MoonPhase> listPhases = Stream.generate(supplier).limit(10).collect(Collectors.toList());
		System.out.println(listPhases);
	}

	/**
	 * 
	 */
	private static void showMoonPhase(String period) {
		Optional<MoonPhase> phase = MoonPhase.getPhase(period);
		System.out.println(phase.toString());
	}
	
//	private static void showAllPhaseNamesWithGetter() {
//		for (MoonPhase phase : MoonPhase.values()) {
//			System.out.println(phase.getPhaseName());
//		}
//	}

}
