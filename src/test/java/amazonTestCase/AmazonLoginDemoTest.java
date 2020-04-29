
package amazonTestCase;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import library.Utilities;
import library.baseHelper;
import library.readDataFromExcel;
import pageObjects.AddToCartPage;
import pageObjects.HomePage;
import pageObjects.Login;
import pageObjects.ProductPage;

public class AmazonLoginDemoTest extends baseHelper {

	//public static AndroidDriver<AndroidElement> driver;
	public static readDataFromExcel data;
	public static Login login;
	public static HomePage home;
	

	@Test
	public void firstRun() throws InterruptedException, IOException {

		
		// Set Capabilities
		driver = capabilities("AmazonStoreApp");

		data = new readDataFromExcel();
		// Create Object for pageobject
		login = new Login(driver);
		home = new HomePage(driver);
		

		ArrayList al = data.getData("AmazonLogin", "LoginC");
		String username = al.get(1).toString();
		String password = al.get(2).toString();
		
		String dispalyname = login.getSignInMsg().getText();
		// Verify Correct App installed and Launched successfully or not
		Assert.assertEquals(dispalyname, baseHelper.getProperty("appdisplayname"));
		baseHelper.getScreenshot("Login Page");

		login.loginApp(username, password);

		// Creat Object for HomePAGE Class

		String homemessage = home.getCategory().getText();
		// Verify login successfull or not

		if (!homemessage.contains(baseHelper.getProperty("homeMsg"))) {
			Assert.fail();
		}
		baseHelper.getScreenshot("Ammazon Home Page");
		
	}

}