package pr;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientNumbersClient {

	public static void main(String[] args) {
		try (
				Socket s = new Socket("127.0.0.1", 12345);
				Scanner in = new Scanner(s.getInputStream());
				PrintWriter pw = new PrintWriter(s.getOutputStream());) {
				System.out.println(in.nextLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
