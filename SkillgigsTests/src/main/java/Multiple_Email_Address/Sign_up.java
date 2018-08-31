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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;


@Test

public class Sign_up
{
public WebDriver driver;

     public void Test() throws IOException, InterruptedException {
	
	 Thread.sleep(3000);
	
	 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	 driver.manage().window().maximize();  
     driver.findElement(By.id("u665")).click(); //
 	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

 	 driver.findElement(By.xpath("/html/body/header/div/div[1]/div[2]/nav/div/div[2]/ul[2]/li[2]/a")).click(); //click on Sign Up button
 	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
 try
 {
	 
	    FileInputStream file = new FileInputStream(new File("E:\\SignUp.xlsx")); 
	    XSSFWorkbook workbook = new XSSFWorkbook(file);
	 
	    XSSFSheet sheet = workbook.getSheetAt(0);
	   
	    for (int i=0; i <= sheet.getLastRowNum(); i++)
	    
	         {
	    	 Thread.sleep(5000); //wait for loading screen
	         
	    	 WebElement fname = driver.findElement(By.id("fullname"));
	    	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	    	 WebElement email = driver.findElement(By.id("email")); 
	    	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    	 
	    	 WebElement password = driver.findElement(By.id("password")); 
	    	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    	 
	    	 String fnamecell = sheet.getRow(i).getCell(0).getStringCellValue();
	         String emailcell = sheet.getRow(i).getCell(1).getStringCellValue();
	         String passwordcell = sheet.getRow(i).getCell(2).getStringCellValue();
	         String phonecell = sheet.getRow(i).getCell(3).getStringCellValue();
 

	         fname.sendKeys(fnamecell);
	         email.sendKeys(emailcell);
	         password.sendKeys(passwordcell);
	         
	         fname.submit();   
	         email.submit();
	         password.submit();
	         System.out.println(fnamecell);
	         System.out.println(emailcell);
	         System.out.println(passwordcell);
	       

	    	driver.findElement(By.id("btnSubmit")).click(); //Click on Sign up button
	    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    	
	        WebElement phone = driver.findElement(By.id("txtMobilePhone"));
	    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    	phone.sendKeys(phonecell);
	    	phone.submit();
	    	
	   
	    	Thread.sleep(1000);
            Select profession = new Select(driver.findElement(By.id("ddlProfessions")));
			profession.selectByIndex(1);
			
		
            Select citizenship = new Select(driver.findElement(By.id("ddlVisaStatuses")));
			citizenship.selectByIndex(1);
			

			
	        WebDriverWait wait = new WebDriverWait(driver, 50);
            WebElement waits=wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/form/div[3]/button"))));
			waits.click();
			waits.sendKeys("https://appdev.skillgigs.com/TalentV2/Welcome/Wizard/ProfilePic/");
		    System.out.println("yes");
			
		//	driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/form/div[3]/button")).click();
			//wait for few second for loading site
		

			            }
			
			//WebElement navigate = driver.findElement(By.xpath("/html/body/div[2]/div/div/p[2]/button"));
			//navigate.click();
		
			//driver.switchTo().activeElement().sendKeys("https://appdev.skillgigs.com/TalentV2/Welcome/Wizard/ProfilePic/");

	 
	 
 
	        workbook.close();
	        file.close();
 }catch(Exception e){ 
 }
 }
	  

@BeforeMethod

public void beforeMethod() throws Exception {
	
	
	System.setProperty("webdriver.gecko.driver", "C:\\Users\\Win10\\Downloads\\geckodriver-v0.19.1-win64\\geckodriver.exe");
    driver = new FirefoxDriver();
	driver.manage().window().maximize(); //maximize the browser window
	
	String URL="https://dev.skillgigs.com/";
	
	driver.get(URL); //enter URL
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


