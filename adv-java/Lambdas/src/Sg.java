import java.util.stream.Stream;

public class Sg {
	public static void main(String[] args) {
		args = "123 53 543".split(" ");
		
		Integer sum = Stream.of(args).reduce(
				0,
				(num, arg) -> Integer.parseInt(arg) + num,
				(num1, num2) -> num1 + num2
				);
		
		System.out.println(sum);
	}
}
