/**
 * 
 */
package za.co.sindi.cipc.rest.service.utils;

import java.text.ParseException;
import java.util.ArrayList;
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

import io.github.bonigarcia.wdm.WebDriverManager;
import za.co.sindi.cipc.rest.model.EnterpriseDetails;
import za.co.sindi.cipc.rest.model.EnterpriseNameSearchResult;
import za.co.sindi.common.utils.Dates;

/**
 * @author buhake.sindi
 * @since 2020/04/14
 *
 */
public class WebBrowserUtils {

	private static final String CIPC_ESERVICES_SEARCH_URL_STRING = "https://eservices.cipc.co.za/Search.aspx";
	private static WebDriver webDriver;
	
	private WebBrowserUtils() {
		throw new AssertionError("Private Constructor.");
	}
	
	public static EnterpriseDetails getCipcEnterpriseDetailsByEnterpriseNumber(final String enterpriseNumber) {
		//Create web driver, when necessary
		createWebDriver();
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
		
		try {
			webDriver.get(CIPC_ESERVICES_SEARCH_URL_STRING);
			
			List<WebElement> searchOptions = webDriverWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//*[@id=\"ctl00_cntMain_drpSearchOptions\"]/option"), 2));
			for (WebElement option : searchOptions) {
				if (option.getText().equals("Enterprise No.")) {
					option.click();
				}
			}//*[@id="ctl00_cntMain_drpSearchOptions"]/option[1]
			
			WebElement searchBox = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id=\"ctl00_cntMain_txtSearchCIPC\"]")));
			searchBox.sendKeys(enterpriseNumber);
//			searchBox.submit();
			
			WebElement searchLink = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ctl00_cntMain_lnkSearchIcon\"]"))); 
			searchLink.click();
			
//			String cipcEnterpriseNumber = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblEntNo\"]"))).getText(); 
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ctl00_cntMain_pnlEntNoSearch\"]")));
			String cipcEnterpriseNumber = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblEntNo\"]")).getText();
			String cipcEnterpriseName = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblEntName\"]")).getText();
			String cipcEnterpriseType = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblEntType\"]")).getText();
			String cipcEnterpriseStatus = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblEntStatus\"]")).getText();
			String cipcComplianceNoticeStatus = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblNonComply\"]")).getText();
			String cipcRegistrationDate = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblRegDate\"]")).getText();
			String cipcPhysicalAddress = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblPhysAddress\"]")).getText();
			String cipcPostalAddress = webDriver.findElement(By.xpath("//*[@id=\"ctl00_cntMain_TabContainer1_TabPanel1_lblPostalAddress\"]")).getText();
			
			return new EnterpriseDetails(cipcEnterpriseNumber, cipcEnterpriseName, cipcEnterpriseType, cipcEnterpriseStatus, cipcComplianceNoticeStatus, Dates.parse(cipcRegistrationDate, "yyyy-MM-dd"), cipcPhysicalAddress, cipcPostalAddress);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} finally {
			quitWebDriver();
		}
	}
	
	public static EnterpriseNameSearchResult[] getCipcEnterpriseDetailsByEnterpriseName(final String enterpriseName) {
		List<EnterpriseNameSearchResult> results = new ArrayList<>();
		
		//Create web driver, when necessary
		createWebDriver();
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
		
		try {
			webDriver.get(CIPC_ESERVICES_SEARCH_URL_STRING);
			
			List<WebElement> searchOptions = webDriverWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//*[@id=\"ctl00_cntMain_drpSearchOptions\"]/option"), 2));
			for (WebElement option : searchOptions) {
				if (option.getText().equals("Enterprise Name")) {
					option.click();
				}
			}//*[@id="ctl00_cntMain_drpSearchOptions"]/option[1]
			
			WebElement searchBox = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id=\"ctl00_cntMain_txtSearchCIPC\"]")));
			searchBox.sendKeys(enterpriseName);
//			searchBox.submit();
			
			WebElement searchLink = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ctl00_cntMain_lnkSearchIcon\"]"))); 
			searchLink.click();
			
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ctl00_cntMain_pnlNameSearch\"]")));
			
			List<WebElement> tableRows = webDriver.findElements(By.xpath("//*[@id=\"ctl00_cntMain_gdvNames\"]/tbody/tr"));
//			int columnSize = webDriver.findElements(By.xpath("//*[@id=\"ctl00_cntMain_gdvNames\"]/tbody/tr[1]/th")).size();
			
			for (int row = 1; row < tableRows.size(); row++) {
				EnterpriseNameSearchResult result = new EnterpriseNameSearchResult();
				
				List<WebElement> tableColumns = tableRows.get(row).findElements(By.tagName("td"));
				
				for (int column = 0; column < tableColumns.size(); column++) {
				
					String value = tableColumns.get(column).getText();
					
					switch(column) {
						case 0 : result.setEnterpriseName(value);
							break;
							
						case 1 : result.setEnterpriseTrackingNumber(value);
							break;
							
						case 2 : result.setStatus(value);
							break;
							
						default: break;
					}
				}
				
				//Add to List
				results.add(result);
			}
		} finally {
			quitWebDriver();
		}
		
		return results.toArray(new EnterpriseNameSearchResult[results.size()]);
	}
	
	private static void createWebDriver() {
		if (webDriver == null) {
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
		}
	}
	
	private static void quitWebDriver() {
		if (webDriver != null) {
			webDriver.quit();
			webDriver = null;
		}
	}
}
