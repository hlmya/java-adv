package tcpip;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import book.BookStore;

public class Server {
	public static void main(String[] args) throws Exception {
		BookStore bookStore = new BookStore();
		bookStore.addSomeBooks();

		final int PORT = 12345;
/* NOT SO GOOD
		while (true) {
			try (ServerSocket ss = new ServerSocket(PORT);) {
				handleOneClient(bookStore, ss);
			}
		}
*/
		try (ServerSocket ss = new ServerSocket(PORT);) {
			while (true) {
				handleOneClient(bookStore, ss);
			}
		}
	}

	private static void handleOneClient(BookStore bookStore, ServerSocket ss)
			throws IOException, ClassNotFoundException {
		try (Socket client = ss.accept();
				ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());) {
			String bookTitle = (String) objectInputStream.readObject();
			boolean hasBook = bookStore.hasBook(bookTitle);
			objectOutputStream.writeBoolean(hasBook);
			if (hasBook) {
				objectOutputStream.writeObject(bookStore.get(bookTitle));
			}
			objectOutputStream.flush();
		}
	}
}
