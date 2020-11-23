package com.bungle.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Calendar;
import java.util.UUID;
import java.util.Base64.Decoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bungle.dto.Image;

@Service
public class ImageServiceImpl implements ImageService {

	
	@Value("${file.path.dictationsubmit}")
	private String dictationSubmitFilePath;
	@Value("${file.path.wordsubmit}")
	private String wordSubmitFilePath;
	
	// 저장 경로 설정용도
	private Path currentRelativePath = Paths.get("");
	private String s = currentRelativePath.toAbsolutePath().toString();

	@Override
	public boolean saveImage(Image img) throws IOException{
		// TODO Auto-generated method stub
		Decoder decoder = Base64.getDecoder();
		
		byte[] decodedByte = decoder.decode(img.getImgData().split(",")[1]);
		// Write the response to the output file.
		System.out.println(img.getFileName());
		String savePath;
		if(img.getType().equals("word")) {
			savePath = wordSubmitFilePath;
		}else {
			savePath = dictationSubmitFilePath;
		}
		try (OutputStream out = new FileOutputStream(savePath + "/" + img.getFileName())) {
			out.write(decodedByte);
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public String saveImage(MultipartFile file, String path) throws IOException {
		String url = null;
		String SAVE_PATH = s + path;

		System.out.println("Current relative path is: " + s);

		// 저장 이름 설정
		String originFilename = file.getOriginalFilename();
		String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
		System.out.println(originFilename);
		Long size = file.getSize();

		// 서버에서 저장 할 파일 이름
		String saveFileName = genSaveFileName(extName);
		System.out.println("originFilename : " + originFilename);
		System.out.println("extensionName : " + extName);
		System.out.println("size : " + size);
		System.out.println("saveFileName : " + saveFileName);
		String tempfile = new String();
		tempfile = saveFileName + extName;
		File target = new File(SAVE_PATH, tempfile);
		// 파일이 이미 존재하는 경우
		if (target.exists()) {
			System.out.println("파일이 중복됨");
			UUID uuid = UUID.randomUUID();
			System.out.println(uuid);
			String convertPw = UUID.randomUUID().toString().replace("-", "");
			saveFileName += convertPw + extName;
		} else {
			saveFileName += extName;
		}
		writeFile(file, saveFileName, SAVE_PATH);
		url = SAVE_PATH + "/" + saveFileName;
		return url;
	}

	@Override
	public String genSaveFileName(String extName) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		return fileName;
	}

	@Override
	public boolean writeFile(MultipartFile multipartFile, String saveFileName, String SAVE_PATH) throws IOException {
		boolean result = false;
		System.out.println(this.getClass().getResource("/").getPath());
		byte[] data = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
		fos.write(data);
		fos.close();
		return result;
	}

	@Override
	public byte[] getFile(String filePath) throws IOException {
		System.out.println(s);
		RandomAccessFile f;
		try {
			f = new RandomAccessFile(s + "/src/main/resources" + filePath, "r");

		} catch (Exception e) {
			f = new RandomAccessFile(s + "/src/main/resources/default.jpg", "r");
		}

		byte[] b = new byte[(int) f.length()];
		f.readFully(b);
		f.close();
		return b;
	}

	@Override
	public byte[] getVideo(String filePath) throws IOException {
		System.out.println(s);
		RandomAccessFile f;
		try {
			f = new RandomAccessFile(s + "/src/main/resources" + filePath, "r");

		} catch (Exception e) {
			return null;
		}
		byte[] b = new byte[(int) f.length()];
		f.readFully(b);
		f.close();
		return b;
	}

	@Override
	public void deleteFile(String filePath) throws IOException {
		System.out.println(filePath);
		String[] fileName = filePath.split("/");
		String tempPath = "\\src\\main\\resources";
		System.out.println(fileName[2]);
		File file = new File(s + tempPath + File.separator + fileName[1] + File.separator + fileName[2]);
		System.out.println(s + tempPath + File.separator + fileName[1] + File.separator + fileName[2]);
		if (file.exists()) {
			if (file.delete()) {
				System.out.println("파일삭제 성공");
			} else {
				System.out.println("파일삭제 실패");
			}
		} else {
			System.out.println("파일이 존재하지 않습니다.");
		}

	}

}
