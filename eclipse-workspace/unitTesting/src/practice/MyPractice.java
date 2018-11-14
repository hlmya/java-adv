package practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MyPractice {
	public enum WeekDay{
		Mon, Tue, Wed, Thu, Fri, Sat, Sun
	}
	
	public static void main(String[] args) throws IOException {
//		Map<String, String> dict = new HashMap<String, String>();
//		dict.put("good", "jo");
//		dict.put("morning", "reggelt");
//
//		String result = dictionary2(dict, "good a");
//		System.out.println(result);
		
		// prime test
		System.out.println(primeNumber(7).toString()); // cannot print value, why? cannot compare equal array as usual
		System.out.println(Arrays.toString(primeNumber(7))); // 
		
		Function<WeekDay, Supplier<WeekDay>> weeker = startingday -> new Supplier<WeekDay>() {
			int idx = startingday.ordinal();
			int len = WeekDay.values().length;
			
			@Override
			public WeekDay get() {
				idx++;
				return WeekDay.values()[idx%len];
			}
		};
		
		System.out.println("----------------");
//		Supplier<WeekDay> sunWeek = weeker.apply(WeekDay.Mon);
//		for (int i = 0; i < 3; i++) {
//			System.out.println(sunWeek.get());
//		}
		System.out.println(printWeekDay(WeekDay.Mon, 3));
		
		fileHandler("test.txt",2,3,4,5);
	}
	
	// 1
	public static int summer (int a, int b) {
		return IntStream.rangeClosed(a, b).sum();
	}
	
	// 2
	public static int shortSummer (int a, int b) {
		return (a+b)*(Math.abs(a-b)+1)/2;
	}
	
	// 3
	public static String dictionary (Map<String, String> dict, String textEn) {
		return Stream.of(textEn.split(" ")) 		
				.map(s -> dict.getOrDefault(s, "?"))	//get Value with key 's', if no value -> get default value '?'
				.collect(Collectors.joining(" "));		//combine elements in stream by delimiter " " -> dau phan cach
	}
	
	// 3 - longer
	public static String dictionary2 (Map<String, String> dict, String textEn) {
		return Stream.of(textEn.split(" "))
				.map(s -> {
					if (dict.containsKey(s)) {
						return dict.get(s);
					}else{
						return "?";
					}
				})
				.collect(Collectors.joining(" "));
	}
	
	//4
	public static int[] primeNumber (int n) {
		if(n<0) {
			throw new IllegalArgumentException("Negative number");
		}else {
			return IntStream.iterate(2, i->i+1)
					.filter(i -> IntStream.rangeClosed(2, (int) Math.sqrt(i)).noneMatch(num -> i%num == 0))
					.limit(n).toArray();
		}
	}
	
	//5
	/*
	public static List<String> printWeekDay(WeekDay day, int n){
		List<String> result = new ArrayList<>();
		
		Supplier<WeekDay> weekDay = getWeekDay().apply(day);
		for (int i = 0; i < n; i++) {
			result.add(weekDay.get()+"");
		}
		
		return result;
	}*/
	
	public static WeekDay printWeekDay(WeekDay day, int n) {
		WeekDay result = null;
		Supplier<WeekDay> weekDay = getWeekDay().apply(day);
		for (int i = 0; i < n; i++) {
			result = weekDay.get();
		}
		return result;
	}
	
	private static Function<WeekDay, Supplier<WeekDay>> getWeekDay(){
		Function<WeekDay, Supplier<WeekDay>> weeker = startingday -> new Supplier<WeekDay>() {
			int idx = startingday.ordinal();
			int len = WeekDay.values().length;
			
			@Override
			public WeekDay get() {
				idx++;
				return WeekDay.values()[idx%len];
			}
		};
		return weeker;
	}
	
	//7
//	fil("fdshajfdksa")
//	fil("fdshajfdksa", 12, 421, 352, 134, 12)
	public static void fileHandler(String fileName, int... args) throws IOException {
		int sumResult = IntStream.of(args)
//							.filter(s->s.matches("[0-9]"))
//							.map(Integer::parseInt)
//							.mapToInt(e->e)
							.sum();
		
		FileWriter fw=new FileWriter(new File(fileName));
		fw.write(sumResult+"");
		fw.flush();
		fw.close();
	}
	
	 
}
