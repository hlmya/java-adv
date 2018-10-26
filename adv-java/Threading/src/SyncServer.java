import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SyncServer {
	public static void main(String[] args) throws Exception {
		try (ServerSocket ss = new ServerSocket(12345);) {
			while (true) {
				Socket s = ss.accept();
				Scanner sc = new Scanner(s.getInputStream());
				PrintWriter pw = new PrintWriter(s.getOutputStream());

				new Thread(() -> {
					try (s; sc; pw) {
						handleClient(pw);
					} catch (Exception e) {
					}

//					try {
//						s.close();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				}).start();
			}

		}
	}

	private static void handleClient(PrintWriter pw) throws InterruptedException {
		pw.println("Hello");
		pw.flush();

		Thread.sleep(10000);

		pw.println("world");
		pw.flush();
	}
}
