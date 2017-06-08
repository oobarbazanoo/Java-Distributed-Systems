package FirstPractice.calculable;

import java.io.*;
import java.net.*;

public class ClientToSendCalculable
{
    public ClientToSendCalculable(int port)
    {
        try
        (
            Socket s = new Socket("localhost", port);
            ObjectOutputStream writer = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream reader = new ObjectInputStream(s.getInputStream());
        )
        {
            System.out.println("Connection was established!");

            Calculable addition = new Addition(5, 7);

            writer.writeObject(addition);
            double res = (Double)reader.readObject();

            System.out.println(res);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new ClientToSendCalculable(7777);
    }
}
