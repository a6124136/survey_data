package com.example.survey.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SurveyDeleteReq {
	@JsonProperty("idlist")
	private List<Integer> idlist;
	//刪除要求整數list....包起來?  笑死 物件陣列格式真的能刪欸
	
	public List<Integer> getIdlist() {
		return idlist;
	}

	public void setIdlist(List<Integer> idlist) {
		this.idlist = idlist;
	}
	
	
}
