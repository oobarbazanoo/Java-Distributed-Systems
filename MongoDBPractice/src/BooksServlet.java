import javax.servlet.Servlet;
import javax.servlet.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import http.Request;

public class BooksServlet implements Servlet 
{
	private static BookSessionBean bookDB;
	
    public void init(ServletConfig config) throws ServletException
    {
    	if(bookDB == null)
    	{this.bookDB = new BookSessionBean();}
    	System.out.println("---from books init---");
	}

    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException 
    {
        System.out.println("---from books service---");
        
        if(((Request)request).isPost())
        {
        	String name = request.getParameter("aName");
        	String year = request.getParameter("aYear");
        	String language = request.getParameter("aLanguage");
        	String author = request.getParameter("anAuthor");
        	
        	Book bookToAdd = new Book(name, year, language, author);
        	
        	bookDB.addToDB(bookToAdd);
        	
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
    	
    	try(BufferedReader in = new BufferedReader(new FileReader("webroot/index.html"));)
    	{
    	    String str;
    	    
    	    boolean alreadyInsertedAllBooks = false;
    	    
    	    while ((str = in.readLine()) != null) 
    	    {
    	    	contentBuilder.append(str);
    	    	
    	    	if(str.contains("</tr>") && (!alreadyInsertedAllBooks))
    	    	{
    	    		alreadyInsertedAllBooks = true;
    	    		appendAllBooksTo(contentBuilder);
	    		}
	    	}
    	} 
    	catch (IOException e)
    	{e.printStackTrace();}
    	
    	out.println(contentBuilder.toString());
	}

    private void appendAllBooksTo(StringBuilder contentBuilder) 
    {
    	
    	for(Book book: bookDB.getBooks())
    	{
    		contentBuilder.append("<tr>\n");
    		contentBuilder.append("<td>" + book.date + "</td>\n");
    		contentBuilder.append("<td>" + book.name + "</td>\n");
    		contentBuilder.append("<td>" + book.year + "</td>\n");
    		contentBuilder.append("<td>" + book.language + "</td>\n");
    		contentBuilder.append("<td>" + book.author + "</td>\n");
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