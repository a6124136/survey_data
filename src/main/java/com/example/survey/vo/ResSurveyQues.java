package com.example.survey.vo;

import java.util.List;

import com.example.survey.constant.RtnCode;
import com.example.survey.entity.SurveyQuestion;

public class ResSurveyQues {
	
	List<SurveyQuestion> surveyQuestion;
	
	RtnCode rtnCode;

	public ResSurveyQues() {
		super();
	}

	public ResSurveyQues(List<SurveyQuestion> surveyQuestion, RtnCode rtnCode) {
		super();
		this.surveyQuestion = surveyQuestion;
		this.rtnCode = rtnCode;
	}

	public List<SurveyQuestion> getSurveyQuestion() {
		return surveyQuestion;
	}

	public void setSurveyQuestion(List<SurveyQuestion> surveyQuestion) {
		this.surveyQuestion = surveyQuestion;
	}

	public RtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(RtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}
	
	
}
