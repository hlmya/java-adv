/*
 * Cave of Programming
 * https://www.youtube.com/watch?v=A0GHaVRlYAQ
*/
package pr;

import java.util.HashMap;
import java.util.Map;

public enum AnimalEnum {
	CAT("nino"), DOG("choco"), MOUSE("jerry");
	
	private String value;
	private static final Map<String,AnimalEnum> myMap = new HashMap<String,AnimalEnum>();
	static {
		for (AnimalEnum val : AnimalEnum.values()) { //AnimalEnum[] array = AnimalEnum.values();
			myMap.put(val.getValue(), val);
		}
	}
	
	//The structure of constructor ~ CAT("nino"): CAT is AnimalEnum, nino is String value 
	AnimalEnum(String value){
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public static AnimalEnum getEnumNameByEnumValue(String key) {
		return myMap.get(key);
	}
	
	public String toString1() {
		return "This animal is called: " + this.value;
	}
}
