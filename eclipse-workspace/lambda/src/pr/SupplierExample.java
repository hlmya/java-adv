/* https://www.youtube.com/watch?v=rPDdtsNqu_w */
 
package pr;

import java.util.function.Supplier;

public class SupplierExample {

	public static void main(String[] args) {
		// Simple example
		Supplier<String> name1 = new Supplier<String>() {
			@Override
			public String get() {
				return new String("Hello");
			}	
		};
		System.out.println(name1.get());
		
		Supplier<String> name2 = () -> new String("Hello name 2");
		System.out.println(name2.get());
		
		Supplier<String> name3 = () -> "Hello name 3";
		System.out.println(name3.get());
		
		// Example Supplier with class
		Supplier<User> userSupplier1 = () -> new User("AN",1);
		printUser(userSupplier1);
		
		Supplier<User> userSupplier2 = () -> createUser();
		printUser(userSupplier2);
	}
	public static void printUser(Supplier<User> userSupplier) {
		System.out.println(userSupplier.get());
	}
	
	public static User createUser() {
		return new User("NHI",2);
	}
	
}

class User{
	private String name;
	private int id;
	
	User(String name, int id){
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + "]";
	}
	
}
