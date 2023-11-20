package com.example.survey.repository;

import java.time.LocalDate;
//import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.survey.entity.Survey;
import com.example.survey.entity.SurveyQuestion;
import com.example.survey.vo.ResponseSurvey;

@Repository
public interface SurveyDao extends JpaRepository<Survey, Integer> {
	/**
	 * 好遛的用法 用id倒序找第一筆 就是最新id wtf
	 **/
	public Survey findTop1ByOrderByIdDesc();
	// 挖靠 兩顆星星包起來可以在引用的地方游標移過去看寫的註解欸

	/**
	 * 刪除相關用的 找到輸入對應id的list去刪除
	 **/
	public List<Survey> findByIdIn(List<Integer> idList);

//	public List<Survey> findByIdInAndPublishedFalseOrIdInAndPublishedTrueAndStartdateLessThan(List<Integer> idList,List<Integer> idList2,LocalDate now);
	// JPA語法真的好靠北
	
//	public ResponseSurvey findByNameContaining(String name);
//	//????模糊搜尋
	public List<Survey>findByTitleContainingAndStartdateGreaterThanEqualAndEnddateLessThanEqual(String title,LocalDate startDate,LocalDate endDate);
	//???更抽象了找標題(模糊搜尋)以及起始日期之後還有結束日期之前  標題名稱 + 起始日到結束日之間
//	public List<Survey>findByTitleContainingAndStartDateGreaterThanEqual(String title,LocalDate startDate);
//	//標題(模糊搜尋) 以及範圍在起始日之後
	public List<Survey>findByTitleContainingAndStartdateGreaterThanEqualAndEnddateLessThanEqualAndPublishedTrue(String title,LocalDate startDate,LocalDate endDate);
//   給前端用的搜尋 改的條件只有最後面的已發布=true
}
