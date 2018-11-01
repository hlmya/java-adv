package pr;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

public class BookServer {
	public static void main(String[] args) throws Exception {
		BookStore store = new BookStore();
		store.loadBooks("book.bin");

		try (
//			InetAddress addr = InetAddress.getByName("127.0.0.1");
			// Open the server socket
			ServerSocket serverSocket = new ServerSocket(2, 0, InetAddress.getByName("127.0.0.1"));
			
			// Wait for the Client Resquest
			//Accept is executed within an infinite loop, return a new socket for a new channel
			//It blocks until connection is made
			Socket socket = serverSocket.accept();
				
			// Create I/O streams for communicating with the client
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		) {
			// Receive from client
			String bookName = (String)ois.readObject();

			// Check request in data or not
			Optional<Book> maybeBook = store.get(bookName);
			
			// Send to Client
			oos.writeObject(maybeBook.isPresent());
			if (maybeBook.isPresent()) {
				oos.writeObject(maybeBook.get());
			}
			oos.flush();
		}
	}
}
