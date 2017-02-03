/**
 * 
 */
package ulearning.autotest.analysislog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author WH1506041
 * @since 2017年1月6日
 * 
 */
public class FindActionMethod {
	
	public static void main(String[] args) {
		String fileName = "C:\\Users\\wh1506041\\Desktop\\catalina.out";
		readFileByLines(fileName);
	}
	
	 /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        
        Set<String> stringSet = new TreeSet<String>();
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                if(tempString.contains("Executed action")){
                	tempString = tempString.substring(tempString.indexOf("Executed action"));
                	tempString = tempString.substring(tempString.indexOf("[")+1, tempString.indexOf("]"));
                	stringSet.add(tempString);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                	e.printStackTrace();
                }
            }
        }
        writeFile(stringSet);
    }
    
    public static void writeFile(Set<String> stringSet){
    	Iterator<String> stringIterator = stringSet.iterator();
    	String filePath = "C:\\Users\\wh1506041\\Desktop\\ActionMethod.txt";
    	File file = new File(filePath);
    	if(file.exists()){
    		file.delete();
    	}
    	BufferedWriter out = null;
    	try {
			file.createNewFile();
			
			out = new BufferedWriter(new FileWriter(file));
			String str = "";
			while(stringIterator.hasNext()){
				String strTemp = stringIterator.next();
				if(strTemp.contains("_")){
					str = "namespace:" + strTemp.substring(strTemp.indexOf("/")+1,strTemp.indexOf("/", 1))
							+ ",action:" + strTemp.substring(strTemp.indexOf("/",1)+1,strTemp.indexOf("_"))
							+ ",method:" + strTemp.substring(strTemp.indexOf("_")+1,strTemp.indexOf("!"));
				}else{
					str = "namespace:" + strTemp.substring(strTemp.indexOf("/")+1,strTemp.indexOf("/", 1))
							+ ",action:" + strTemp.substring(strTemp.indexOf("/",1)+1,strTemp.indexOf("!"))
							+ ",method:" + strTemp.substring(strTemp.indexOf("!")+1);
				}
				System.out.println(str);
	        	System.out.println(strTemp);
	        	out.write(str+System.getProperty("line.separator"));
	        }
			
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} finally {
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }
}
