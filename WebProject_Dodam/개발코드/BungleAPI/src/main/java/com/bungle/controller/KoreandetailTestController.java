package com.bungle.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bungle.dto.Koreandetail;
import com.bungle.dto.User;
import com.bungle.service.KoreandetailService;

@RestController
@RequestMapping("/koreandetail")
public class KoreandetailTestController {
	
	@Autowired
	KoreandetailService koreandetailService;
	
	@PostMapping("/submit")
	public ResponseEntity<String> createKoreandetail(@RequestBody Koreandetail koreandetail) throws Exception {
		Koreandetail kor=koreandetailService.selectOneKoreandetail(koreandetail);
		if (kor == null) {
			koreandetailService.createKoreandetail(koreandetail);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			kor.setCorrect(koreandetail.isCorrect());
			koreandetailService.updateKoreandetail(kor);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
	} 
	
	@GetMapping("/score")
	public ResponseEntity<HashMap<Integer, Integer>> selectUserScore(HttpServletRequest request) throws Exception{
		User userinfo = (User) request.getAttribute("userinfo"); 
		System.out.println("-----------정답 개수----------");
		List<Koreandetail> list=koreandetailService.selectUserCorrect(userinfo.getUserNo());
		int arr[]=new int[5];
	
		System.out.println(list.size());
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).toString());
			if(list.get(i).getKoreandetailNo()<=13) {//자음
				arr[0]+=1;
			}else if(list.get(i).getKoreandetailNo()<=23){//모음
				arr[1]+=1;
			}else if(list.get(i).getKoreandetailNo()<=28){//쌍자음
				arr[2]+=1;
			}else if(list.get(i).getKoreandetailNo()<=39){//이중모음
				arr[3]+=1;
			}else if(list.get(i).getKoreandetailNo()<=53){//글자
				arr[4]+=1;
			}
		}
		HashMap<Integer, Integer> map=new HashMap<>();
		map.put(1,arr[0]);
		map.put(2,arr[1]);
		map.put(3, arr[2]);
		map.put(4, arr[3]);
		map.put(5, arr[4]);
		
		return new ResponseEntity<HashMap<Integer, Integer>>(map,HttpStatus.OK);
	}
	
	@GetMapping("/scoreuserno/{userNo}")
	public ResponseEntity<HashMap<Integer, Integer>> selectUserScore(@PathVariable int userNo) throws Exception{
		System.out.println("-----------정답 개수----------");
		List<Koreandetail> list=koreandetailService.selectUserCorrect(userNo);
		int arr[]=new int[5];
	
		System.out.println(list.size());
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).toString());
			if(list.get(i).getKoreandetailNo()<=13) {//자음
				arr[0]+=1;
			}else if(list.get(i).getKoreandetailNo()<=23){//모음
				arr[1]+=1;
			}else if(list.get(i).getKoreandetailNo()<=28){//쌍자음
				arr[2]+=1;
			}else if(list.get(i).getKoreandetailNo()<=39){//이중모음
				arr[3]+=1;
			}else if(list.get(i).getKoreandetailNo()<=53){//글자
				arr[4]+=1;
			}
		}
		HashMap<Integer, Integer> map=new HashMap<>();
		map.put(1,arr[0]);
		map.put(2,arr[1]);
		map.put(3, arr[2]);
		map.put(4, arr[3]);
		map.put(5, arr[4]);
		
		return new ResponseEntity<HashMap<Integer, Integer>>(map,HttpStatus.OK);
	}
}
