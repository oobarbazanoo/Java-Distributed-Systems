import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.enterprise.inject.Model;
import javax.validation.constraints.Size;

@Model
public class Book 
{
    public String name, author, language, year, date;
    
    public Book(String name, String year, String language, String author)
    {
    	this.name = name;
    	this.year = year;
    	this.language = language;
    	this.author = author;
    	
    	LocalDate localDate = LocalDate.now();
		this.date = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(localDate);
    }
    
    public Book()
    {
    	this(null, null, null, null);
    }

	public BasicDBObject toDBObject() 
    {
        BasicDBObject document = new BasicDBObject();
        
        document.put("name", name);
        document.put("year", year);
        document.put("language", language);
        document.put("author", author);
        
        return document;
    }
    
    public static Book fromDBObject(DBObject document) 
    {
        Book b = new Book();
        
        b.name = (String) document.get("name");
        b.year = (String) document.get("year");
        b.language = (String) document.get("language");
        b.author = (String) document.get("author");
        
        return b;
    }
}