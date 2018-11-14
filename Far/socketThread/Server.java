package socketThread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(12345);
		Socket s;
		while (true) {
			s = server.accept();
			new HandleClient(s).start();
		}
	}

	public static class HandleClient extends Thread {
		Socket s;
		Scanner in;
		PrintWriter pw;

		public HandleClient(Socket s) throws IOException {
			this.s = s;
		}

		public void run() {
			try {
				in = new Scanner(this.s.getInputStream());
				pw = new PrintWriter(this.s.getOutputStream());

				int num = Integer.parseInt(in.nextLine());
				pw.println(fibonacci(num));
				pw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public static int fibonacci(int n) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (n == 1)
				return 1;
			else if (n == 2)
				return 1;
			else
				return fibonacci(n - 1) + fibonacci(n - 2);
		}
	}

}
