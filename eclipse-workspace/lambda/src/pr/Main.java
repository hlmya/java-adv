package pr;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
	
	enum WeekDay {
		Mon, Tue, Wed, Thu, Fri, Sat, Sun
		
//		private String name;
//		
//		String getName(String name) {
//			return this.name;
//		}
	}
	
	public static void main(String[] args) {
		//Supplier: () -> WeekDay
		int[] idx = {0};
//		Supplier<WeekDay> weekLam = () -> WeekDay.values()[idx[0]++];
//		Supplier<WeekDay> weekLam = () -> WeekDay.values()[++idx[0]];
		Supplier<WeekDay> weekLam = () -> WeekDay.values()[(idx[0]++) % WeekDay.values().length];
	
		Supplier<WeekDay> weekLam2 = new Supplier<WeekDay>(){
			int idx = -1;
			int len = WeekDay.values().length;
			@Override
			public WeekDay get() {
				idx++;
				return WeekDay.values()[idx%len];
			}
		};
		
		System.out.println(weekLam.get());//Mon
		System.out.println(weekLam.get());//Tue
		
		for (int i = 0; i < 10; i++) {
			System.out.println(weekLam.get());
		}
		//===============
		
		System.out.println("===============");
		Function<WeekDay, Supplier<WeekDay>> weeker = startingDay -> new Supplier<WeekDay>() {
			
			int idx = startingDay.ordinal()-1;
			int len = WeekDay.values().length;
			@Override
			public WeekDay get() {
				idx++;
				return WeekDay.values()[idx % len];
			}
			
		};
		
		Supplier<WeekDay> sunWeek = weeker.apply(WeekDay.Tue);
		for (int i = 0; i < 10; i++) {
			System.out.println(sunWeek.get());
		}
		
//		Supplier<WeekDay> weekLam3 = new Supplier<WeekDay>(){
//			int idx = 0;
//			int len = WeekDay.values().length;
//			@Override
//			public WeekDay get() {
//				idx++;
//				return WeekDay.values()[idx%len];
//			}
//		};
		//===============
		System.out.println("===============");
		
//		BiFunction<WeekDay, WeekDay, Boolean> f4 = new BiFunction<Main.WeekDay, Main.WeekDay, Boolean>() {
//			
//			
//			public Boolean apply(WeekDay t, WeekDay u) {
//				
//				return t.compareTo(u) < 0;
//			}
//		};
		
		BiFunction<WeekDay, WeekDay, Boolean> f4 = (d1, d2) -> d1.compareTo(d2) < 0;
		
		System.out.println("d1 is earlier?" + f4.apply(WeekDay.Mon,WeekDay.Fri));
				

		//================
		
		//sortbychar
		String[] tests = {
				"dsfgsdfg","fgfgfg"
		};
		Arrays.sort(tests);
		System.out.println();

	}
}
