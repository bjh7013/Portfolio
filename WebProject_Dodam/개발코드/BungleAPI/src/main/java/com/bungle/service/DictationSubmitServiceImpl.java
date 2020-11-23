package com.bungle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bungle.dao.DictationSubmitDAO;
import com.bungle.dao.HomeworkSubmitDAO;
import com.bungle.dto.DictationCount;
import com.bungle.dto.DictationSubmit;
import com.bungle.dto.Homework;
import com.bungle.dto.HomeworkSubmit;
import com.bungle.dto.Image;
import com.bungle.dto.User;

import net.coobird.thumbnailator.resizers.configurations.Dithering;

@Service
public class DictationSubmitServiceImpl implements DictationSubmitService {

	@Autowired
	DictationSubmitDAO dictationsubmitdao;
	@Autowired
	ImageService imageService;
	@Value("${resource.path.dictationsubmit}")
	String dictationSubmitResourcePath;
	@Autowired
	HomeworkService homeworkService;
	@Autowired
	HomeworkSubmitDAO homeworkSubmitDAO;
	@Override
	public int createDictationSubmit(List<DictationSubmit> dictationsubmitList, User loginUser) throws Exception {
		/**
		 * 권한 검사 해당 문제가 기본 제공 컨텐츠 dictation_level이 null이 아니라면 통과 homework에 소속된 문제라면 해당
		 * homework가 속한 클래스에 유저가 가입되어있는 학생인지 검사
		 */
		Homework homework = dictationsubmitdao.getHomework(dictationsubmitList.get(0).getDictationNo());
		
		if(homework != null) {//homework에 속한 dictation일시 homeworkSubmit insert
			HomeworkSubmit homeworkSubmit = HomeworkSubmit
					.builder()
					.homeworkNo(homework.getHomeworkNo())
					.build();
			if(!homeworkService.createHomeworkSubmit(homeworkSubmit, loginUser)) {
				System.out.println("과제 제출중 오류 발생");
				return 0;
			}
		}

		for (DictationSubmit dictationsubmit : dictationsubmitList) {
			dictationsubmit.setStudentNo(loginUser.getUserNo());

			if (!dictationsubmitdao.validateC(dictationsubmit)) {
				System.out.println("문제를 제출할 권한이 없습니다");
				return 0;
			}
			DictationSubmit deleteDic=dictationsubmitdao.checkDictationSubmit(dictationsubmit);
			if(deleteDic!=null) {
				System.out.println("기존정보 존재 삭제");
				dictationsubmitdao.deleteDictationSubmit(deleteDic);
			}
			
			if (dictationsubmitdao.createDictationSubmit(dictationsubmit) == 1) {
				System.out.println(dictationsubmit.isCorrect());

				dictationsubmit.setSubmitImg(dictationSubmitResourcePath + "/" + dictationsubmit.getSubmitNo() + ".png");
				
				Image img = new Image();
				img.setImgData(dictationsubmit.getSubmitImgData());
				img.setFileName(dictationsubmit.getSubmitNo() + ".png");
				img.setType("dictation");
				imageService.saveImage(img);
				
				dictationsubmitdao.updateDictationSubmit(dictationsubmit);
			}else {
				return 0;
			}
		}
		return 1;


	}

	@Override
	public int updateDictationSubmit(DictationSubmit dictationsubmit, User loginUser) throws Exception {
		if (dictationsubmitdao.updateDictationSubmit(dictationsubmit) == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteDictationSubmit(DictationSubmit dictationsubmit, User loginUser) throws Exception {
		if (dictationsubmitdao.deleteDictationSubmit(dictationsubmit) == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public DictationSubmit selectOneDictationSubmit(DictationSubmit dictationsubmit) throws Exception {
		return dictationsubmitdao.selectOneDictationSubmit(dictationsubmit);
	}

	@Override
	public List<DictationSubmit> selectAllDictationSubmit(DictationSubmit dictationsubmit) throws Exception {
		return dictationsubmitdao.selectAllDictationSubmit(dictationsubmit);
	}

	@Override
	public List<DictationSubmit> selectDictationSubmitByHomeworkNo(int homeworkNo) throws Exception {
		return dictationsubmitdao.selectDictationSubmitByHomeworkNo(homeworkNo);
	}

	@Override
	public List<DictationSubmit> selectDictationSubmitByDictationNo(int dictationNo) throws Exception {
		return dictationsubmitdao.selectDictationSubmitByDictationNo(dictationNo);
	}

	@Override
	public int getSubmitCount(int usreNo) {
		return dictationsubmitdao.getSubmitCount(usreNo);
	}

	@Override
	public List<DictationCount> getSubmitGroupLevel(int userNo) {
//		System.out.println(dictationsubmitdao.getSubmitGroupLevel(userNo).toString());
		return dictationsubmitdao.getSubmitGroupLevel(userNo);
	}

	@Override
	public List<DictationSubmit> selectDictationSubmitByUserNo(int userNo) throws Exception {
		return 	dictationsubmitdao.selectDictationSubmitByUserNo(userNo);

	}

}
