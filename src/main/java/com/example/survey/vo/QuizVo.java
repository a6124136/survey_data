package com.example.survey.vo;

import java.util.ArrayList;
import java.util.List;

import com.example.survey.entity.Survey;
import com.example.survey.entity.SurveyQuestion;

public class QuizVo {
	
	//單一個Survey內的多個問題
	
	private Survey survey = new Survey();
	
	private List<SurveyQuestion> surveyQuestionList = new ArrayList<SurveyQuestion>();

	public QuizVo() {
		super();
	}

	public QuizVo(Survey survey, List<SurveyQuestion> surveyQuestionList) {
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

	public void setSurveyQuestion(List<SurveyQuestion> surveyQuestionList) {
		this.surveyQuestionList = surveyQuestionList;
	}
	
	
	
}
