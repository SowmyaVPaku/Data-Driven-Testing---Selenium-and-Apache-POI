
/*This program is to test the login credentials of the website by taking the userID and passwords from an excel sheet*/

package testNG;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class datadriven {

	WebDriver driver;
	
	@Test(dataProvider="testdata")
	public void DemoProject(String username, String password) throws InterruptedException
	{
		
			System.setProperty("webdriver.chrome.driver", "D:\\Sowmya\\chromedriver\\chromedriver.exe");
			driver = new ChromeDriver();
			
			driver.get("https://www.facebook.com/");
			//driver.manage().window().maximize();
			//driver.manage().deleteAllCookies();
			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
			
			driver.findElement(By.name("email")).sendKeys(username);
			driver.findElement(By.name("pass")).sendKeys(password);
			driver.findElement(By.Id("loginbutton")).click();
			
			Thread.sleep(5000);
			Assert.assertTrue(driver.getTitle().matches("Find a Flight: Mercury Tours:"),"Invalid credentials");
			System.out.println("Login Successful");
		
	}
		
		@AfterMethod
		void ProgramTermination()
		{
			
			driver.quit();
		}
		
		@DataProvider(name="testdata")
		public Object[][] TestDataFeed()
		{
			ReadExcelFile config = new ReadExcelFile("D:\\Sowmya\\LoginCredentials.xlsx");
			int rows = config.getRowCount(0);
			Object[][] credentials = new Object[rows][2];
			for (int i=0;i<rows;i++)
			{
				
				credentials[i][0] = config.getData(0,i,0);
				credentials[i][1]= config.getData(0,i,1);
				
			}
			
			return credentials;
		}
		
	}
	



