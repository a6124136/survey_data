package com.example.survey;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.survey.entity.Survey;
import com.example.survey.entity.SurveyQuestion;
import com.example.survey.service.ifs.SurveyService;
import com.example.survey.vo.QuizVo;
import com.example.survey.vo.RequestSurvey;
import com.example.survey.vo.ResponseSurvey;

@SpringBootTest
class SurveyApplicationTests {
	
	@Autowired
	SurveyService surveyService;
	
	@Test
	public void creatSurvey() {
		LocalDate startday= LocalDate.now();
		LocalDate endday=startday.plusDays(7);
		
		Survey survey = new Survey("調皮問卷","很調皮",false,startday,endday);
		SurveyQuestion surveyQuestion = new SurveyQuestion(1,5,"調皮?","單選題",false,"才不;很調皮;"); 
		List<SurveyQuestion> surveyQuestionList = new ArrayList<>(); 
		surveyQuestionList.add(surveyQuestion);
		RequestSurvey req= new RequestSurvey(survey,surveyQuestionList);
		
		surveyService.creatSurvey(req);
		//成功儲存
	}
	@Test
	public void updateSurvey() {
		LocalDate startday = LocalDate.now().plusDays(2);
		LocalDate endday = startday.plusDays(7);
		Survey survey = new Survey(4,"四號問卷","更新",false,startday,endday);
		
		//id title describe published startdate enddate
		List<SurveyQuestion> surveyQuestionList = new ArrayList<>(); 
		SurveyQuestion surveyQuestion = new SurveyQuestion(1,4,"日期也被改了","修改後類型",false,"修改後選項"); 
		//(id,q_id,q_title,q_type,necessary,options)
		surveyQuestionList.add(surveyQuestion);
		surveyQuestionList.add(new SurveyQuestion(2,4,"日期已改","修改類型",false,"修改選項"));
		RequestSurvey req= new RequestSurvey(survey,surveyQuestionList);
		surveyService.updateSurvey(req);
	}
	@Test
	public void searchSurvey() {
		surveyService.search("哭", null, null);
	}
}
