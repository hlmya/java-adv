package practice;

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

public class StreamPr2 {

	public static void main(String[] args) {
		
//		solution1(args);
		solution2(args);
//		solution4(args);
	
	}
	
	private static void solution4(String[] args) {
		List<Integer> s1 = Arrays.asList(1,2,3);
		List<Integer> s2 = Arrays.asList(4,5,6,7);
//		List<String> s2 = Arrays.asList("a","b","c","d");
//		Stream<Integer> s1 = Stream.of(1, 2, 3, 4, 5, 6);
//		Stream<String> s2 = Stream.of("one", "two","three", "four","five", "six", "seven");
//		Stream<String> streamA = Stream.of("A", "B", "C");
//		Stream<String> streamB = Stream.of("Apple", "Banana", "Carrot", "Doughnut");

		IntStream zipped = zip2stream(s1, s2);
		zipped.forEach(System.out::println);
		
		Map<Integer, Set<String>> collectedMap3 = Stream.of(342, 6354, 2, 4, 6, 533, 1)
				.collect(Collectors.toMap(
			      n -> n % 2,
//                  n -> {
//                	  Set<String> retval = new HashSet<String>();
//                	  retval.add("value" + n);
//                	  return retval;
//                  },
			      n -> {
			    	Set<String> hashSet = new HashSet<>();
			    	hashSet.add("value" + n);
			    	return hashSet;
			      },
//			      	Set.of("value" + n),
                  (set1, set2) -> {
                	  Set<String> retval = new HashSet<>(set1);
                	  retval.addAll(set2);
                	  return retval;
                  }
                  ));

		System.out.println(collectedMap3);
		System.out.println("--------------------");
		
//		Function<Integer, Map.Entry<Integer, String>> toEntry = n -> new AbstractMap.SimpleEntry<>(n * 2, "value" + n);
////		n -> new AbstractMap.SimpleEntry<>(n * 2, "value" + n);
//
//		Function<Function<Integer, Map.Entry<Integer, String>>,
//			Collector<Integer, Integer, String>> toCollector = fun -> 
//				Collectors.toMap(
//						streamVal -> fun.apply(streamVal).getKey(),
//						streamVal -> fun.apply(streamVal).getValue());
//				
//		Map<Integer, String> collectedMap2 = Stream.of(342, 6354, 412)
//				.collect(toCollector(toEntry));
		
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
		
		//3rd
		
		
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
		Stream.of(args).forEach(System.out::println);
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
		//3rd
		IntStream
			.range(0, args.length)
			.mapToObj(idx -> new SimpleEntry<Integer, String>(-idx, args[idx]))
			.sorted(Comparator.comparing(SimpleEntry::getKey))
			// remove the negative idx
			.map(SimpleEntry::getValue)
			.map(String::length)
			.forEach(System.out::println);
	}
}
