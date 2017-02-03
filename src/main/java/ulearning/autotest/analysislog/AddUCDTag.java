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
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author WH1506041
 * @since 2017年1月6日
 * 
 */
public class AddUCDTag {
	private static String actionMethodPath = "C:\\Users\\wh1506041\\Desktop\\ActionMethod.txt";
	private static String projectPath = "E:\\WorkSpace\\Drgo\\src\\com\\wistron\\wh\\ets\\drgo\\action";
	
	public static void main(String[] args) {
		List<String> stringList = readFileAsList();
		Iterator<String> stringIterator = stringList.iterator();
		while (stringIterator.hasNext()) {
			String string = stringIterator.next();
			addTag(string);
		}
	}
	
	public static List<String> readFileAsList(){

        File file = new File(actionMethodPath);
        BufferedReader reader = null;
        
        List<String> stringList = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
            	stringList.add(tempString);
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
    
		return stringList;
	}
	
	public static void addTag(String str){
		String namespace = "";
		try {
			namespace = str.split(",")[0].split(":")[1];
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
		String action = str.split(",")[1].split(":")[1];
		String method = str.split(",")[2].split(":")[1];
		String filePath = findFilePathV2(action);
		if(filePath != null){
			writeFile(method,filePath);
		}
	}
	
	public static String findFilePathV2(String action) {
		Path dir = Paths.get(projectPath);
		final List<File> files = new ArrayList<File>();
		final String tempAction = new String(action);
		SimpleFileVisitor<Path> finder = new SimpleFileVisitor<Path>(){
		    @Override
		    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//		    	System.out.println(tempAction);
		    	String filePath = file.toFile().toString().toUpperCase();
		    	filePath = filePath.substring(filePath.lastIndexOf("\\")+1, filePath.lastIndexOf("."));
		    	if(filePath.startsWith(tempAction.toUpperCase())){
		    		files.add(file.toFile());
		    	}
		        return super.visitFile(file, attrs);
		    }
		};
		 
		try {
			java.nio.file.Files.walkFileTree(dir, finder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(files.size() != 1){
			System.out.println(action);
			return null;
		}else{
			return files.get(0).getAbsolutePath();
		}
	}
	
	public static void writeFile(String method,String filePath){
		BufferedReader br = null;
        String line = null;
        StringBuffer buf = new StringBuffer();
        BufferedWriter bw = null;
        try {
            // 根据文件路径创建缓冲输入流
            br = new BufferedReader(new FileReader(filePath));
            
            // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
            while ((line = br.readLine()) != null) {
                // 此处根据实际需要修改某些行的内容
            	buf.append(line);
            	if(line.contains("class") && line.contains("public") && !line.contains("NO_UCD")){
            		buf.append("// NO_UCD");
            	}
            	if(line.contains(method) && line.contains("public") && !line.contains("NO_UCD")){
            		buf.append("// NO_UCD");
            	}
                buf.append(System.getProperty("line.separator"));
            }
            
            bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(buf.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                }
            }
            // 关闭流
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    bw = null;
                }
            }
            System.out.println(filePath);
        }
	}
}
