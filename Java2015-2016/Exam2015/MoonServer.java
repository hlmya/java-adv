package advjava.exam;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SocketHandler;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoonServer {
	static Logger logger = Logger.getLogger("MoonLogger");
	
	public static void main(String[] args) throws IOException {
		String logFilename = "server.log";
		int logPort = 12346;

		logger.setUseParentHandlers(false);
		logger.addHandler(new FileHandler(logFilename));
		logger.addHandler(new SocketHandler("localhost", logPort));
		
		int port = 12345;
		try (
			ServerSocket serverSocket = new ServerSocket(port);
		) {
			while (true) {
				try (
					Socket socket = serverSocket.accept();
					ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				) {
					handleSocket(objectInputStream, objectOutputStream);
				} catch (Exception e) {
					// note: this is not supposed to happen
				}
			}
		}
	}

	private static void handleSocket(ObjectInputStream objectInputStream,
			ObjectOutputStream objectOutputStream) throws ClassNotFoundException, IOException {
		String phaseName = (String)objectInputStream.readObject();
		int[] steps = (int[])objectInputStream.readObject();
		int stepCount = (Integer)objectInputStream.readObject();

		Optional<MoonPhase> phase = MoonPhase.getPhase(phaseName);
		List<MoonPhase> answer;
		if (phase.isPresent()) {
			Supplier<MoonPhase> supplier = phase.get().getSupplier(steps);
			answer = Stream.generate(supplier).limit(stepCount).collect(Collectors.toList());
		} else {
			answer = new ArrayList<>();
		}

		logger.info(answer.toString());
		
		objectOutputStream.writeObject(answer);
		objectOutputStream.flush();
	}
}
