package Multiple_Email_Address;

import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;



@Test

public class Talent
{

public WebDriver driver;

public void Test() throws IOException, InterruptedException {
	
	 Thread.sleep(3000);
	
	 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	 driver.manage().window().maximize();  
     driver.findElement(By.xpath("//*[@id=\"u665\"]")).click(); //
 	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

 	 driver.findElement(By.xpath("/html/body/header/div/div[1]/div[2]/nav/div/div[2]/ul[2]/li[1]/a")).click(); //click on login button
 	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
 
	    FileInputStream file = new FileInputStream(new File("E:\\Testdata.xlsx")); 
	    XSSFWorkbook workbook = new XSSFWorkbook(file);
	 
	    XSSFSheet sheet = workbook.getSheetAt(0);
	   
	    for (int i=0; i <= sheet.getLastRowNum(); i++)
	    
	         {
	    	 Thread.sleep(5000); //wait for loading screen
	         
	    	 WebElement user = driver.findElement(By.name("email"));
	    	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	    	 WebElement pass = driver.findElement(By.name("pwd")); 
	    	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    	 
	    	 String usercell = sheet.getRow(i).getCell(0).getStringCellValue();
	         String passcell = sheet.getRow(i).getCell(1).getStringCellValue();
	         user.sendKeys(usercell);
	         pass.sendKeys(passcell);
	         user.submit();   
	         pass.submit();
	         System.out.println(usercell);
	         System.out.println(passcell);
	       

	    	driver.findElement(By.id("btnSubmit")).click(); //Click on Login button
	    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    	
	    	Thread.sleep(3000);
	    	driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div/div/div/span")).click(); //Click on Profile dropdown button
	    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	    	driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div/div/div/div/a[1]")).click(); //Select Account Setting option
	    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    	driver.findElement(By.id("btnSubmit")).click();
	    			
	        }
	    workbook.close();
	    file.close();

	  }
	  	
@BeforeMethod

public void beforeMethod() throws Exception {
	
	System.setProperty("webdriver.gecko.driver", "C:\\Users\\Win10\\Downloads\\geckodriver-v0.19.1-win64\\geckodriver.exe");
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


