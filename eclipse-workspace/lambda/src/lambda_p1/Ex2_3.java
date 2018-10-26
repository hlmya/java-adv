package lambda_p1;

public class Ex2_3 {

	public static void main(String[] args) {
	    String test1 = "Hello";
	    String test2 = "Goodbye";
	    String message ="Better of %s and %s based on %s is %s.%n";
	    
	    // Ex2
	    String result1 = StringUtils.betterString(test1, test2, new TwoStringPredicate() {
			
			@Override
			public boolean isBetter(String s1, String s2) {
				return s1.length() > s2.length();
			}
		});
//	    String result1 = StringUtils.betterString(test1, test2, (s1,s2) -> s1.length() > s2.length());
	    System.out.printf(message,test1,test2,"length",result1);
	    
	    // Ex3
	    String result3 = ElementUtils.betterElement(test1, test2, 
	                                       (s1, s2) -> s1.length() > s2.length());
	    System.out.printf(message, test1, test2, "length_e2", result3);
	    
	    int result4 = ElementUtils.betterElement(1, 2, (s1,s2) -> s1 > s2);
	    System.out.printf(message, 2, 1, "num_e2", result4);
	        
	        
	    


	}

}
