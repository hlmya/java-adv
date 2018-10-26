import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.PrintWriter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest2 {
	SystemUnderTest sut;
	PrintWriter pw;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		sut = new SystemUnderTest(10);

		try (
			PrintWriter pw = new PrintWriter("abc.txt");
		) {
			pw.println(12345);
			pw.flush();
		}
	}

	@AfterEach
	void fdhsjkglshdjf() throws Exception {
		// delete abc.txt
		//		pw.close();
	}

	@Test
	void test() {
		sut.setData(500);
		assertEquals(12345, sut.getData());
	}

	@Test
	void testException() {
		IllegalArgumentException e =
			assertThrows(IllegalArgumentException.class, () -> {
				sut.setData(10000000);
			});
		assertEquals("number is too big", e.getMessage());
	}

	@Nested
	class Level2 {
		SystemUnderTest sut2;

		@BeforeEach
		void f() {
			System.out.println("I'm in");
		}

		@Test
		void test() {
			fail("Oh no");
		}
	}




	@ValueSource(ints= {3,5,97})
	@ParameterizedTest
	void testSimpleNums(int num) {
		sut.setData(num);
		assertEquals(num, sut.getData());
	}


	@CsvSource({"3,3", "4,7"})
	@ParameterizedTest(name = "setting {0} results in {1}")
	void testSimpleNums(int in, int expected) {
		sut.setData(in);
		assertEquals(expected, sut.getData());
	}

	@CsvSource({"3,a,aaa", "4,z,zzzz", "0,gdhkdsfghsdj,''"})
	@ParameterizedTest
	void testStringMultiplier(int in, String inStr, String expected) {
		assertEquals(expected, SystemUnderTest.multiplyString(in, inStr));
	}

}
