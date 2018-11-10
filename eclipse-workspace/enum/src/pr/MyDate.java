/*
 * JavaAdv exercise - Enumerations
 * http://kitlei.web.elte.hu/segedanyagok/felev/2018-2019-osz/adv-java/adv-java-exercises.html
*/
package pr;

public class MyDate {

	enum Language {
		HU,EN,VIE
	}
	
	enum Month {
		JAN("SziaJAN","January","Thang Mot"), FEB("SziaFEB","February","Thang Hai");
		
		public String[] names;
		//Constructor
		Month(String...names){
			this.names = names; // store "SziaJAN","January","Thang Mot" into an array
		}
//		private Month(String[] names) {
//		private Month(int a, String... names) {
//		private Month(String huName, String... names) {
		public String getLanguageName(Language lang) {
			return names[lang.ordinal()]; // return value by index in the array:names // ordinal(): index
		}
	}
	public static void main(String[] args) {
		Month jan = Month.JAN;
		System.out.println("Jan in HU: " + jan.getLanguageName(Language.HU));
		System.out.println("Jan in EN: " + jan.getLanguageName(Language.EN));
		System.out.println("Jan in Vietnam: " + jan.getLanguageName(Language.VIE));
	}

}
