package com.bungle.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bungle.dto.Audio;
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

@Service
public class VoiceServiceImpl implements VoiceService {
	@Value("${file.path.dictation}")
	private String dictationPath;
	@Value("${file.path.dictation_set_content}")
	private String dictationSetContentFilePath;
	@Value("${static_path}")
	private String staticPath;
	private TextToSpeechSettings settings;
	@Value("${google.key.path}")
	private String keyFilePath;
	@Value("${file.path.chat_audio}")
	String chatAudioFilePath;

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

	public byte[] recordTTS(Audio audio) throws Exception {
		if (audio.getAudioText().length() > 100) {
			System.out.println("길이 초과");
			throw new Exception();
		}

		try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create(settings)) {
			// Set the text input to be synthesized
			SynthesisInput input = SynthesisInput.newBuilder().setText(audio.getAudioText()).build();

			// Build the voice request
			VoiceSelectionParams voice = VoiceSelectionParams.newBuilder().setLanguageCode("ko-KR")
					.setSsmlGender(SsmlVoiceGender.FEMALE) // ssmlVoiceGender = SsmlVoiceGender.FEMALE
					.build();

			// Select the type of audio file you want returned
			AudioConfig audioConfig = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3) // MP3 audio.
					.build();

			// Perform the text-to-speech request
			SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

			// Get the audio contents from the response
			ByteString audioContents = response.getAudioContent();

			return audioContents.toByteArray();

		} catch (IOException e1) {
			// TODO Auto-generated catch block

			e1.printStackTrace();
			throw new Exception();
		}
	}

	public boolean saveChatAudio(Audio audio) {
		Decoder decoder = Base64.getDecoder();
		byte[] decodedByte;

		decodedByte = decoder.decode(audio.getAudioData().split(",")[1]);

		// Write the response to the output file.
		try (OutputStream out = new FileOutputStream(chatAudioFilePath + "/" + audio.getFileName())) {
			out.write(decodedByte);
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean recordVoice(Audio audio) {
		Decoder decoder = Base64.getDecoder();
		byte[] decodedByte;

		if (audio.getAudioData() == null || audio.getAudioData().equals("")) {
			try {
				decodedByte = recordTTS(audio);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} else if (audio.getAudioData().equals("fromSet")) {
			System.out.println("fromSet");

			InputStream inStream = null;
			OutputStream outStream = null;

			try {
				inStream = new FileInputStream(staticPath + "/" + audio.getAudioPath()); // 원본파일
				outStream = new FileOutputStream(dictationPath + "/" + audio.getFileName()); // 이동시킬 위치

				byte[] buffer = new byte[1024];

				int length;

				while ((length = inStream.read(buffer)) > 0) {
					outStream.write(buffer, 0, length);
				}
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			} finally {
				try {
					inStream.close();
					outStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return true;

		} else {
			decodedByte = decoder.decode(audio.getAudioData().split(",")[1]);
		}

		// Write the response to the output file.
		try (OutputStream out = new FileOutputStream(dictationPath + "/" + audio.getFileName())) {
			out.write(decodedByte);
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public boolean recordDictationSetVoice(Audio audio) {
		Decoder decoder = Base64.getDecoder();
		byte[] decodedByte;

		if (audio.getAudioData() == null || audio.getAudioData().equals("")) {

			try {
				decodedByte = recordTTS(audio);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} else {
			decodedByte = decoder.decode(audio.getAudioData().split(",")[1]);
		}

		// Write the response to the output file.
		try (OutputStream out = new FileOutputStream(dictationSetContentFilePath + "/" + audio.getFileName())) {
			out.write(decodedByte);
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
