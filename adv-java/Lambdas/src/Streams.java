import static java.lang.System.out;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;
import static java.util.stream.Stream.of;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {
	public static void main(String[] args) throws Exception {
		Stream<String> stream1 = Stream.of(args);
		Stream<String> stream1b = of(args);
		Stream<Integer> stream2 = Stream.of(1, 2, 3, 4);
		Stream<String> stream3 = Arrays.stream(args);
		Stream<String> stream3b = stream(args);

//		Stream<int>
		IntStream.of(1, 2, 3).forEach(out::println);
		IntStream.range(1, 10).forEach(out::println);
		range(1, 10).forEach(out::println);
		IntStream.rangeClosed(1, 10).forEach(out::println);
		rangeClosed(1, 10).forEach(out::println);

		for (int i = 1; i < 10; i++) {
			System.out.println(i);
			// do not modify counter in loop body!
			i = 4352;
		}

		range(1, 10).map(i -> i * 2).forEach(i -> {
			System.out.println(i);
			i = 4352;
		});

		Stream.iterate(2, i -> i * 2 + 1)
			.limit(100)
			.forEach(out::println);

		DoubleStream.iterate(2, i -> i * 2 + 1)
			.limit(1000)
			.forEach(out::println);

		DoubleStream.iterate(2, i -> i * 2 + 1)
			.skip(3)
			.limit(1000)
			.forEach(out::println);

		DoubleStream.iterate(2, i -> i * 2 + 1)
//			.takeWhile(d -> d != Double.POSITIVE_INFINITY)
			.takeWhile(Double::isFinite)
			.forEach(out::println);

		long count = DoubleStream.iterate(2, i -> i * 2 + 1)
//			.takeWhile(d -> d != Double.POSITIVE_INFINITY)
			.takeWhile(Double::isFinite)
			.count();
		System.out.println(count);

		double sum = DoubleStream.iterate(2, i -> i * 2 + 1)
				.takeWhile(d -> d < 1000)
				.sum();
		System.out.println(sum);

		DoubleSummaryStatistics summaryStatistics = DoubleStream.iterate(2, i -> i * 2 + 1)
				.takeWhile(d -> d < 1000)
				.summaryStatistics();
		// DoubleSummaryStatistics{count=9, sum=1524,000000,
		// min=2,000000, average=169,333333, max=767,000000}
		System.out.println(summaryStatistics);
		System.out.println(summaryStatistics.getMin());

		Supplier<Supplier<String>> alphabetSupplierGen = () -> new Supplier<String>() {
			String txt = "";
			char currentChar = 'A';

			@Override
			public String get() {
				txt += currentChar;
				currentChar += 1;
				return txt;
			}
		};
		Stream.generate(alphabetSupplierGen.get())
			.limit(100)
			.forEach(out::println);

		Stream.generate(alphabetSupplierGen.get())
			.limit(100)
			.forEach(txt -> {
				// insert txt into database
				// send txt over network
			});

		List<String> elems =
			Stream.generate(alphabetSupplierGen.get())
				.limit(26)
				.collect(Collectors.toList());

		Set<String> elems2b =
				Stream.generate(alphabetSupplierGen.get())
					.limit(26)
					.collect(Collectors.toSet());

		List<String> elems2 =
			Stream.generate(alphabetSupplierGen.get())
				.map(txt -> "hehe")
				.limit(26)
				.collect(toList());

		elems2.set(0, "foo");
		System.out.println(elems2);

//		System.out.println(Stream.of(1,2,3).count());

		Stream.generate(alphabetSupplierGen.get())
			.map(new Function<String, Map.Entry<Integer, String>>() {
				int idx = 0;

				@Override
				public Map.Entry<Integer, String> apply(String txt) {
					++idx;
					return Map.entry(idx, txt);
				}
			})
			.limit(26)
			.forEach(out::println);

		Stream.generate(alphabetSupplierGen.get())
			.map(addIndex())
			.limit(26)
			.forEach(out::println);

		IntStream.rangeClosed(1, 10)
			.mapToObj(i -> i * 3 + 1)
//			.map(i -> i * 3 + 1)
//			.boxed()
			.map(addIndex())
			.forEach(out::println);

		Optional<Integer> findFirst = Stream.of(324, 56432, 234132, 6432)
			.filter(i -> i % 2 == 1)
			.findFirst();

		Integer oddNum = Stream.of(324, 56432, 234132, 6432)
			.filter(i -> i % 2 == 1)
			.findFirst()  // here: Optional<Integer>
			.orElse(1);
		System.out.println(oddNum + 1);
//		if (oddNum.isPresent()) {
//			System.out.println(oddNum.get() + 1);
//		}

//		Integer myInt = 3;
//		myInt = null;
//		System.out.println(myInt + 1);

//		stream1.forEach(out::println);
		// using it again: IllegalStateException
		// stream1.forEach(out::println);

		Map<String, Integer> map = new HashMap<>();
		Integer output = map.get("abc");
		System.out.println(output);
		System.out.println(map.getOrDefault("abc", -1));
	}

	private static <E> Function<E, Entry<Integer, E>> addIndex() {
		return new Function<E, Map.Entry<Integer, E>>() {
			int idx = 0;

			@Override
			public Map.Entry<Integer, E> apply(E elem) {
				++idx;
				return Map.entry(idx, elem);
			}
		};
	}

	public static void main2(String[] args) throws Exception {
		String[] texts = { "432", "10", "2", "12", "055", "42978" };
		Stream.of(texts)
			.map(Integer::parseInt)
			.forEach(out::println);

		Files.lines(Paths.get("src", "Lambdas.java"))
			.map(line -> "Hi there: " + line)
			.filter(line -> !line.contains("a"))
			.forEach(out::println);

		long count = Files.lines(Paths.get("src", "Lambdas.java"))
			.map(line -> "Hi there: " + line)
			.count();

		System.out.println(count);
	}
}
