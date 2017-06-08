package http;

import java.io.InputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import javax.servlet.*;


public class Request implements ServletRequest{

  private InputStream input;
  private String uri;
  private String fullRequestAsText;

  private Map<String, String> parameters; 
  
  public Request(InputStream input)
  {
	  this.input = input;
	  this.parameters = new HashMap<>();
  }

  public String getURI() {
    return uri;
  }

  private String parseUri(String requestString) {
    int index1, index2;
    index1 = requestString.indexOf(' ');
    if (index1 != -1) {
      index2 = requestString.indexOf(' ', index1 + 1);
      if (index2 > index1)
        return requestString.substring(index1 + 1, index2);
    }
    return "";
  }

  public void parse() 
  {
	    // Read a set of characters from the socket
	    StringBuffer request = new StringBuffer(2048);
	    int i;
	    byte[] buffer = new byte[2048];
	    try {
	      i = input.read(buffer);
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	      i = -1;
	    }
	    for (int j=0; j<i; j++) {
	      request.append((char) buffer[j]);
	    }
	    System.out.print(request.toString());
	    fullRequestAsText = request.toString();
	    if(isPost())
	    {parseParameters(request.toString());}
	    
	    uri = parseUri(request.toString());
  }

  private void parseParameters(String strToParse)
  {
	  String param = (strToParse.split("aNote=")[1]).split("&")[0];
	  try 
	  {
		  String note = URLDecoder.decode(param, "UTF-8");
		  parameters.put("aNote", note);
	  } 
	  catch (UnsupportedEncodingException e) 
	  {e.printStackTrace();}
  }

/* implementation of the ServletRequest*/
  public Object getAttribute(String attribute) {
    return null;
  }

  public Enumeration getAttributeNames() {
    return null;
  }

  public String getRealPath(String path) {
    return null;
  }

  public RequestDispatcher getRequestDispatcher(String path) {
    return null;
  }

  public boolean isSecure() {
    return false;
  }

  public String getCharacterEncoding() {
    return null;
  }

  public int getContentLength() {
    return 0;
  }

  public String getContentType() {
    return null;
  }

  public ServletInputStream getInputStream() throws IOException {
    return null;
  }

  public Locale getLocale() {
    return null;
  }

  public Enumeration getLocales() {
    return null;
  }

  public String getParameter(String name)
  {
    return parameters.get(name);
  }

  public Map getParameterMap() {
    return null;
  }

  public Enumeration getParameterNames() {
    return null;
  }

  public String[] getParameterValues(String parameter) 
  {
    return null;
  }

  public String getProtocol() {
    return null;
  }

  public BufferedReader getReader() throws IOException {
    return null;
  }

  public String getRemoteAddr() {
    return null;
  }

  public String getRemoteHost() {
    return null;
  }

  public String getScheme() {
   return null;
  }

  public String getServerName() {
    return null;
  }

  public int getServerPort() {
    return 0;
  }

  public void removeAttribute(String attribute) {
  }

  public void setAttribute(String key, Object value) {
  }

  public void setCharacterEncoding(String encoding)
    throws UnsupportedEncodingException {
  }

    @Override
    public int getRemotePort() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getLocalName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getLocalAddr() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getLocalPort() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ServletContext getServletContext() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AsyncContext startAsync() throws IllegalStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isAsyncStarted() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isAsyncSupported() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AsyncContext getAsyncContext() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DispatcherType getDispatcherType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@Override
	public long getContentLengthLong() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean isPost()
	{
		return fullRequestAsText.startsWith("POST");
	}

}
