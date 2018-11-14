package pr;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SumNumbersServer {

	public static void main(String[] args) throws IOException {
//		method3();
//		method2();
		
		try (
				ServerSocket serverSocket = new ServerSocket(12345);
				// Wait for the Client Resquest
				Socket socket = serverSocket.accept();
				// Create I/O streams for communicating with the client
				DataInputStream dIn = new DataInputStream(socket.getInputStream());
//				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			) {

//		Socket socket = ... // Set up receive socket
//				DataInputStream dIn = new DataInputStream(socket.getInputStream());

				boolean done = false;
				while(!done) {
				  byte messageType = dIn.readByte();// 1 or 2 or 3
				  
				  switch(messageType)
				  {
				  case 1: // Type A
				    System.out.println("Message A: " + dIn.readUTF());
				    break;
				  case 2: // Type B
				    System.out.println("Message B: " + dIn.readUTF());
				    break;
				  case 3: // Type C
				    System.out.println("Message C [1]: " + dIn.readUTF());
				    System.out.println("Message C [2]: " + dIn.readUTF());
				    break;
				  default:
				    done = true;
				  }
				}
				
				System.out.println("received");

				dIn.close();
			}
		
		/*server console:
		 * Message A: This is the first type of message.
			Message B: This is the second type of message.
			Message C [1]: This is the third type of message (Part 1).
			Message C [2]: This is the third type of message (Part 2).
			received
		 */

	}

	/**
	 * mya
	 * @throws IOException
	 */
	private static void method3() throws IOException {
		try (
				ServerSocket serverSocket = new ServerSocket(12345);
				// Wait for the Client Resquest
				Socket socket = serverSocket.accept();
				// Create I/O streams for communicating with the client
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			) {

				System.out.println("Receive client input");
				// Receive from client
				List<Integer> lists = new ArrayList<Integer>();
				lists.add(dis.readInt());
				lists.add(dis.readInt());
				
				// Calulate
				int sumVal = lists.stream().reduce(0, Integer::sum);
				
				//Send to Client
				dos.writeInt(sumVal);
				System.out.println("sent to client");
				dos.flush();
			}
	}

	/**
	 * Farhad
	 * @throws IOException
	 */
	private static void method2() throws IOException {
		try (
				ServerSocket server = new ServerSocket(12345);
				Socket s = server.accept();
				Scanner in = new Scanner(s.getInputStream());
				PrintWriter pw = new PrintWriter(s.getOutputStream());
			) {
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
