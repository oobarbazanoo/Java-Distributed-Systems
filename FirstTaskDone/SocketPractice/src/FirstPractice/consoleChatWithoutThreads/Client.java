package FirstPractice.consoleChatWithoutThreads;

import java.io.*;
import java.net.Socket;

public class Client
{
    public Client(int portToConnect)
    {
        try
        (
                Socket s = new Socket("localhost", 7777);

                BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);

                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        )
        {
            System.out.print("Give a desired name: ");
            String name = keyboard.readLine();
            writer.println(name);

            System.out.println("Wait while interlocutor picks up the name.");
            String serverName = reader.readLine();
            System.out.println("Your interlocutor picked up " + serverName + " name.");

            System.out.println("----Dialog was started!----");

            while(true)
            {
                System.out.print("I: ");
                String messageToSend = keyboard.readLine();
                writer.println(messageToSend);
                if(messageToSend.equals("Bye"))
                {break;}


                String gotMessage = reader.readLine();
                if(gotMessage.equals("Bye"))
                {break;}

                System.out.println(serverName + ": " + gotMessage);
            }

            System.out.println("----The dialog is over!----");

        }
        catch(Exception ignoreIt)
        {}
    }


    public static void main(String[] args)
    {
        new Client(7777);
    }
}
