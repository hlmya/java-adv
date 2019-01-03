package pr;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MyStream_draft {

	public static void main(String[] args) {
	
//		checkFindFrist();
		
//		checkJoiningAndSetHashSet();
//		Object object = Stream.of().max((e1, e2) -> 1).get();
//		System.out.println(object);

//		// stack, push and pop
//		Function<Stream<Integer>, Stream<Integer>> reverseFiniteStream = stream -> {
//			Stack<Integer> stack = stream.collect(Collectors.toCollection(Stack::new));
//			return Stream.generate(() -> {
//					if (!stack.isEmpty()) {
//						return stack.pop();
//					}
//					return null;
//				});
//		};
//
//		reverseFiniteStream.apply(Stream.of(words).map(arg -> arg.length())).forEach(System.out::println);
		Stream<Integer> nums = Stream.of(3,4,1,5,7);
		List<Integer> a = nums.filter(s -> s < 7).sorted().collect(Collectors.toList()); //expected: 1,3,4,5
		System.out.println(a.stream().map(s -> s + "").reduce((s1,s2) -> s1 + "," + s2).orElse(""));
		//a.stream().map(s -> s + "").reduce("",(s1,s2) -> s1 + "," + s2);
		
		Stream<String> words = Stream.of("a","b","c","d","a","b");
		String[] arrayW = words.distinct().toArray(String[]::new); // distinct: return unique elements
		Stream.of(arrayW).forEach(System.out::println);
	}

	/**
	 * no duplicate and descend the order with TreeSet and HashSet
	 */
	private static void checkJoiningAndSetHashSet() {
		String joined1 = Stream.of("a", "b", "c").collect(Collectors.joining()); 
		System.out.println(joined1); //abc

		String joined2 = Stream.of("a", "b", "c").collect(Collectors.joining(", "));
		System.out.println(joined2); //a, b, c

		String joined3 = Stream.of("a", "b", "c").collect(Collectors.joining(", ", "(", ")"));
		System.out.println(joined3); //(a, b, c)
		
		Set<String> treeSet = new TreeSet<String>();
		treeSet.addAll(Arrays.asList(new String[] { "b", "a", "b", "a", "fddwgwas" }));
		System.out.println(treeSet);//[a, b, fddwgwas]

		Set<String> set = new HashSet<String>();
		set.addAll(Arrays.asList(new String[] { "b", "a", "b", "d", "a", "fddwgwas", "c" }));
		System.out.println(set);//[a, b, c, d, fddwgwas]
	}

	/**
	 * find first is optional and it will return the 1st element satisfy filter
	 */
	private static void checkFindFrist() {
		List<Integer> list = Arrays.asList(10, 3, 7, 5);
		int a = list.stream()
		            .peek(num -> System.out.println("will filter " + num))
		            .filter(x -> x < 10)
		            .findFirst() //optional
		            .get(); // return value of optional
		System.out.println(a);
		System.out.println(list.stream().sorted().collect(Collectors.toList()));
	}
}
