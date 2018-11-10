package a.b.c;

public enum Month {
	JAN("január", "ja"), FEB("február"), MAR("m"), APR("m"), MAY("m"), JUN("m"), JUL("m"), AUG("m"), SEP("m"), OCT(
			"m"), NOV("m"), DEC("m");

	String[] langCodes = { "hu", "en" };
	String[] names;

	Month(String... names) {
		this.names = names;
	}

	public String getHunName() {
		int langIdx = indexOf("hu");
		return names[langIdx];
	}

	private int indexOf(String lang) {
		for (int i = 0; i < langCodes.length; i++) {
			if (langCodes[i].equals(lang))
				return i;
		}
		return -1;
	}

	public Month getNextMonth() {
		Month[] values = Month.values();
		int newIdx = (this.ordinal() + 1) % values.length;
		return values[newIdx];
	}
}
