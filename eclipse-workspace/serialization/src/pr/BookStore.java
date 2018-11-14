package pr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BookStore {
	static Map<String, Book> bookMap = new HashMap<String, Book>();
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		addSomeBooks();
		saveBooks("book.bin");
		loadBooks("book.bin");
	}
	
	// Puts some predefined books into the map, to serve as initial data.
	public static void addSomeBooks() {
		bookMap.put("Math",new Book("Math", "Anh"));
		bookMap.put("English", new Book("English", "Hoa"));
	}

	// Writes the books into the given file using serialization.
	// Returns whether the save operation was successful.
	public static void saveBooks(String fileName) throws IOException {
		try(FileOutputStream fos = new FileOutputStream(fileName)) {
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(bookMap);// bookMap is an object so use writeObject()
			
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Save not successfully");
		}
		System.out.println("Save successfully");
	}

	// Loads the books from the given file.
	// Returns whether the load operation was successful.
	@SuppressWarnings("unchecked")
	public static void loadBooks(String fileName) throws IOException, ClassNotFoundException{
		try(FileInputStream fis = new FileInputStream(fileName)) {
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			bookMap = (Map<String, Book>) ois.readObject();
			
			ois.close();
			
			System.out.println(bookMap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//?
	public static Optional<Book> get(String title){
		return Optional.ofNullable(bookMap.get(title));
	}
}
