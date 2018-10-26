import java.util.function.Function;
import java.util.function.Supplier;

enum WeekDay {
	MON, TUE, WED, THU, FRI, SAT, SUN
}

public class WeekLambda {
	public static void main(String[] args) {
		solution1();
		solution2();
		solution3();
	}

	private static void solution1() {
		// Supplier: () -> T
		//           () -> WeekDay
		int[] idx = { 0 };
		Supplier<WeekDay> weekLambda =
//			() -> WeekDay.values()[++idx[0]];
			() -> WeekDay.values()[(idx[0]++) % WeekDay.values().length];

		System.out.println(weekLambda.get()); // MON
		System.out.println(weekLambda.get()); // TUE
		System.out.println(weekLambda.get()); // WED
		for (int i = 0; i < 10; i++) {
			System.out.println(weekLambda.get());
		}
	}

	private static void solution2() {
		Supplier<WeekDay> weekLambda2 = new Supplier<WeekDay>() {
			int idx = -1;
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

	private static void solution3() {
		Function<WeekDay, Supplier<WeekDay>> weeker = startingDay -> new Supplier<WeekDay>() {
			int idx = startingDay.ordinal() - 1;
			int len = WeekDay.values().length;

			@Override
			public WeekDay get() {
				idx++;
				return WeekDay.values()[idx % len];
			}
		};

		System.out.println("----------------");
		Supplier<WeekDay> sunWeek = weeker.apply(WeekDay.SUN);
		for (int i = 0; i < 10; i++) {
			System.out.println(sunWeek.get());
		}

		System.out.println("----------------");
		Supplier<WeekDay> weekendLambda = weeker.apply(WeekDay.FRI);
		for (int i = 0; i < 10; i++) {
			System.out.println(weekendLambda.get());
		}
	}
}
