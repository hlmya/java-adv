package lambda_p1;

public class ElementUtils {
	public static <T> T betterElement(T e1, T e2, TwoElementPredicate<T> tester) {
		if(tester.isBetter(e1, e2)) {
			return e1;
		}else {
			return e2;
		}
	}
}
