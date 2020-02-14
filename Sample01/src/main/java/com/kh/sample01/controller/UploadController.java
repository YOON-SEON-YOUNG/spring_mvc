package com.kh.sample01.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.sample01.service.BoardService;
import com.kh.sample01.util.FileUploadUtil;

@Configuration
@RequestMapping("/upload")
public class UploadController {

	@Resource
	private String uploadPath; // servlet-context.xml (id="uploadPath")
	
	@Inject
	private BoardService boardService;
	
	
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST,
					produces="text/plain; charset=utf-8")
	@ResponseBody
	public String uploadAjax(MultipartFile file) throws Exception {
		
		String originalFilename = file.getOriginalFilename();
		String dirPath = FileUploadUtil.uploadFile(uploadPath, originalFilename, file.getBytes());
		String path = dirPath.replace("\\", "/"); // "\"를 "/"로 변경
		
		return path;
		
	}
	
	// 썸네일받기
	@RequestMapping(value = "/displayFile", method = RequestMethod.GET)
	@ResponseBody
	public byte[] displayFile(@RequestParam("fileName") String fileName) throws Exception {
		
		String realPath = uploadPath + File.separator + fileName.replace("\\", "/"); // 실제경로 넘기기
		FileInputStream is = new FileInputStream(realPath);
		byte[] bytes = IOUtils.toByteArray(is);
		is.close();
		return bytes;
	}
	
	
	// 첨부파일 삭제
	@RequestMapping(value = "/deleteFile", method = RequestMethod.GET)
	@ResponseBody
	public String deleteFile(@RequestParam("fileName") String fileName) throws Exception	{
		System.out.println("fileName: " + fileName);
		FileUploadUtil.delete(fileName, uploadPath); // 파일 삭제 (uploadUtil 참고)
		return "success";
	}
	
	
	// 첨부파일 & 데이터 삭제
	@RequestMapping(value = "/deleteFileAndData", method = RequestMethod.GET)
	@ResponseBody
	public String deleteFileAndData(@RequestParam("fileName") String fileName) throws Exception	{
		System.out.println("fileName: " + fileName);
		FileUploadUtil.delete(fileName, uploadPath); // 파일 삭제 (uploadUtil 참고)
		boardService.deleteAttach(fileName); // 데이터 삭제
		return "success";
	}
		
	
		
}
