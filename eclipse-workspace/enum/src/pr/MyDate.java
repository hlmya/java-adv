package pr;

class C1{} // 

public class MyDate {
	class C2{} //
	
	String[] names = {"January"};
	
	public enum Month {
		JAN("január","January"), FEB("február","February")
		;
		//JAN(new String[] {"január","January"}...)
		//JAN("január","January")
		private String en;
		private String hu;
		
		Month(String hu, String en){
			this.en = en;
			this.hu = hu;
		}
//		Month(List<String> names){ //String[] names // String... names
//			
//		}
		
		public String getEnglishName() {
			return en;
		}
		
		public String getHunName() {
			return hu;
		}
		
		public String getXName(String x) {
			return x;
		}
	}
	
	public enum Weekday {
		MON("hétfő","Monday"), TUE("kedd","Tuesday")
		;
		
		private String en;
		private String hu;
		
		Weekday(String hu, String en){
			this.en = en;
			this.hu = hu;
		}
		
		public String getEnglishName() {
			return en;
		}
		
		public String getHunName() {
			return hu;
		}
		
		public String getXName(String x) {
			return x;
		}
		
		//
		public boolean isThisDay(Month month, int day) {
			
			return false;
		}
			
	}
	//should return day of week for today, use Date function
	public String getWeekDay() {
		return "";
	}

	public static void main(String[] args) {
		// Different instant
		new MyDate().new C2(); 
		C1 c1 = new C1();
		
//		public String getWeekDay() {
//			return "";
//		}
		System.out.println(Month.JAN.getEnglishName());
		System.out.println(Month.JAN.getHunName());
		
		System.out.println(Weekday.MON.getEnglishName());
		System.out.println(Weekday.MON.getHunName());
		
	}
	
	

}
