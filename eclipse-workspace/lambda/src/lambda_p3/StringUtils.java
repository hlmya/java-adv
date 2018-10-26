package lambda_p3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class StringUtils {
	public static List<String> allMatches(List<String> words, Predicate<String> p){
		List<String> result = new ArrayList<>();
		for(String matchE : words) {
			if(p.test(matchE)) {
				result.add(matchE);
			}
		}
		
		return result;
	}
	
	public static List<String> transformedList(List<String> words, Function<String, String> f){
		List<String> result = new ArrayList<>();
		for(String w: words) {
			result.add(f.apply(w));
		}
		return result;
	}
	
	//private StringUtils() {};
}
