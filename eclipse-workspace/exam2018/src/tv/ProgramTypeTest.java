package tv;


import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProgramTypeTest {
	@CsvSource({"13,true,14, NORMAL","17,false,16,AFTERNOON","2,true,1,LATE_NIGHT","16,false,20,SPECIAL"})
	@ParameterizedTest
	public void testShiftNormal(int h, boolean sh, int expected, String pt) {
		ProgramType ptype = ProgramType.valueOf(pt);
		assertEquals(Optional.of(expected), ptype.shift(h,sh));
	}
}
