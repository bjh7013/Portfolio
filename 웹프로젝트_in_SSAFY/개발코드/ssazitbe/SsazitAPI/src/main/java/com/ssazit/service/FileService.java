package com.ssazit.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssazit.dto.FileDTO;

public interface FileService {

	// 책 커버 tmp폴더에 작성
	public List<FileDTO> createBookCover(MultipartFile frontFile, MultipartFile middleFile, MultipartFile backFile, int userNo) throws Exception;
	
	// 책 커버 tmp폴더에서 책폴더로 복사
	public List<FileDTO> copyBookCover(List<FileDTO> list, int bookNo) throws Exception;
	
	// 책 커버 tmp폴더에서 삭제
	public boolean deleteTemp(List<FileDTO> list) throws Exception;
	
	// 책 커버 책폴더에서 삭제
	public boolean deleteBookCover(int bookNo) throws Exception;
	
	
}
