import com.mongodb.*;
import java.net.UnknownHostException;
import java.util.*;

public class BookSessionBean
{
	private DBCollection bookCollection;

	public BookSessionBean()
	{
		try 
		{initDB();} 
		catch (UnknownHostException e) 
		{e.printStackTrace();}
	}
	
	private void initDB() throws UnknownHostException
	{
		Mongo mongo = new Mongo();
		DB db = mongo.getDB("booksDB");
		bookCollection = db.getCollection("books");
		if (bookCollection == null) 
		{bookCollection = db.createCollection("books", null);}
	}
	
	public void addToDB(Book book) 
	{
		BasicDBObject doc = book.toDBObject();
		bookCollection.insert(doc);
	}

	public List<Book> getBooks() 
	{
		List<Book> books = new ArrayList<Book>();

		DBCursor cursor = bookCollection.find();
		while (cursor.hasNext()) {
			DBObject dbo = cursor.next();
			books.add(Book.fromDBObject(dbo));
		}

		return books;
	}
	
	public static void main(String[] args)
	{
		BookSessionBean bookDB = new BookSessionBean();
		try {
			bookDB.initDB();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Book book = new Book("forTestName", "forTestYear", "forTestLanguage", "forTestAuthor");
		
		bookDB.addToDB(book);
		
		for(Book b : bookDB.getBooks())
		{
			System.out.println(b.name + " " + b.year + " " + b.language + " " + b.author);
		}
	}
}