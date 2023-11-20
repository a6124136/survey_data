package com.example.survey.service.ifs;

import java.time.LocalDate;
import java.util.List;

import com.example.survey.vo.RequestSurvey;
import com.example.survey.vo.ResSurveyList;
import com.example.survey.vo.ResSurveyQues;
import com.example.survey.vo.ResponseSurvey;

public interface SurveyService {
	public ResponseSurvey creatSurvey(RequestSurvey req);
	//返回回應=ResponseSurvey
	public ResponseSurvey updateSurvey(RequestSurvey req);
	//刪除特定問卷或多張問卷
	public ResponseSurvey deleteSurvey(List<Integer> id);
	
	/**
	 * 前面int id =問卷的id 後面的list 是每個被選中問題的id
	 * **/
	public ResponseSurvey deleteSurveyQuestion(int id,List<Integer> ListId);
	/**
	 還沒確認效果 Containing是模糊搜尋 (空字串可以搜尋到全部) null則是什麼都找不到 所以參數不要null
	 **/
	public ResponseSurvey search(String title,LocalDate startdate,LocalDate enddate);
	
	public ResSurveyList searchSurveyList(String title,LocalDate startdate,LocalDate enddate,boolean isPublished);
	//返回多筆問卷清單
//	public ResSurveyQues searchQuestionList(int qid);
	//找同一張問卷的所有問題
}
