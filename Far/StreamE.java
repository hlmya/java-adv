import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamE {
//Given some filenames in the command line arguments, print the names on one line, separated with commas,
//in descending order in the number of lines in the file. If two files have the same number of lines,
//then the one with more words on its first line should come first.
//task 4
public static void main(String[] args) {
List<String> arg= Arrays.asList(args);
arg.sort(Comparator.comparing(StreamE::fileLength).thenComparing(StreamE::lineLength));
arg.stream().map(str -> str + "       ").forEach(System.out::println);
}

public static long fileLength(String file) {
	try {
		return Files.lines(Paths.get(file)).count();
	} catch(IOException e) {
		e.printStackTrace();
	}
	return 0;
}

public static long lineLength(String file) {
	 	try {
			return Files.lines(Paths.get(file)).findFirst().get().split("\\s+").length;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	return 0;
}
	//Make a method that generates a stream of the first n prime numbers.
public static void main3(String[] args) {
	int n = 44;
	String pri = IntStream.iterate(2, i -> i+1)
	.filter( p -> IntStream.range(2, p/2).noneMatch(num -> p % num == 0))
	.limit(n)
	.mapToObj(ch -> ch+"")
	.collect(Collectors.joining(","));
	System.out.println(pri);
}
	//Print the sum of even numbers that are greater than 8 from the command line arguments.
public static void main2(String[] args) {
	Predicate<String> isNumber = x -> {
		try {
			Integer.parseInt(x);
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	};

	long a = Stream.of(args).filter(el -> isNumber.test(el) && Integer.parseInt(el) > 8 && Integer.parseInt(el) % 2 ==0)
	.map(x -> Integer.parseInt(x))
	.reduce(0, (acc,el) -> acc+el);
	System.out.println(a);
}
	//Print the lengths of the command line arguments in reverse order.
public static void main1(String[] args) {

	Stream.of(args).collect(Collectors.collectingAndThen(Collectors.toList(), list
			-> {
				Collections.reverse(list);
				return list;
			})).stream().map(str -> str.length())
					.forEach(System.out::println);

}
}
