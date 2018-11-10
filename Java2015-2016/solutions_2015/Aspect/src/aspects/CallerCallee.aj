package aspects;

import code.SomeCalls;

public aspect CallerCallee {
	int count = 0;
	int count2 = 0;
	
	before(Object obj, Object obj2): this(obj) && target(obj2) && call(* SomeCalls.*(..)) {
		if (obj != obj2)   return;
		++count;
		System.out.println("Object call to itself #" + count + ": " + obj + " " + thisJoinPoint.getSignature());
	}

	before(Object obj, Object obj2): this(obj) && target(obj2) && within(SomeCalls) {
		if (obj != obj2)   return;
		++count2;
		System.out.println("V2 Object call to itself #" + count2 + ": " + obj + " " + thisJoinPoint.getSignature());
	}
}
