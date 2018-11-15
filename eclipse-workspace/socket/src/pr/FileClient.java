package pr;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class FileClient {

	public static void main(String[] args) throws IOException {
		
		try(
				// Create socket object
				Socket socket = new Socket("127.0.0.1", 12346);// IP: "127.0.0.1"; PORT: 12345
				
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				DataInputStream dis = new DataInputStream(socket.getInputStream());
			
		){
			//Send object/Data to server
			dos.writeUTF("textfile");
			dos.flush();
			
//			//Receive msg from server
			String result = dis.readUTF();
			System.out.println(result);
			socket.close();
		};
		
//		// Open the file
//		FileInputStream fstream = new FileInputStream("textfile.txt");
//		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
//
//		String strLine;
//
//		//Read File Line By Line
//		while ((strLine = br.readLine()) != null)   {
//		  // Print the content on the console
//		  System.out.println (strLine);
//		}
//
//		//Close the input stream
//		br.close();
	}

}
