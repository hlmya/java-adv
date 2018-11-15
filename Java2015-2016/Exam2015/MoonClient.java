package advjava.exam;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class MoonClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		String filename = "moon.txt";
		
		Files.readAllLines(Paths.get(filename)).forEach(line -> {
			try {
				String[] lineParts = line.split(";");
				String phaseName = lineParts[0];
				int[] steps = Arrays.asList(lineParts[1].split(",")).stream().mapToInt(Integer::parseInt).toArray();
				int stepCount = Integer.parseInt(lineParts[2]);

				sendToServer(phaseName, steps, stepCount);
			} catch (Exception e) {
				// note: should not happen
				e.printStackTrace();
			}
		});
	}

	private static void sendToServer(String phaseName, int[] steps,
			int stepCount) throws IOException, ClassNotFoundException,
			UnknownHostException {
		int port = 12345;

		try (
			Socket socket = new Socket("localhost", port);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
		) {
			objectOutputStream.writeObject(phaseName);
			objectOutputStream.writeObject(steps);
			objectOutputStream.writeObject(stepCount);
			objectOutputStream.flush();
			
			@SuppressWarnings("unchecked")
			List<MoonPhase> phases = (List<MoonPhase>)objectInputStream.readObject();

			System.out.println(phases);
		}
	}
}
