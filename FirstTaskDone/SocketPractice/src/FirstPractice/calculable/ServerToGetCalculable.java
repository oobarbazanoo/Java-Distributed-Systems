package FirstPractice.calculable;

import java.io.*;
import java.net.*;

public class ServerToGetCalculable
{
    public ServerToGetCalculable(int port)
    {
        while(true)
        {
            try
                    (
                            ServerSocket ss = new ServerSocket(port);
                            Socket s = ss.accept();
                            ObjectOutputStream writer = new ObjectOutputStream(s.getOutputStream());
                            ObjectInputStream reader = new ObjectInputStream(s.getInputStream());
                    )
            {
                System.out.println("Got a client!");

                Calculable calcObj = (Calculable) reader.readObject();
                double res = calcObj.calc();

                writer.writeObject(res);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {new ServerToGetCalculable(7777);}
}
