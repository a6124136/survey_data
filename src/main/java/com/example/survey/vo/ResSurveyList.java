package com.example.survey.vo;

import java.util.List;

import com.example.survey.constant.RtnCode;
import com.example.survey.entity.Survey;

public class ResSurveyList {
	
	//多張問卷 跟ResponseSurvey不一樣 那個是單張問卷 這邊是多張問卷	
	private List<Survey> SurveyList ;
	
	private RtnCode rtnCode;

	public ResSurveyList() {
		super();
	}

	public ResSurveyList(List<Survey> surveyList, RtnCode rtnCode) {
		super();
		SurveyList = surveyList;
		this.rtnCode = rtnCode;
	}

	public List<Survey> getSurveyList() {
		return SurveyList;
	}

	public void setSurveyList(List<Survey> surveyList) {
		SurveyList = surveyList;
	}

	public RtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(RtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}
	
	
}
