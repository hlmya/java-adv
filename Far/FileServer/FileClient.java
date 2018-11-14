package FileServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class FileClient {
	public static void main(String[] str) throws UnknownHostException, IOException {
		try(
				Socket s=new Socket("127.0.0.1", 12345);
				Scanner in=new Scanner(s.getInputStream());
				PrintWriter pw=new PrintWriter(s.getOutputStream());
				){
			pw.println("1.txt");
			pw.println("2.txt");
			pw.println("4.txt");
			pw.println("file.txt");
			pw.println("done");
			pw.flush();
			System.out.println("sent");
			while(in.hasNextLine())
			System.out.println(in.nextLine());
		}
	}
}
