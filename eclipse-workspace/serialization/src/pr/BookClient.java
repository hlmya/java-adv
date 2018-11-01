package pr;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class BookClient {
	public static void main(String[] args) throws Exception {
		try (
			// Create socket object
			Socket socket = new Socket("127.0.0.1", 2);
				
			//Create I/O streams for communicating with the server
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		) {
			//Send object/Data to server
			oos.writeObject("English");
			oos.flush();

			// Receive boolean book is present in the server?
			Boolean isBookPresent = (Boolean)ois.readObject();
			if (isBookPresent) {
				// Receive object/data from the server
				Book book = (Book)ois.readObject();
				System.out.println(book);
			} else {
				System.out.println("Book not found");
			}

		}
	}
}
