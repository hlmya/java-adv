import static java.lang.Integer.parseInt;
import static java.lang.System.out;
import static java.util.Arrays.sort;
import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Something {}

interface Crazy {
	void crazy(int a1, int a2, int a3, int a4, int a5,
			   int a6, int a7, int a8, int a9, int aa,
			   int ab, int ac, int ad);
}

interface MyBiF {
	int thisIsMyMethod(String s, String s2);
}

interface MyBiF2<T> {
	int thisIsMyMethod(T s, T s2);
}

interface MyBiF3<T, T2> {
	int thisIsMyMethod(T s, T2 s2);
}

public class Lambdas {
	public static int myFun(String s, String s2) {
		return s == s2 ? 1 : s.equals(s2) ? 2 : 3;
	}

	public static void main(String[] args) throws Exception {
		List<Integer> collectedList = Stream.of(342, 6354, 412, 342)
			.collect(Collectors.toList());
		Set<Integer> collectedSet = Stream.of(342, 6354, 412, 342)
				.collect(Collectors.toSet());

//		Function<Integer, Map.Entry<Integer, String>> toEntry =
//				n -> Map.entry(n * 2, "value" + n);
////				n -> new AbstractMap.SimpleEntry<>(n * 2, "value" + n);
//
//		Function<Function<Integer, Map.Entry<Integer, String>>,
//				 Collector<Integer, Integer, String> toCollector =
//				fun -> Collectors.toMap(streamVal -> fun.apply(streamVal).getKey(),
//						                streamVal -> fun.apply(streamVal).getValue());

		// Map
		Map<Integer, String> collectedMap = Stream.of(342, 6354, 412)
			.collect(Collectors.toMap(n -> n * 2, n -> "value" + n));

//		Map<Integer, String> collectedMap2 = Stream.of(342, 6354, 412)
//				.collect(toCollector(toEntry));

		System.out.println(collectedMap);

		Map<Integer, Set<String>> collectedMap2b = Stream.of(342, 6354, 533, 1)
				.collect(Collectors.toMap(n -> n, n -> Set.of("" + n)));

		System.out.println(collectedMap2b);

		Map<Integer, Set<String>> collectedMap3 = Stream.of(342, 6354, 2, 4, 6, 533, 1)
				.collect(Collectors.toMap(
			      n -> n % 2,
//                  n -> {
//                	  Set<String> retval = new HashSet<String>();
//                	  retval.add("value" + n);
//                	  return retval;
//                  },
			      n -> Set.of("value" + n),
                  (set1, set2) -> {
                	  Set<String> retval = new HashSet<>(set1);
                	  retval.addAll(set2);
                	  return retval;
                  }
                  ));

		System.out.println(collectedMap3);



		// int[]
		Object[] array = Stream.of(342, 6354, 1, 1, 432, 1)
			.toArray();

		array[2] = "hello world";

		System.out.println(Arrays.toString(array));

		Integer[] array2 = Stream.of(342, 6354, 1, 1, 432, 1)
				.toArray(Integer[]::new);

//		array2[2] = "fdhsaj";

		Function<Integer, Integer[]> supp1 = Integer[]::new;
		Supplier<String> supp2 = String::new;
		Supplier<Something> supp3 = Something::new;
		Supplier<Lambdas> supp4 = Lambdas::new;

		Integer[] sixElems = supp1.apply(6);
		System.out.println(Arrays.toString(sixElems));

		Consumer<String> printer = System.out::println;
		Consumer<String> printer2 = s -> System.out.println(s);
		printer.accept("hello world");
		printer2.accept("hello world");

		Supplier<String> simple  = () -> "anything".toString();
		Supplier<String> simple2 = "anything"::toString;

		BiFunction<Double, Double, Double> toPower =
				(base, exp) -> Math.pow(base, exp);
		BiFunction<Double, Double, Double> toPower2 = Math::pow;

		Crazy crazy1 = (a1,a2,a3,a4,a5,a6,a7,a8,a9,aa,ab,ac,ad) ->
						anotherCrazyFun(a1,a2,a3,a4,a5,a6,a7,a8,a9,aa,ab,ac,ad);
		crazy1.crazy(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

		Crazy crazy2 = Lambdas::anotherCrazyFun;
		crazy2.crazy(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

		boolean result1 = Stream.of(1, 2, 3, 4, 5).allMatch(i -> i < 10);
		System.out.println(result1);

		boolean result2 = Stream.of(1, 20, 3, 4, 5).allMatch(i -> i < 10);
		System.out.println(result2);

		boolean result3 = Stream.of(1, 20, 3, 4, 5).anyMatch(i -> i < 10);
		System.out.println(result3);

		boolean result4 = Stream.of(1, 20, 3, 4, 5).parallel().noneMatch(i -> i < 10);
		System.out.println(result4);

		List.of(1,2,3).stream().parallel();
		List.of(1,2,3).parallelStream();


		// keeping a state with a parallel stream is a BAD idea
//		int[] previousValue = { 0 };
//		int sum = IntStream.range(0, 1000000).parallel()
//			.map(i -> i < previousValue[0] ? (previousValue[0] = i + 1) : i)
//			.sum();
//		System.out.println(sum);

	}

	private static void anotherCrazyFun(int a1, int a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9,
			int aa, int ab, int ac, int ad) {
		System.out.println("I am crazy");
	}

	public static void main8(String[] args) throws Exception {
		String[] texts = { "432", "10", "2", "12", "055", "42978" };
		Arrays.sort(texts);
		System.out.println(Arrays.toString(texts));

		Arrays.sort(texts, Comparator.comparing(Integer::parseInt));
		Arrays.sort(texts, comparing(Integer::parseInt));
		sort(texts, comparing(Integer::parseInt));
		System.out.println(Arrays.toString(texts));

		int[] numbers = new int[texts.length];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = Integer.parseInt(texts[i]);
		}

		System.out.println(Arrays.toString(numbers));
	}

	public static void main7(String[] args) {
		System.out.println("Hello");
		out.println("Hello");

		String out;

		Function<Integer, String[]> newString = String[]::new;
		IntFunction<String[]> newString2 = String[]::new;
		String[] myStrings = newString.apply(3);
		String[] myStrings2 = newString.apply(5);
		myStrings[0] = "foo";
		myStrings[1] = "bar";
		System.out.println(Arrays.toString(myStrings));

		String[] stringsCreatedTheUsualWay = new String[3];


		int myInt = Integer.parseInt("123");
		int myInt2 = parseInt("123");

		BiFunction<String, String, Integer> f = Lambdas::myFun;
		MyBiF f2 = Lambdas::myFun;
		MyBiF2<String> f3 = Lambdas::myFun;
		MyBiF3<String, String> f4 = Lambdas::myFun;
//		IntUnaryOperator f5 = Lambdas::myFun;

//		(Lambdas::myFun).apply(2, "blah", new ArrayList<Double>());
		((BiFunction<String, String, Integer>)Lambdas::myFun)
			.apply("2", "blah");
		((MyBiF2<String>)Lambdas::myFun)
			.thisIsMyMethod("2", "blah");

		Consumer<String> cons = System.out::println;
		cons.accept("abc");

//		PrintStream out2 = out;

		System.out.println(new Object() == new Object());
		Object o = new Object();
		Object o2 = o;
		System.out.println(o == o2);

		Object o3 = "abc";
		Object o4 = "abc";
		Object o4b = new String("abc");
		Object o5 = "abcxyz";
		System.out.println(f.apply("abc", "abc"));
		System.out.println(o3 == o4);
		System.out.println(o3 == o4b);
		System.out.println(o3.equals(o4b));
		o3 += "xyz";
		System.out.println(o3 == o4);
		System.out.println(o3 == o5);
	}

	public static void main6(String[] args) {

		MyLambda lam = (i, s, ds) -> {
			String retval = "";
			for (int j = 0; j < i; j++) {
				retval += s;
			}

			IntUnaryOperator op = j -> j + 1;

			for (Double d : ds) {
				retval += d;
			}

			return retval;
		};

		MyLambda lam2 = new MyLambda() {
			@Override
			public String myMethod(int i, String s, List<Double> ds) {
				String retval = "";
				for (int j = 0; j < i; j++) {
					retval += s;
				}

				for (Double d : ds) {
					retval += d;
				}

				return retval;
			}
		};

		List<Double> doubles = new ArrayList<>(Arrays.asList(1.2, 2.3));
		System.out.println(lam.myMethod(3, "abc", doubles));

	}

	public static void main5(String[] args) {
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

