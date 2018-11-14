package socketThread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerChat {
	static List<Socket> users;
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(12345);
		Socket s;
		users=new ArrayList<>();
		while (true) {
			s = server.accept();
			users.add(s);
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
                while(in.hasNextLine()) {
                	String message=in.nextLine();
                	users.forEach( s -> {
                		try {
                			pw=new PrintWriter(s.getOutputStream());
                			pw.println(s.getRemoteSocketAddress()+": sent -> "+message);
                			pw.flush();
                		}catch(IOException e) {
                			e.printStackTrace();
                		}
                	});
                }

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
