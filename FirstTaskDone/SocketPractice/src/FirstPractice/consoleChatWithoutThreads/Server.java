package FirstPractice.consoleChatWithoutThreads;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private BufferedReader reader;
    private PrintWriter writer;
    private Socket s;

    public Server(int port)
    {
        try
        (
            ServerSocket ss = new ServerSocket(port);
            Socket s = ss.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        )
        {
            System.out.println("Wait while interlocutor picks up the name.");
            String nameOfTheClient = reader.readLine();
            System.out.println("Your interlocutor picked up " + nameOfTheClient + " name.");

            System.out.print("Give a desired name: ");
            String name = keyboard.readLine();
            writer.println(name);

            System.out.println("----Dialog was started!----");

            while(true)
            {
                String gotMessage = reader.readLine();
                System.out.println(nameOfTheClient + ": " + gotMessage);

                if(gotMessage.equals("Bye"))
                {break;}


                System.out.print("I: ");
                String messageToSend = keyboard.readLine();
                writer.println(messageToSend);
            }

            System.out.println("----The dialog is over!----");
        }
        catch(Exception ignoreIt)
        {}
    }

    public static void main(String[] args)
    {new Server(7777);}
}
