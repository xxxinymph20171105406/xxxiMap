package cn.edu.imnu.photo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class File {
	private static String Path = "C:\\Users\\hp\\Desktop\\java" ;
	
	private static String filenameTemp;
	
	

	public static boolean createFile(String fileName,String filecontent) {
		Boolean bool = false;
		filenameTemp = Path+fileName+".txt";
		File file = new File();
		try {
			if(!file.exists()) {
				try {
					file.createNewFile();
					
				}catch (IOException e) {
					e.printStackTrace();
				}
				
				bool = true;
				System.out.println("success create file,the file is  " + filenameTemp);
				writeFileContent(filenameTemp,filecontent);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return bool;
		
	}

	

	private static boolean writeFileContent(String filepath, String newstr)throws IOException {
		Boolean bool = false;
		String filein = newstr + "\r\n";
		String temp = "";
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		FileOutputStream fos = null;
		PrintWriter pw = null;
		
		try {
			File file = new File(filepath);
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			StringBuffer buffer = new StringBuffer();
			
			for(int i = 0 ;(temp = br.readLine())!= null ; i++) {
				buffer.append(temp);
				buffer = buffer.append(System.getProperty("line.separator"));
				
			}
			buffer.append(filein);
			
			
			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);
			pw.write(buffer.toString().toCharArray());
			pw.flush();
			bool = true;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pw != null) {
				pw.close();
			}
			if(fos != null) {
				fos.close();
			}
			if(br != null) {
				br.close();
			}
			if(isr != null) {
				isr.close();
				
			}
			if(fis != null) {
				fis.close();
			}
		}
		
		
	
		
		
		
		
		return bool;
		
	}

}
