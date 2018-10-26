import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

class MySaveClass implements Serializable {
	private static final long serialVersionUID = -237438784446530196L;
//	private static final long serialVersionUID = 6259991282960842330L;

	int data;
	String data2;
	List<Integer> data3;
	List<Integer> data4;
}

public class IoTest {
	public static void main2(String[] args) throws Exception {
		MySaveClass mySaveClass = new MySaveClass();
		mySaveClass.data = 123;
		mySaveClass.data2 = "dshfajdshd";
		mySaveClass.data3 = List.of(3124, 543, 1234);

		try (
			ObjectOutputStream oos =
				new ObjectOutputStream(new FileOutputStream(new File("obj.data")));
		) {
			oos.writeObject(mySaveClass);
			oos.flush();
		}

		try (
			ObjectInputStream ois =
				new ObjectInputStream(new FileInputStream(new File("obj.data")));
		) {
			MySaveClass obj = (MySaveClass)ois.readObject();
			System.out.println(obj.data);
			System.out.println(obj.data2);
			System.out.println(obj.data3);
		}

	}

	public static void main(String[] args) throws Exception {
		String eol = System.getProperty("line.separator");
		System.out.println(eol);
		System.out.println("abc");
		System.out.println("\r\n");
		System.out.println(eol.length());
		System.out.println(eol.charAt(0) == '\r');
		System.out.println(eol.charAt(1) == '\n');

		System.out.println("dsfaghdsajfds\n");
		System.out.printf("dsfaghdsajfds%n");

		String formatted = String.format("dsfaghdsajfds%n");

		PrintStream out = System.out;
		PrintWriter pw;

		System.out.write(65);
		System.out.write(97);
		System.out.write('\n');

		// try-with-resources
		try (
//			int something = 1;
			// AutoCloseable
//			Scanner sc = new Scanner("a        b c");
//			Scanner sc = new Scanner("abaaaaaacaaaad");
//			Scanner sc = new Scanner("foo\nbar");
			Scanner sc = new Scanner(new File("in.txt"));
//			Scanner sc = new Scanner(new FileInputStream(new File("in.txt")),
//										"UTF-8");
			Scanner sc2 = new Scanner(new FileInputStream(new File("in.txt")),
										StandardCharsets.UTF_8);
			Scanner sc3 = new Scanner(new FileInputStream(new File("in.txt")), UTF_8);
		) {
//			sc.useDelimiter("ba*");
//
//			String token = sc.next();
//			System.out.println(token);
//			String token2 = sc.next();
//			System.out.println(token2);
//			if (sc.hasNext()) {
//			    String token3 = sc.next();
//          }
//			System.out.println(token3);

			if (sc.hasNextInt()) {
				int token = sc.nextInt();
			}
			String nextLine = sc.nextLine();
			String nextLine2 = sc.nextLine();

//			System.out.println(token + 433);
			System.out.println(nextLine);
			System.out.println(nextLine2);
//			if (sc.hasNextLine()) {
//				sc.nextLine();
//			}
		}



		Scanner sc2 = new Scanner("a b c");
		try (sc2) {
			// ... (in Java 9+)
		}

		oldApproach();

	}

	private static void oldApproach() {
		Scanner sc = null;
		try {
			sc = new Scanner("a b c");
			// work with the resource
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}
}
