package counter.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import counter.CounterTest;
import something.SomethingTest;

@RunWith(Suite.class)
@SuiteClasses({ CounterTest.class, SomethingTest.class })
public class AllTests {

}
