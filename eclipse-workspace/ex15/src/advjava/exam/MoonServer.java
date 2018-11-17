package advjava.exam;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoonServer {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
	
			try (
					// Open the server socket
					ServerSocket serverSocket = new ServerSocket(12345);
				) {
					while(true) {
						try(
								// Wait for the Client Request
								Socket socket = serverSocket.accept();
									
								// Create I/O streams for communicating with the client
								ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
								ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
						){
							handleReceivedMsg(ois, oos);
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
			}
	}

	private static void handleReceivedMsg(ObjectInputStream ois, ObjectOutputStream oos) throws ClassNotFoundException, IOException {
		
		String phaseName = (String) ois.readObject();
		int[] steps = (int[]) ois.readObject();
		int stepCount = (int) ois.readObject();
		
		System.out.println(phaseName);// test
		System.out.println(Arrays.asList(steps)); //test
		System.out.println(stepCount);// test
		
		Optional<MoonPhase> phase = MoonPhase.getPhase(phaseName); //to get MoonPhase -> phase.get()
		List<MoonPhase> answer = new ArrayList<MoonPhase>();
		
		if(phase.isPresent()) {
			System.out.println("yes");
			Supplier<MoonPhase> supplierAfterMoon = phase.get().getSupplier(steps);
			Stream.generate(supplierAfterMoon).limit(stepCount).collect(Collectors.toList());
		}
		// Send answer to Client
		oos.writeObject(answer);
		oos.flush();
	}

}
