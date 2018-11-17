package advjava.exam;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class MoonTester {

	@Test
	public void testPhases() {
		assertAll(
			() -> assertEquals(Optional.of(MoonPhase.PHASE0), MoonPhase.getPhase("New Moon")),
			() -> assertEquals(Optional.of(MoonPhase.PHASE14), MoonPhase.getPhase("Full Moon")),
			() -> assertEquals(Optional.empty(), MoonPhase.getPhase("Unknown"))
		);
	}
	
	@Test
	public void testSupplier() {
		Supplier<MoonPhase> supplierAfter11 = MoonPhase.PHASE11.getSupplier(new int[] {3,6,7});
		List<MoonPhase> listPhases = Stream.generate(supplierAfter11).limit(10).collect(Collectors.toList());
		
		assertAll(
			() -> assertEquals(MoonPhase.PHASE11, listPhases.get(0)),
			() -> assertEquals(MoonPhase.PHASE14, listPhases.get(1)),
			() -> assertEquals(MoonPhase.PHASE2, listPhases.get(4))
		);
	}
}
