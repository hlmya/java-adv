package socket;

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
	Map<String,Book> books = new HashMap<>();
	public static void main(String[] args) {
		BookStore obj=new BookStore();
		obj.addSomeBooks();
		obj.save("books.data");
		BookStore store2 = new BookStore();
		store2.addSomeBooks();
		store2.save("books.data");
		store2.load("books.data");
		// Optional<Book> book = store2.get("HelloWorld");
		// System.out.println(book);
		// System.out.println(store2.get("HelloWorld").get().getYear());
		// obj.load("books.data");
	}
	// Puts some predefined books into the map, to serve as initial data.
	void addSomeBooks() {
		books.put("Hello", new Book("java","sid",2017,412));
		books.put("Foobar", new Book("java","dar",2017,412));
		books.put("BestSeller", new Book("java","duda",2017,412));
		books.put("HelloWorld", new Book("HelloWorld", "ia",2011,1234));
	}

	// Writes the books into the given file using serialization.
	public void save(String fileName){

		try(
				ObjectOutputStream fw=new ObjectOutputStream(new FileOutputStream(fileName))
		){
			fw.writeObject(books);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Loads the books from the given file.
	// Returns whether the load operation was successful.
	@SuppressWarnings("unchecked")
	public void load(String fileName){

		try(
				ObjectInputStream ois=new ObjectInputStream(new FileInputStream(fileName))
		){
			books = (Map)ois.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void saveNetwork(String name, Book book){
		books.put(name, book);
	}

	public Optional<Book> get(String title) {
		return Optional.ofNullable(books.get(title));
		}

}
