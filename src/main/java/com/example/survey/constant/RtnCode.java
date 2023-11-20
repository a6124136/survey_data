package com.example.survey.constant;

public enum RtnCode {
	//列舉必須要用逗號分隔  
	SUCCESSFUL(200,"問卷創建完成"),//
	SURVEY_ERROR(400,"問卷項目有缺"),//
	QUESTION_ERROR(400,"天啊 問題不對阿"),
	BAD_RUQUES(400,"客戶端的錯 乾我屁事"),//
	SURVEY_NOT_FOUND(404,"找不到問卷欸"),
	SURVEY_ID_ERROR(400,"問卷沒ID欸"),
	SURVEY_Q_ID_ERROR(400,"問題沒ID欸"),
	SURVEY_ID_NOT_FOUND(404,"找不到存在此ID的問卷"),
	SURVEY_UPDATE_ERROR(400,"更新錯誤"),
	;
	private int code;
	private String msg;
	
	private RtnCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
