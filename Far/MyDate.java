
public class MyDate {

	public enum Month {
		JAN("january", "január"), FEB("february", "február"), MAR("march", "március"), APR("april", "április"),
		MAY("may", "lehet"), JUN("june", "június"), JUL("july", "július"), AUG("august", "augusztus"),
		SEP("september", "szeptember"), OCT("october", "október"), NOV("november", "november"),
		DEC("december", "december");

		String langCodes[] = { "en", "hu" };
		String[] names;

		Month(String... names) {
			this.names = names;
		}

		String getHunNames() {
			int indexOf = indexOf("hu");
			return names[indexOf];
		}

		String getEngNames() {
			int indexOf = indexOf("en");
			return names[indexOf];
		}

		Month nextMonth() {
			int index = (this.ordinal() + 1) % Month.values().length ;
			return Month.values()[index];
		}
		private int indexOf(String code) {
			for (int i = 0; i < langCodes.length; i++) {
				if (code.equals(langCodes[i]))
					return i;
			}
			return -1;
		}
	}

	public enum Weekday {
		MON("monday", "hétfő"), TUE("tuesday", "kedd"), WED("wednesday", "szerda"), THUR("thursday", "csütörtök"),
		FRI("friday", "péntek"), SAT("saturday", "szombat"), SUN("sunday", "vasárnap");

		String langCodes[] = { "en", "hu" };
		String[] names;

		Weekday(String... names) {
			this.names = names;
		}

		String getHunNames() {
			int indexOf = indexOf("hu");
			return names[indexOf];
		}

		String getEngNames() {
			int indexOf = indexOf("en");
			return names[indexOf];
		}

		Weekday nextDay() {
			int index = (this.ordinal() + 1) % Weekday.values().length ;
			return Weekday.values()[index];
		}
		
		private int indexOf(String code) {
			for (int i = 0; i < langCodes.length; i++) {
				if (code.equals(langCodes[i]))
					return i;
			}
			return -1;
		}
	}
	
	public static void main(String[] str) {
		System.out.println(Month.JAN.getEngNames());
		System.out.println(Month.AUG.getHunNames());
		System.out.println(Month.FEB.nextMonth());
		System.out.println(Month.APR.nextMonth().nextMonth().nextMonth().getHunNames());
		
		System.out.println(Weekday.MON.ordinal());
		System.out.println(Weekday.FRI.nextDay().nextDay().nextDay().nextDay().nextDay().nextDay().nextDay().getEngNames());
	}
}
