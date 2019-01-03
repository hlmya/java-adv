package tv;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

enum Weekday {
	MON, TUE, WED, THU, FRI, SAT, SUN
}
public enum ProgramType {
	NORMAL(h -> true), 
	AFTERNOON(h -> h >= 16 && h < 22 ), 
	LATE_NIGHT(h -> h >= 22 || h <= 2), 
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
	public Optional<Integer> shift(int h, boolean shifting) {
		int newH;
		do {
			if(shifting) {
				newH = h + 1;
				System.out.println("increase:" + newH);
			}else {
				newH = h - 1;
				System.out.println("decrease: " + newH);
			}
			
			
			if(newH == 24) {
				newH = 0;
			}else if(newH == -1) {
				newH = 23;
			}
			System.out.println("new hour shift " + newH);
			if(startHourTest.test(newH)) {
				return Optional.of(newH);
			}
		}while(newH != h);
		
		return Optional.empty();
	}
	//WEEKDAY;HOUR;PROGRAMTYPE;NAME -> FRI;15;SPECIAL;Advanced Java practical exam
	static BiFunction<Stream<String>, Boolean, Map<Map<Weekday, Integer>,Map<ProgramType, String>>> loader = (stream,b) -> {
		Map<Weekday, Integer> key = new HashMap<>();
		Map<ProgramType, String> value = new HashMap<>();
		Map<Map<Weekday, Integer>,Map<ProgramType, String>> programs = new HashMap<>();
		
		stream.forEach(string -> {
			String[] parts = string.split(";");
			int hour = Integer.parseInt(parts[1]);
			ProgramType pt = ProgramType.valueOf(parts[2]);
			
			// check hour + setup key
			if(pt.startHourTest.test(hour)) {
				key.put(Weekday.valueOf(parts[0]), hour);
			}else {
				
				Optional<Integer> shiftHour = pt.shift(hour,b);
				if(shiftHour.isPresent()) {
					key.put(Weekday.valueOf(parts[0]), shiftHour.get());
				}
			}
			
			// value of map
			value.put(pt, parts[3]);
			
		});
		programs.put(key, value);
		return programs;
	};

}
