package com.mytests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.windows.WindowsDriver;


public class NotepadTest {

	public static WindowsDriver driver = null;
	
	@BeforeMethod
	public void setUp() {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("app", "C:\\Windows\\System32\\notepad.exe");
		cap.setCapability("platformName", "Windows");
		cap.setCapability("deviceName", "WindowsPC");
		
		try {
			driver = new WindowsDriver(new URL("http://127.0.0.1:4723/"), cap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
	}
	
	@AfterMethod
	public void cleanUp() {
		driver.quit();
	}
	
	
	@Test(priority = 1)
	public void checkHelpAboutNowTest() throws InterruptedException {
		driver.findElementByName("Help").click();
		driver.findElementByName("About Notepad").click();
		driver.findElementByName("OK").click();
	}
	
	
	@Test(priority = 2)
	public void writeTextTest() {
		driver.findElementByName("Text Editor").sendKeys("Hello Notepad!" + 
		"\nThis my first windows app automation test");
		driver.findElementByName("Text Editor").clear();
	}
	
	@Test(priority = 3)
	public void enterTimeAndDate() {
		driver.findElementByName("Edit").click();
		driver.findElementByAccessibilityId("26").click(); // Select Time/Date from Edit option
		//String valueString = driver.findElementByName("Text Editor").getAttribute("Value.Value");
		//System.out.println(valueString);
		//Assert.assertTrue(valueString.contains("9/16/2022"));
		driver.findElementByName("Close").click();
		driver.findElementByAccessibilityId("CommandButton_6").click();
		driver.findElementByName("New Volume (D:)").click();
		driver.findElementByAccessibilityId("{E44616AD-6DF1-4B94-85A4-E465AE8A19DB}").click();
		driver.findElementByAccessibilityId("13").sendKeys(Keys.F2);
		driver.findElementByClassName("UIRenameTextElement").sendKeys("Notepad");
		driver.findElementByAccessibilityId("1").click();
		driver.findElementByAccessibilityId("1001").sendKeys("NotepadAutomation.txt");
		driver.findElementByAccessibilityId("1").click();
	}
	
	
}