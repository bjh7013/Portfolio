package com.bungle.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bungle.dao.DictationDAO;
import com.bungle.dto.Dictation;
import com.bungle.dto.SpeechInfo;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.TextToSpeechSettings;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
import com.google.protobuf.ByteString;


@RestController
public class TTSController {
	private String kakaoURL = "https://kakaoi-newtone-openapi.kakao.com/v1/synthesize";
	@Autowired
	ResourceLoader resourceLoader;
	@Autowired
	DictationDAO dictationDAO;
	
	@Value("${google.key.path}")
	private String keyFilePath;
	@Value("${file.path.audio}")
	private String audioFilePath;
	@Value("${resource.path.audio}")
	private String audioResourcePath;
	@Value("${static_path}")
	private String staticPath;
	

	private TextToSpeechSettings settings;

	@PostConstruct
	public void init() {
		try {

			CredentialsProvider credentialsProvider = FixedCredentialsProvider
					.create(ServiceAccountCredentials.fromStream(new FileInputStream(keyFilePath)));

			settings = TextToSpeechSettings.newBuilder().setCredentialsProvider(credentialsProvider).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@PostMapping("kakao")
	public void kakao(@RequestBody String text) {
		System.out.println(text);
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "KakaoAK 3d61b324cd6a62e2fdc94f291a64f068");

		headers.setContentType(MediaType.APPLICATION_XML);

		// Create a new HttpEntity
		HttpEntity entity = new HttpEntity(text, headers);

		System.out.println(entity);
		System.out.println(entity.getHeaders());
		System.out.println(entity.getBody());

		// Execute the method writing your HttpEntity to the request
		ResponseEntity<String> response = restTemplate.exchange(kakaoURL, HttpMethod.POST, entity, String.class);
//		System.out.println(response);
//		System.out.println(response.getHeaders());
		System.out.println("-------------------------------------");
		System.out.println("-------------------------------------");
		System.out.println("-------------------------------------");
		System.out.println("-------------------------------------");
//		System.out.println(response.getBody().substring(0,10));
		System.out.println(response.getBody());
		
		//	Base64Decoder decoder = new Base64Decoder();
//		byte[] brr = response.getBody().getBytes();
//		brr = decoder.decode(brr, 0, 100);
		
//		File
//		try (OutputStream out = new FileOutputStream(audioFilePath + "/kakao.mp3")) {
////			System.out.println();
//			out.write(brr);
//			
//			System.out.println("Audio content written to file \"output.mp3\"");
//		} catch (IOException e) {
////			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

//	@GetMapping("/dictationtts")
//	public ResponseEntity<HttpStatus> dictationTTS(){
//		System.out.println("fsdfsdfsdfsdfs");
//		Dictation dictation = new Dictation();
//		List<Dictation> list = dictationDAO.selectAllDictation(dictation);
//		
//		for(Dictation d : list) {
//			if(d.getDictationNo() < 217)
//				continue;
//						
//			try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create(settings)) {
//				// Set the text input to be synthesized
//				SynthesisInput input = SynthesisInput.newBuilder().setText(d.getDictationSentence()).build();
//
//				// Build the voice request
//				VoiceSelectionParams voice = VoiceSelectionParams.newBuilder().setLanguageCode("ko-KR")
//						.setSsmlGender(SsmlVoiceGender.FEMALE) // ssmlVoiceGender = SsmlVoiceGender.FEMALE
//						.build();
//
//				// Select the type of audio file you want returned
//				AudioConfig audioConfig = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3) // MP3 audio.
//						.build();
//
//				// Perform the text-to-speech request
//				SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);
//
//				// Get the audio contents from the response
//				ByteString audioContents = response.getAudioContent();
//
//				// Write the response to the output file.
//				try (OutputStream out = new FileOutputStream("./src/main/resources/static/dictation/" + d.getDictationNo() + ".mp3")) {
//					out.write(audioContents.toByteArray());
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//
//				e1.printStackTrace();
//			}
//		}
//		
//		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
//	}
	
	@PostMapping("/tts")
	public ResponseEntity<String> tts(@RequestBody SpeechInfo speechInfo) {
		if (speechInfo.getText().length() > 100) {
			return new ResponseEntity<>("텍스트는 100자까지 입력 가능합니다.", HttpStatus.BAD_REQUEST);
		}
		File file = new File(audioFilePath + "/" + speechInfo.getText() + ".mp3");
		String audioResource = audioResourcePath + "/" + speechInfo.getText() + ".mp3";

		if (file.exists())
			return new ResponseEntity<>(audioResource, HttpStatus.OK);

		try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create(settings)) {
			// Set the text input to be synthesized
			SynthesisInput input = SynthesisInput.newBuilder().setText(speechInfo.getText()).build();

			// Build the voice request
			VoiceSelectionParams voice = VoiceSelectionParams.newBuilder().setLanguageCode(speechInfo.getLanguage())
					.setSsmlGender(SsmlVoiceGender.FEMALE) // ssmlVoiceGender = SsmlVoiceGender.FEMALE
					.build();

			// Select the type of audio file you want returned
			AudioConfig audioConfig = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3) // MP3 audio.
					.build();

			// Perform the text-to-speech request
			SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

			// Get the audio contents from the response
			ByteString audioContents = response.getAudioContent();

			// Write the response to the output file.
			try (OutputStream out = new FileOutputStream(audioFilePath + "/" + speechInfo.getText() + ".mp3")) {
				out.write(audioContents.toByteArray());

	
//				while (true) {
//					// Create a new HttpEntity
//					Resource resource = resourceLoader.getResource(staticPath + "/audio/" + speechInfo.getText() + ".mp3");
//
//					if(resource.exists()) {
//						break;
//					}
////					System.out.println(resource);
//					Thread.sleep(200);
//				}

				return new ResponseEntity<>(audioResource, HttpStatus.OK);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block

			e1.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		
//		System.out.println("heehehehehfffffffffffffffeheheheh");
	}
}
