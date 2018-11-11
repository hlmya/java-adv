package pr;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

enum WeekDay {
	MON, TUE, WED, THU, FRI, SAT, SUN
}
public class JavaExercises {

	public static void main(String[] args) {
		
//		example1(); //Print 10 weekdays with Supplier
//		example2(); //Print 10 weekdays with Supplier - way 2 without lambda
		solution1(WeekDay.SUN, 2); //Print weekdays after starting day with n days
		
		
	}

	/**
	 * Print weekdays after starting day with n days
	 */
	private static void solution1(WeekDay start, int n) {
		Function<WeekDay, Supplier<WeekDay>> f = (startingDay) -> new Supplier<WeekDay>() {
			int idx = startingDay.ordinal();
			int len = WeekDay.values().length;
			@Override
			public WeekDay get() {
				idx++;
				return WeekDay.values()[idx % len];
			}
		};
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
