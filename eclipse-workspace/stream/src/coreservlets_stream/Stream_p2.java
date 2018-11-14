package coreservlets_stream;

import java.util.Arrays;
import java.util.List;

public class Stream_p2 {

	public static void main(String[] args) {
		List<String> words = Arrays.asList("hi", "hello", "alo");
//		List<String> words = Arrays.asList();
		
		// Produce: the result should be "HIHELLO..."
		System.out.println(words.stream().reduce("", (s1,s2) -> (s1+ s2).toUpperCase()));
		
		//Produce the same String as above, but this time via a map operation that turns the words into upper
		//case, followed by a reduce operation that concatenates them
		System.out.println(words.stream().map(s->s.toUpperCase()).reduce("", String::concat));
		
		// comma:  "hi,hello,..."
		System.out.println(words.stream().reduce((s1,s2) -> s1 + "," + s2)); //console: Optional[hi,hello,alo]
		System.out.println(words.stream().reduce((s1,s2) -> s1 + "," + s2).orElse("at least 2 elements")); 
		// hi,hello,alo : if words = Arrays.asList("hi", "hello", "alo");
		// at least 2 elements : if words = Arrays.asList();
		
		//the total number of characters
		System.out.println(words.stream().mapToInt(String::length).sum());
		
		//Find the number of words that contain an “h”.
		System.out.println(words.stream().filter(s->s.contains("h")).count());
	}

}
