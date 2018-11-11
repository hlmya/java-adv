import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestClass {

	FunsUnderTest obj=new FunsUnderTest();

	@Before
	 void initialize() {


	}
	@Test
	@DisplayName("Summer")
	 void testSummer() {
		assertEquals("Check sum of a range", 45, obj.summer(5, 10));
	}

	@Test
	@DisplayName("Summer - formula")
	void testshortSummer() {
		assertEquals("Check sum of a range", 45, obj.shortSum(5, 10));
	}

	@Test
	@DisplayName("Testing dictionary")
	void testDic() {
		Map<String, String> dic=new HashMap<>();
		dic.put("one", "egy");
		dic.put("two", "ketto");
		dic.put("three", "harom");
		assertEquals("translated: ","egy ketto harom ?", obj.dictionary(dic, "one two three four"));
	}

	@Test
	@DisplayName("Test primeGen")
	void testPrimeGen() {
		assertThrows(IllegalArgumentException.class, () -> obj.primeGen(-3));
		int[] a = { 2, 3, 5, 7, 11 };
		assertArrayEquals(a,obj.primeGen(5));
	}

	@Test
	@DisplayName("Day after that")
	void testWeekDayAfter() {
		assertEquals(MyDate.Weekday.FRI,obj.weekday(MyDate.Weekday.WED, 2));
	}

	@Test
	@DisplayName("File content")
	void testWriteToFile() throws IOException {
		int[] a = {1,2,3,4,5};
		obj.writeToFile("testFile.txt", a);
		File file =new File("testFile.txt");
		assertAll( () -> {
		assertTrue(file.exists());
		Scanner in=new Scanner(file);
		assertEquals(15,Integer.parseInt(in.nextLine()));
		in.close();
		});
	}

	@Test
	@DisplayName("practice")
	void groupedAsserttion() {
		assertAll(
				() -> assertTrue(true),
				() -> assertFalse(false),
				() -> assertEquals(4,2*2)
				);
	}
	@AfterAll
	static void deleteFile() {
		File file=new File("testFile.txt");
		if(file.exists()) {
		file.delete();
		}
	}
}
