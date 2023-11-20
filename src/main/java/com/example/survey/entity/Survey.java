package com.example.survey.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="survey_title")
@Entity
//@IdClass(value=SurveyId.class) //value 保管ID.class
public class Survey {
	//及時反饋AI值
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id 
	@Column(name="title_id")
	private int id;
	@Column(name="title")
	private String title;
	@Column(name="description")
	private String description;
	@Column(name="status_is_published")  
	private boolean published;  //命名布林值不要包含is 會有衝突
	@Column(name="start_date")
	private LocalDate startdate;  //日期資料型態用LocalDate  包含日期時間的改LocalDayTime
	@Column(name="end_date")
	private LocalDate enddate;
	
	
	
	public Survey() {
		super();
	}

	public Survey(int id,String title, String description, boolean status_is_published, LocalDate start_date,
			LocalDate end_date) {
		super();
		this.id=id;
		this.title = title;
		this.description = description;
		this.published = status_is_published;
		this.startdate = start_date;
		this.enddate = end_date;
		
	}
	public Survey(String title, String description, boolean status_is_published, LocalDate start_date,
			LocalDate end_date) {
		super();
		this.title = title;
		this.description = description;
		this.published = status_is_published;
		this.startdate = start_date;
		this.enddate = end_date;
		
	}

	public int getid() {
		return id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStatus_is_published() {
		return published;
	}

	public void setStatus_is_published(boolean status_is_published) {
		this.published = status_is_published;
	}

	public LocalDate getStart_date() {
		return startdate;
	}

	public void setStart_date(LocalDate start_date) {
		this.startdate = start_date;
	}

	public LocalDate getEnd_date() {
		return enddate;
	}

	public void setEnd_date(LocalDate end_date) {
		this.enddate = end_date;
	}
	
	
	
}
