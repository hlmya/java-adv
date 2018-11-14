package socketThread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
public static void main(String[] str) throws UnknownHostException, IOException {
	Socket s=new Socket("127.0.0.1",12345);
	Scanner in=new Scanner(s.getInputStream());
	PrintWriter pw=new PrintWriter(s.getOutputStream());
	pw.println(9);
	pw.flush();
	System.out.println(in.nextLine());
}
}
