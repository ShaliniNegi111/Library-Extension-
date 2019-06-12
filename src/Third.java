import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import net.minidev.json.parser.JSONParser;
import ru.sbtqa.tag.parsers.JsonParser; 
import org.json.simple.parser.*;

public class Third  {
	
	
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
				file.flush();
				file.close();
			}
			catch ( IOException ex )
			{
				System.out.println(ex);
			}
			in.close();
			
	}
	
	public static void main ( String args [ ] ) throws net.minidev.json.parser.ParseException, JSONException, ParseException
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
				String name="" , password="" ;
				JSONParser parser = new JSONParser();
		        try (FileReader reader = new FileReader("data.json"))
		        {
		            
		            JSONObject obj = (JSONObject)parser.parse(new FileReader("./data.json"));
		            name = obj.getString("USERNAME");
					password = obj.getString("PASSWORD");
					reader.close();
		 
		        } 
		        catch (FileNotFoundException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
				
			    driver.findElement(By.xpath("//*[@id=\"txtUserName\"]")).sendKeys(name);
			    driver.findElement(By.xpath("//*[@id=\"Password1\"]")).sendKeys(password);
			    WebElement button =  driver.findElement(By.xpath("//*[@id=\"Submit1\"]"));
			    button.click();
			    if ( driver.getCurrentUrl() == url )
			    {
				    attempt -- ;
				    driver.findElement(By.xpath("//*[@id=\\\"txtUserName\\\"]")).clear();
			    	try(FileReader reader = new FileReader("data.json"))
					{
			    		
			    		    JSONObject obj = (JSONObject)parser.parse(new FileReader("/data.json"));
				            name = (String)obj.get("USERNAME");
							password = (String)obj.get("PASSWORD");
						    obj .remove(obj.toString());
						    reader.close();
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
					catch(Exception e ) { e.printStackTrace();}
			    }
			    else break ;
	    }
		ArrayList < WebElement > rows = (ArrayList<WebElement>) driver.findElements(By.cssSelector("#ctl00_ContentPlaceHolder1_CtlMyLoans1_lblItems"));
		int tot = Integer.parseInt((rows.get(0).getText()).toString());
	    System.out.println("No. of books issued:"  + tot) ;
	    ArrayList < String > due_date = new ArrayList <String >(); 
	    for ( int i = 0 ; i < tot ; i ++ )
		{
		    String css = "#ctl00_ContentPlaceHolder1_CtlMyLoans1_grdLoans > tbody > tr:nth-child(" + (i +2)  + ") > td:nth-child(5)" ;
			ArrayList < WebElement > d = (ArrayList<WebElement>) driver.findElements(By.cssSelector( css ));
			String date = d.get(0).getText().toString();
		    due_date.add( date ) ;
		 }
	     for (  String s : due_date )
				System.out.println( s ) ;
	     ArrayList < String > BookName = new ArrayList <String >(); 
		 for ( int i = 0 ; i < tot ; i ++ )
		  {	 
			   String css = "#ctl00_ContentPlaceHolder1_CtlMyLoans1_grdLoans > tbody > tr:nth-child(" + (i + 2 )+ ") > td:nth-child(2)";
			   ArrayList < WebElement > d  = (ArrayList<WebElement>) driver.findElements(By.cssSelector( css ));
			   String Book  = d.get(0).getText().toString();
			   BookName.add( Book ) ;
		   }
		   for (  String s :  BookName )
				 System.out.println( s ) ;
		  /* ArrayList < String > Button  = new ArrayList <String >(); 
		   for ( int i = 0 ; i < tot ; i ++ )
			{ 
			     String css = "#ctl00_ContentPlaceHolder1_CtlMyLoans1_grdLoans_ctl0"+(i+2)+"_Button1";
				 ArrayList < WebElement > d  = (ArrayList<WebElement>) driver.findElements(By.cssSelector( css ));
			     Button.addAll( (Collection<? extends String>) d.get(0)) ;
			}*/
			/*for (  String s :  Button)
				 	System.out.println( s ) ;*/
			String date = LocalDate.now().toString();
			String months [ ] = { "Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"} ;
			if ( tot != 0 )
			{
				for ( int i = 0 ; i < tot ; i ++ )
				{
					System.out.println( "Book no:-"+ i+1 + " ");
					System.out.print( "Due date is:-" );
					String[] d = due_date .get(i).split("-"); 
					System.out.println ( d [ 0 ] + d [ 1 ] + d [ 2 ] ) ;
					int year = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(date)).getYear();
					int month = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(date)).getYear();
					int day = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(date)).getYear();
					if ( d [ 2 ] == Integer.toString(year))
					{
						if ( d [ 1 ] ==  months [ month ] )
						{
							if ( d [ 0 ] == Integer.toString(day))
							{
								/*if ()
								{
									
								}
								else 
								{
									 System.out.println ( "RETURN NEEDED !!!");
				                 System.out.println ( "You need to return " +  BookName.get( i ) + " today. ") ;
								}*/
							}
						} 
					}
				}    
			}
			driver.close();
			System.out.println( "THANK YOU"); 
	}		
	
}

