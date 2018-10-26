package lecture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambdas_lec1 {
	public static void main(String[] args) {
		List<int[]> myList = new ArrayList<>();
		Map<int[], Supplier<String>> myMap = new HashMap<>();

		List<List<Integer>> myList2 = new ArrayList<>();
		// never use arrays as keys for Set and Map
		Map<List<Integer>, Supplier<String>> myMap2 = new HashMap<>();

		int[] vals1 = {1,2,3};
		List<Integer> vals2 = new ArrayList<>();
		vals2.add(1);
		vals2.add(2);
		vals2.add(3);
		int[] vals1b = {1,2,3};
		List<Integer> vals2b = new ArrayList<>();
		vals2b.add(1);
		vals2b.add(2);
		vals2b.add(3);

		myList.add(vals1);
		myList2.add(vals2);

		Supplier<String> winSupplier = () -> "you won";
		myMap.put(vals1, winSupplier);
		myMap2.put(vals2, winSupplier);

		System.out.println(vals2 == vals2b);		// compares reference (is built-in)
		System.out.println(vals2.equals(vals2b));	// compares content (calls equals)
		Supplier<String> supplier2 = myMap2.get(vals2b);
		System.out.println(supplier2.get());

		System.out.println(vals1 == vals1b);		// compares reference (is built-in)
		System.out.println("!!!! " + vals1.equals(vals1b));	// NOT compares content (compares reference)
		Supplier<String> supplier = myMap.get(vals1b);
		System.out.println(supplier.get());
	}

	public static void main4(String[] args) {

//		byte (short char) int long | float double
//		boolean
		byte b = (byte)3;
		long myLong = b;
		byte b2 = (byte)myLong;
		System.out.println(b2);

		long myLong2 = 0x100000000L;
		long myLong3 =  0xffffffffL;
		System.out.println((int)myLong2);
		System.out.println((int)myLong3);
		System.out.println(myLong3);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);

		long myLong4 = 0x100000000L;
		float f4 = (float)myLong4;
		System.out.printf("%f\n", f4);

		long myLong5 = Long.MAX_VALUE;
		float f5 = (float)myLong5;
		System.out.printf("%d\n", myLong5);
		System.out.printf("%f\n", f5);
	}

	public static void main3(String[] args) {
		// Runnable: () -> ()
		// Consumer: T -> ()
		// IntConsumer: int -> ()
		// Supplier: () -> T
		// Function: T1 -> T2
		// IntFunction: int -> T
		// IntUnaryOperator: int -> int
		// BiFunction: (T1, T2) -> T3
		// IntBinaryOperator: (int, int) -> T3
		// Predicate: T -> boolean
		Runnable r = createALambda();

		// T -> ()
		Consumer<Integer> cons1 = n -> {
			System.out.println(n);
		};
		Consumer<String> cons2 = n -> {};
		Consumer<List<Consumer<Runnable>>> cons3 = n -> {};

		Consumer<String> cons4 = System.out::println;
		Consumer<String> cons4b = s -> System.out.println(s);

		cons4b.accept("abc");

		// () -> T
		Supplier<Integer> supp = () -> 1;
		System.out.println(supp.get());
		System.out.println(supp.get());
		System.out.println(supp.get());
		System.out.println(supp.get());
		System.out.println(supp.get());

		Supplier<Integer> supp2 = new Supplier<Integer>() {
			int val = 0;
			@Override
			public Integer get() {
				++val;
				return val;
			}
		};

		System.out.println(supp2.get());
		System.out.println(supp2.get());
		System.out.println(supp2.get());
		System.out.println(supp2.get());


		final int[] val = { 0 };
		Supplier<Integer> supp3 = () -> ++val[0];

		System.out.println(supp3.get());
		System.out.println(supp3.get());
		System.out.println(supp3.get());
		System.out.println(supp3.get());

//		anyNumber = 567;

		r.run();

		// (Integer) -> int

		Predicate<Integer> isEven = i -> i % 2 == 0;

		System.out.println(isEven.test(0));
		System.out.println(isEven.test(1));
	}

	private static Runnable createALambda() {
		int anyNumber = 234;

		return () -> {
			System.out.println("Hello world " + anyNumber);
		};
	}

	public static void main2(String[] args) {
		IntFunction<Integer> f = n -> n + 1;
		IntFunction<Integer> f2 = (int n) -> n + 1;
		IntFunction<Integer> f3 = (int n) -> {
			return n + 1;
		};

		String[] texts = { "abc", "fdhsjfs", "fdsfaa" };


		BiFunction<Integer, Integer, Integer> f4v2 =
			new BiFunction<Integer, Integer, Integer>() {
				@Override
				public Integer apply(Integer from, Integer to) {
					int retval = 0;
					for (int i = from; i <= to; i++) {
						retval += texts[i].length();
						if (retval == 13) {
							return -1;
						}
					}
					return retval;
				}
			};

		BiFunction<Integer, Integer, Integer> f4 = (from, to) -> {
			int retval = 0;
			for (int i = from; i <= to; i++) {
				retval += texts[i].length();
				if (retval == 13) {
					return -1;
				}
			}
			return retval;
		};

		IntUnaryOperator myOpPlus = n -> n + 1;
		IntUnaryOperator myOpMinus = n -> n - 1;

//		IntUnaryOperator myOpWrong = new IntUnaryOperator();
		IntUnaryOperator myOp2 = new IntUnaryOperator() {
			@Override
			public int applyAsInt(int n) {
				return n + 1;
			}
		};
		IntUnaryOperator myOpMinus2 = new IntUnaryOperator() {
			@Override
			public int applyAsInt(int n) {
				return n - 1;
			}
		};

		myOpPlus.applyAsInt(45678);

		BiFunction<String, Integer, String> f5 = (s1, val2) -> s1 + val2 + s1;
		Function<Integer, BiFunction<String, Integer, String>> f6 =
				val1 ->
					(s1, val2) -> val1 > val2 ? s1 + val2 + s1 : s1;

		BiFunction<String, Integer, String> f6out = f6.apply(10);
		System.out.println("Txt = " + f5.apply("foo", 1234));
		System.out.println("Txt2a = " + f6out.apply("foo", 1234));
		System.out.println("Txt2b = " + f6out.apply("foo", 0));
		System.out.println("Txt2c = " + f6.apply(47).apply("foo", 0));
		System.out.println("Total length = " + f4.apply(0, 2));
		System.out.println("Total length = " + f4.apply(1, 2));
		System.out.println("Total length = " + f4.apply(1, 1));

		String[] texts2 = { "aaa", "bbbbb", "cc", "xxx", "yyy", "zzz" };
		Arrays.sort(texts2, (String s1, String s2) -> s2.length() - s1.length());
		Arrays.sort(texts2, (s1, s2) -> s2.length() - s1.length());
		System.out.println(Arrays.toString(texts2));
		Arrays.sort(texts2, Comparator.comparing(s -> -s.length()));
//		Arrays.sort(texts2, Comparator.comparing(String::length));
		System.out.println(Arrays.toString(texts2));
		Arrays.sort(texts2, Comparator.comparing(s -> s.length()));
		System.out.println(Arrays.toString(texts2));

		//		IntFunction g = f;
//		System.out.println(f.apply(15));
//		System.out.println(g.apply(15));
//
		System.out.println(new Adder().inc(15));

		doMyThing(f);
		doMyThing(n -> n / 2);
		doMyThing(n -> f.apply(f.apply(n)));

		System.out.println(myOpMinus.getClass().getName());
		System.out.println(myOpMinus2.getClass().getName());
	}

	static void doMyThing(IntFunction<Integer> intFun) {
		for (int i = 0; i < 5; i++) {
			System.out.print(intFun.apply(i) + " ");
		}
		System.out.println();
	}
}


class Adder {
	public int inc(int n) { return n + 1; }
}

class MyIntOp implements IntUnaryOperator {
	@Override
	public int applyAsInt(int arg0) {
		return 0;
	}
}

