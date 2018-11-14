package coreservlets_lambda;
/** An interface for which lambdas can be used. See StringUtils for
 *  examples of USING this interface in code, which are the same
 *  as in Java 7. But, see IsBetterExamples for examples of PASSING IN
 *  instances of this interface, where now in Java 8 you can use lambdas.
 *  Also see TwoElementPredicate for a genericized version of this interface,
 *  and see the next solution set, where we add in the @FunctionalInterface annotation.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, JavaScript, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, Spring MVC,
 *  servlets, JSP, Java 8 lambdas and streams (for those that know Java already), 
 *  and Java 8 programming (for those new to Java)</a>.
 */
@FunctionalInterface
interface TwoStringPredicate{
	boolean isbetter(String s1, String s2);
}

class StringUtils{
	public static String betterString(String s1, String s2, TwoStringPredicate tester) {
		if(tester.isbetter(s1,s2)) {
			return s1; 
		}else {
			return s2;
		}
	}
}
public class Lambda1_ex2 {
	public static void main(String[] args) {
		String test1 = "Hello";
	    String test2 = "Goodbye";
	    
	    String longer = StringUtils.betterString(test1, test2, (a,b) -> a.length() > b.length());
	    System.out.println(longer);
	    // Ex2
//	    String result1 = StringUtils.betterString(test1, test2, new TwoStringPredicate() {
//			
//			@Override
//			public boolean isBetter(String s1, String s2) {
//				return s1.length() > s2.length();
//			}
//		});
	    
	    String first = StringUtils.betterString(test1, test2, (a,b) -> true); // true -> get s1; false -> get s2
	    System.out.println(first);
	}

}
