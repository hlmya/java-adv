import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toCollection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
	public static void main(String[] args) {
		args = new String[] { "abc", "gwagd", "a" };

		Stream.of(args).forEach(System.out::println);
		Stream.of(args).forEach(arg -> System.out.println(arg));

		Stream.of(args).forEach(arg -> System.out.println(arg));

		String collected = Stream.of(args).collect(joining(", ", "[", "]"));
		System.out.println(collected);

		Stream.of(args).map(arg -> arg.length()).forEach(System.out::println);

		Stream.of(args).mapToInt(arg -> arg.length()).forEach(System.out::println);

		Function<Stream<Integer>, Stream<Integer>> reverseFiniteStream = stream -> {
			Stack<Integer> stack = stream.collect(toCollection(Stack::new));
			return Stream.generate(new Supplier<Integer>() {
				public Integer get() {
					if (!stack.isEmpty()) {
						return stack.pop();
					}
					return null;
				}
			});
		};

		reverseFiniteStream.apply(Stream.of(args).map(arg -> arg.length())).forEach(System.out::println);
	}

	public static void main2(String[] args) throws IOException {
		Object object = Stream.of().max((e1, e2) -> 1).get();
		System.out.println(object);

		Path path = Paths.get("abc.txt");
		Consumer<String> consumeLine = System.out::println;
		processLinesV1(path, consumeLine);
		processLinesV1b(path, consumeLine);
		processLinesV2(path, consumeLine);
		processLinesV3(path, consumeLine);

		Files.lines(path).limit(10).forEach(consumeLine);
		Files.lines(path).skip(10).forEach(consumeLine);

		// meaningless
		Files.lines(path).limit(10).skip(10).forEach(consumeLine);

		Files.lines(path).skip(10).limit(10).forEach(consumeLine);

		// String -> int
		IntFunction<String> f = i -> "a " + i;
		System.out.println(f.apply(5));

		IntSupplier f2 = () -> 2;
		System.out.println(f2.getAsInt());
	}

	public static void main3(String[] args) {
		String joined1 = Stream.of("a", "b", "c").collect(joining());
		System.out.println(joined1);

		String joined2 = Stream.of("a", "b", "c").collect(joining(", "));
		System.out.println(joined2);

		String joined3 = Stream.of("a", "b", "c").collect(joining(", ", "(", ")"));
		System.out.println(joined3);

		Set<String> treeSet = new TreeSet<String>();
		treeSet.addAll(Arrays.asList(new String[] { "b", "a", "b", "a", "fddwgwas" }));
		System.out.println(treeSet);

		Set<String> set = new HashSet<String>();
		set.addAll(Arrays.asList(new String[] { "b", "a", "b", "d", "a", "fddwgwas", "c" }));
		System.out.println(set);

		// makeTheCPUWork();
	}

	private static void makeTheCPUWork() {
		for (int j = 0; j < 1000; j++) {
			// int sum = IntStream.range(0, 100000000).map(i -> 2 * i).sum();
			int sum = IntStream.range(0, 100000000).parallel().map(i -> 2 * i).sum();
			System.out.println(sum);
		}
	}

	private static void processLinesV3(Path path, Consumer<String> consumeLine) throws IOException {
		Files.lines(path).forEach(consumeLine);
	}

	private static void processLinesV2(Path path, Consumer<String> consumeLine) throws IOException {
		try (Scanner sc = new Scanner(path)) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				consumeLine.accept(line);
			}
		}
	}

	private static void processLinesV1c(Path path, Consumer<String> consumeLine) throws IOException {
		Optional<String> max = Files.readAllLines(path).stream().max((s1, s2) -> s1.length() - s2.length());
		if (max.isPresent()) {
			String string = max.get();
			System.out.println("Max is " + string);
		}

		String maxOrElse;
		if (max.isPresent()) {
			maxOrElse = max.get();
		} else {
			maxOrElse = "Else";
		}

		String orElse = max.orElse("No max");
	}

	private static void processLinesV1b(Path path, Consumer<String> consumeLine) throws IOException {
		Files.readAllLines(path).stream().sorted().forEach(consumeLine);
	}

	private static void processLinesV1(Path path, Consumer<String> consumeLine) throws IOException {
		List<String> allLines = Files.readAllLines(path);
		for (String line : allLines) {
			consumeLine.accept(line);
		}
	}
}
