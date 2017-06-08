package FirstPractice.consoleChat;

import java.io.*;
import java.net.*;

public class Client
{
    public Client(int portToConnect)
    {
        try
        {
            Socket s = new Socket("localhost", 7777);

            BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            String str = reader.readLine();
            while(str.startsWith("NAME"))
            {
                System.out.print("Give a desired name: ");
                String name = keyboard.readLine();
                writer.println(name);

                str = reader.readLine();
            }

            while(true)
            {
                System.out.print("I wrote: ");
                String message = keyboard.readLine();
                writer.println(message);
                System.out.println(reader.readLine().substring(8));
            }

        }
        catch(Exception ignoreIt)
        {}
    }


    public static void main(String[] args)
    {
        new Client(7777);
    }
}
