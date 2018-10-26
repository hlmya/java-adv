
import java.net.*;
import java.util.*;
import java.io.*;

public class Kliens
{
    public static void main(String[] args) throws Exception {
        // String MACHINE = "localhost";
        String MACHINE = "127.0.0.1";
        int PORT = 12345;

        try (
            Socket s = new Socket(MACHINE, PORT);
            Scanner sc = new Scanner(s.getInputStream(), "UTF-8");
            PrintWriter pw = new PrintWriter(s.getOutputStream());
        ) {
            pw.println(12345);
            pw.flush();

            int result = sc.nextInt();
            System.out.println(result);
        }
    }
}
