package lecture;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class UTest {

	@Test
	public void whenOrElseWorks_thenCorrect() {
	    String nullName = null;
	    String name = Optional.ofNullable(nullName).orElse("john");
	    assertEquals("john", name);
	}
	
//	@Test
//	public void whenOptionalFilterWorks_thenCorrect() {
//	    Integer year = 2016;
//	    Optional<Integer> yearOptional = Optional.of(year);
//	    boolean is2016 = yearOptional.filter(y -> y == 2016).isPresent();
//	    assertTrue(is2016);
//	    boolean is2017 = yearOptional.filter(y -> y == 2017).isPresent();
//	    assertFalse(is2017);
//	}
	
	@Test
	public void givenOptional_whenMapWorks_thenCorrect() {
		List<String> companyNames = Arrays.asList("paypal", "oracle", "", "microsoft", "", "apple");
		Optional<List<String>> listOptional = Optional.of(companyNames);
		
		int size = listOptional
				.map(List::size)
				.orElse(0);
		assertEquals(6, size);
	}
}
