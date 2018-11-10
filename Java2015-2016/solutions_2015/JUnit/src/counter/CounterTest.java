package counter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CounterTest {

	Counter counter;

	@Before
	public void init() {
		counter = new Counter();
	}

	@Test
	public void initIsOne() {
		assertEquals(1, counter.getNext());
	}

	@Test
	public void afterNextIsTwo() {
		counter.getNext();
		assertEquals(2, counter.getNext());
	}

	// not so good, two conditions in one test case
	@Test
	public void someMoreNexts() {
		counter.getNext();
		counter.getNext();
		assertEquals(3, counter.getNext());
		counter.getNext();
		counter.getNext();
		counter.getNext();
		counter.getNext();
		assertEquals(8, counter.getNext());
	}

}
