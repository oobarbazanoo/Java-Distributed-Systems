package http;

import java.io.IOException;

public class StaticResourceProcessor implements Processor {

    @Override
  public void process(Request request, Response response) {
    try {

      response.sendStaticResource(request.getURI());
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
