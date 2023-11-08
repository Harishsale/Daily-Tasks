package Task;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Brokenlinks {
	
	public static void main(String[] args) {
		
	
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://e-quarz.com/");
		List<WebElement> links=driver.findElements(By.tagName("a"));
		for(WebElement link:links)
		{
			String url=link.getAttribute("href");
			if(url!=null&&!url.isEmpty())
			{
			try {
				URL link1 = new URL(url);
				HttpURLConnection httpURLsConnection = (HttpURLConnection) link1.openConnection();
				//httpURLsConnection.setConnectTimeout
				//httpURLsConnection.connect();
				httpURLsConnection.setRequestMethod("GET");
				int RScode=httpURLsConnection.getResponseCode();


				if (RScode != 200) 
				{
				System.out.println(url + " - " +"broken link");
				System.out.println(httpURLsConnection.getResponseMessage());
				System.out.println("response code:"+RScode);
				}
				
			}
				
				catch (Exception e) {
					
				System.out.println(url + " - " + "is a broken link");
					
				}
			}
			
		}
		driver.close();
	}

}
