package something;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SomethingTest {

	@Test
	public void fdhsjkfhjskghfsdkj() {
		// fail("Not yet implemented");
	}

	@Test
	public void failing() {
		// assertTrue(1 == 1);
		assertTrue("2#2=2", Something.doubleFun(2) == 5);
	}

	@Test
	public void test3() {
		assertEquals(",,,,,,,fdsfdas", 6, Something.doubleFun(3));
	}

	@SuppressWarnings("deprecation")
	@Test(expected = Exception.class)
	public void excTest() throws Exception {
		Something.boom(111);
	}

	@SuppressWarnings("deprecation")
	@Test(timeout = 1000)
	public void infTest() throws Exception {
		Something.infinite();
	}

}
