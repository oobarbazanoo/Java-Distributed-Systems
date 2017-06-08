package FirstPractice.consoleChat;

import java.io.*;
import java.net.*;
import java.util.HashSet;

public class Server
{
    private final HashSet<ClientServerSide> clients = new HashSet<ClientServerSide>();

    public Server(int port)
    {
        try(ServerSocket ss = new ServerSocket(port))
        {
            while(true)
            {
                Socket s = ss.accept();
                new ClientServerSide(s).start();
                System.out.println("got a client!");
            }
        }
        catch(Exception ignoreIt)
        {}
    }

    private class ClientServerSide extends Thread
    {
        private BufferedReader reader;
        private PrintWriter writer;
        private Socket s;

        private String name;

       public ClientServerSide(Socket s)
       {
           this.s = s;

           try
           {
               this.reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
               this.writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
           }
           catch(Exception ignoreIt)
           {}
       }

       public void run()
       {
           this.name = getNameFromClient();

           synchronized(Server.this.clients)
           {Server.this.clients.add(this);}

           try
           {
               while(true)
               {
                   String messageFromClient = reader.readLine();
                   broadCastMessage(messageFromClient);
               }
           }
           catch(Exception ignoreIt)
           {}
           finally
           {
               try
               {
                   reader.close();
                   writer.close();
                   this.s.close();
               }
               catch(Exception ignoreIt)
               {}

               synchronized(Server.this.clients)
               {Server.this.clients.remove(this);}
           }
       }

       private String getNameFromClient()
       {
           String name = null;

           try
           {
               while(!appropriate(name))
               {
                   writer.println("NAME");
                   name = reader.readLine();
                   System.out.println(name);
               }

               writer.println("OKNAME");
           }
           catch(Exception ignoreIt)
           {}

           return name;
       }

        private boolean appropriate(String name)
        {
           if(name == null)
           {return false;}

            synchronized(Server.this.clients)
            {
                for(ClientServerSide cc : Server.this.clients)
                {
                    if(cc.getClientServerSideName().equals(name))
                    {return false;}
                }
            }

            return true;
        }

        public String getClientServerSideName()
        {return this.name;}

        public PrintWriter getClientServerSideWriter()
        {return this.writer;}

        private void broadCastMessage(String m)
        {
            synchronized(Server.this.clients)
            {
                for(ClientServerSide css : Server.this.clients)
                {
                    try
                    {
                        if(css != this)
                        {
                            PrintWriter pw = css.getClientServerSideWriter();
                            pw.println("MESSAGE" + getClientServerSideName() + ": " + m);
                        }
                    }
                    catch(Exception ignoreIt)
                    {}
                }
            }
        }
    }

    public static void main(String[] args)
    {new Server(7777);}
}
