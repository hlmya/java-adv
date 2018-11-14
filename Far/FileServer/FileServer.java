package FileServer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileServer {
public static void main(String[] args) throws IOException {

	try(
			ServerSocket server=new ServerSocket(12345);
			Socket s= server.accept();
			Scanner in=new Scanner(s.getInputStream());
			PrintWriter pw=new PrintWriter(s.getOutputStream());
			){
		List<String> fileContents=new ArrayList<>();
		String singleFile="";
		while(in.hasNextLine()) {
			String fileName=in.nextLine();
			File file=new File(fileName);
			if(fileName.equals("done")) break;
			if(file.exists()) {
				Scanner inf=new Scanner(file);
				while(inf.hasNextLine())
					singleFile += inf.nextLine();

				singleFile += "\n";
				fileContents.add(fileName + "\n" + singleFile);
				singleFile="";
				inf.close();
			} else {
				fileContents.add(fileName + "\n" + "doesn't exist\n");
			}
		}//after the server received all the file, it will send the content back to the client
		fileContents.forEach(cont -> {
			pw.write(cont);
			pw.flush();
		});

	}
}
}
