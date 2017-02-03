/**
 * 
 */
package ulearning.autotest.common;

import org.openqa.selenium.WebDriver;

/**
 * @author WH1506041
 * @since 2017年1月3日
 * 
 */
public class Singleton {  
	
	private WebDriver driver;
    private String baseUrl;
    private String projectPath = new ulearning.autotest.App().projectPath;
    private CommonTest commonTest;
	
    private static class SingletonHolder {  
        private static final Singleton INSTANCE = new Singleton();  
    }  
    private Singleton (){
    	  System.setProperty("webdriver.chrome.driver", projectPath + "chromedriver.exe");
          this.driver = new MyChromeDriver(); 
//          this.baseUrl = "http://10.50.147.160:8080/ulearning";
          this.baseUrl = "http://localhost:8080/drgo";
          this.commonTest = new CommonTest(driver, baseUrl);
    }  
    public static final Singleton getInstance() {  
        return SingletonHolder.INSTANCE; 
    }
    
	/**
	 * @return the driver
	 */
	public WebDriver getDriver() {
		return driver;
	}
	/**
	 * @return the baseUrl
	 */
	public String getBaseUrl() {
		return baseUrl;
	}
	/**
	 * @return the projectPath
	 */
	public String getProjectPath() {
		return projectPath;
	}
	/**
	 * @return the commonTest
	 */
	public CommonTest getCommonTest() {
		return commonTest;
	}
    
    
}