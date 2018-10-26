package lambda_p1;

public class StringUtils {
	//Cannot make a static reference to the non-static method betterString(String, String, TwoStringPredicate) 
	//from the type StringUtils
	public static String betterString(String s1, String s2, TwoStringPredicate tester) {
		if(tester.isBetter(s1, s2)) {
			return s1;
		}else {
			return s2;
		}
	}
	// Uninstantiatable class; contains only static methods. 
	//-> still works without this
	private StringUtils() {}
}
