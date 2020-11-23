package com.bungle.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Base64.Decoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bungle.dto.Audio;

@RestController
public class VoiceController {
	@Value("${file.path.record}")
	private String recordFilePath;

//	@PostMapping("/recordvoice")
//	public void recordVoice(@RequestBody Audio audio) {
//		
//        Decoder decoder = Base64.getDecoder();
//        byte[] decodedByte = decoder.decode(audio.getAudioData().split(",")[1]);
//		// Write the response to the output file.
//		try (OutputStream out = new FileOutputStream(recordFilePath + "/" + audio.getAudioText() + ".mp3")) {
//			out.write(decodedByte);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
