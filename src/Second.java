import java.io.File;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import ru.sbtqa.tag.parsers.JsonParser; 
public class Second {
	
	public static void main ( String args [ ] )
	{
		
		
		
		 //WebDriverManager.chromedriver().setup();
		
		 //ChromeDriverManager.getInstance().setup();
		 ChromeOptions chromeOptions = new ChromeOptions();

		  chromeOptions.addArguments("--headless");
		  System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");
		   WebDriver driver = new ChromeDriver(chromeOptions);
		//WebDriver driver = new ChromeDriver();
		String url = "http://14.139.108.229/W27/login.aspx?ReturnUrl=%2fw27%2fMyInfo%2fw27MyInfo.aspx" ;
		driver.get(url);
		driver.findElement(By.xpath("//*[@id=\"txtUserName\"]")).sendKeys("17477");
	    driver.findElement(By.xpath("//*[@id=\"Password1\"]")).sendKeys("MEMBER");
         WebElement button =  driver.findElement(By.xpath("//*[@id=\"Submit1\"]"));
	     button.click();
	    if ( driver.getCurrentUrl() == url )
	    {
			    
			  driver.findElement(By.xpath("//*[@id=\\\"txtUserName\\\"]")).clear();
			  System.out.println("Wrong UserName or Password");
			  driver.close();
			  System.exit(0);
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
		 /*ArrayList < String > Button  = new ArrayList <String >(); 
		 for ( int i = 0 ; i < tot ; i ++ )
		 {
			 
		    String css = "#ctl00_ContentPlaceHolder1_CtlMyLoans1_grdLoans_ctl0"+(i+2)+"_Button1";
			 ArrayList < WebElement > d  = (ArrayList<WebElement>) driver.findElements(By.cssSelector( css ));
		     Button.addAll( (Collection<? extends String>) d.get(0)) ;
		 }
		 for (  String s :  Button)
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
							if ()
							{
								
							}
							else 
							{
								 System.out.println ( "RETURN NEEDED !!!");
			                 System.out.println ( "You need to return " +  BookName.get( i ) + " today. ") ;
							}
						}
					} 
				}
			}    
		}
		driver.close();
		System.out.println( "THANK YOU"); 
	}		
	
}

