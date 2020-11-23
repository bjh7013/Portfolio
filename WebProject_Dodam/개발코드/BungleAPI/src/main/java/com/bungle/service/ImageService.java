package com.bungle.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.bungle.dto.Image;

public interface ImageService {
	public String saveImage(MultipartFile file,String path) throws IOException;
	String genSaveFileName(String extName);
	boolean writeFile(MultipartFile multipartFile, String saveFileName,String SAVE_PATH) throws IOException;
	byte[]  getFile(String userNo) throws IOException;
	void deleteFile(String filePath) throws IOException;
	byte[] getVideo(String filePath) throws IOException;
	public boolean saveImage(Image img) throws IOException;
}
