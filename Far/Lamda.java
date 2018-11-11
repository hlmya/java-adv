import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lamda {

	//task 4
	public static void main(String[] args) {
   //Create the method createArray that fills in an array. It takes the length of the array as an argument,
   //and an int→int function f. Use the f function on each index of the array, and it will produce the value for the index.
		BiFunction<Integer, IntUnaryOperator, Integer[]> createArray = (n, f) -> {
			Integer[] a=new Integer[n];
			for(int i=0; i< a.length; i++)
				a[i]=f.applyAsInt(i);
         return a;
         };
         IntUnaryOperator f = n -> n+2;

        for(int elem : createArray.apply(10, f))
        	System.out.println(elem);

        System.out.println("------------------------------------------");
        //Make an array of arrays in a similar way. Here, the function has to take two arguments for the two indices.
         BiFunction<Integer[], IntBinaryOperator, Integer[][]> createArray2 = (a,fn) -> {
        	 Integer[][] array = new Integer[a[0]][a[1]];
        	 for(int i=0; i< a[0]; i++)
        		 for(int j=0; j<a[1]; j++)
        			 array[i][j]=fn.applyAsInt(i,j);

        	 return array;
         };

         IntBinaryOperator fn = (i,j) -> i+j;
         Integer a[] = {3,4};
         Integer[][] array = createArray2.apply(a,fn);
         for(int i=0; i< a[0]; i++) {
    		 for(int j=0; j<a[1]; j++)
         	System.out.print(array[i][j]+"  ");
    			System.out.println();}
	}
    //task 3
	public static void main3(String[] args) {
		//Sort the command line arguments so that those arguments that contains names of weekdays come first.
		List<String> days= Arrays.asList("mon", "tue", "wed", "thur", "fri", "sat", "sun");
        Arrays.sort(args, (s1, s2) -> days.indexOf(s2) - days.indexOf(s1));
       Arrays.asList(args).forEach(System.out::println);
	}
	//task 2
	public static void main2(String[] args) {
		//Sort the command line arguments based on the number of 'a' characters in them.
		Function<String,Integer> countAs = str -> {
			return str.chars().mapToObj(ch -> (char)ch).filter(ch -> ch == 'a').collect(Collectors.toList()).size();
		};
        Arrays.sort(args, (s1,s2) -> countAs.apply(s2) - countAs.apply(s1));
        Arrays.asList(args).forEach(System.out::println);
	}
	// task1.3
	public static void main1_3(String[] args) {
		// Make a lambda that, when called, returns a day of the week; when called again
		// and again, it produces the days of the week
		// in order. If called even more times, it starts at the first day again.
		Function<MyDate.Weekday, MyDate.Weekday> nextDay = d -> {
			return d.nextDay();
		};

		System.out.println(nextDay.apply(nextDay.apply(nextDay.apply(
				nextDay.apply(nextDay.apply(nextDay.apply(nextDay.apply(nextDay.apply(MyDate.Weekday.FRI)))))))));

		//Make a lambda that returns a lambda like the previous one. The “outer” lambda gets a day of the week as a parameter;
				//the returned lambda starts from that day.
         Function<MyDate.Weekday, Supplier<MyDate.Weekday>> nextDay2 = (d) -> {
        	 return  () -> { return d.nextDay();  };
         };

         System.out.println(nextDay2.apply(MyDate.Weekday.MON).get());
	}

	// task1.2
	public static void main1_2(String[] args) {
		// Make a lambda that takes two weekdays, and returns whether the first one is
		// earlier in the week than the second one.
		BiFunction<MyDate.Weekday, MyDate.Weekday, Boolean> isBefore = (d1, d2) -> d1.ordinal() < d2.ordinal();

		System.out.println(isBefore.apply(MyDate.Weekday.FRI, MyDate.Weekday.SAT));
	}

	// task1.1
	public static void main1_1(String[] args) {
		// Make a lambda that takes a day of the week and a number n, and returns the
		// weekday that is n days after the other one.
		// Keep in mind that n can be a big number, or a negative one.
		BiFunction<MyDate.Weekday, Integer, MyDate.Weekday> nAfter = (day, n) -> {
			IntBinaryOperator getIndex = (d, m) -> {
				int length = MyDate.Weekday.values().length;
				int in = d;
				if (m > 0)
					in = (m + d) % length;
				else if (m < 0) {
					for (int i = 0; i > n; i--) {
						--in;
						if (in == -1)
							in = 6;
					}
				}
				return in;
			};

			return MyDate.Weekday.values()[getIndex.applyAsInt(day.ordinal(), n)];
		};
		System.out.println(nAfter.apply(MyDate.Weekday.FRI, 1));
		System.out.println(nAfter.apply(MyDate.Weekday.FRI, -1));
	}

}