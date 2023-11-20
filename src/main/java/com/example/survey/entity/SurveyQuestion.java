package com.example.survey.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Table(name="survey_questions")
@Entity
@IdClass(value=SurveyQuestionId.class)
public class SurveyQuestion {
	@Id
	@Column(name="id")
	private int quid;
	@Id
	@Column(name="q_id")
	private int qid;
	@Column(name="q_title")
	private String title;
	@Column(name="q_type")
	private String type;
	@Column(name="necessary")
	private boolean necessary;
	@Column(name="q_options")
	private String options;
	
	public SurveyQuestion() {
		super();
	}

	public SurveyQuestion(int quid, int qid, String title, String type, boolean necessary, String options) {
		super();
		this.quid = quid;
		this.qid = qid;
		this.title = title;
		this.type = type;
		this.necessary = necessary;
		this.options = options;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isNecessary() {
		return necessary;
	}

	public void setNecessary(boolean necessary) {
		this.necessary = necessary;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

}
