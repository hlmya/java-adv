package advjava.exam;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class MoonTesterWithAspects {

	@Test
	public void phaseNewMoon() {
		assertEquals("New Moon phase wrong", Optional.of(MoonPhase.PHASE0), MoonPhase.getPhase("even New Moon"));
	}

	@Test
	public void phaseFullMoon() {
		assertEquals("Full Moon phase wrong", Optional.of(MoonPhase.PHASE14), MoonPhase.getPhase("even Full Moon"));
	}

	@Test
	public void phaseUnknown() {
		assertEquals("Unknown phase wrong", Optional.empty(), MoonPhase.getPhase("Unknown"));
	}

	@Test
	public void phaseSupplier() {
		Supplier<MoonPhase> supplier = MoonPhase.PHASE11.getSupplier(new int[]{3, 6, 7});
		List<MoonPhase> phasesAfter11 = Stream.generate(supplier).limit(10).collect(Collectors.toList());
		assertEquals("supplied phase #1 wrong", MoonPhase.PHASE11, phasesAfter11.get(0));
		assertEquals("supplied phase #2 wrong", MoonPhase.PHASE14, phasesAfter11.get(1));
		assertEquals("supplied phase #10 wrong", MoonPhase.PHASE3, phasesAfter11.get(9));
	}

}
