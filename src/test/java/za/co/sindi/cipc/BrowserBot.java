/**
 * 
 */
package za.co.sindi.cipc;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import io.github.bonigarcia.wdm.WebDriverManager;
import za.co.sindi.cipc.rest.model.EnterpriseNameSearchResult;

/**
 * @author buhake.sindi
 * @since 2020/04/16
 *
 */
public class BrowserBot {

	public static void getCipc() {
		WebDriver driver = null;
		
		try {
			//C:\Applications\Google\Chrome\Driver\win32
//			System.setProperty("webdriver.chrome.driver", "C:/Applications/Google/Chrome/Driver/win32/chromedriver.exe");
//
//			driver = new ChromeDriver();
			
			
			
			
			
			WebDriverManager.chromedriver().version("81.0.4044.113").setup();
			ChromeOptions options = new ChromeOptions();
//			options.addArguments("start-maximized"); 
			options.addArguments("--headless"); // Run in background.
			options.addArguments("enable-automation"); 
			options.addArguments("--no-sandbox"); 
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--disable-browser-side-navigation"); 
			options.addArguments("--disable-gpu");
			
			driver = new ChromeDriver(options); 
			
			
			
			
			
			
			
//			driver = new HtmlUnitDriver(true);
			driver.get("https://eservices.cipc.co.za/Search.aspx");
			Thread.sleep(5000);  // Let the user actually see something!
			
			List<WebElement> searchOptions = driver.findElements(By.xpath("//*[@id=\"ctl00_cntMain_drpSearchOptions\"]/option"));
			for (WebElement option : searchOptions) {
//				System.out.println(option.getText());
				if (option.getText().equals("Enterprise No.")) {
					option.click();
				}
			}//*[@id="ctl00_cntMain_drpSearchOptions"]/option[1]
			Thread.sleep(5000);  // Let the user actually see something!
			
			WebElement searchBox = driver.findElement(By.xpath("//input[@id=\"ctl00_cntMain_txtSearchCIPC\"]"));
			searchBox.sendKeys("K2019518166");
//			searchBox.submit();
//			Thread.sleep(5000);
			
			WebElement searchLink = driver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_lnkSearchIcon\"]")); //(new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.(By.id("//a[@id=\"ctl00_cntMain_lnkSearchIcon\"]")));
			searchLink.click();
			Thread.sleep(5000);  // Let the user actually see something!
			
			String enterpriseName = driver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblEntName\"]")).getText();
			System.out.println(enterpriseName);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (driver != null) {
				driver.quit();
			}
		}
	}
	
	public static void getCipc2() {
		
		WebClient webClient = null;
		
		try {
			webClient = new WebClient(BrowserVersion.CHROME);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setUseInsecureSSL(true);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getCookieManager().setCookiesEnabled(true);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getCookieManager().setCookiesEnabled(true);
			webClient.waitForBackgroundJavaScript(3000);
			 
			final HtmlPage page1 = webClient.getPage("https://eservices.cipc.co.za/Search.aspx");
			
			final HtmlForm form = page1.getFormByName("aspnetForm");
			
			final HtmlSelect searchOptionSelect = page1.getElementByName("ctl00$cntMain$drpSearchOptions");
			for (HtmlOption searchOption : searchOptionSelect.getOptions()) {
				if (searchOption.getText().equals("Enterprise No.")) {
					searchOption.setSelected(true);
				}
			}
			
			Thread.sleep(3000);
			
			final HtmlTextInput cipcSearchText = form.getInputByName("ctl00$cntMain$txtSearchCIPC");
			cipcSearchText.setText("K2019518166"); // Same as cipcSearchText.setValueAttribute("K2019518166");
			
			final HtmlAnchor searchLink = (HtmlAnchor) page1.getElementById("ctl00_cntMain_lnkSearchIcon");
			final HtmlPage page2 = searchLink.click();
			
			Thread.sleep(3000);
			System.out.println(page2.asXml());
		} catch (FailingHttpStatusCodeException | ElementNotFoundException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (webClient != null) {
				webClient.close();
			}
		}
		
//		final HtmlSubmitInput button = form.getInputByName("srcBtn");
//		final HtmlTextInput textField = form.getInputByName("origin1");
//		final HtmlTextInput textField2 = form.getInputByName("date1Local");
//		final HtmlTextInput textField3 = form.getInputByName("destination1");
//		final HtmlTextInput textField24 = form.getInputByName("date2Local");
//		
//		textField.setValueAttribute("MAD");
//		textField2.setValueAttribute("10/11/2010");
//		textField3.setValueAttribute("CDG");
//		textField24.setValueAttribute("15/11/2010");
//		
//		
//		final HtmlPage page2 = button.click();
		
//		webClient.closeAllWindows();
	}
	
	public static void getCipc3() {
		WebDriver webDriver = null;
		
		try {
			WebDriverManager.chromedriver().version("81.0.4044.113").setup();
			ChromeOptions options = new ChromeOptions();
//			options.addArguments("start-maximized"); 
			options.addArguments("--headless"); // Run in background.
			options.addArguments("enable-automation"); 
			options.addArguments("--no-sandbox"); 
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--disable-browser-side-navigation"); 
			options.addArguments("--disable-gpu");
			
			webDriver = new ChromeDriver(options);
			WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
			
			webDriver.get("https://eservices.cipc.co.za/Search.aspx");
			
			List<WebElement> searchOptions = webDriverWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//*[@id=\"ctl00_cntMain_drpSearchOptions\"]/option"),  2));
			for (WebElement option : searchOptions) {
				if (option.getText().equals("Enterprise No.")) {
					option.click();
				}
			}//*[@id="ctl00_cntMain_drpSearchOptions"]/option[1]
			
			WebElement searchBox = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id=\"ctl00_cntMain_txtSearchCIPC\"]")));
			searchBox.sendKeys("K2019518166");
//			searchBox.submit();
			
			WebElement searchLink = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ctl00_cntMain_lnkSearchIcon\"]"))); 
			searchLink.click();
			
//			WebElement divElement = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_pnlEntNoSearch\"]"));
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ctl00_cntMain_pnlEntNoSearch\"]")));
			String cipcEnterpriseNumber = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblEntNo\"]")).getText();
			String cipcEnterpriseName = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblEntName\"]")).getText();
			String cipcEnterpriseType = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblEntType\"]")).getText();
			String cipcEnterpriseStatus = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblEntStatus\"]")).getText();
			String cipcComplianceNoticeStatus = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblNonComply\"]")).getText();
			String cipcRegistrationDate = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblRegDate\"]")).getText();
			String cipcPhysicalAddress = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblPhysAddress\"]")).getText();
			String cipcPostalAddress = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblPostalAddress\"]")).getText();
			
			System.out.println("Enterprise Number: " + cipcEnterpriseNumber);
			System.out.println("Enterprise Name: " + cipcEnterpriseName);
			System.out.println("Enterprise Type: " + cipcEnterpriseType);
			System.out.println("Enterprise Status: " + cipcEnterpriseStatus);
			System.out.println("Compliance Notice Status: " + cipcComplianceNoticeStatus);
			System.out.println("Registration Date: " + cipcRegistrationDate);
			System.out.println("Phyiscal Address: " + cipcPhysicalAddress);
			System.out.println("Postal Address: " + cipcPostalAddress);
		} finally {
			if (webDriver != null) {
				webDriver.quit();
			}
		}
	}
	
	public static void getCipc4() {
		WebDriver webDriver = null;
		
		try {
			WebDriverManager.chromedriver().version("81.0.4044.113").setup();
			ChromeOptions options = new ChromeOptions();
//			options.addArguments("start-maximized"); 
			options.addArguments("--headless"); // Run in background.
			options.addArguments("enable-automation"); 
			options.addArguments("--no-sandbox"); 
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--disable-browser-side-navigation"); 
			options.addArguments("--disable-gpu");
			
			webDriver = new ChromeDriver(options);
			// Implicit wait for 5 seconds
			webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			webDriver.get("https://eservices.cipc.co.za/Search.aspx");
			
			List<WebElement> searchOptions = webDriver.findElements(By.xpath("//*[@id=\"ctl00_cntMain_drpSearchOptions\"]/option"));
			for (WebElement option : searchOptions) {
				if (option.getText().equals("Enterprise No.")) {
					option.click();
				}
			}//*[@id="ctl00_cntMain_drpSearchOptions"]/option[1]
			
			WebElement searchBox = webDriver.findElement(By.xpath("//input[@id=\"ctl00_cntMain_txtSearchCIPC\"]"));
			searchBox.sendKeys("K2019518166");
//			searchBox.submit();
			
			WebElement searchLink = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_lnkSearchIcon\"]"));
			searchLink.click();
			
			String cipcEnterpriseNumber = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblEntNo\"]")).getText();
			String cipcEnterpriseName = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblEntName\"]")).getText();
			String cipcEnterpriseType = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblEntType\"]")).getText();
			String cipcEnterpriseStatus = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblEntStatus\"]")).getText();
			String cipcComplianceNoticeStatus = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblNonComply\"]")).getText();
			String cipcRegistrationDate = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblRegDate\"]")).getText();
			String cipcPhysicalAddress = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblPhysAddress\"]")).getText();
			String cipcPostalAddress = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblPostalAddress\"]")).getText();
			
			System.out.println("Enterprise Number: " + cipcEnterpriseNumber);
			System.out.println("Enterprise Name: " + cipcEnterpriseName);
			System.out.println("Enterprise Type: " + cipcEnterpriseType);
			System.out.println("Enterprise Status: " + cipcEnterpriseStatus);
			System.out.println("Compliance Notice Status: " + cipcComplianceNoticeStatus);
			System.out.println("Registration Date: " + cipcRegistrationDate);
			System.out.println("Phyiscal Address: " + cipcPhysicalAddress);
			System.out.println("Postal Address: " + cipcPostalAddress);
		} finally {
			if (webDriver != null) {
				webDriver.quit();
			}
		}
	}
	
	public static void getCipc5() {
		WebDriver webDriver = null;
		
		try {
			WebDriverManager.chromedriver().version("81.0.4044.113").setup();
			ChromeOptions options = new ChromeOptions();
//			options.addArguments("start-maximized"); 
			//options.addArguments("--headless"); // Run in background.
			options.addArguments("enable-automation"); 
			options.addArguments("--no-sandbox"); 
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--disable-browser-side-navigation"); 
			options.addArguments("--disable-gpu");
			
			webDriver = new ChromeDriver(options);
			WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
			
			webDriver.get("https://eservices.cipc.co.za/Search.aspx");
			
			List<WebElement> searchOptions = webDriverWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//*[@id=\"ctl00_cntMain_drpSearchOptions\"]/option"),  2));
			for (WebElement option : searchOptions) {
				if (option.getText().equals("Enterprise Name")) {
					option.click();
				}
			}//*[@id="ctl00_cntMain_drpSearchOptions"]/option[1]
			
			WebElement searchBox = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id=\"ctl00_cntMain_txtSearchCIPC\"]")));
			searchBox.sendKeys("Sindi Technologies");
//			searchBox.submit();
			
			WebElement searchLink = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ctl00_cntMain_lnkSearchIcon\"]"))); 
			searchLink.click();
			
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ctl00_cntMain_pnlNameSearch\"]")));//*[@id="ctl00_cntMain_Updatepanel1"] //*[@id="ctl00_cntMain_pnlNameSearch"]
			
			List<WebElement> tableRows = webDriver.findElements(By.xpath("//*[@id=\"ctl00_cntMain_gdvNames\"]/tbody/tr"));
//			int columnSize = webDriver.findElements(By.xpath("//*[@id=\"ctl00_cntMain_gdvNames\"]/tbody/tr[1]/th")).size();
			
			for (int row = 1; row < tableRows.size(); row++) {
				List<WebElement> tableColumns = tableRows.get(row).findElements(By.tagName("td"));
				
				for (int column = 0; column < tableColumns.size(); column++) {
				
					String value = tableColumns.get(column).getText();
					
					switch(column) {
						case 0 : System.out.println("Enterprise Name: " + value);
							break;
							
						case 1 : System.out.println("Enterprise/Tracking Number: " + value);
							break;
							
						case 2 : System.out.println("Status: " + value);
							break;
							
						default: break;
					}
				}
			}
		} finally {
			if (webDriver != null) {
				webDriver.quit();
			}
		}
	}
	
	public static void mainBackup(String[] args) {
		
		try {
			//C:\Applications\Google\Chrome\Driver\win32
			System.setProperty("webdriver.chrome.driver", "C:/Applications/Google/Chrome/Driver/win32/chromedriver.exe");

			WebDriver driver = new ChromeDriver();
			driver.get("http://www.google.com/"); // https://eservices.cipc.co.za/Search.aspx
			Thread.sleep(5000);  // Let the user actually see something!
			WebElement searchBox = driver.findElement(By.name("q"));
			searchBox.sendKeys("ChromeDriver");
			searchBox.submit();
			Thread.sleep(5000);  // Let the user actually see something!
			driver.quit();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		getCipc5();
	}
}
