package code;

import java.io.IOException;

public class ExceptionThrower {
	public static void main(String[] args) {
		try {
			throw1();
		} catch (IOException e) {
			System.out.println("Handled 1");
		}

		try {
			throw2();
		} catch (NumberFormatException e) {
			System.out.println("Handled 2");
		}
	}

	private static void throw2() {
		throw new NumberFormatException("Exc 2");
	}

	private static void throw1() throws IOException {
		throw new IOException("Exc 1");
	}
}
