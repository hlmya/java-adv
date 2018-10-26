package books;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BookStore {
	Map<String, Book> titleToBook = new HashMap<>();

	public void addSomeBooks() {
		titleToBook.put("HelloWorld", new Book("HelloWorld", 1234));
		titleToBook.put("FooBar", new Book("FooBar", 2018));
	}

	public void save(ObjectOutputStream oos) throws IOException {
		oos.writeObject(titleToBook);
	}

	public void save(String filename) throws IOException {
		try (
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
		) {
//			titleToBook.entrySet().stream()
//				.map(Map.Entry::getValue)
////				.forEach(oos::writeObject);
//				.forEach(book -> {
//					try {
//						oos.writeObject(book);
//					} catch (IOException e) {
//
//					}
//				});

			save(oos);
		}
	}

	@SuppressWarnings("unchecked")
	public void load(String filename) throws ClassNotFoundException, IOException {
		try (
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
		) {
//			titleToBook = (Map)ois.readObject();
			load(ois);
		}
	}

	@SuppressWarnings("unchecked")
	public void load(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		titleToBook = (Map<String, Book>)ois.readObject();
	}

	public Optional<Book> get(String title) {
		return Optional.ofNullable(titleToBook.get(title));
	}

}
