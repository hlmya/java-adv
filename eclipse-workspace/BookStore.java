package seria;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class BookStore {
	public static Map<String, Book> listBooks;
	
	public static void main(String[] args) {
		addSomeBooks();
		saveBooks("Java");
		saveBooks("JavaAdv");
	}
	// Puts some predefined books into the map, to serve as initial data.
	public static void addSomeBooks() {
		listBooks = new HashMap<>();

		Book book = new Book();
		book.title = "Java";
		book.author = "James";
		book.year = 2000;
		book.pages = 100;
		listBooks.put(book.title, book);
		
		Book book1 = new Book();
		book1.title = "JavaAdv";
		book1.author = "James";
		book1.year = 2018;
		book1.pages = 108;
		listBooks.put(book1.title, book1);
	}

	// Writes the books into the given file using serialization.
	// Returns whether the save operation was successful.
	public static boolean saveBooks(String fileName) {
		try {
			FileOutputStream fileOut = new FileOutputStream("Books.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
			out.writeObject(listBooks);
			
			out.close();
			fileOut.close();
			
			System.out.println("save successfully");
			return true;
		} catch (IOException i) {
			i.printStackTrace();
			return false;
		}	
	}

	// Loads the books from the given file.
	// Returns whether the load operation was successful.
	@SuppressWarnings("unchecked")
	boolean loadBooks(String fileName) {
		//Book b = null;
		try {
			FileInputStream fileIn = new FileInputStream("Book.ser");
			ObjectInputStream in =  new ObjectInputStream(fileIn);
			listBooks = (Map<String, Book>) in.readObject();
			in.close();
			fileIn.close();
			return true;
		} catch (Exception e) {
			System.out.println("Employee class not found");
	         e.printStackTrace();
	         return false;
		}
	}
	
}
