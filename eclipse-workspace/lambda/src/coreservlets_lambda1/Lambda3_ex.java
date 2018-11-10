package coreservlets_lambda1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;


class ElementUtilsP3{
	public static <T> List<T> allMatches(List<T> list, Predicate<T> p){
		List<T> tempList = new ArrayList<T>();
		/*
		for (int i = 0; i < list.size(); i++) {
			if(p.test(list.get(i))) {
				tempList.add(list.get(i));
			}
		}*/
		
		for(T e: list) {
			if(p.test(e)) {
				tempList.add(e);
			}
		}
		return tempList;
	}
	// Should be List<R>, because R of Function<T,R>. if List<T> ->  cast
	public static <T, R> List<R> transformedList(List<T> list, Function<T,R> f){
		List<R> tempList = new ArrayList<R>();
		for(T e : list) {
			tempList.add(f.apply(e));
		}
		return tempList;
	}
}
public class Lambda3_ex {

	public static void main(String[] args) {
		List<String> words = Arrays.asList("hi", "hello", "hola", "bye", "goodbye", "adios");
		
		// Test with Predicate
		List<String> shortword = ElementUtilsP3.allMatches(words, s -> s.length() < 4);
		System.out.printf("short word: %s%n", shortword);
		
		List<String> wordsWithB = ElementUtilsP3.allMatches(words, s -> s.contains("b"));
		System.out.printf("b word: %s%n", wordsWithB);
		
		List<String> evenLength = ElementUtilsP3.allMatches(words, s->s.length()%2 == 0);
		System.out.printf("even length: %s%n", evenLength);
		
		List<Integer> nums = Arrays.asList(1, 10, 100, 1000, 10000);
		List<Integer> bigNums = ElementUtilsP3.allMatches(nums, n -> n>500);
		System.out.printf("num: %s%n", bigNums);
		
		// Test with Function
		List<String> excitingWords = ElementUtilsP3.transformedList(words, s -> s + "!");
		System.out.printf("exciting words: %s%n", excitingWords);
		
		List<String> upperWords = ElementUtilsP3.transformedList(words, String::toUpperCase);
		System.out.printf("Upper words: %s%n", upperWords);
		
		List<Integer> wordLengths = ElementUtilsP3.transformedList(words,String::length);
		System.out.printf("Word lengths: %s.%n", wordLengths);
	}

}
