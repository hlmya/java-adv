package code;

public class FirstArgTester {
	public static void main(String[] args) {
		f1();
		f2(1);
		f3("abc");
		f4(1, "abc");
	}

	static void f1() {
		System.out.println("Running f1");
	}

	static void f2(int i) {
		System.out.println("Running f2");
	}

	static void f3(String s) {
		System.out.println("Running f3");
	}

	static void f4(int i, String s) {
		System.out.println("Running f4");
	}
}
