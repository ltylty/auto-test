package ulearning.autotest;


/**
 * Hello world!
 *
 */
public class App 
{
	public String projectPath;

	/**
	 * 
	 */
	public App() {
		super();
		// TODO Auto-generated constructor stub
		ClassLoader loader = this.getClass().getClassLoader();
		
		this.projectPath = loader.getResource("ulearning/autotest/App.class").toString();
		this.projectPath = this.projectPath.substring(this.projectPath.indexOf("/")+1, this.projectPath.lastIndexOf("/")+1 );
		this.projectPath = this.projectPath.replace("/", "\\");
	}
    
}
