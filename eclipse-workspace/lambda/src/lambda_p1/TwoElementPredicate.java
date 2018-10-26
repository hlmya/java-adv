package lambda_p1;

/** An interface for which lambdas can be used. See StringUtils for
 *  examples of USING this interface in code, which are more or less the same
 *  as in Java 7. But, see IsBetterExamples for examples of PASSING IN
 *  instances of this interface, where now in Java 8 you can use lambdas.
 *  Also see TwoStringPredicate for a weaker version of this interface
 *  that works only for Strings.
 **/
@FunctionalInterface
public interface TwoElementPredicate<T>{
	boolean isBetter(T e1, T e2);
}
