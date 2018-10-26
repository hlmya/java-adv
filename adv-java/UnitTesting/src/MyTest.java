import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MyTest {

	@Test
	@DisplayName("2 + 2 equals 4")
	void testAddition() {
		assertEquals(5, 2+2);
	}

	@Test
	void testManyThings() {
		assertAll(
			() -> assertEquals(5, 2+2),
			() -> assertEquals(7, 2+675723)
		);
	}

	@Disabled
	@Test
//	@DisplayName("2 - 2 equals 0")
	void testSubtraction() {
//		assertEquals(2, 2-2);
//		assertEquals(2, 2-2, "2 - 2 is zero");
		int param = 2;
		assertEquals(2, 2-param, () -> String.format("2 - %d is zero", param));
	}

//	@Test
//	void testDataIsBiggerThan100() {
//		SystemUnderTest sut = new SystemUnderTest(10);
//		sut.setData(123456);
//
//		assertEquals(100, sut.getData());
//	}

	@Test
	void testDataIsSmallerThan100() {
		SystemUnderTest sut = new SystemUnderTest(10);
		sut.setData(85);

		assertEquals(85, sut.getData());
	}

}
