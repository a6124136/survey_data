package com.example.survey.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="start_date")
	private LocalDate startdate;  //日期資料型態用LocalDate  包含日期時間的改LocalDayTime
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="end_date")
	private LocalDate enddate;
	
	//你媽的  沒註解@JsonFormat 讀取不到yyyy-MM-dd的格式欸機掰
	
	
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
