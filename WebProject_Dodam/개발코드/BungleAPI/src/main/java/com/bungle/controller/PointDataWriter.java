package com.bungle.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bungle.dto.PointData;

@RestController
public class PointDataWriter {
	@Value("${file.path.letter}")
	private String letterPath;
	@PostMapping("/point")
	public void pointDataWrite(@RequestBody PointData pointData){ 
		System.out.println(pointData);
		System.out.println(pointData.getFileName());
		
		if(pointData.isAni()) {
			pointData.setFileName("ani_" + pointData.getFileName());
		}
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(letterPath + "/" + pointData.getFileName()))) {
			writer.write(pointData.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
	}
	
//	@PostMapping("/curveLetter")
//	public void saveCurveLetter(@RequestBody PointData pointData) {
//		try(BufferedWriter writer = new BufferedWriter(new FileWriter("./src/main/resources/static/letter/" + pointData.getFileName()))) {
//			writer.write(pointData.toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 				
//	}
}
