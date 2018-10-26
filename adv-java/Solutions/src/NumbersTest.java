import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumbersTest {
	@Test
	@DisplayName("Sum of 6..9")
	void test() {
		assertEquals(30, Numbers.summer(6, 9));
	}

	@Test
	@DisplayName("Sum with formula of 6..9")
	void test2() {
		assertEquals(30, Numbers.summer2(6, 9));
	}
	
	@Test
	@DisplayName("Sum with formula of 0..9")
	void test2b() {
		assertEquals(45, Numbers.summer2(0, 9));
	}
	
	@Test
	@DisplayName("'Hello world' to Hungarian")
	void testHW() {
//		Map<String, String> dict = Map.of("Hello", "Szia", "world", "világ");
		var dict = Map.of("Hello", "Szia", "world", "világ");
		assertEquals("Szia világ", Numbers.translator(dict, "Hello world"));
	}
	
	@Test
	@DisplayName("first 10 primes")
	void testPrimes() {
		int[] array = Numbers.allPrimes().limit(10).toArray();
		assertArrayEquals(new int[] {2,3,5,7,11,13,17,19,23,29}, array);
//		assertEquals(new int[] {2,3,5,7,11,13,17,19,23,29}, array);
	}
	
	
	@Test
	@DisplayName("first 10 primes v2")
	void testPrimes2() {
		int[] array = Numbers.allPrimes2().limit(10).toArray();
		assertArrayEquals(new int[] {2,3,5,7,11,13,17,19,23,29}, array);
	}
	
	
	@Test
	@DisplayName("first -3 primes v3")
	void testPrimes3() {
		IllegalArgumentException ex =
			assertThrows(IllegalArgumentException.class, () -> {
				Numbers.firstPrimes(-3);
			});

		assertEquals("boom", ex.getMessage());
	}
	
}
