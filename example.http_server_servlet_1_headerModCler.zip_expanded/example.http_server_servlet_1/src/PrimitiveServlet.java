import javax.servlet.Servlet;
import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import example.http_server_servlet_1.*;

import javax.servlet.http.*;

public class PrimitiveServlet implements Servlet {
    int count;

    public void init(ServletConfig config) throws ServletException
    {System.out.println("---from Servlet init---");}

    public void service(ServletRequest request,
            ServletResponse response)
            throws ServletException, IOException {
        System.out.println("---from Servlet service---");


        PrintWriter out = response.getWriter();       
 
         //RBS begin
	      String HTTP_header1 = "HTTP/1.1 200 OK";
	      out.println(HTTP_header1);
	      String HTTP_header2 = "Content-Type: text/html\r\n";
	      out.println(HTTP_header2);
	      //RBS end      
        
        out.println("<html><head/><body>");
        out.println("<b>Hello. Roses are red.</b> <br>");
        out.println("Count: " + NewClass.getCount());
        out.println("<br>");
        out.print("<i>Violets are blue.</i>");
        String[] list = {"A", "V", "C"};
        for (String s : list) {
            out.println(s);
            out.println("<input type=\"submit\" value=\"Show\"/>");
            out.println("<br>");
        }

        out.println("</body></html>");
        out.close();
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