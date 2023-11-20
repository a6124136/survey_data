package com.example.survey.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SurveyQuestionId implements Serializable{
	private int quid;
	private int qid;
	public SurveyQuestionId() {
		super();
		
	}
	public SurveyQuestionId(int quid, int qid) {
		super();
		this.quid = quid;
		this.qid = qid;
	}
	public int getQuid() {
		return quid;
	}
	public void setQuid(int quid) {
		this.quid = quid;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	
	
}
