/**
 * 
 */
package ulearning.autotest.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author WH1506041
 * @since 2016年12月20日
 * 
 */
public class MyChromeDriver extends ChromeDriver{

	/* (non-Javadoc)
	 * @see org.openqa.selenium.remote.RemoteWebDriver#findElement(org.openqa.selenium.By)
	 */
	
	public WebElement findElement(By by) {
		Singleton.getInstance().getCommonTest().wait(1000);
		return super.findElement(by);
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.remote.RemoteWebDriver#switchTo()
	 */
	
	public TargetLocator switchTo() {
		// TODO Auto-generated method stub
		Singleton.getInstance().getCommonTest().wait(2000);
		return super.switchTo();
	}
	
	

}
