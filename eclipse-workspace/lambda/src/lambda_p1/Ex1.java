package lambda_p1;

import java.util.Arrays;
import java.util.Comparator;

public class Ex1 {
	
	static String[] texts = {"one", "three", "four", "two"};
	static String[] texts2 = {"d", "b", "c", "a", "z"};
	static String[] words = { "hi", "hello", "hola", "bye", "goodbye", "adios" };

	public static void main(String[] args) {
		
		// sort by length
//		Arrays.sort(texts, (s1,s2)->s1.length() - s2.length());
		Arrays.sort(texts, Comparator.comparing(s->s.length()));
		System.out.println(Arrays.toString(texts));
		
		// reverse length
		Arrays.sort(texts, (s1,s2)-> -(s1.length() - s2.length()));
		Arrays.sort(texts, Comparator.comparing(s->-s.length()));
		System.out.println(Arrays.toString(texts));
		
		// alphabetically by the first character only
//		Arrays.sort(texts2, Comparator.comparing(s->s.charAt(0)));
		Arrays.sort(texts2, (s1,s2)->s1.charAt(0) - s2.charAt(0));
		System.out.println(Arrays.asList(texts2));
		
		// Strings that contain “e” first, everything else second
		Arrays.sort(words, (s1,s2)-> s1.contains("e")? -1:1);//not cover all cases but can accept
		System.out.println(Arrays.toString(words));
		
		Arrays.sort(words, (s1,s2) -> {
			int flag = 0;
			if(s1.contains("e") && !s2.contains("e")) {
				flag = -1;
			}else if(s2.contains("e") && !s1.contains("e")) {
				flag = 1;
			}
			return flag;
		});
		System.out.println(Arrays.toString(words));
		
		// Redo the previous problem, but use a static helper method
		Arrays.sort(words, (s1,s2)-> sortCharater(s1, s2));
		System.out.println(Arrays.toString(words));
		
		Arrays.sort(words, Ex1::sortCharater);
		System.out.println(Arrays.toString(words)+"reference method");
		

	}
	
	public static int sortCharater(String s1, String s2) {
		int flag = 0;
		if(s1.contains("e") && !s2.contains("e")) {
			flag = -1;
		}else if(s2.contains("e") && !s1.contains("e")) {
			flag = 1;
		}
		return flag;
	}

}
