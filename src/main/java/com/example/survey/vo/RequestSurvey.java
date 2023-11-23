package com.example.survey.vo;

import java.util.ArrayList;
import java.util.List;

import com.example.survey.entity.Survey;
import com.example.survey.entity.SurveyQuestion;
import com.fasterxml.jackson.annotation.JsonProperty;

//前端發送來的請求 


public class RequestSurvey extends QuizVo{
	//把問卷本體跟問題打包
	private Survey survey = new Survey(); 
	//預設一個空值 可以省略null  check 只有裡面屬性沒填會是預設值 null、0、false... 
	@JsonProperty("survey_questionList")
	private List<SurveyQuestion> surveyQuestionList = new ArrayList<>(); 
	//預設一個空值 跑For迴圈就不是null而是一個空值 才不會因為沒放問題而報錯
	public RequestSurvey() {
		super();
	}
	public RequestSurvey(Survey survey, List<SurveyQuestion> surveyQuestionList) {
		super();
		this.survey = survey;
		this.surveyQuestionList = surveyQuestionList;
	}
	public Survey getSurvey() {
		return survey;
	}
	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	public List<SurveyQuestion> getSurveyQuestionList() {
		return surveyQuestionList;
	}
	public void setSurveyQuestionList(List<SurveyQuestion> surveyQuestionList) {
		this.surveyQuestionList = surveyQuestionList;
	}
	
}
