package com.example.survey.vo;

import java.util.List;

import com.example.survey.constant.RtnCode;


public class ResponseSurvey {
	
	//   容器
	private List<QuizVo> quizVoList;  //QuizVo 一張問卷以及內部的題目  用list承接多張  (可能只有單張或多張)
	
	private RtnCode rtncode;
//怎麼包裝??
	
	public ResponseSurvey() {
		super();
	}
	

	public ResponseSurvey(RtnCode rtncode) {
		super();
		this.rtncode = rtncode;
	}


	public ResponseSurvey(List<QuizVo> quizVoList, RtnCode rtncode) {
		super();
		this.quizVoList = quizVoList;
		this.rtncode = rtncode;
	}


	public RtnCode getRtncode() {
		return rtncode;
	}

	public void setRtncode(RtnCode rtncode) {
		this.rtncode = rtncode;
	}


	public List<QuizVo> getQuizVoList() {
		return quizVoList;
	}


	public void setQuizVoList(List<QuizVo> quizVoList) {
		this.quizVoList = quizVoList;
	}
	
}
