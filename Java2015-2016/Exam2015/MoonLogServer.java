package advjava.exam;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MoonLogServer {
	public static void main(String[] args) throws IOException {
		int logPort = 12346;
		try (
			ServerSocket serverSocket = new ServerSocket(logPort);
		) {
			while (true) {
				try (
					Socket socket = serverSocket.accept();
					Scanner sc = new Scanner(socket.getInputStream());
				) {
					while (sc.hasNextLine()) {
						String line = sc.nextLine();
						System.out.println("logServer: " + line);
					}
				}
			}
		}
	}
}
