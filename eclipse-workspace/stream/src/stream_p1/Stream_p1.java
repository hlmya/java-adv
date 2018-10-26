package stream_p1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stream_p1 {

	public static void main(String[] args) {
		List<String> words = Arrays.asList("hi", "hello", "hola", "bye", "goodbye", "adios");
		
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
		
//		words.stream().map(s->s.replace("i", "eye")).collect(Collectors.toList());
//		System.out.println();
//		words.stream().map(String::toUpperCase).collect(Collectors.toList());
		
		//4
		System.out.println("ex4:");
		words.stream().filter(s->s.length()<4);
		

	}

}
