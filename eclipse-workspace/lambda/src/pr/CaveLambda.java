/*
 * Cave of Programming - Lambda Expressions in Java
 * https://www.youtube.com/watch?v=q5i_O4Uj_O8
 */
package pr;

interface Executable {
	int execute(int a, int b);
}

class Runner {
	public void run(Executable e) {
		System.out.println("Execute code block");
		int value = e.execute(12, 13);
		System.out.println("value: "+ value);
	}
}
//runner.run(() -> System.out.println("hello there"));

/*
 runner.run(
 		() -> {
			System.out.println("hello there");
			System.out.println("Alo");
		});
 */


public class CaveLambda {

	public static void main(String[] args) {
		Runner runner = new Runner();
		//Normal way
		runner.run(new Executable() {
			public int execute(int a, int b) {
				int d = 10;
				System.out.println("hello there");
				return a + b + d;
			}
		});
		
		System.out.println("===================");
		
		runner.run((a,b) -> {
			int d = 10;
			return a+b + d;
			});
	}

}
