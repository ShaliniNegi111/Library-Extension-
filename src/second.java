import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

  
public class second {
	
	
	 
	public static void main ( String args [ ] ) throws JSONException
	{
		JSONObject obj = new JSONObject() ;
		obj .put("USERNAME" , "shalini negi " ) ;
		obj.put("PASSWORD" ,"MEMBER" ) ;
		
		try (FileWriter file = new FileWriter("data.json"))
		{
			file.write(obj.toString());
		}
		catch ( IOException ex )
		{
			System.out.println(ex);
		}
		
		System.out.println(obj) ;
			
	}		
	
}

