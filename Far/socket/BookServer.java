package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

public class BookServer {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ServerSocket server = new ServerSocket(12345);
		while (true) {
			try (Socket s = server.accept();
					ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());) {
				BookStore obj = new BookStore();
				String bookName = (String) ois.readObject();
                  System.out.println(bookName);
				obj.load("books.data");
				if (obj.books.isEmpty())
					obj.addSomeBooks();

				Optional<Book> book = obj.books.entrySet().stream().filter(b1 -> b1.getKey().equals(bookName))
						.findFirst().map(b2 -> b2.getValue());

				if (book.isPresent()) {
					oos.writeBoolean(true);
					oos.writeObject(book.get());
					oos.flush();
					obj.save("books.data");
				} else {
					oos.writeBoolean(false);
					oos.flush();
				}

			}
		}
	}
}
