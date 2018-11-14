package coreservlets_lambda;
/** An interface for which lambdas can be used. See StringUtils for
 *  examples of USING this interface in code, which are more or less the same
 *  as in Java 7. But, see IsBetterExamples for examples of PASSING IN
 *  instances of this interface, where now in Java 8 you can use lambdas.
 *  Also see TwoStringPredicate for a weaker version of this interface
 *  that works only for Strings.
 **/
@FunctionalInterface
interface TwoElementPredicate<T> {
	boolean isbetter(T s1, T s2);
}

class ElementUtils {
	public static <T> T betterElement(T s1, T s2, TwoElementPredicate<T> tester) {
		if(tester.isbetter(s1, s2)) {
			return s1;
		}else {
			return s2;
		}
	}
}
public class Lambda1_ex3 {

	public static void main(String[] args) {
		int greaterNumber = ElementUtils.betterElement(12, 17, (a,b) -> a > b);
		System.out.println(greaterNumber);
	}

}
