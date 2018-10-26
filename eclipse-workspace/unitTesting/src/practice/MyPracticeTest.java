package practice;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import practice.MyPractice.WeekDay;

class MyPracticeTest {

	@Test
	@DisplayName("Test with summer")
	void testSummer() {
		assertEquals(28, MyPractice.summer(1, 7));
	}
	
	@Test
	@DisplayName("Test with shortSummer")
	void testShortSummer() {
		assertEquals(28, MyPractice.shortSummer(1, 7));
	}
	
	@Test
	@DisplayName("Test with dictionary")
	void testDictionary() {
		//Map<String, String> dict = Map.of("good", "jo", "morning","reggelt");
		Map<String, String> dict = new HashMap<String, String>();
		dict.put("good", "jo");
		dict.put("morning", "reggel");
		// test all assert functions in body
		assertAll(
				() -> assertEquals("jo reggel", MyPractice.dictionary2(dict, "good morning")),
				() -> assertEquals("? reggel", MyPractice.dictionary2(dict, "a morning")),
				() -> assertNotEquals("? ?", MyPractice.dictionary2(dict, "a morning"))
		);
	}
	
	@Test
	@DisplayName("Test with prime numbers with 6")
	void testPrimeNumber() {
		int[] testVal = {2,3,5,7,11,13};
//		assertEquals(testVal, MyPractice.primeNumber(6)); //-> fail??
		assertArrayEquals(testVal, MyPractice.primeNumber(6)); //-> should use assertArrayEquals
	}
	
	@Test
	@DisplayName("Test prime numbers with Exception")
	void testPrimeNumberWithException() {
//		assertThrows(IllegalArgumentException.class, MyPractice.primeNumber(-6), "Negative"); //-> error, why?? ok for Java 9
		assertThrows(IllegalArgumentException.class, () -> MyPractice.primeNumber(-6), "Negative");
	}
	
	@Test
	@DisplayName("Test WeekDay with Monday and n = 3")
	void testWeekDay() {
//		List<String> expected = new ArrayList<>();
//		expected.add();
//			{WeekDay.Tue, WeekDay.Wed, WeekDay.Thu, WeekDay.Fri};
		assertEquals(WeekDay.Fri, MyPractice.printWeekDay(WeekDay.Mon, 3));
	}
	
//	@Test
//	@DisplayName("Test the file is present")
//	void testFile() throws IOException {
//		String fileName = "test.txt";
//		MyPractice.fileHandler(fileName,1,2,3,4,5);
//		File file = new File(fileName);
//		assertAll(
//				()-> assertTrue(file.exists()),
//				()-> {
//					Scanner sc = new Scanner(file);
//					
//				}
//				);
//	}
}
