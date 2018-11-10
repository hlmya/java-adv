package tcpip;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import book.Book;

public class Client {
	public static void main(String[] args) throws Exception {
		final int PORT = 12345;
		final String MACHINE = "127.0.0.1";

		String bookTitle = "t6";

		// final String MACHINE = "localhost";
		try (Socket server = new Socket(MACHINE, PORT);
				ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(server.getInputStream());) {
			oos.writeObject(bookTitle);
			oos.flush();

			boolean success = ois.readBoolean();
			if (success) {
				Book book = (Book) ois.readObject();
				System.out.println("Got: " + book);
			} else {
				System.out.println("Out of luck");
			}
		}
	}
}
