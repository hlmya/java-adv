package socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NumberClient {
public static void main(String[] str) throws UnknownHostException, IOException {
	try(
			Socket s=new Socket("127.0.0.1", 12345);
			Scanner in=new Scanner(s.getInputStream());
			PrintWriter pw=new PrintWriter(s.getOutputStream());
			){
		pw.println(3);
		pw.println(4);
		pw.println(44);
		pw.println("done");
		pw.flush();
		System.out.println("sent");
		System.out.println("result: "+in.next());
	}
}
}
