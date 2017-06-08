import javax.servlet.Servlet;
import javax.servlet.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import http.Request;

public class WebNotesServlet implements Servlet 
{
	private static List<Note> notes = new ArrayList<Note>();;
	
    public void init(ServletConfig config) throws ServletException
    {System.out.println("---from Servlet init---");}

    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException 
    {
        System.out.println("---from webNotes service---");
        
        if(((Request)request).isPost())
        {
        	String textOfANewNote = request.getParameter("aNote");
        	Note noteToAdd = new Note(textOfANewNote);
        	notes.add(noteToAdd);
        	
        	showOrdinaryPage(response);
        }
        else
        {
        	showOrdinaryPage(response);
        }
    }

    private void showOrdinaryPage(ServletResponse response)
    {
    	try (PrintWriter out = response.getWriter()) 
        {
        	writeHeadTo(out);
            writePageTo(out);
        }
        catch(Exception e)
        {e.printStackTrace();}
	}

    private void writeHeadTo(PrintWriter out)
    {
    	  String HTTP_header1 = "HTTP/1.1 200 OK";
	      out.println(HTTP_header1);
	      String HTTP_header2 = "Content-Type: text/html\r\n";
	      out.println(HTTP_header2);
	}

	private void writePageTo(PrintWriter out)
    {
    	StringBuilder contentBuilder = new StringBuilder();
    	
    	try(BufferedReader in = new BufferedReader(new FileReader("index.html"));)
    	{
    	    String str;
    	    
    	    boolean alreadyInsertedAllNotes = false;
    	    
    	    while ((str = in.readLine()) != null) 
    	    {
    	    	contentBuilder.append(str);
    	    	
    	    	if(str.contains("</tr>") && (!alreadyInsertedAllNotes))
    	    	{
    	    		alreadyInsertedAllNotes = true;
    	    		appendAllNotesTo(contentBuilder);
	    		}
	    	}
    	} 
    	catch (IOException e)
    	{e.printStackTrace();}
    	
    	out.println(contentBuilder.toString());
	}

    private void appendAllNotesTo(StringBuilder contentBuilder) 
    {
    	for(Note note: notes)
    	{
    		contentBuilder.append("<tr>\n");
    		contentBuilder.append("<td>" + note.getDate() + "</td>\n");
    		contentBuilder.append("<td>" + note.getText() + "</td>\n");
		    contentBuilder.append("</tr>\n");
    	}
	}

	public void destroy() {
        System.out.println("---from Servlet destroy---");
    }

    public String getServletInfo() {
        return null;
    }

    public ServletConfig getServletConfig() {
        return null;
    }

}