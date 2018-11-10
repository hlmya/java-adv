package coreservlets_lambda1;

import java.util.Arrays;

public class Lambda1_Ex1 {

	public static void main(String[] args) {
	    String[] words = { "hi", "hello", "hola", "bye", "goodbye", "adios" };
	    System.out.println("Original array: " + 
	                       Arrays.asList(words));
	    //(s1, s2) -> s2.length() - s1.length()
	    // 1 : s2,s1
	    // -1: s1,s2
	    // 0: s1 == s2
	    Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());
	    System.out.println("Sorted by length ascending: " 
//	    		+ Arrays.asList(words)); // ~ Arrays.toString(words) -> String
	    		+ Arrays.toString(words));
	    
	    Arrays.sort(words, (s1, s2) -> s2.length() - s1.length());
	    System.out.println("Sorted by length descending : " + Arrays.asList(words));
	    
	    // first letter -> charAt(0); second letter -> charAt(1)
	    Arrays.sort(words, (s1, s2) -> s1.charAt(0) - s2.charAt(0));
	    System.out.println("Sorted by first letter : " + Arrays.asList(words));
	    
	    Arrays.sort(words, (s1, s2) -> 
	                 {
	                   if(s1.contains("e") && !s2.contains("e")) {
	                     return -1;
	                   } else if(s2.contains("e") && !s1.contains("e")) {
	                     return 1;
	                   }else
	                   return 0;
	                 });
	    System.out.println("Sorted by whether it contains 'e' [v1] : " + Arrays.asList(words));
	    
	  }


}
