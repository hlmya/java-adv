import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FunsUnderTest {

	public long summer(int begin, int end) {
		return IntStream.rangeClosed(begin, end).sum();
	}

	public long shortSum(int begin, int end) {
		return ((end - begin+1) * (begin + end)) / 2;
	}

	public String dictionary(Map<String, String> dic, String text) {
		String[] words = text.split("\\s");
		return Arrays.stream(words).map(word -> word = dic.containsKey(word) ? dic.get(word) : "?")
				.collect(Collectors.joining(" "));
	}

	public int[] primeGen(int n) {
		if (n < 0)
			throw new IllegalArgumentException();
		return IntStream.iterate(2, f -> f + 1)
				.filter(p -> IntStream.rangeClosed(2,  (int) Math.sqrt(p)).parallel().noneMatch(e -> p % e == 0))
				.limit(n).toArray();
	}

	public MyDate.Weekday weekday(MyDate.Weekday day, int n) {

		BiFunction<MyDate.Weekday, Integer, MyDate.Weekday> dayAfter = (da, nu) -> {
			int length = MyDate.Weekday.values().length;
			int ind = da.ordinal();
			return MyDate.Weekday.values()[(nu+ind) % length ];
		};

		return dayAfter.apply(day, n);
	}

	public void writeToFile(String fileName, int... numbers) throws IOException {
		//int sum = Arrays.stream(numbers).reduce(0, (x,y) -> x+y);
		int sum = Arrays.stream(numbers).reduce(0, Integer::sum);
		FileWriter fw=new FileWriter(new File(fileName));
		fw.write(sum+"");
		fw.flush();
		fw.close();
	}
	public static void main(String[] args) throws IOException {

		FunsUnderTest o=new FunsUnderTest();
		int[] n= {4,5,3,5};
		o.writeToFile("afdafd.txt", n);


	}
}
