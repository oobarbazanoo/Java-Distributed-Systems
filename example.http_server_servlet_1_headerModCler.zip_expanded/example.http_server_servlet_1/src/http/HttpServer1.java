package http;

import java.net.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.io.*;

public class HttpServer1 
{
	private static final Executor exec = Executors.newFixedThreadPool(2);
	
    /**
     * WEB_ROOT is the directory where our HTML and other files reside. For this
     * package, WEB_ROOT is the "webroot" directory under the working directory.
     * The working directory is the location in the file system from where the
     * java command was invoked.
     */
	
    // shutdown command
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    // the shutdown command received
    private volatile boolean shutdown = false;

    public static void main(String[] args) 
    {
        HttpServer1 server = new HttpServer1();
        server.await();
    }

    public void await() 
    {
        ServerSocket serverSocket = null;
        int port = 7777;
        
        try 
        {serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));} 
        catch (IOException e) 
        {
            e.printStackTrace();
            System.exit(1);
        }

        // Loop waiting for a request
        System.out.println("Server is waiting for request at port: " + port);
        
        while (!shutdown) 
        {
            try 
            {
            	Socket socket = serverSocket.accept();
        		InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();
            	
            	Runnable task = () -> 
            	{
	                // create Request object and parse
	                Request request = new Request(input);
	                request.parse();
	
	                // create Response object
	                Response response = new Response(output);
	               
	                // check if this is a request for a servlet or a static resource
	                // a request for a servlet begins with "/servlet/"
	                Processor processor;
	
	                if (request.getURI().startsWith("/servlet/")) 
	                {processor = new ServletProcessor();} 
	                else
	                {processor = new StaticResourceProcessor();}
	                
	                processor.process(request, response);
	
	                //check if the previous URI is a shutdown command
	                shutdown = request.getURI().equals(SHUTDOWN_COMMAND);
	                
	                try 
	                {socket.close();} 
	                catch (IOException e) 
	                {e.printStackTrace();}
	                
	                try {
						Thread.sleep(15000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	};
            	
            	exec.execute(task);
            } 
            catch (Exception e) 
            {
            	e.printStackTrace(); 
            	System.exit(1);
            }
        }
    }
}

