package books;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

public class BookServer {
	public static void main(String[] args) throws Exception {
		BookStore store = new BookStore();
		store.load("save.data");

		try (
			ServerSocket serverSocket = new ServerSocket(12345);
			Socket socket = serverSocket.accept();
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		) {
			String bookName = (String)ois.readObject();

			Optional<Book> maybeBook = store.get(bookName);
			oos.writeObject(maybeBook.isPresent());
			if (maybeBook.isPresent()) {
				oos.writeObject(maybeBook.get());
			}
			oos.flush();
		}
	}
}
