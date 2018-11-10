
package aspects;

import code.Fibonacci;

public aspect FibValuesAspect {
	pointcut fib(int n):
		call(int Fibonacci.fib(int)) && args(n);
	
	before(int n): fib(n) {
		System.out.println("calling fib(" + n + ")");
	}
	
	int around(int n): fib(n) {
		int retval = proceed(n);
		System.out.println("fib(" + n + ") = " + retval);
		return retval;
	}
}
