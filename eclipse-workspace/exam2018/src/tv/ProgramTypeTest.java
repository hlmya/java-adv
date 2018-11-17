package tv;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProgramTypeTest {
//,"15,false,16","22,true,22" ,"2,true,2"
	@CsvSource({"12,true,12"})
	@ParameterizedTest
	public void testShiftNormal(int h, boolean b, Optional expected) {
		ProgramType normal = ProgramType.NORMAL;
		assertEquals(expected, normal.shift(h, b));
	}

}
