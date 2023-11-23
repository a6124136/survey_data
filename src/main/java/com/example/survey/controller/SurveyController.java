package com.example.survey.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.survey.service.ifs.SurveyService;
import com.example.survey.vo.RequestSurvey;
import com.example.survey.vo.ResSurveyList;
import com.example.survey.vo.ResponseSurvey;
import com.example.survey.vo.SurveyDeleteReq;
import com.example.survey.vo.SurveySearchReq;

//跨網域請求CrossOrigin 這樣前端fetch才能接手

@CrossOrigin
@RestController
public class SurveyController {
// Controller命名要跟隨提供服務的項目  RestController RestApi
	
	@Autowired
	private SurveyService service;
	
	//value地址 可以自定義 外部來的請求照這個路徑觸發個別方法
	//localhost:(port)/路徑
	//Mapping會打包請求 JSON內部的東西包成RequestBody
	//對應的請求+Mapping 後面參數加上RequestBody才能正確接收發送的請求
	
//	====================新增=========================
	
	@PostMapping(value = "api/survey/create")
	public ResponseSurvey create(@RequestBody RequestSurvey req) {
		
		
		ResponseSurvey res = service.creatSurvey(req);
		
		return res;
	}
	
//	==========================搜尋======================
	
	@PostMapping(value = "api/survey/search")
	@DateTimeFormat(pattern = "yyyy-MM-dd")//接受的時間格式....?
	public ResponseSurvey search(@RequestBody SurveySearchReq req) {
		//test測試沒問題 Controller OK
		String title= req.getTitle();
		LocalDate st = req.getStartDate();
		LocalDate ed = req.getEndDate();
		title = StringUtils.hasText(title) ? title : "";
//		title有沒有 如果沒有就補上空字串
		st = st == null ? LocalDate.of(1971, 1, 1) : st;
		ed = ed == null ? LocalDate.of(2077, 8, 8) : ed;
//		如果預設日期沒填上 自動生成搜尋的時間範圍  因為null值不會列入搜尋時間的範圍
		//search的參數是title 起始日 結束日
		//實作的篩選放到Cntroller這邊搞 讓他在進去cache之前先被處理過
		//這樣cache就不會用收到null值
		ResponseSurvey res = service.search(title,st,ed); 
		return res;
	}
//	======================刪除=====================
	
	@PostMapping(value = "api/survey/delete")
	public ResponseSurvey deleteSurvey(@RequestBody SurveyDeleteReq req) {
		//刪除要求的是integerList  包一個
		List<Integer> delTarget=new ArrayList<>();
		//API接口命名  idList
		for(int i:req.getIdlist()) {
			delTarget.add(i);
		}
		
		
		ResponseSurvey res = service.deleteSurvey(delTarget);
		
		return res;
	}
	
//	=======================更新===========================
	
	@PostMapping(value = "api/survey/update")
	public ResponseSurvey updateSurvey(@RequestBody RequestSurvey req){
//	更新資料
		
		ResponseSurvey res = service.updateSurvey(req);
		return res;
	}
//	=====================搜尋前後台分離 單純問卷list不包含問題===============================
	
	@GetMapping(value="api/survey/searches")
	public ResSurveyList searchAll(@RequestBody SurveySearchReq req) {
		String title= req.getTitle();
		LocalDate st= req.getStartDate();
		LocalDate ed= req.getEndDate();
		boolean pub = req.isPublished();
		ResSurveyList res = service.searchSurveyList(title, st,ed, pub);
		return res;
	}
	
	
	//@RequestParam  Map<String,Object> paramMap 
	//url轉參數寫法 String Object =key跟value映射
	//url  https://****/****?title=參數名&start_date=2023-11-20.....
	
	//@RestController  restfulAPI啦
	
}
