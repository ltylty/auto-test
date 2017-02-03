/**
 * 
 */
package ulearning.autotest.common;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Predicate;

/**
 * @author WH1506041
 * @since 2016年12月29日
 * 
 */
public class CommonTest {
	/**
	 * @param driver
	 */
	public CommonTest(WebDriver driver,String baseUrl) {
		super();
		this.driver = driver;
		this.baseUrl = baseUrl;
	}

	private WebDriver driver;
	
	protected String baseUrl;
	
	public void login(String userName,String password) {
		driver.get(baseUrl);
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("loginId")).clear();
		driver.findElement(By.id("loginId")).sendKeys(userName);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.cssSelector("button.sign_in")).click();
		wait(2000);
	}
	
	public void logout() {
		driver.findElement(By.id("showAccount")).click();
		driver.findElement(By.cssSelector("a.ico3")).click();
		wait(2000);
	}
	
	/**
     * Checks if is element exsit.
     *
     * @param locator the locator
     * @return true, if is element exsit
     */
    public boolean isElementExsit(By locator) {
    	driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
    	boolean exists = driver.findElements(locator).size() != 0;
    	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
    	System.out.println(locator.toString()+":"+exists);
        return exists;
    }
    
    public void sendKey(String path){
		StringSelection stringSelection = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		try {
			Robot robot = new Robot();
			// 同时按下CONTROL+V，将上传文件路径黏贴到文件名
			robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
			robot.keyPress(java.awt.event.KeyEvent.VK_V);
			// 释放CONTROL+V
			robot.keyRelease(java.awt.event.KeyEvent.VK_V);
			robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
			//回车
			robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
			robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void wait(int millis){
    	try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void waitForLoad(){
    	Predicate<WebDriver> pageLoaded = new Predicate<WebDriver>() {
            @Override
            public boolean apply(WebDriver input) {
                return ((JavascriptExecutor) input).executeScript("return document.readyState").equals("complete");
            }
    	};
    	new FluentWait<WebDriver>(driver).until(pageLoaded);
    }
}
