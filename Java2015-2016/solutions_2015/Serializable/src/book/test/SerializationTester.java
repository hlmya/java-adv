package book.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import book.Book;
import book.BookStore;

public class SerializationTester {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		testOneBook();
		testBookStore();
	}

	private static void testBookStore() throws IOException, ClassNotFoundException {
		String filename = "bookstore.ser";

		BookStore bookStore = new BookStore();
		bookStore.addSomeBooks();
		bookStore.saveBooks(filename);
		bookStore.loadBooks(filename);

		System.out.println(bookStore);
	}

	private static void testOneBook() throws IOException, FileNotFoundException, ClassNotFoundException {
		Book book = new Book("Title", "Author", 2015, 100);
		System.out.println(book);

		String filename = "book.ser";

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));) {
			oos.writeObject(book);
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));) {
			Book book2 = (Book) ois.readObject();
			System.out.println(book2);
		}
	}
}
