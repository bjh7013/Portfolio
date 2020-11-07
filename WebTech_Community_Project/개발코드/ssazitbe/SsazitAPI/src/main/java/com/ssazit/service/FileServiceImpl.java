package com.ssazit.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.resource.ResourceResolver;

import com.ssazit.dto.FileDTO;

@Service
public class FileServiceImpl implements FileService{

//	@Autowired
//	ResourceLoader resourceLoader;
	
	// 책 커버 tmp폴더에 작성
	@Override
	public List<FileDTO> createBookCover(
								MultipartFile frontFile, 
								MultipartFile middleFile, 
								MultipartFile backFile,
								int userNo
									) throws Exception {
		List<FileDTO> result = new ArrayList<>();
//		String path = "./src/main/resources/static/resource/temp/";
		String path = "./classes/static/resource/temp/";
		Resource res;
		
		// frontFile 처리
		if(frontFile != null) {
			String[] tmp = frontFile.getContentType().split("/");
			String FileName = userNo + "_bookcover_front";
			String Extension = tmp[tmp.length-1].toUpperCase();
			try (FileOutputStream fos = new FileOutputStream(path + FileName + "." + Extension);
					InputStream is = frontFile.getInputStream();) {
				int readCount = 0;
				byte[] buffer = new byte[1024];
				while ((readCount = is.read(buffer)) != -1) {
					System.out.println("front file create ing...");
					fos.write(buffer, 0, readCount);
				}
				System.out.println("front file create done!");
				result.add(new FileDTO(path, FileName, Extension));
			} catch(Exception e) {
				throw new RuntimeException("front file Save Error");
			}			
			
		}
		
		// middleFile 처리
		if(middleFile != null) {
			String[] tmp = middleFile.getContentType().split("/");
			String FileName = userNo + "_bookcover_middle";
			String Extension = tmp[tmp.length-1].toUpperCase();
			try (FileOutputStream fos = new FileOutputStream(path + FileName + "." + Extension);
					InputStream is = middleFile.getInputStream();) {
				int readCount = 0;
				byte[] buffer = new byte[1024];
				while ((readCount = is.read(buffer)) != -1) {
					System.out.println("middle file create ing...");
					fos.write(buffer, 0, readCount);
				}
				System.out.println("middle file create done!");
				result.add(new FileDTO(path, FileName, Extension));
			} catch(Exception e) {
				throw new RuntimeException("middle file Save Error");
			}			
		}
		
		// backFile 처리
		if(backFile != null) {
			String[] tmp = backFile.getContentType().split("/");
			String FileName = userNo + "_bookcover_back";
			String Extension = tmp[tmp.length-1].toUpperCase();
			try (FileOutputStream fos = new FileOutputStream(path + FileName + "." + Extension);
					InputStream is = backFile.getInputStream();) {
				int readCount = 0;
				byte[] buffer = new byte[1024];
				while ((readCount = is.read(buffer)) != -1) {
					System.out.println("back file create ing...");
					fos.write(buffer, 0, readCount);
				}
				System.out.println("back file create done!");
				result.add(new FileDTO(path, FileName, Extension));
			} catch(Exception e) {
				throw new RuntimeException("back file Save Error");
			}			
		}
		
		return result;
	}

	
	// 책 커버 tmp폴더에서 책폴더로 복사
	@Override
	public List<FileDTO> copyBookCover(List<FileDTO> list, int bookNo) throws Exception {
		// 책 넘버에 해당하는 폴더 생성 
		String ckuse = "";
		List<FileDTO> result = new ArrayList<>();
		
//		File Folder = new File("./src/main/resources/static/resource/book_img/" + Integer.toString(bookNo));
		File Folder = new File("./classes/static/resource/book_img/" + Integer.toString(bookNo));
		if (!Folder.exists()) {
			try{
			    Folder.mkdirs(); //폴더 생성합니다.
			    System.out.println("폴더가 생성되었습니다.");
	        } 
	        catch(Exception e){
			    e.getStackTrace();
			    System.out.println("폴더 생성에 문제있어?!");
			}        
	    }else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}
		
		// 반복문 돌면서 파일 복사
		for(int i=0; i<list.size(); i++) {
			String oripath = list.get(i).getPath();
			String oriname = list.get(i).getFileName();
			String oriext = list.get(i).getExtension();
			
			if(oriname.contains("front")) {
				ckuse = "front";
			} else if(oriname.contains("middle")) {
				ckuse = "middle";
			} else if(oriname.contains("back")) {
				ckuse = "back";
			} else {
				System.out.println("알수 없는 파일입니다.");
			}
			
//			String coppath = "./src/main/resources/static/resource/book_img/" + Integer.toString(bookNo) + "/";
			String coppath = "./classes/static/resource/book_img/" + Integer.toString(bookNo) + "/";
			String copname = Integer.toString(bookNo) + "_" + ckuse;
			String copext = oriext;
			
			try {
				FileOutputStream fos = new FileOutputStream(coppath + copname + "." + copext);
				FileInputStream fis = new FileInputStream(oripath + oriname + "." + oriext);
				
				int readCount = 0;
				byte[] buffer = new byte[1024];
				while ((readCount = fis.read(buffer)) != -1) {
					System.out.println("file copy ing...");
					fos.write(buffer, 0, readCount);					
				}
				
				System.out.println(i + ") file copy done!");
				fis.close();
				fos.close();
			} catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			
			// 복사된 파일 경로, 파일명, 확장자 리스트에 넣어서 리턴
			result.add(new FileDTO(coppath,copname,copext));
		}
		
		return result;
	}

	// 책 커버 tmp폴더에서 삭제
	@Override
	public boolean deleteTemp(List<FileDTO> list) throws Exception {
		// TODO Auto-generated method stub
		for(int i=0; i<list.size(); i++) {
			File file = new File(list.get(i).getPath() + list.get(i).getFileName() + "." + list.get(i).getExtension());
			if( file.exists() ){ 
				if(file.delete()){ 
					System.out.println(i + ") 파일삭제 성공"); 
				} else{ 
					System.out.println(i + ") 파일삭제 실패"); 
					return false;
				} 
			}else{ 
				System.out.println(i + ") 파일이 존재하지 않습니다.");
				return false;
			}
		}

		return true;
	}


	@Override
	public boolean deleteBookCover(int bookNo) throws Exception {
		// TODO Auto-generated method stub
//		String path = "./src/main/resources/static/resource/book_img/" + Integer.toString(bookNo);
		String path = "./classes/static/resource/book_img/" + Integer.toString(bookNo);
		File folder = new File(path);
		try {
		    while(folder.exists()) {
				File[] folder_list = folder.listFiles(); //파일리스트 얻어오기
						
				for (int j = 0; j < folder_list.length; j++) {
					folder_list[j].delete(); //파일 삭제 
					System.out.println("파일이 삭제되었습니다.");
							
				}
						
				if(folder_list.length == 0 && folder.isDirectory()){ 
					folder.delete(); //대상폴더 삭제
					System.out.println("폴더가 삭제되었습니다.");
				}
		    }
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("파일 삭제에 실패했습니다.");
			return false;
		}
		
		return true;
	}
	
}
