package com.ssazit.controller;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.ssazit.dto.FileDTO;
import com.ssazit.dto.UploadResult;
import com.ssazit.dto.User;
import com.ssazit.service.FileService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	FileService fileService;
	@Autowired
	ResourceLoader resourceLoader;
	
	@ApiOperation(value = "책 커버를 tmp폴더에 저장 / 성공여부에 따라 List<file> 반환한다.", response = List.class)
	@PostMapping("/create_book_cover")
	public ResponseEntity<List<FileDTO>> createBookCover(
												@RequestParam(value="frontFile", required=false) MultipartFile frontFile,
												@RequestParam(value="middleFile", required=false) MultipartFile middleFile,
												@RequestParam(value="backFile", required=false) MultipartFile backFile,
												HttpServletRequest request
													){
		System.out.println("controller(File) | createBookCover");
		
		User userinfo = (User)request.getAttribute("userinfo");
		int userNo = userinfo.getUserNo();
		
		System.out.println(frontFile);
		System.out.println(middleFile);
		System.out.println(backFile);
		System.out.println(userNo);
		
		List<FileDTO> result;
		try {
			result = fileService.createBookCover(frontFile, middleFile, backFile, userNo);
			if(result != null) {
				return new ResponseEntity<List<FileDTO>>(result, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<List<FileDTO>>(new ArrayList<FileDTO>(), HttpStatus.NO_CONTENT);				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<List<FileDTO>>(new ArrayList<FileDTO>(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/articleimg")
	public ResponseEntity<UploadResult> uploadArticleImg(@RequestParam("upload") MultipartFile articleImg, HttpServletRequest request){
		System.out.println("controller(articleImg) | uploadArticleImg");		
		System.out.println(articleImg);

		
		if(!articleImg.getContentType().toLowerCase().startsWith("image/")) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		String uploadPath = "./classes/static/img/";
		String fileName = UUID.randomUUID().toString();
		uploadPath += fileName;
		
		String extension = articleImg.getOriginalFilename().substring(articleImg.getOriginalFilename().lastIndexOf("."));
		System.out.println(extension);
		if(!(extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg") || extension.equalsIgnoreCase(".gif") || extension.equalsIgnoreCase("bmp"))) {
			return new ResponseEntity<UploadResult>(HttpStatus.BAD_REQUEST);
		}
		
		uploadPath += extension;
		
		
		System.out.println("uploadPath ====================================== " + uploadPath);
					
		UploadResult result = new UploadResult();
		try(FileOutputStream fos = new FileOutputStream(uploadPath)) {
			fos.write(articleImg.getBytes());
			
			result.setUploaded(1);
			result.setFileName(fileName + extension);
			result.setUrl("http://i3d104.p.ssafy.io/img/" + fileName + extension);
			fos.close();
			
//			Resource resource = resourceLoader.getResource("classpath:static/img/" + fileName + extension);
			
			final HttpHeaders headers = new HttpHeaders();
	        RestTemplate restTemplate = new RestTemplate();
	        
	        //Create a new HttpEntity
	        final HttpEntity<String> entity = new HttpEntity<String>(headers);
	        
	        //Execute the method writing your HttpEntity to the request
	        

			while(true) {
				ResponseEntity<String> response = restTemplate.exchange("http://localhost/img/" + fileName + extension, HttpMethod.GET, entity,String.class);        
				System.out.println(response.getStatusCode());
				if(response.getStatusCode() == HttpStatus.OK) {
					break;
				}
				Thread.sleep(100);
			}
			
			return new ResponseEntity<UploadResult>(result, HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
