package book.network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Optional;

import books.Book;

public class BookClient {
	public static void main(String[] args) throws Exception {
		try (
			Socket s = new Socket("localhost", 12345);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		) {
			oos.writeObject("FooBar");
			oos.flush();

			@SuppressWarnings("unchecked")
			Book answer = (Book)ois.readObject();

			System.out.println(answer);
		}
	}
}
