import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import net.minidev.json.parser.JSONParser;
import ru.sbtqa.tag.parsers.JsonParser; 

  
public class First {
	
	
	 static void user_auth()throws JSONException
	{
			Scanner in = new Scanner ( System.in) ;
			String username , password ;
			System.out.println ("Enter username(reg. no.):-");
			username = in.next();
			System.out.println (" Enter password:-");
			password = in.next();
			JSONObject obj = new JSONObject() ;
			obj.put("USERNAME" ,username  ) ;
			obj.put("PASSWORD" ,password  ) ;
			try (FileWriter file = new FileWriter("data.json"))
			{
				file.write(obj.toString());
			}
			catch ( IOException ex )
			{
				System.out.println(ex);
			}
			
			
	}
	public static void main ( String args [ ] )
	{
		// checking if the file exists or not 
		// If it is first time 
		File f = new File("./data.json");
		if(f.exists() == false && f.isDirectory()== false ) { 
		    System.out.println("It's your first time...");
		    user_auth();
		}
		
		
		System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");
		WebDriver driver = new ChromeDriver();
		String url = "http://14.139.108.229/W27/login.aspx?ReturnUrl=%2fw27%2fMyInfo%2fw27MyInfo.aspx" ;
		driver.get(url);
		int attempt = 2 ;
		
		while ( attempt > 0  )
		{
				if ( attempt != 2 )
					user_auth();
				String name , password ;
				try
				{
					FileReader file = new FileReader ( "data.json") ;
					JSONParser jsonParser = new JSONParser();
					Object obj = jsonParser.parse(file);
					JSONObject jsonobj = (JSONObject) obj ;
					name = (String)jsonobj.get("USERNAME");
					password = (String)jsonobj.get("PASSWORD");
				}
				catch(FileNotFoundException e ) { e.printStackTrace();}
				catch(IOException e ) { e.printStackTrace();}
				catch(ParseException e ) { e.printStackTrace();}
				catch(Exception e ) { e.printStackTrace();}
			    driver.findElement(By.xpath("//*[@id=\"txtUserName\"]")).sendKeys(name);
			    driver.findElement(By.xpath("//*[@id=\"Password1\"]")).sendKeys(password);
			    WebElement button =  driver.findElement(By.xpath("//*[@id=\"Submit1\"]"));
			    button.click();
			    if ( driver.getCurrentUrl() == url )
			    {
			    	attempt -- ;
			    	driver.findElement(By.xpath("//*[@id=\\\"txtUserName\\\"]")).clear();
			    	try
					{
			    		JSONParser jsonp = new JSONParser();
						FileReader file = new FileReader ( "data.json") ;
						Object obj = new Object () ;
						JSONObject jsonobj = (JSONObject) obj ;
						jsonobj .remove(jsonobj.toString());
						if ( attempt == 0 )
						{
							System.out.println("Wrong UserName or Password");
							driver.close();
							System.exit(0);
						}
						System.out.println("Try Re-Login");
								
					}
					catch(FileNotFoundException e ) { e.printStackTrace();}
					catch(IOException e ) { e.printStackTrace();}
					catch(ParseException e ) { e.printStackTrace();}
					catch(Exception e ) { e.printStackTrace();}
			    }
			    else break ;
	    }
		
		WebElement no_of_books = driver.findElement(By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_CtlMyLoans1_lblItems\"]"));
		String totBooks = no_of_books .toString();
		System.out.println("No. of books issued:"  + totBooks ) ;
		
			
	}		
	
}

