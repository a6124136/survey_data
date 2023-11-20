package com.example.survey.vo;

import java.util.List;

import com.example.survey.entity.Survey;
import com.example.survey.entity.SurveyQuestion;

public class QuizReq extends QuizVo{

	public QuizReq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuizReq(Survey survey, List<SurveyQuestion> surveyQuestionList) {
		super(survey, surveyQuestionList);
		// TODO Auto-generated constructor stub
	}

}
