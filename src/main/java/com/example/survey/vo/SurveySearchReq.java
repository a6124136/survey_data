package com.example.survey.vo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SurveySearchReq {
	//搜尋用的請求 要打包封裝用的
	private String title;
	@JsonFormat(pattern="yyyy-MM-dd")
	@JsonProperty("start_date")
	private LocalDate startDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	@JsonProperty("end_date")
	private LocalDate endDate;
	
	private boolean published;
	
	//前端Json屬性的命名跟後端不一樣的時候用@JsonProperty註明
	
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
}
