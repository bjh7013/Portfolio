package com.bungle.service;

import com.bungle.dto.Audio;
import com.bungle.dto.Chat;

public interface VoiceService {
	public boolean recordVoice(Audio audio);
	public boolean recordDictationSetVoice(Audio audio);
	public boolean saveChatAudio(Audio audio);
	
}
