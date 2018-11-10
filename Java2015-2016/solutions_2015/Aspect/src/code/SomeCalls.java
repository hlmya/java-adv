package code;

public class SomeCalls {
	String name;

	public SomeCalls(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		new SomeCalls("obj1").runTest();
	}

	private void runTest() {
		SomeCalls obj2 = new SomeCalls("obj2");
		this.someMethod();
		obj2.someMethod();
		this.someMethod();
		obj2.someMethod();
	}

	void someMethod() {
		System.out.println("Called: " + name);
	}
}
