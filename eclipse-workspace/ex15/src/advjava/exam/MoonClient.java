package advjava.exam;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class MoonClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		String fileName = "moon.txt";
		//Incorrect in this case java.lang.ArrayIndexOutOfBoundsException
//		List<String> content = new ArrayList<String>();
//		Scanner input = new Scanner(new File(fileName));
//        //Read file
//        while (input.hasNext()) {
//            String word = input.next();
//            content.add(word);
//        };
//        
//        content.forEach(line -> {
//        	try {
//	        	String[] lineParts = line.split(";");
//	        	System.out.println(Arrays.asList(lineParts)); //test
//	        	String phaseName = lineParts[0];
////	        	int[] steps = Stream.of(lineParts[1].split(",")).mapToInt(Integer::parseInt).toArray();
//	        	int[] steps = Arrays.asList(lineParts[1].split(",")).stream().mapToInt(Integer::parseInt).toArray();
//	        	System.out.println(Arrays.asList(steps)); //test
//	        	int stepCount = Integer.parseInt(lineParts[2]);
//	        	
//	        	sendToServer(phaseName,steps,stepCount);
//			} catch (Exception e) {
//				// note: should not happen
//				e.printStackTrace();
//			}
//        });
		
		Files.readAllLines(Paths.get(fileName)).forEach(line -> {
			try {
				String[] lineParts = line.split(";");
				System.out.println(Arrays.asList(lineParts)); //test
				String phaseName = lineParts[0];
				System.out.println(phaseName);// test
				int[] steps = Arrays.asList(lineParts[1].split(",")).stream().mapToInt(Integer::parseInt).toArray();
				System.out.println(Arrays.asList(steps));// test
				int stepCount = Integer.parseInt(lineParts[2]);

				sendToServer(phaseName, steps, stepCount);
			} catch (Exception e) {
				// note: should not happen
				e.printStackTrace();
			}
		});

	}

	private static void sendToServer(String phaseName, int[] steps, int stepCount) throws UnknownHostException, IOException, ClassNotFoundException {
		try (
				// Create socket object
				Socket socket = new Socket("localhost", 12345);// IP: "127.0.0.1"; PORT: 2
					
				//Create I/O streams for communicating with the server
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			) {
			// Send to server
			oos.writeObject(phaseName);
			oos.writeObject(steps);
			oos.writeObject(stepCount);
			oos.flush();
			
			// Receive from server and printout
			@SuppressWarnings("unchecked")
			List<MoonPhase> phases = (List<MoonPhase>) ois.readObject();
			System.out.println(phases);
		}
		
	}

}
