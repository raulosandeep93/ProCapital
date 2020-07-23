package com.procapital.mohd.faiz;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class IntradayCalls {

	private WebDriver driver;
	private String baseUrl = "https://procapital.mohdfaiz.com/";
	private String intradayUrl = "https://procapital.mohdfaiz.com/intraday-calls.php";

	@Before
	public void setUp() {
		String basePath = System.getProperty("user.dir");
		System.out.println(basePath);
		System.setProperty("webdriver.chrome.driver",
				basePath + "\\src\\test\\resources\\browserDriver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
	}

	@Test
	public void IntradayLogin() {
		// Login Script
		WebElement linkSignIn = driver.findElement(By.xpath("//a[text()='Sign in']"));
		linkSignIn.click();

		WebElement txtEmailId = driver.findElement(By.xpath("//input[@name='loginid']"));
		txtEmailId.sendKeys("raulosandeep93@gmail.com");

		WebElement txtPassword = driver.findElement(By.xpath("//input[@name='pass']"));
		txtPassword.sendKeys("Mus526@shreya");

		WebElement btnLogin = driver.findElement(By.xpath("//input[@name='login']"));
		btnLogin.click();

		driver.get(intradayUrl);

		String systemDate = java.time.LocalDate.now().toString();
		System.out.println(systemDate);

		WebElement tblData = driver.findElement(By.xpath("//table[@class='table table-condensed table-bordered']"));
		List<WebElement> allRows = tblData.findElements(By.tagName("tr"));		
		for (int i = 0; i < allRows.size(); i++) {
			List<WebElement> allColumns = allRows.get(i).findElements(By.tagName("td"));
			System.out.println(allColumns.get(2).getText());
		}
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
