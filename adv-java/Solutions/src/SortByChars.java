import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

public class SortByChars {
	public static void main(String[] args) {
		String[] texts = {
			"dsafdsa", "fwegse",
			"árvíztűrő tükörfúrógép",
			"the fox jumped over the lazy dog",
			"fdhuiuhewia"
		};

		printout(texts);

		Arrays.sort(texts, (s1, s2) -> s1.length() - s2.length());
		printout(texts);

		Arrays.sort(texts, Comparator.comparing(s -> s.length()));
		Arrays.sort(texts, Comparator.comparing(String::length));
		printout(texts);

		Arrays.sort(texts, Comparator.comparing(SortByChars::countCharsInString));
		printout(texts);

		Function<Character, Comparator<String>> makeComparator =
				c -> Comparator.comparing(s -> countCharsInString(s, c));

		Arrays.sort(texts, makeComparator.apply('x'));
		printout(texts);

	}

	private static Integer countCharsInString(String s) {
		return countCharsInString(s, 'a');
	}

	private static Integer countCharsInString(String s, char checked) {
		int retval = 0;
		for (char c : s.toCharArray()) {
			if (c == checked)   ++retval;
		}
		return retval;
	}

	private static void printout(String[] texts) {
		Arrays.sort(texts);
		for (String text : texts) {
			System.out.println(text);
		}
		System.out.println("-------------");
	}
}
