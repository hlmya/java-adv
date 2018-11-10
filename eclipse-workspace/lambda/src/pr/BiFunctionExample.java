/* https://www.youtube.com/watch?v=FOJYM8q4X1k */
package pr;

import java.util.function.BiFunction;
import java.util.function.Function;

public class BiFunctionExample {

	public static void main(String[] args) {
		/*
		BiFunction<String, Integer, Integer> bifunction = (position,age) -> {
			if(position.equalsIgnoreCase("Manager") && age > 30) {
				return 120000;
			}else if(position.equalsIgnoreCase("Developer") && age > 25) {
				return 100000;
			}else {
				return 0;
			}
		}; */
		// shorter method -> create a method "getSalary"
		BiFunction<String, Integer, Integer> bifunction = (position,age) -> getSalary(position, age);
		
		System.out.println("Test bifunction1: " + bifunction.apply("Manager", 39)); //Test bifunction1: 120000
		System.out.println("Test bifunction2: " + bifunction.apply("Developer", 26)); //Test bifunction2: 100000
		
		Function<Integer, String> function = salary -> {
				if(salary <= 100000) {
					return "Band 4";
				}else if (salary >= 120000) {
					return "Band 5";
				}else {
					return "";
				}
		};
		
		// Example for andThen-> bifunction will apply and then consider function
		System.out.println("andthen: " + bifunction.andThen(function).apply("manager", 40)); // andthen: Band 5
	}

	/**
	 * @param position
	 * @param age
	 * @return
	 */
	private static Integer getSalary(String position, Integer age) {
		if(position.equalsIgnoreCase("Manager") && age > 30) {
			return 120000;
		}else if(position.equalsIgnoreCase("Developer") && age > 25) {
			return 100000;
		}else {
			return 0;
		}
	}

}
