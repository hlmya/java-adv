package coreservlets_lambda3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ElementUtils {
	public static <T> List<T> allMatches(List<T> words, Predicate<T> p){
		List<T> result = new ArrayList<T>();
		for(T matchE : words) {
			if(p.test(matchE)) {
				result.add(matchE);
			}
		}
		
		return result;
	}
	
	public static <T, R> List<R> transformedList(List<T> words, Function<T, R> f){
		List<R> result = new ArrayList<>();
		for(T w: words) {
			result.add(f.apply(w));
		}
		return result;
	};
}
