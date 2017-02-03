/**
 * 
 */
package ulearning.autotest.analysislog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
 * @since 2017年1月9日
 * 
 */
public class ModifyMethodAsDeprecated {
	
	
		private static String projectPath = "E:\\WorkSpace\\Drgo\\src\\com\\wistron\\wh\\ets\\drgo\\action";
		
		public static void main(String[] args) {
			List<String> filePathList = findFilePathV2();
			Iterator<String> stringIterator = filePathList.iterator();
			while (stringIterator.hasNext()) {
				String string = stringIterator.next();
				writeFile(string);
			}
		}
		
		public static List<String> findFilePathV2() {
			Path dir = Paths.get(projectPath);
			final List<String> filePathList = new ArrayList<String>();
			SimpleFileVisitor<Path> finder = new SimpleFileVisitor<Path>(){
			    @Override
			    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			    	filePathList.add(file.toFile().getAbsolutePath());
			        return super.visitFile(file, attrs);
			    }
			};
			 
			try {
				java.nio.file.Files.walkFileTree(dir, finder);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return filePathList;
		}
		
		public static void writeFile(String filePath){
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
	            	if( line.contains("public") && line.contains("(") && line.contains(")") && line.contains("{") 
	            			&& !line.contains("NO_UCD") && !line.contains("get") && !line.contains("set") ){
	            		line = line.substring(0, line.indexOf("(")) + "Bak" + line.substring(line.indexOf("("));
	            	}
	            	
	            	buf.append(line);
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
