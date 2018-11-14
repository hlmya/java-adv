package countServer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountServer {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(12345);
		List<Socket> clients=new ArrayList<>();
		while (true) {
			try (   Socket s = server.accept();
					Scanner in = new Scanner(s.getInputStream());
					PrintWriter pw = new PrintWriter(s.getOutputStream());) {
				clients.add(s);
				pw.write(clients.size()+" client(s) connected so far");
				pw.flush();
			}
		}
	}
}
