
package a.b.c;

public class Test {

	public static void main(String[] args) {
		System.out.println(Month.JAN.getHunName());
		System.out.println(Month.JAN.getNextMonth().getNextMonth().getNextMonth().getHunName());
	}
}
