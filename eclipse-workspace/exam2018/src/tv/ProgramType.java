package tv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;


public enum ProgramType {
	NORMAL(h -> true), 
	AFTERNOON(h -> h >= 16 && h < 22), 
	LATE_NIGHT(h -> h >= 22 || h <=2), 
	SPECIAL(h -> h % 4 == 0);
	
	Predicate<Integer> startHourTest;
	
	ProgramType(Predicate<Integer> testHour){
		this.startHourTest  = testHour;
	}
	
	public Predicate<Integer> getStartHourTest() {
		return startHourTest;
	}
	public void setStartHourTest(Predicate<Integer> startHourTest) {
		this.startHourTest = startHourTest;
	}
	
	//The boolean is false if you are shifting to later hours, and true if you are shifting to earlier hours.
	public Optional<Integer> shift(int h, boolean b) {
		if(startHourTest.test(h)) {
			return Optional.of(h);
		}
		int newHour = h;
		if(b == true) {
			
			do{
				newHour --;
				if(startHourTest.test(newHour)) return Optional.of(newHour);
				
				if(!isHourDay(newHour)) return Optional.empty();
				
			}while(!startHourTest.test(newHour));
			
		}else if(b == false) {
			do{
				newHour ++;
				if(startHourTest.test(newHour)) return Optional.of(newHour);
				
				if(!isHourDay(newHour)) return Optional.empty();
				
			}while(!startHourTest.test(newHour));
		}
		
		return Optional.empty();
	}
	
	public static boolean isHourDay(int h) {
		if(h >= 0 && h <= 23) {
			return true;
		}
		return false;
	}
	
	static BiFunction<String, Boolean, Map> loader = (s, b) -> {
		String[] newlist = s.split(";");
		//WEEKDAY;HOUR;PROGRAMTYPE;NAME
		
		Weekday getWeek = Weekday.valueOf(newlist[0]);	
		int hour = Integer.parseInt(newlist[1]);
		ProgramType pt = ProgramType.valueOf(newlist[2]);
		String name = newlist[3];
		
		return null;
		
	};
}
