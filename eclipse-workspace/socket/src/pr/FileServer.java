package pr;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileServer {

	public static void main(String[] args) throws IOException {
		
		try(
			ServerSocket serverSocket = new ServerSocket(12346);
			// Wait for the Client Resquest
			Socket socket = serverSocket.accept();
			// Create I/O streams for communicating with the client
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		){
			List<String> content = new ArrayList<String>();
			// Receive msg from client
			String fileName = dis.readUTF();
			
			// Open the file
			FileInputStream fstream = new FileInputStream(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	
			String strLine;
	
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
			  // Print the content on the console
			  //System.out.println (strLine);
				content.add(strLine);
			}
	
			//Close the input stream
			br.close();
			
			// convert ArrayList to String and send to client
			dos.writeUTF(content.stream().map(Object::toString).collect(Collectors.joining("\n")));

			//Another way to send but not good in this case
//			content.forEach(cont -> {
//				try {
//					dos.writeUTF(cont);
//					dos.flush();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			});
		}

	}

}
