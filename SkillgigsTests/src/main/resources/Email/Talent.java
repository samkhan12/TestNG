package Multiple_Email_Address;



import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.commons.io.FileUtils;

import org.testng.annotations.Test;



@Test
public class Talent
{

public WebDriver driver;

public void Test() throws IOException, InterruptedException {
	
Thread.sleep(3000); //wait for loading screen

driver.findElement(By.xpath("//*[@id=\"u665\"]")).click(); //
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

driver.findElement(By.xpath("/html/body/header/div/div[1]/div[2]/nav/div/div[2]/ul[2]/li[1]/a")).click(); //click on login button
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 

driver.findElement(By.xpath("//*[@id=\"signinForm\"]/div/div/div/div/form/div/div[1]/div/div/input")).sendKeys("khansamuel678@gmail.com"); //Enter userID    
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.findElement(By.xpath("//*[@id=\"signinForm\"]/div/div/div/div/form/div/div[2]/div/div/input")).sendKeys("12345678");	 //Enter Password  
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


driver.findElement(By.id("btnSubmit")).click(); //Click on Login button
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div/div/div/span")).click(); //Click on Profile dropdown button
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div/div/div/div/a[1]")).click(); //Select Account Setting option
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
driver.findElement(By.id("btnSubmit")).click();
		
}//Select Account Setting option



@BeforeMethod
public void beforeMethod() throws Exception {
	
	driver = new FirefoxDriver();
	driver.manage().window().maximize(); //maximize the browser window
	
	driver.get("https://dev.skillgigs.com/"); //enter URL
    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 
}
@AfterMethod
public void screenShot(ITestResult result) throws InterruptedException{
	//using ITestResult.FAILURE is equals to result.getStatus then it enter into if condition
		if(ITestResult.FAILURE==result.getStatus()){
			try{
	String timeStamp;
	File screenShotName;
	
	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	//The below method will save the screen shot in E drive with name "screenshot.png"
	timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); 
	screenShotName = new File("E:\\MyTest\\Screenshots\\"+timeStamp+".png");
	FileUtils.copyFile(scrFile, screenShotName);
	
	System.out.println("Successfully captured a screenshot");
			}catch (Exception e){
				System.out.println("Exception while taking screenshot "+e.getMessage());
			} 
}
}
}


