package com.ssazit.util;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerUtil {
	
	// temp를 하루에 한번 비워줍시다! (수정한지 하루 이상된 파일 날리기)
	// 초 분 시 일 월 요일 (매일 18시정각 수행)
	@Scheduled(cron = "0 0 18 * * *")
	public void cleanTempDir() {
		Calendar cal = Calendar.getInstance() ;
		long todayMil = cal.getTimeInMillis() ;     // 현재 시간(밀리 세컨드)
		long oneDayMil = 24*60*60*1000 ;            // 일 단위
		 
		Calendar fileCal = Calendar.getInstance() ;
		Date fileDate = null ;
		
		File path = new File("./src/main/resources/static/resource/temp") ;
		File[] list = path.listFiles() ;            // 파일 리스트 가져오기
		
		System.out.println("스케줄러 수행");
		
		for(int j=0 ; j < list.length; j++){
		                           
		    // 파일의 마지막 수정시간 가져오기
			fileDate = new Date(list[j].lastModified()) ;
		 
			// 현재시간과 파일 수정시간 시간차 계산(단위 : 밀리 세컨드)
			fileCal.setTime(fileDate);
			long diffMil = todayMil - fileCal.getTimeInMillis() ;
		 
			//날짜로 계산
		    int diffDay = (int)(diffMil/oneDayMil) ;
		 
		    // 1일이 지난 파일 삭제
		    if(diffDay > 1 && list[j].exists()){
			    list[j].delete() ;
			    System.out.println(list[j].getName() + " 파일을 삭제했습니다.");
		    }
		    
		}
	}
	
}
