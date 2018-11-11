package pr;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;

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
		
	}

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
