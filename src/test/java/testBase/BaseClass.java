package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Properties prop;
	
	@BeforeClass(groups= {"sanity", "regression", "master"})
	@Parameters({"os", "browser"})
    public void setUp(String os, String browser) throws MalformedURLException {
		
		//loading config
		try {
			FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
			prop = new Properties();
			prop.load(file);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("No matching os");
				return;
			}
			
			//browser
			switch(browser.toLowerCase()) {
			
			case "chrome": capabilities.setBrowserName("chrome");
			break;
			
			case "firefox": capabilities.setBrowserName("firefox");
			break;
			
			case "edge": capabilities.setBrowserName("MicrosoftEdge");
			break;
			
			case "safari": capabilities.setBrowserName("safari");
			break;
			
			default: System.out.println("No matching browser....");
			return;
			
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444"), capabilities);
			
		}
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("local")) {
			
			//browser
		switch(browser.toLowerCase()) {
		
		case "chrome" : driver = new ChromeDriver();
		break;
		
		case "edge" : driver = new InternetExplorerDriver();
		break;
		
		case "firefox" : driver = new FirefoxDriver();
		break;
		
		case "safari" : driver = new SafariDriver();
		break;
		
		default: System.out.println("Invalid browser name : ");
		
		return;
		
		}
		}
    	driver = new ChromeDriver();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	//driver.get("https://tutorialsninja.com/demo/");
    	driver.get(prop.getProperty("url"));
    	driver.manage().window().maximize();
    }
    
	@AfterClass(groups={"sanity", "regression", "master", "datadriven"})
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		return RandomStringUtils.randomAlphabetic(5);
	}
	
	public String randomNumber() {
		return RandomStringUtils.randomNumeric(5);
	}
	
	public String randomAlphaNumber() {
		return RandomStringUtils.randomAlphabetic(5) + "@" + RandomStringUtils.randomAlphanumeric(5);
	}

	public String captureScreen(String name) {
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"/screenshots"+name+"_"+timeStamp;
		File targetFile = new File(targetFilePath);
		
		srcFile.renameTo(targetFile);
		return targetFilePath;
	}

}
