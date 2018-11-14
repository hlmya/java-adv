package socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumberServer {
	public static void main(String[] args) throws IOException {

		try (ServerSocket server = new ServerSocket(12345);
				Socket s = server.accept();
				Scanner in = new Scanner(s.getInputStream());
				PrintWriter pw = new PrintWriter(s.getOutputStream());) {
			List<Integer> list = new ArrayList<>();
			while (in.hasNextLine()) {
				String input = in.nextLine();
				if (input.equals("done")) {
					break;
				} else
					list.add(Integer.parseInt(input));
			}
			pw.println(list.stream().reduce(0, (x, y) -> x + y));
			pw.flush();
		}
	}
}
