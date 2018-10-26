
import java.net.*;
import java.util.*;
import java.io.*;

public class ObjectClient
{
    public static void main(String[] args) throws Exception {
        String MACHINE = "127.0.0.1";
        int PORT = 12345;

        try (
            Socket s = new Socket(MACHINE, PORT);
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
        ) {
            MsgObject result = (MsgObject)ois.readObject();
            System.out.println(result.data);
            System.out.println(result.data2);
            System.out.println(result.data3);
            System.out.println(result.obj2.value);
            System.out.println(result.obj2.ref.value);

            System.out.println(result);
            System.out.println(result.obj2);
            System.out.println(result.obj2.ref);
        }
    }
}
