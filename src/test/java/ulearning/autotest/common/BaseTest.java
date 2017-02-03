/**
 * 
 */
package ulearning.autotest.common;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.openqa.selenium.WebDriver;

/** @author WH1506041
 * @since 2016年12月16日 */
public abstract class BaseTest {

    protected WebDriver driver;
    protected String baseUrl;
    protected String projectPath;
    protected CommonTest commonTest;

    
    /**
	 * 
	 */
	public BaseTest() {
		super();
		// TODO Auto-generated constructor stub
		Singleton singleton = Singleton.getInstance();
		driver = singleton.getDriver();
		baseUrl = singleton.getBaseUrl();
		commonTest = singleton.getCommonTest();
		projectPath = singleton.getProjectPath();
	}

	@Before
    public void setUp() throws Exception {

        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        //driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        template();
    }

    /*@After*/
    public void tearDown() throws Exception {
        driver.quit();
    }

    public void template() {
        commonTest.login("86teacher1", "123456q");
    }

}
