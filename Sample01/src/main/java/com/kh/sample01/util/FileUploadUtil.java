package com.kh.sample01.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class FileUploadUtil {
	
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
		UUID uuid = UUID.randomUUID();	// 겹치지 않는 고유한 값 생성
		System.out.println("uuid: " + uuid);
		
		String uuidName = uuid + "_" + originalName;
		System.out.println("uuidName: " + uuidName);
		
		String datePath = calcPath(uploadPath);
		
		// D:/upload/2020/1/20/<uuid>_파일명
		String filePath = uploadPath + File.separator + datePath + File.separator + uuidName;
		// 파일 객체(바이너리 데이터 복사)
		File target = new File(filePath);
		FileCopyUtils.copy(fileData, target);
		
		String formatName = getFormatName(originalName);
		System.out.println("formatName: " + formatName);
		
		boolean isImage = isImage(formatName);
		System.out.println("isImage: " + isImage);
		
		if(isImage == true) {
			makeThumbnail(uploadPath, datePath, uuidName);		// 썸네일 만들기
		}
		
		// 2020/1/20/<uuid>_filename
		return datePath + File.separator + uuidName;
	}
	
	
	// 날짜에 해당하는 폴더 계산 -> 2020/1/20
	public static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		String yearPath = "" + cal.get(Calendar.YEAR);									// 2020
		String monthPath = yearPath + File.separator + (cal.get(Calendar.MONTH) + 1);	// 2020/1
		String datePath = monthPath + File.separator + cal.get(Calendar.DATE);			// 2020/1/20
		System.out.println("dataPath: " + datePath);
		
		String dirPath = uploadPath + File.separator + datePath; // D:/upload/2020/1/20
		
		File f = new File(dirPath);
		// 해당 경로(폴더)가 존재 하지 않으면 생성
		if (!f.exists()) {
			f.mkdirs();	// 하위 폴더까지 생성
		}
		
		return datePath;
	}
	
	
	// 확장자 얻기 (.jpg, .의 위치를 얻고 그 뒤의 텍스트가 이미지이면 썸네일할거니까..ㅎㅅㅎ)
	public static String getFormatName(String fileName) {
		int dotIndex = fileName.lastIndexOf(".");	// 뒤에서부터 읽기. "."부터
		String formatName = fileName.substring(dotIndex + 1);
		
		return formatName;
	}
	
	
	// 이미지 인지 아닌지
	public static boolean isImage(String formatName) {
		String[] arrFormat = {"JPG", "PNG", "GIF"}; // 비교할 확장자
		for(String aFormat : arrFormat) {
			if (formatName.toUpperCase().equals(aFormat)) {
				return true;
			}
		}
		
		return false;
	}

	// 썸네일 이미지 생성
	public static void makeThumbnail(String uploadPath, String dirPath, String uuidName) throws Exception	{

		String uploadedPath = uploadPath + File.separator 
							+ dirPath + File.separator 
							+ uuidName;	// 실제 업로드된 파일명
										//  D:/upload/2020/1/20/<uuid>_파일명
		
		// 업로드된 원본 이미지 파일을 읽어서 메모리에 로딩
		BufferedImage sourceImg = ImageIO.read(new File(uploadedPath));	
		
		// pom.xml - imgscalr-lib
		BufferedImage destImg = Scalr.resize(sourceImg, 
											Scalr.Method.AUTOMATIC, 
											Scalr.Mode.FIT_TO_HEIGHT,
											100);
		
		// 파일의 이름
		String thumnailName = uploadPath + File.separator 
							+ dirPath + File.separator 
							+ "s_" + uuidName; //  D:/upload/2020/1/20/s_<uuid>_파일명
		
		// 파일 객체
		File tager = new File(thumnailName);
		
		ImageIO.write(destImg, getFormatName(uuidName), tager);
		
		
	}
	
	
	// 파일을 하나 받는 경우 오버로딩
	public static void delete(String fileName, String uploadPath) throws Exception	{
		List<String> list = new ArrayList<>();
		list.add(fileName);
		delete(list, uploadPath);
	}
	
	
	// 파일 삭제 (컨트롤에서 데려옴...)
	public static void delete(List<String> fileNames, String uploadPath) throws Exception	{
		
		for (String fileName : fileNames) {
		
			String filePath = uploadPath + File.separator + fileName.replace("/", "\\");
			
			File f = new File(filePath);
			if (f.exists())	{	// 파일이 존재하면 삭제
				f.delete();
			}
			
			// 썸네일 삭제
			String formatName = FileUploadUtil.getFormatName(fileName);
			boolean isImage = FileUploadUtil.isImage(formatName);
			System.out.println("isImage: " + isImage);
			if (isImage == true)	{
				// 2020/1/20/8f120ba2-67c0-4e18-b71c-12a8c787b281_peng02.jpg
				int slashIndex = fileName.lastIndexOf("/");
				String front = fileName.substring(0, slashIndex + 1);
				String rear = fileName.substring(slashIndex + 1);
				String thumbnailName = front + "s_" + rear;
				String thumbnailPath = uploadPath + File.separator + thumbnailName.replace("/", "\\");
				System.out.println("thumbnailPath: " + thumbnailPath);
				
				File fThumb = new File(thumbnailPath);
				
				if (fThumb.exists()) {	// 파일이 존재하면 삭제
					System.out.println("exists");
					fThumb.delete();
				}
			}
		} // for
	} // delete
	
	
}
