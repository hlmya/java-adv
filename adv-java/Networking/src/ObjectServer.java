
import java.net.*;
import java.util.*;
import java.io.*;

public class ObjectServer
{
    public static void main(String[] args) throws Exception {
        int PORT = 12345;

        try (
            ServerSocket ss = new ServerSocket(PORT);
            Socket s = ss.accept();
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
        ) {
            MsgObject msg = new MsgObject();
            msg.data = 43235;
            msg.data2 = "fgdshgds";
            msg.data3 = List.of(432,72,4326);
            msg.obj2 = new MsgObject2();
            msg.obj2.value = 352;

            MsgObject2 other = new MsgObject2();
            other.value =12345;
            msg.obj2.ref = other;
            other.ref = msg.obj2;

            oos.writeObject(msg);
            oos.flush();

            System.out.println(msg);
            System.out.println(msg.obj2);
            System.out.println(msg.obj2.ref);
        }

    }
}
