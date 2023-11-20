package com.example.survey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.survey.entity.SurveyQuestion;
import com.example.survey.entity.SurveyQuestionId;
@Repository
public interface SurveyQuestionDao extends JpaRepository<SurveyQuestion,SurveyQuestionId >{
	public  List<SurveyQuestion> findByQid(int Qid);
	public List<SurveyQuestion> deleteAllByQidIn(List<Integer> QidList);//屬性有複數(List)要+In
	public List<SurveyQuestion> findAllByQidIn (List<Integer> idList);
	public List<SurveyQuestion> deleteAllByQidInAndQuid(List<Integer> QidList,int id);
//	public List<SurveyQuestion> findAllByQidIn (int qid);

}
