package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class BookClient {
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		String MACHINE = "127.0.0.1";
		int PORT = 12345;
		try (
				Socket client = new Socket(MACHINE, PORT);
				ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
				) {
			oos.writeObject("HelloWorld");
			oos.flush();
			Boolean bt = ois.readBoolean();
			if (bt) {
				System.out.println("Requested book: " + (Book) ois.readObject());
			} else
				System.out.println("The book not found");
		}

	}

}
