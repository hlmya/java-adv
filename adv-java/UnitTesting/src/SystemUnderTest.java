import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// SUT
public class SystemUnderTest {
	private int data;

	public SystemUnderTest(int data) {
		this.setData(data);
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		if (data > 10000) {
			throw new IllegalArgumentException("number is too big");
		}

		if (data == 500) {
			try (
				Scanner sc =  new Scanner("abc.txt");
			) {
				this.data = sc.nextInt();
				return;
			}
		}

		if (data > 100)   data = 100;
		if (data % 2 == 0)   data += 3;

		this.data = data;
	}

	public static String multiplyString(int in, String inStr) {
		return IntStream.range(0, in)
					.mapToObj(i -> inStr)
					.collect(Collectors.joining());
	}
}
