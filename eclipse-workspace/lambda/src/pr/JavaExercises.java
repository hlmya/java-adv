package pr;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collectors;

enum WeekDay {
	MON, TUE, WED, THU, FRI, SAT, SUN
}
public class JavaExercises {

	public static void main(String[] args) {
		
//		example1(); //Print 10 weekdays with Supplier
//		example2(); //Print 10 weekdays with Supplier - way 2 without lambda
//		solution1_1(WeekDay.FRI, 0); //Print weekdays after starting day with n days(Mon-7 days: Mon)
//		solution1_1_bifunction();
//		solution1_2(); // which one is earlier
//		solution2();
//		solution3();

	}
	/**
	 * Sort the command line arguments so that those arguments 
	 * that contains names of weekdays come first.
	 */
	private static void solution3() {
		List<String> weekdays = Arrays.asList("mon","tue","wed","thu","fri","sat","sun");
		String[] texts = {"haha","mon","dsaxfdsa", "tue","aaabce","h"};
		
		Arrays.sort(texts, (s1,s2) -> {
			if(weekdays.contains(s1) && !weekdays.contains(s2)) {
				return -1;
			}else if(!weekdays.contains(s1) && weekdays.contains(s2)) {
				return 1;
			}else {
				return 0;
			}
		});
		
		System.out.println(Arrays.asList(texts));
	}
	//========= beginning of solution 2
	/**
	 * Sort the command line arguments based on the number of 'a' characters in them.
	 */
	private static void solution2() {
		//way1 - String[]
		String[] texts = {"a","bxxd","dsaxfdsa", "fwegse","aaabce","h"};
		//Arrays.asList(texts).forEach(System.out::println); //print if texts is String[]
		
		Arrays.sort(texts, Comparator.comparing(JavaExercises::countCharsInString));
		Arrays.asList(texts).forEach(System.out::println);
		System.out.println("===");
		//OR
		
		Function<Character, Comparator<String>> makeComparator =
				ch -> Comparator.comparing(s -> countCharsInString(s, ch));
		Arrays.sort(texts, makeComparator.apply('a')); // if texts is String[]
		Arrays.asList(texts).forEach(System.out::println);
		System.out.println("===========");
		
		//way2 - List<String>
		List<String> textlist = Arrays.asList("a","bxxd","dsaxfdsa", "fwegse","aaabce","h");
		//System.out.println(textlist); // print if texts is List<String>
		
		Collections.sort(textlist, makeComparator.apply('a')); // if texts is List<String>
		System.out.println(textlist);
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
	//================= end of 2
	/**
	 * Make a lambda that takes two weekdays, 
	 * and returns whether the first one is earlier in the week than the second one.
	 */
	private static void solution1_2() {
		BiFunction<WeekDay, WeekDay, Boolean> isBefore = (d1,d2) -> d1.ordinal() < d2.ordinal();
		System.out.println(isBefore.apply(WeekDay.TUE, WeekDay.WED));
	}

	/**
	 * Sol with BiFunction
	 * Make a lambda that takes a day of the week and a number n, and returns the
	 * weekday that is n days after the other one.
	 * Keep in mind that n can be a big number, or a negative one.
	 */
	private static void solution1_1_bifunction() {
		BiFunction<WeekDay, Integer, WeekDay> nafter = (start,n) -> {
			IntBinaryOperator getIndex = (startingIdx, num) -> {
				int len = WeekDay.values().length;
				int idx = startingIdx;
				
				if(num > 0) {
					idx = (startingIdx + num) % len;
				}else if(num <0) {
					for (int i = 0; i > num; i--) {
						idx--;
						if(idx == -1) {
							idx = 6;
						}
					}
				}
				return idx;
			};
			return WeekDay.values()[getIndex.applyAsInt(start.ordinal(), n)];
		};
		System.out.println(nafter.apply(WeekDay.MON, 16));
		System.out.println(nafter.apply(WeekDay.MON, -2));
	}

	/**
	 * Make a lambda that takes a day of the week and a number n, and returns the
	 * weekday that is n days after the other one.
	 * Keep in mind that n can be a big number, or a negative one.
	 */
	private static void solution1_1(WeekDay start, int n) {
		Function<WeekDay, Supplier<WeekDay>> f = (startingDay) -> new Supplier<WeekDay>() {
			int idx = startingDay.ordinal();
			int len = WeekDay.values().length;
			@Override
			public WeekDay get() {
				idx++;
				return WeekDay.values()[idx % len];
			}
		};
		
		Supplier<WeekDay> getWeekDay = f.apply(start);
		WeekDay result = null;
		
		if (n < 0) {
			for (int i = 0; i < (7 - (Math.abs(n))%7); i++) { // Monday - 7days : Monday
				result = getWeekDay.get();
			}
		}else if(n > 0){
			for (int i = 0; i < n; i++) { // Monday - 7days : Monday
				result = getWeekDay.get();
			}
		}else { // n = 0
			result = start;
		}
		System.out.println("("+start + "," + n + "): " + result);
	}

	/**
	 * Print 10 weekdays with Supplier - way 2 without lambda
	 */
	private static void example2() {
		// Cannot use lambda to initial idx or len -> value never change
		// Wrong
		/*
		Supplier<WeekDay> weekLambda1 = ()-> {
			int idx = -1;
			int len = WeekDay.values().length;
			idx++;
			return WeekDay.values()[idx % len];
		}; 
		for (int i = 0; i < 10; i++) {
			System.out.println(weekLambda1.get()); // result is MON MON MON ...
		}*/
		
		// Correct
		Supplier<WeekDay> weekLambda2 = new Supplier<WeekDay>() {
			int idx = -1; // choose idx < 0;
			int len = WeekDay.values().length;

			@Override
			public WeekDay get() {
				idx++;
				return WeekDay.values()[idx % len];
			}
		};
		System.out.println("----------------");
		for (int i = 0; i < 10; i++) {
			System.out.println(weekLambda2.get());
		}
	}

	/**
	 * Print 10 weekdays with Supplier
	 */
	private static void example1() {
		// Supplier get() weekday
		int[] idx = {0};
		int len =  WeekDay.values().length;
		Supplier<WeekDay> weeks = () -> WeekDay.values()[(idx[0]++) % len];// make idx small in range:(0-6)
		
		// length of enum: enum.values().length
		for (int i = 0; i < 10; i++) {
			System.out.println(weeks.get());
		}
	}

}
