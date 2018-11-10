import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class LambdaExercises {
	public static void main(String[] args) {
		Supplier<Integer> supplyNextPrime = getPrimeSupplier();

		Consumer<Integer> cons = val -> {
			System.out.println(val);
			System.out.println(val);
		};

		Stream.generate(supplyNextPrime).limit(10).forEach(cons);

		/*
		 * for (int i = 0; i < 10; i++) { cons.accept(supplyNextPrime.get()); }
		 */

		Supplier<Integer> supplySomething = () -> 532;

		System.out.println(supplySomething.get());

		// Integer -> Integer
		Function<Integer, Integer> f = n -> 2 * n + 1;
		Function<Integer, Integer> f2 = (n) -> 2 * n + 1;
		Function<Integer, Integer> f3 = (Integer n) -> {
			return 2 * n + 1;
		};

		Function<Integer, Integer> g = n -> n / 3;
		Function<Integer, Integer> g2 = new Function<Integer, Integer>() {
			public Integer apply(Integer n) {
				return n / 3;
			}
		};

		Integer result = f.apply(10);
		System.out.println(result);

		// BiFunction<Function<Integer, Integer>, Function<Integer, Integer>,
		// Function<Integer, Integer>> h = (fun1,
		// fun2) -> k -> 42;

		// BiFunction<Function<Integer, Integer>, Function<Integer, Integer>,
		// Function<Integer, Integer>> h = (fun1,
		// fun2) -> k -> fun1.apply(k);

		BiFunction<Function<Integer, Integer>, Function<Integer, Integer>, Function<Integer, Integer>> h = (fun1,
				fun2) -> k -> fun2.apply(fun1.apply(k));

		BiFunction<Function<Integer, Integer>, Function<Integer, Integer>, Function<Integer, Integer>> h2 = (fun1,
				fun2) -> fun2.compose(fun1);

		Integer finalResult = h.apply(f, g).apply(100);
		System.out.println(finalResult);
		System.out.println(finalResult == 67);

		Function<Integer, Integer> composed = f.compose(g);
		System.out.println(composed.apply(100));
		System.out.println(f.compose(g).apply(100));

		BiFunction<Function<Integer, Integer>, Integer, Function<Integer, Integer>> iterate = (fun, n) -> k -> {
			int retval = k;
			for (int i = 0; i < n; i++) {
				retval = fun.apply(retval);
			}
			return retval;
		};

		BiFunction<Function<Integer, Integer>, Integer, Function<Integer, Integer>> iterate2 = (
				whatIHaveToDoOverAndOver, howManyTimesIHaveToDoThis) -> startingValue -> {
					int currentValue = startingValue;
					for (int i = 0; i < howManyTimesIHaveToDoThis; i++) {
						currentValue = whatIHaveToDoOverAndOver.apply(currentValue);
					}
					return currentValue;
				};

		System.out.println(iterate.apply(f, 0).apply(7));
		System.out.println(iterate.apply(f, 1).apply(7));
		System.out.println(iterate.apply(f, 2).apply(7));
		System.out.println(iterate.apply(f, 3).apply(7));

		System.out.println(iterate.apply(f, 0).apply(1));
		System.out.println(iterate.apply(f, 1).apply(1));
		System.out.println(iterate.apply(f, 2).apply(1));
		System.out.println(iterate.apply(f, 3).apply(1));

		// int -> int ?
		// ???

		// int -> T
		// java.util.function.UnaryOperator<T>

		// T -> int
		// java.util.function.IntFunction<T>

		Integer[] myArray = new Integer[] { 1, 2, 3 };
		Integer[] myArray2 = { 1, 2, 3 };
		// List<Integer> myList = new ArrayList<Integer>(1, 2, 3);
		List<Integer> myList2 = Arrays.asList(myArray);

		List<Integer> myList3 = new ArrayList<>(Arrays.asList(new Integer[] { 1, 3, 2, -214 }));

		Map<String, Integer> myMap = new HashMap<>();
		myMap.put("dsaf", 64);

		// myList2.add(1123);
		myList3.add(1123);

		Collections.sort(myList3);

		System.out.println(myList3);

		Arrays.sort(args);
		System.out.println(Arrays.toString(args));

		Arrays.sort(args, (s1, s2) -> {
			int diff = numberOfAs(s1) - numberOfAs(s2);
			if (diff != 0)
				return diff;
			return s1.compareTo(s2);
		});

		/*
		 * Arrays.sort(args, new Comparator<String>() { public int
		 * compare(String s1, String s2) { return numberOfAs(s1) -
		 * numberOfAs(s2); } });
		 */

		System.out.println(Arrays.toString(args));

		int[] primes = createArray(6, supplyNextPrime);
		// primes = new int[0];
		int[] primes2 = createArray2(6, i -> primes[i]);

		System.out.println(Arrays.toString(primes));
		System.out.println(Arrays.toString(primes2));

		int[] primes3 = createArray(6, getPrimeSupplier());
		// primes = new int[0];
		int[] primes4 = createArray2(6, i -> primes3[i]);

		System.out.println(Arrays.toString(primes3));
		System.out.println(Arrays.toString(primes4));
	}

	static int[] createArray(int numOfElements, Supplier<Integer> supplier) {
		int[] retval = new int[numOfElements];
		for (int i = 0; i < retval.length; i++) {
			retval[i] = supplier.get();
		}
		return retval;
	}

	static int[] createArray2(int numOfElements, Function<Integer, Integer> supplier) {
		int[] retval = new int[numOfElements];
		for (int i = 0; i < retval.length; i++) {
			retval[i] = supplier.apply(i);
		}
		return retval;
	}

	private static int numberOfAs(String s2) {
		int numOfAs = 0;
		char[] charArray = s2.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == 'a') {
				++numOfAs;
			}
		}
		return numOfAs;
	}

	private static Supplier<Integer> getPrimeSupplier() {
		return new Supplier<Integer>() {
			int previousPrime = 2;

			@Override
			public Integer get() {
				int tmp = previousPrime;

				while (true) {
					++previousPrime;
					if (isPrime(previousPrime))
						break;
				}

				return tmp;
			}

			private boolean isPrime(int previousPrime2) {
				for (int i = 2; i < previousPrime2; i++) {
					if (previousPrime2 % i == 0)
						return false;
				}
				return true;
			}
		};
	}
}
