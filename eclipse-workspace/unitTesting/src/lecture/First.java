package lecture;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class First {

	@Test
	@DisplayName("2 + 2 = 4")
	void test() {
//		fail("Not yet implemented");
//		assert ("Hello") != null;
		assertEquals(4, 2+2);
	}
	
	@Test
	@DisplayName("2 + 2 = 4 tset 1")
	void test1() {
		int param = 2;
		assertEquals(1, 2-param, ()->String.format("2-%d is zero", param));
	}
	
//	@Test
//	void testManyThing() {
//		assertAll(
//				()-> assertEquals(expected, actual),
//				()-> assertEquals(expected, actual));
//	}
	
	@Test
	void testDataIsBiggerThan100() {
		
	}
	

}
