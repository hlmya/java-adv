package lecture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lecture_pr {

	public static void main(String[] args) {
//		example1();
		
//		example2();	
		
//		example3();
		
		Map<Integer, String> collectedMap = Stream.of(342, 6354, 412)
				.collect(Collectors.toMap(n -> n * 2, n -> "value" + n));
		System.out.println(collectedMap);

	}

	/**
	 * 
	 */
	private static void example3() {
		String[] texts = { "abc", "fdhsjfs", "fdsfaa" };
		BiFunction<Integer, Integer, Integer> f4 = (from, to) -> {
			int retval = 0;
			for (int i = from; i <= to; i++) {
				retval += texts[i].length();
				if (retval == 13) {
					return -1;
				}
			}
			return retval;
		};
		
		System.out.println("Total length = " + f4.apply(0, 2));
		System.out.println("Total length = " + f4.apply(1, 2));
		System.out.println("Total length = " + f4.apply(1, 1));
	}

	/*
	 * NOT use Array in Set,Map
	 */
	private static void example2() {
		List<int[]> myList = new ArrayList<>();
		Map<int[], Supplier<String>> myMap = new HashMap<>(); // NEVER use arrays as keys for Set and Map

		List<List<Integer>> myList2 = new ArrayList<>();
		// NEVER use arrays as keys for Set and Map
		Map<List<Integer>, Supplier<String>> myMap2 = new HashMap<>();

		int[] vals1 = {1,2,3};
		List<Integer> vals2 = new ArrayList<>();
		vals2.add(1);
		vals2.add(2);
		vals2.add(3);
		int[] vals1b = {1,2,3};
		List<Integer> vals2b = new ArrayList<>();
		vals2b.add(1);
		vals2b.add(2);
		vals2b.add(3);

		myList.add(vals1);
		myList2.add(vals2);

		Supplier<String> winSupplier = () -> "you won";
		myMap.put(vals1, winSupplier);
		myMap2.put(vals2, winSupplier);

		System.out.println(vals2 == vals2b); //false	-> == compares reference (is built-in)
		System.out.println(vals2.equals(vals2b));// true -> equals() compares content (calls equals)
		Supplier<String> supplier2 = myMap2.get(vals2b);
		System.out.println(supplier2.get());

		System.out.println(vals1 == vals1b);//false		// compares reference (is built-in)
		
		//false	// NOT compares content (compares reference) -> vals1 is array
		System.out.println("!!!! " + vals1.equals(vals1b)); // false
		
		//ERROR -> java.lang.NullPointerException
//		Supplier<String> supplier = myMap.get(vals1b);
//		System.out.println(supplier.get());
	}

	/*
	 * ParseInt
	 */
	private static void example1() {
		String[] texts = { "432", "10", "5", "12", "055", "42978" };
		
		Arrays.sort(texts);// sort according to 1st element
		System.out.println(Arrays.toString(texts)); //[055, 10, 12, 42978, 432, 5]

		Arrays.sort(texts, Comparator.comparing(Integer::parseInt)); // sort after parseInt
		System.out.println(Arrays.toString(texts)); //[5, 10, 12, 055, 432, 42978] -> 055 still keeps 0

		int[] numbers = new int[texts.length];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = Integer.parseInt(texts[i]);
		}

		System.out.println(Arrays.toString(numbers));//[5, 10, 12, 55, 432, 42978] -> 055 removes 0
	}

}
