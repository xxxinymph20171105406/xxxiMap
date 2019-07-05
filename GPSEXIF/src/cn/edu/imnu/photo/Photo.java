package cn.edu.imnu.photo;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;


public class Photo {
	public static void main(String[] args) throws Exception, Exception{
		File file = new File("C:\\Users\\hp\\Desktop\\java\\house.jpg");
		System.out.println("File Name: " + file.getName());
		printImageTags(file);
	}
	private static void printImageTags(File file) throws ImageProcessingException ,Exception{
		Metadata metadata = ImageMetadataReader.readMetadata(file);
		
		for(Directory directory : metadata.getDirectories()){
			for(Tag tag : directory.getTags()) {
				String tagName = tag.getTagName();
				String info = tag.getDescription();
				if(tagName.equals("Exif Image Height")) {
					System.out.println("图片高度 ： " + info);
				    //JOptionPane.showMessageDialog(null, "图片高度:" + info);
				} 
				if(tagName.equals("Exif Image Width")) {
					System.out.println("图片宽度： " + info);
					//JOptionPane.showMessageDialog(null, "图片宽度:" + info);
				}
				if(tagName.equals("Size")) {
					System.out.println("图片大小： " + info);
				}
				if(tagName.equals("Exposure Time")) {
					System.out.println("曝光时间： " + info);
				}
				if(tagName.equals("Date/Time Original")) {
					System.out.println("拍摄时间： " + info);
					//JOptionPane.showMessageDialog(null, "拍摄时间:" + info);
				}
				if(tagName.equals("GPS Latitude")) {
					//System.out.println("纬度：" + info);
					//JOptionPane.showMessageDialog(null, "纬度:" + info);
					System.out.println("经度：" + pointToLatlong(info));
				}
				if(tagName.equals("GPS Longitude")) {
					//System.err.println("经度： " + info);
					//JOptionPane.showMessageDialog(null, "经度:" + info);
					System.out.println("纬度：" + pointToLatlong(info));
				}
				if(tagName.equals("Model")) {
					System.out.println("型号： " + info);
					
				}
				
			}
		}
		
	}
	public static String pointToLatlong (String point ) {
		Double du = Double.parseDouble(point.substring(0, point.indexOf("°")).trim());  
		Double fen = Double.parseDouble(point.substring(point.indexOf("°")+1, point.indexOf("'")).trim());  
		Double miao = Double.parseDouble(point.substring(point.indexOf("'")+1, point.indexOf("\"")).trim());  
	    Double duStr = du + fen / 60 + miao / 60 / 60 ;  
		return duStr.toString();  
		
	
				
		
		
		
	}
	
		
		
	
	

	
	
	
}  
	
