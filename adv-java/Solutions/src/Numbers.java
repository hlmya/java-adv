import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Numbers {

	public static int summer(int from, int to) {
//		return IntStream.range(from, to).sum();
		return IntStream.rangeClosed(from, to).sum();
	}
	
	public static int summer2(int from, int to) {
		return sumUptoN(to) - sumUptoN(from - 1);
	}

	private static int sumUptoN(int n) {
		return n * (n+1) / 2;
	}

	public static String translator(Map<String, String> dict, String text) {
		return Stream.of(text.split(" "))
			.map(word -> dict.getOrDefault(word, "?"))
			.collect(Collectors.joining(" "));
	}

	public static IntStream allPrimes() {
		return IntStream.iterate(2, i -> i + 1)
			.filter(p -> IntStream.range(2, p).parallel()
					        .allMatch(smaller -> p % smaller != 0));
	}
	
	public static IntStream allPrimes2() {
		Set<Integer> prevPrimes = new HashSet<>();
//		prevPrimes = null;
//		prevPrimes = new HashSet<>();

		return IntStream.iterate(2, i -> i + 1)
				.filter(p -> prevPrimes.parallelStream()
						.allMatch(prevPrime -> p % prevPrime != 0))
				.map(p -> {
					prevPrimes.add(p);
					return p;
				});
	}
	
	public static IntStream firstPrimes(int n) {
		if (n < 0)   throw new IllegalArgumentException("boom");
		return allPrimes2().limit(n);
	}

}
