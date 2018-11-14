package pr;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SumNumbersClient {

	public static void main(String[] args) throws IOException {
//		method3();
//		method2();
		try(
				// Create socket object
				Socket socket = new Socket("127.0.0.1", 12345);// IP: "127.0.0.1"; PORT: 12345
				
				DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
//				DataInputStream dis = new DataInputStream(socket.getInputStream());
		){
//			Socket socket = new ; // Create and connect the socket
//			DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());

			// Send first message
			dOut.writeByte(1);
			dOut.writeUTF("This is the first type of message.");
			dOut.flush(); // Send off the data

			// Send the second message
			dOut.writeByte(2);
			dOut.writeUTF("This is the second type of message.");
			dOut.flush(); // Send off the data

			// Send the third message
			dOut.writeByte(3);
			dOut.writeUTF("This is the third type of message (Part 1).");
			dOut.writeUTF("This is the third type of message (Part 2).");
			dOut.flush(); // Send off the data
			// Send the exit message
			dOut.writeByte(-1);
			dOut.flush();
			System.out.println("sent");
			dOut.close();
		}
	}

	/**
	 * mya
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	private static void method3() throws IOException, UnknownHostException {
		try (
				// Create socket object
				Socket socket = new Socket("127.0.0.1", 12345);// IP: "127.0.0.1"; PORT: 12345
					
				//Create I/O streams for communicating with the server
				// Use DataOutputStream for int
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				DataInputStream dis = new DataInputStream(socket.getInputStream());
			) {
				//Send object/Data to server
				dos.writeInt(15);
				dos.writeInt(40);
				dos.flush();
				
				int result = dis.readInt();
				System.out.println(result);
				socket.close();
			}
	}

	/**
	 * Farhad
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	private static void method2() throws IOException, UnknownHostException {
		try(
				Socket s=new Socket("127.0.0.1", 12345);
				Scanner in=new Scanner(s.getInputStream());
				PrintWriter pw=new PrintWriter(s.getOutputStream());
				){
			pw.println(3);
			pw.println(4);
			pw.println(5);
			pw.println("done");
			pw.flush();
			System.out.println("sent");
			System.out.println("result: "+in.next());
		}
	}

}
