package practice;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class MyStream {

	public static void main(String[] args) {
	
		//List<String> words = Arrays.asList("hi", "hello", "hola", "bye", "goodbye", "adios");

		/*
		List<String> words = new ArrayList<String>();
		for (String s: args) {
            words.add(s);
        }
		
		List<Integer> result1 = words.stream().map(s -> s.length()).collect(Collectors.toList());
		System.out.printf("length: %s.%n", result1);
		
		List<Integer> result2 = result1.stream().collect(Collections.reverse(result1));	 
		
//		Collections.reverse(result1);
		System.out.println("length and reverse order: %s.%n" + result1);
		*/
		
//		Stream.of(args).forEach(System.out::println);
//		reversed(Stream.of(args).map(s -> s.length()).collect(Collectors.toList()));
//		Stream.of(args)
//			.map(String::length)
//			.map()
		
		
		//.map(Integer::parseInt)
//		int result = Stream.of(args)
//			.filter(s->s.matches("[0-9]+"))
//			.filter(s->Integer.parseInt(s)%2 == 0 && Integer.parseInt(s) > 8)
//			.mapToInt(i->i).sum();
//			.collect(Collectors.toList()).forEach(System.out::println);
		
		//Make a method that generates a stream of the first n prime numbers.
		
//		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 20);
		
//		Stream.of(numbers)
//			.map()
//			.collect(Collectors.toList())
//			.forEach(System.out::println);

	}
	
//	public static boolean isPrime(int number) {
//	    return IntStream.rangeClosed(2, number/2).noneMatch(i -> number%i == 0);
//	}

//	private static void reversed(List<Integer> collect) {
////		Stream.of(collect).
//		Stream.of(collect)
//	      .collect(Collectors.toCollection(ArrayDeque::new)) // or LinkedList
//	      .descendingIterator()
//	      .forEachRemaining(System.out::println);
//	}	

}
