package book.network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

import books.Book;
import books.BookStore;

public class BookServer {
	public static void main(String[] args) throws Exception {
		BookStore store = new BookStore();
		store.addSomeBooks();

		try (
			ServerSocket ss = new ServerSocket(12345);
			Socket s = ss.accept();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		) {
			String bookName = (String)ois.readObject();

			Optional<Book> answer = store.get(bookName);
			oos.writeObject(answer.get());
			oos.flush();
		}
	}
}
