package pr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExercises {

	public static void main(String[] args) {
		String[] argstest = {"abce","heh","kakaaa","le"};
//		solution1(argstest);
//		solution2(argstest);
//		solution3();
		///?????
		List<String> arg= Arrays.asList(args);
		arg.sort(Comparator.comparing(StreamExercises::fileLength).thenComparing(StreamExercises::lineLength));
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

	/**
	 * Make a method that generates a stream of the first n prime numbers.
	 */
	private static void solution3() {
		Function<Integer, int[]> genPrime = n -> IntStream.iterate(2, i -> i + 1)
							.filter(i -> IntStream.rangeClosed(2, (int) Math.sqrt(i)).noneMatch(num -> i%num == 0))
							.limit(n).toArray();
		
		System.out.println(Arrays.toString(genPrime.apply(6))); // to print out int[] array
	}

	private static IntStream zip2stream(List<Integer> s1, List<Integer> s2) {
		int shortlength = Math.min(s1.size(), s2.size());
		return IntStream.range(0, shortlength).map(i->s1.get(i) + s2.get(i));//Map.entry
		
	}

	//Print the sum of even numbers > 8. Ignore all texts that are not numbers
	private static void solution2(String[] args) {
		//1st
		int result = Stream.of(args)
				.filter(n -> n.matches("[0-9]+"))	// filter only number
				.map(Integer::parseInt)				// parse from string to int
				.mapToInt(i->i)						// change type of data from Obj to primitive (Stream to IntStream)
				.filter(n -> n%2==0 && n > 8)
				.sum();								// sum() makes data changes from IntStream to int
		System.out.printf("Sum is:%d%n", result);
		
		System.out.println("-----------------------------");
		//2nd -> general
		Function<Predicate<Integer>, Integer> f = handleSol2(args);
		System.out.println("Sum is:" + f.apply(n -> n%2==0 && n>8));
		
		System.out.println("-----------------------------");
		// 3rd
		String[] numbers = {"23","4a","44","10"};
		Predicate<String> isNumber = x -> {
			try {
				Integer.parseInt(x);
				return true;
			} catch (Exception e) {
				return false;
			}
		};
		Function<Predicate<Integer>, Integer> fu = pred -> {
			return Stream.of(numbers)
				.filter(x -> isNumber.test(x))
				.map(Integer::parseInt) // parse to int
				.filter(pred)
				.mapToInt(e->e) // sum() can only understand if covert to int
				.sum();
		};
		System.out.println("sum of even numbers is " + fu.apply(x -> x%2 == 0 && x>8) );
		
		
	}

	private static Function<Predicate<Integer>, Integer> handleSol2(String[] args) {
		//pred is Predicate, the condition: n -> n%2==0 && n>8 
		return pred -> Stream.of(args)
				.filter(n -> isNumber(n))
				.map(Integer::parseInt)
				.filter(pred)
				.mapToInt(e -> e)
				.sum();
	}

	private static boolean isNumber(String n) {
		try {
			Integer.parseInt(n);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	//ex1:Print the lengths of the command line arguments in reverse order.
	private static void solution1(String[] args) {
		
//		Stream.of(args).forEach(System.out::println);
		System.out.println(Arrays.asList(args));
		// 1st
		List<Integer> myList = Stream.of(args).map(s->s.length()).collect(Collectors.toList());
		Collections.reverse(myList);
		System.out.println("Reverse order-w1: %s.%n" + myList);
		
		System.out.println("-----------------------------");
		//2nd
		Stream.of(args)
			.collect(Collectors.collectingAndThen(Collectors.toList(),
				l -> {
					Collections.reverse(l);
					return l.stream();
					}))
			.map(s->s.length())
			.forEach(System.out::println);
		
		System.out.println("-----------------------------");
		//3rd ====
		List<Integer> result = 
		IntStream
			.range(0, args.length) // [0,args.length); or rangeClosed() [inclu,inclu] => same for(int i, to i < args.length)
			.mapToObj(idx -> new SimpleEntry<Integer, String>(-idx, args[idx])) //simpleEntry like map
			.sorted(Comparator.comparing(SimpleEntry::getKey))
//			.sorted((k1,k2) -> k1.getKey() - k2.getKey())
			// remove the negative idx
			.map(SimpleEntry::getValue)
			.map(String::length)
//			.forEach(System.out::println);
			.collect(Collectors.toList());
		System.out.println(result);
	}
}
