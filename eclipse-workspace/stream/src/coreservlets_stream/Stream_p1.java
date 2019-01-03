package coreservlets_stream;

import java.util.Arrays;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Stream_p1 {
	private static List<String> words = Arrays.asList("hi", "hello", "hola", "bye", "goodbye", "adios");

	public static void main(String[] args) {	
//		pr1(words);
//		.mapToObj(idx -> new SimpleEntry<Integer, String>(-idx, args[idx]))
	IntStream.range(0,words.size())
				.mapToObj(idx -> new SimpleEntry<Integer, String>(-idx, words.get(idx)))
				.sorted((s1,s2) -> s1.getKey() - s2.getKey())
				.map(SimpleEntry::getValue)
				.mapToInt(s -> s.length())
				.forEach(System.out::println);
	}

	/**
	 * @param words
	 */
	private static void pr1(List<String> words) {
		//1
		System.out.println("ex1:");
		words.stream().forEach(s-> System.out.println("  " + s));
		
		//2
		System.out.println("ex2:");
		words.stream().forEach(System.out::println);
		
		//3
		System.out.println("ex3:");
		List<String> w1 = words.stream().map(s->s + "!").collect(Collectors.toList());
		System.out.printf("Exciting words: %s.%n", w1);
		
		words.stream().map(s->s.replace("i", "eye")).collect(Collectors.toList());
		System.out.println();
		words.stream().map(String::toUpperCase).collect(Collectors.toList());
		
		//4
		System.out.println("ex4:");
		words.stream().filter(s->s.length()<4);
	}

}
