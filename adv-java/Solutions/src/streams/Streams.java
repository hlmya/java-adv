package streams;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// javac Streams.java
// java Streams fdsa grew hrefes tegwa zgsfdzjhfxdbgahus
public class Streams {
	public static void main(String[] args) {
//		Make a method that generates a stream of
		// the first n prime numbers.
		primeGen(9).forEach(System.out::println);

		String txt = primeGen(9)
			.mapToObj(i -> i + "")
			.collect(Collectors.joining(", "));

		System.out.println(txt);

//		IntStream.range(4568, 4975).forEach(System.out::println);
	}

	private static IntStream primeGen2(int n) {
		return IntStream.iterate(2, i -> i + 1)
				.filter(p -> IntStream.range(2, 1+(int)Math.sqrt(p))
						        .parallel()
								.noneMatch(num -> p % num == 0))
				.limit(n);
}


	static IntStream primeGen(int n) {
		Set<Integer> prevPrimes = new HashSet<>();
		return IntStream.iterate(2, i -> i + 1)
//				.limit(n)
				.filter(p -> prevPrimes.parallelStream()
								.noneMatch(prevP -> p % prevP == 0))
				.map(p -> {
					prevPrimes.add(p);
					return p;
				})
				.limit(n);
	}

	private static IntStream primeGenSimpler(int n) {
		Set<Integer> prevPrimes = new HashSet<>();

		return IntStream.iterate(2, i -> i + 1)
				.filter(p -> prevPrimes.stream()
								.noneMatch(prevP -> p % prevP == 0))
				.map(p -> {
					prevPrimes.add(p);
					return p;
				})
				.limit(n);
	}

	public static void main2b(String[] args) {
		args = "124 boom 75 324".split(" ");
		Function<Predicate<Integer>, Integer> solution2 = solution2(args);
		System.out.println(solution2.apply(n -> n > 8));
		System.out.println(solution2.apply(n -> n % 2 == 0));
		System.out.println(solution2.apply(n -> n % 2 == 1));

		System.out.println(solution2b(args).apply(n -> n % 3 == 1));
	}

	private static Function<Predicate<Integer>, Integer> solution2b(String[] args) {
//		Print the sum of even numbers
//		that are greater than (whichever number)
//		from the command line arguments.
		return pred -> Stream.of(args)
			.map(Streams::getOptionalNumber)
			.filter(Optional::isPresent)
			.map(Optional::get)
			.filter(pred)
			.mapToInt(i -> i)
			.sum();

//		Ignore all texts that are not numbers.
	}

	private static Optional<Integer> getOptionalNumber(String arg) {
		try {
			return Optional.of(Integer.parseInt(arg));
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	private static Function<Predicate<Integer>, Integer> solution2(String[] args) {
//		Print the sum of even numbers
//		that are greater than (whichever number)
//		from the command line arguments.
		return pred -> Stream.of(args)
//				.filter(arg -> isNumber(arg))
				.filter(Streams::isNumber)
				.map(Integer::parseInt)
				.filter(pred)
				.mapToInt(i -> i)
				.sum();

//		Ignore all texts that are not numbers.
	}

	private static boolean isNumber(String arg) {
		try {
			Integer.parseInt(arg);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void solution1(String[] args) {
		Stream.of(args).forEach(System.out::println);
		Stream.of(args).forEach(arg -> System.out.println(arg));

		List<Integer> myList =
			Stream.of(args)
				.map(arg -> arg.length())
				.collect(Collectors.toList());
		Collections.reverse(myList);
		System.out.println(myList);

//		532 6532 4213 654 124
//		reverse: 124 654 4213 6532 532
//		sorted: 124 532 654 4213 6532


//		reversed(Stream.of(args)
//			.map(arg -> arg.length())
//			).collect(Collectors.toList());

		System.out.println("-----------------------------");
		IntStream.range(0, args.length)
			.mapToObj(idx -> new SimpleEntry<Integer, String>(-idx, args[idx])  )
			.sorted(Comparator.comparing(SimpleEntry::getKey))
			// remove the negative idx
			.map(SimpleEntry::getValue)
			.map(String::length)
			.forEach(System.out::println);
	}

	public static void main2(String[] args) {
		System.out.println(args.length);
		System.out.println(args[0]);
		System.out.println(Arrays.toString(args));
	}
}
