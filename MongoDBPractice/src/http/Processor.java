package http;

/**
 *
 * @author andrii
 */
public interface Processor {

    void process(Request request, Response response);
    
}