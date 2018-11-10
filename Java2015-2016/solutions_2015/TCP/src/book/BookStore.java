package book;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class BookStore {
	// private TreeMap<String, Book> books = new TreeMap<String, Book>();
	private Map<String, Book> books = new HashMap<>();
	// private Hashtable<String, Book> books = new Hashtable<String, Book>();

	public void addSomeBooks() {
		addBook(new Book("t1", "a1", 123, 234));
		addBook(new Book("t2", "a2", 223, 934));
		addBook(new Book("t3", "a3", 323, 834));
		addBook(new Book("t4", "a4", 423, 734));
		addBook(new Book("t5", "a5", 523, 634));
		addBook(new Book("t6", "a6", 623, 534));
	}

	private void addBook(Book book) {
		books.put(book.getTitle(), book);
	}

	public void saveBooks(String filename) throws IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));) {
			oos.writeObject(books);
		}
	}

	@SuppressWarnings("unchecked")
	public void loadBooks(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));) {
			books = (Map<String, Book>) ois.readObject();
		}
	}

	@Override
	public String toString() {
		return "BookStore [books=" + books + "]";
	}

	public boolean hasBook(String bookTitle) {
		return books.containsKey(bookTitle);
	}

	public Book get(String bookTitle) {
		return books.get(bookTitle);
	}

}
