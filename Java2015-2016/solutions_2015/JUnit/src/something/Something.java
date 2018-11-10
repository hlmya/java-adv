package something;

public class Something {
	public static int doubleFun(int i) {
		return 2 * i;
	}

	public static void boom(int i) throws Exception {
		if (i == 111) {
			throw new Exception();
		}
	}

	public static void infinite() {
		while (true) {
		}
	}

}
