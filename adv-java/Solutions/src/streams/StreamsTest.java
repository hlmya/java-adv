package streams;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

class StreamsTest {
	@Test
	void test() {
//		assertEquals(new int[] {2,3,5,7,11}, Streams.primeGen(5).toArray());
		assertArrayEquals(new int[] {2,3,5,7,11}, Streams.primeGen(5).toArray());
	}

	@Test
	void testWords() {
		var dict = Map.of("asdf", "alien1", "qwer", "alien2");
		assertEquals("alien1 alien2", Translator.translator(dict, "asdf qwer"));
	}

	@Test
	void testWords2() {
		var dict = Map.of("asdf", "alien1", "qwer", "alien2");
		assertEquals("? ? ?", Translator.translator(dict, "a b c"));
	}

}
