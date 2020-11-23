package com.bungle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bungle.dao.DictationDAO;
import com.bungle.dto.Audio;
import com.bungle.dto.Dictation;
import com.bungle.dto.DictationSet;
import com.bungle.dto.DictationSetContent;
import com.bungle.dto.Homework;
import com.bungle.dto.User;

@Service
public class DictationServiceImpl implements DictationService {

	@Autowired
	DictationDAO dictationdao;
	@Autowired
	HomeworkService homeworkService;
	@Autowired
	VoiceService voiceService;
	@Value("${resource.path.dictation}")
	String dictationResourcePath;
	@Value("${resource.path.dictation_set_content}")
	String dictationSetContentResourcePath;
	
	@Override
	public int createDictation(Homework homework, User loginUser) {
		/**
		 * 해당 세션이 소속된 클래스를 유저가 소유하고 있는지 검사
		 * 
		 * 권한 검사 통과하면 -> homework 레코드 insert 후 dictation 레코드 insert
		 */
		List<Dictation> dictationList = homework.getDictationList();
		
		System.out.println(homework);
		if(!homeworkService.createHomework(homework, loginUser)) {
			System.out.println("과제 생성 실패");
			return 0;
		}
		
		for(Dictation dictation : dictationList) {
			dictation.setHomeworkNo(homework.getHomeworkNo());
		}
		
		dictationdao.createDictations(dictationList);
	
		Audio audio;
		for(Dictation dictation : dictationList) {
						
			audio = new Audio();
			audio.setAudioData(dictation.getAudioString());
			audio.setAudioText(dictation.getDictationSentence());
			audio.setFileName(dictation.getDictationNo() + ".mp3");			
			audio.setAudioPath(dictation.getAudioPath());
			
			
			if(!voiceService.recordVoice(audio)) {
				System.out.println("파일 입출력 에러");
			}else {
				System.out.println(audio.getFileName() + " 녹음");
			}
			dictation.setAudioPath(dictationResourcePath + "/" + dictation.getDictationNo() + ".mp3");
			
			if (dictationdao.updateDictation(dictation) != 1) {
				System.out.println("????????????");
				return 0;
			}
		}
		return 1;
	}

	@Override
	public int updateDictation(Dictation dictation, User loginUser) {
		if (dictationdao.updateDictation(dictation) == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteDictation(Dictation dictation, User loginUser) {
		if (dictationdao.deleteDictation(dictation) == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Dictation selectOneDictation(Dictation dictation) {
		return dictationdao.selectOneDictation(dictation);
	}

	@Override
	public List<Dictation> selectAllDictation(Dictation dictation) {
		return dictationdao.selectAllDictation(dictation);
	}

	@Override
	public List<Dictation> selectDictationTest(Dictation dictation) throws Exception {
		return dictationdao.selectDictationTest(dictation);
	}

	@Override
	public int createProblem(DictationSet dictationset) {
		if(dictationdao.createProblem(dictationset) != 1)
			return 0;
		
		return dictationset.getDictationsetNo();
	}


	@Override
	public int createProblems(DictationSetContent dictationSetContent) {
//		dictationSetContentResourcePath
		
		
		dictationSetContent.setAudioPath(dictationSetContentResourcePath + "/" 
											+ dictationSetContent.getDictationsetNo() 
											+ "-" + dictationSetContent.getDictationNumber() + ".mp3");
		
		if(dictationdao.createProblems(dictationSetContent) != 1)
			return 0;
		
		
		Audio audio = new Audio();
		
		audio.setAudioData(dictationSetContent.getAudioData());
		audio.setFileName(dictationSetContent.getDictationsetNo() 
							+ "-" + dictationSetContent.getDictationNumber() + ".mp3");
		audio.setAudioText(dictationSetContent.getDictationProblem());		
		voiceService.recordDictationSetVoice(audio);
		

		
		
		return 1;
	}

	@Override
	public List<Dictation> selectByHomeworkNo(int homeworkNo) {
		return dictationdao.selectByHomeworkNo(homeworkNo);
	}

	@Override
	public List<Dictation> selectDictationLevel(Dictation dictation) {
		return dictationdao.selectDictationLevel(dictation);
	}

	@Override
	public List<DictationSet> selectDictationSet(DictationSet dictation) {
		return dictationdao.selectDictationSet(dictation);
	}

	@Override
	public List<Dictation> selectLevel(Dictation dictation) {
		return dictationdao.selectLevel(dictation);
	}

	@Override
	public List<DictationSetContent> selectDictationSetContent(DictationSetContent dictation) {
		return dictationdao.selectDictationSetContent(dictation);
	}


}
