package aspects;

public aspect FirstArgDangerous {
	/*
	after(): call(* *.*(*, ..)) {
		System.out.println("Crazy for first arg!");
	}
	*/
}
