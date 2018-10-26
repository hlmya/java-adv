package lambda_p3;

import java.util.Arrays;
import java.util.List;

public class ExMain {

	public static void main(String[] args) {
		List<String> words = Arrays.asList("hi", "hello", "hola", "bye", "goodbye", "adios");
		System.out.printf("Original words: %s%n", words);
		
		// Precidcate
		List<String> shortword = StringUtils.allMatches(words, s -> s.length() < 4);
		System.out.printf("short word: %s%n", shortword);
		
		List<String> wordsWithB = StringUtils.allMatches(words, s -> s.contains("b"));
		System.out.printf("b word: %s%n", wordsWithB);
		
		List<String> evenLength = ElementUtils.allMatches(words, s->s.length()%2 == 0);
		System.out.printf("even length: %s%n", evenLength);
		
		List<Integer> nums = Arrays.asList(1, 10, 100, 1000, 10000);
		List<Integer> bigNums = ElementUtils.allMatches(nums, n -> n>500);
		System.out.printf("num: %s%n", bigNums);
		
		// Function
		List<String> excitingWords = StringUtils.transformedList(words, s->s + "!");
		System.out.printf("exciting words: %s%n", excitingWords);
		
		List<String> upperWords = StringUtils.transformedList(words, String::toUpperCase);
		System.out.printf("Upper words: %s%n", upperWords);
		
		List<Integer> wordLengths = ElementUtils.transformedList(words, String::length);
		    System.out.printf("Word lengths: %s.%n", wordLengths);
		
	}

}
