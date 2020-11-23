package com.bungle.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

@RestController
public class TranslateContorller {
	private Translate translate;
	private String kakaoURL = "https://dapi.kakao.com/v2/translation/translate";
	
	@Value("${google.key.path}")
	private String keyFilePath;
	

	@PostConstruct
	public void init() {
		System.out.println("path = " + keyFilePath);
		try {
			translate = TranslateOptions.newBuilder()
					.setCredentials(ServiceAccountCredentials
							.fromStream(new FileInputStream(keyFilePath)))
					.build().getService();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GetMapping("/translate")
	public ResponseEntity<String> translate(String text) {


		/**
		 * 카카오
		 */
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "KakaoAK 3d61b324cd6a62e2fdc94f291a64f068");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("src_lang", "en");
		params.add("target_lang", "kr");
		params.add("query", text);

		// Create a new HttpEntity
		final HttpEntity entity = new HttpEntity(params, headers);

		// Execute the method writing your HttpEntity to the request
		ResponseEntity<String> response = restTemplate.exchange(kakaoURL, HttpMethod.POST, entity, String.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {			
			String result = response.getBody();
			System.out.println(result.substring("{\"translated_text\":[[\"".length(), result.length() - 4).replace("\\", ""));
			String[] arr = result.substring("{\"translated_text\":[[\"".length(), result.length() - 4).replace("\\", "").split("u");
			
			System.out.println(
					Arrays.toString(arr));
			
			StringBuffer output = new StringBuffer();
			try {
				for(int i = 1; i < arr.length; i++) {
					output.append((char)Integer.parseInt(arr[i].replace(" ", ""),16));
				}				
			} catch(NumberFormatException e) {
				output = new StringBuffer(text);
			}
			System.out.println(output);
			return new ResponseEntity<>(output.toString(),HttpStatus.OK);
		}else {
			Translation translation = translate.translate(text, TranslateOption.sourceLanguage("en"),
					TranslateOption.targetLanguage("ko"));
			
			return new ResponseEntity<>(translation.getTranslatedText(), HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/translatetokor")
	public ResponseEntity<String> translateToKor(String text) {


		/**
		 * 카카오
		 */
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "KakaoAK 3d61b324cd6a62e2fdc94f291a64f068");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("src_lang", "kr");
		params.add("target_lang", "en");
		params.add("query", text);

		// Create a new HttpEntity
		final HttpEntity entity = new HttpEntity(params, headers);

		// Execute the method writing your HttpEntity to the request
		ResponseEntity<String> response = restTemplate.exchange(kakaoURL, HttpMethod.POST, entity, String.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {			
			String result = response.getBody();
			System.out.println(result.substring("{\"translated_text\":[[\"".length(), result.length() - 4).replace("\\", ""));
			String output = result.substring("{\"translated_text\":[[\"".length(), result.length() - 4).replace("\\", "");

		
			System.out.println("출력:"+output);
			return new ResponseEntity<>(output,HttpStatus.OK);
		}else {
			Translation translation = translate.translate(text, TranslateOption.sourceLanguage("kr"),
					TranslateOption.targetLanguage("en"));
			
			return new ResponseEntity<>(translation.getTranslatedText(), HttpStatus.OK);
		}
	}
}
