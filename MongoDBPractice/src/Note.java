import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Note 
{
	private String text, date;
	
	public Note(String noteText)
	{
		this.text = noteText;

		LocalDate localDate = LocalDate.now();
		this.date = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(localDate);
	}

	public String getText()
	{return this.text;}
	
	public String getDate()
	{return this.date;}
}
