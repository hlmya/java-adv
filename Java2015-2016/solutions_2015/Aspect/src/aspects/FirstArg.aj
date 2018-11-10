package aspects;

import code.FirstArgTester;

public aspect FirstArg {
	after(): call(* FirstArgTester.*(*, ..)) {
		System.out.println("First arg!");
	}
}
