
import java.net.*;
import java.util.*;
import java.io.*;

class MsgObject2 implements Serializable
{
    int value;
    MsgObject2 ref;
}

public class MsgObject implements Serializable
{
    int data;
    String data2;
    List<Integer> data3;
    List<Integer> data4;
    MsgObject2 obj2;
}
