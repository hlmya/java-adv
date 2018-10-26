package books;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class BookClient {
	public static void main(String[] args) throws Exception {
		try (
			Socket socket = new Socket("localhost", 12345);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		) {
			oos.writeObject("FooBar2");
			oos.flush();

			Boolean isBookPresent = (Boolean)ois.readObject();
			if (isBookPresent) {
				Book book = (Book)ois.readObject();
				System.out.println(book);
			} else {
				System.out.println("Book not found");
			}

		}
	}
}
