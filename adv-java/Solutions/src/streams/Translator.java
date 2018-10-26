package streams;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Translator {
	public static String translator(Map<String, String> dict, String text) {
		return Stream.of(text.split(" "))
			.map(word -> dict.getOrDefault(word, "?"))
			.collect(Collectors.joining(" "));
	}
}
