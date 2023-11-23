package com.example.survey.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.survey.constant.RtnCode;
import com.example.survey.entity.Survey;
import com.example.survey.entity.SurveyQuestion;
import com.example.survey.repository.SurveyDao;
import com.example.survey.repository.SurveyQuestionDao;
import com.example.survey.service.ifs.SurveyService;
import com.example.survey.vo.QuizVo;
import com.example.survey.vo.RequestSurvey;
import com.example.survey.vo.ResSurveyList;
import com.example.survey.vo.ResSurveyQues;
import com.example.survey.vo.ResponseSurvey;

@Service
public class SurveyServiceImpl implements SurveyService {
	@Autowired
	private SurveyDao surveydao;
	@Autowired
	private SurveyQuestionDao surveyQdao;

	// =====================↓CreatSurvey↓=====================================
	@Override
	@Transactional // 只能用在public上 交易偵錯 當存多筆資料or跨表的操作時 必須全部成功才會成功 可以直接寫在class上一勞永逸
	public ResponseSurvey creatSurvey(RequestSurvey req) { // req 包含問卷本體跟問卷內的問題
		// 創建問卷
		ResponseSurvey checkResult = checkParam(req); // 確認參數的方法獨立出去 主程式再呼叫 比較簡潔
		if (checkResult != null) {
			return checkResult; // 回傳null表示成功 (不成功會報RtsCode)
			// 如果回傳的不是null就中斷程式
		}
		surveydao.save(req.getSurvey());
		// 存問卷本體
		int id = req.getSurvey().getid();
		// 存到資料庫必須接回來才有Sql生成的問卷id
		List<SurveyQuestion> sqList = req.getSurveyQuestionList();
		List<QuizVo> qzvo = new ArrayList<QuizVo>();
		qzvo.add(req);
//		 判斷問題的清單是不是空的 有問題才存
		if(!sqList.isEmpty()) {
			for (SurveyQuestion resQ : sqList) {
				resQ.setQid(id);
				// 把問卷的id存成對應問題的qid
			}
			surveyQdao.saveAll(sqList);
			// 存所有的問題到問卷裡
			
		}

		return new ResponseSurvey(qzvo,RtnCode.SUCCESSFUL);
	}

//================================↑creatSurvey↑========================================
//================================↓updateSurvey↓========================================

	@Override
	@Transactional
	public ResponseSurvey updateSurvey(RequestSurvey req) {
		// 更新問卷內容
		ResponseSurvey checkResult = checkParam(req);
		if (checkResult != null) {
			return checkResult; // 回傳null表示成功 (不成功會報RtsCode)
			// 如果回傳的不是null就回傳參數檢查的結果
		}
		// checkParam只有查格式對不對 沒有確認id跟qid 所以要另外查驗
		ResponseSurvey checkQid = checkQidOrempty(req);
		// 判斷問題清單是否為空 是否問題qId=問卷id
		if (checkQid != null) {
			return checkQid;
		}
		// 更新還要判斷DB裡面有沒有存在該筆ID (上面只判斷有沒有ID以及QID等不等於ID)
		Optional<Survey> svOp = surveydao.findById(req.getSurvey().getid());
		// 判斷內部有沒有這筆問卷 Optional這個包裝真的很靠北欸
		if (svOp.isEmpty()) {
			return new ResponseSurvey(RtnCode.SURVEY_ID_NOT_FOUND);
		}

		Survey sv = svOp.get();// 從Optional包裝裡面把問卷拆出來
		System.out.println(sv.getDescription());// 有抓到東西欸

		if (sv.getStatus_is_published()
				|| sv.getStatus_is_published() && LocalDate.now().isBefore(sv.getStart_date())) {
//			判斷是否可以修改的條件   尚未發布  或是已發布但是今天的日期比起始日期還早
			return new ResponseSurvey(RtnCode.SURVEY_UPDATE_ERROR);
		}

		for (SurveyQuestion i : req.getSurveyQuestionList()) {
			System.out.println("取得SQlist的qid=" + i.getQid());
		}

		surveydao.save(req.getSurvey());
		// 存問卷
		surveyQdao.saveAll(req.getSurveyQuestionList());
		surveyQdao.flush();
		// 存題目

		return new ResponseSurvey(RtnCode.SUCCESSFUL);
		// 回傳成功
	}

//===========================↑upDate↑===========================================================
//======================↓delete相關↓============================================================

	@Override
	@Transactional
	public ResponseSurvey deleteSurvey(List<Integer> id) {
		// 刪除問卷(要包含底下的問題)
		List<Survey> delTarget = surveydao.findByIdIn(id);
		// 找出目標問卷
		List<Integer> delList = new ArrayList<>();
		// 準備一個整數的list去存要刪除的id
		// 取得要刪除的問卷id In(符合尋找的ID)
		for (Survey delItem : delTarget) {
			if (!delItem.getStatus_is_published()
					|| delItem.getStatus_is_published() && LocalDate.now().isBefore(delItem.getStart_date())) {
				// 判斷可以刪除的條件 未發布 或是已發布但是今天的日期在開始日期之前
				delList.add(delItem.getid());
				// 判斷過的就加入待刪除的idList
			}
		}
		if (!delList.isEmpty()) {
			// 先能成功刪除的list不是空的再刪除
			surveydao.deleteAllById(delList);
			// 刪除被刪問卷裡的所有問題 id=Qid 所以把能通過刪除問卷的id都填入即可
			surveyQdao.deleteAllByQidIn(delList);
		}
		return new ResponseSurvey(RtnCode.SUCCESSFUL);
	}

	@Override
	@Transactional
	public ResponseSurvey deleteSurveyQuestion(int Qid, List<Integer> ListId) {
		// 單純刪除同一個問卷裡的問題 自己寫的 難驗證
		List<SurveyQuestion> delTargetQues = surveyQdao.findByQid(Qid);
		// Qid找出要刪除問題的問卷 然後從底下的id去找?
		List<Integer> delQuesId = ListId;
		// 要刪除的目標ID
		List<Integer> allQuesId = new ArrayList<>();
		// 整數的list 去接打算要刪除的問題底下包含的的id
		for (SurveyQuestion delQid : delTargetQues) {
			allQuesId.add(delQid.getQuid());
			// 同qid的每一筆id塞到事先準備要接的整數list
		}

		List<Integer> res = new ArrayList<>(allQuesId);
		res.retainAll(delQuesId);// 取交集?

		// 把要刪除的id清單放進去刪除
		return null;
	}

//=======================↑delete相關↑==================================================
//=======================↓搜尋↓========================================================	
//	暫存資料設定
//	@Cacheable(cacheNames = "searchZone",
//			//key的串接寫法 title_st_ed  
//			key = "#title.concat('_').concat(#start_date).toString.concat('_').concat(#end_date).toString",
//			unless = "#res.RtnCode.code!=200")
	@Override
	@Transactional
	public ResponseSurvey search(String title, LocalDate start_date, LocalDate end_date) {
//		title = StringUtils.hasText(title) ? title : "";
////		title有沒有 如果沒有就補上空字串
//		start_date = start_date == null ? LocalDate.of(1971, 1, 1) : start_date;
//		end_date = end_date == null ? LocalDate.of(2077, 8, 8) : end_date;
////		如果預設日期沒填上 自動生成搜尋的時間範圍  因為null值不會列入搜尋時間的範圍

		List<Survey> svList = surveydao.findByTitleContainingAndStartdateGreaterThanEqualAndEnddateLessThanEqual(title,
				start_date, end_date);
		// 超長JPA語法 吐了
		List<Integer> ids = new ArrayList<>(); // 問卷的id
		for (Survey i : svList) {
			int id = i.getid();
			ids.add(id);
		}
		List<SurveyQuestion> quidList = surveyQdao.findAllByQidIn(ids);// 找題目的list 從找到的id裡面去找 id=qid的
		List<QuizVo> quizVoList = new ArrayList<>();// 要回傳的的quizVoList
		for (Survey i : svList) {
			QuizVo vo = new QuizVo();
			vo.setSurvey(i);// 把每一項問卷加入vo
			List<SurveyQuestion> questionList = new ArrayList<>();
			for (SurveyQuestion item : quidList) {
				// i=survey item= questions
				// 把id=qid的問題篩選出來加入questionList (每一輪刷新 到下一張問卷就是空白list了)
				if (item.getQid() == i.getid()) {
					questionList.add(item);
				}
			}
			vo.setSurveyQuestion(questionList);// 把第i項問卷的題目填進去
			quizVoList.add(vo);// 將一份完整的問卷以及底下的問題塞進去要回傳的quizVoList 再跑下個迴圈
		}
		// 回傳打包後的quizVoList 跟成功狀態碼(這邊是用print打印測試查找的結果 )
		for (int i = 0; i < quizVoList.size(); i++) {
			String surveyTitle = quizVoList.get(i).getSurvey().getTitle();
			int surveyQues = quizVoList.get(i).getSurveyQuestionList().size();
			System.out.println("問卷" + surveyTitle);
			for (int j = 0; j < surveyQues; j++) {
				String ques = quizVoList.get(i).getSurveyQuestionList().get(j).getOptions();
				System.out.println("選項:" + (j + 1) + ques);
			}
		}

		return new ResponseSurvey(quizVoList, RtnCode.SUCCESSFUL);
	}
	// =======================↑搜尋↑========================================================
	// =======================↓後台搜尋列表(包含未發布)↓========================================================
	@Cacheable(value="titleZone",key="#title")
	@Override
	public ResSurveyList searchSurveyList(String title, LocalDate startdate, LocalDate enddate, boolean isAll) {

		title = StringUtils.hasText(title) ? title : "";
//		title有沒有 如果沒有就補上空字串
		startdate = startdate == null ? LocalDate.of(1971, 1, 1) : startdate;
		enddate = enddate == null ? LocalDate.of(2077, 8, 8) : enddate;
//		如果預設日期沒填上 自動生成搜尋的時間範圍  因為null值不會列入搜尋時間的範圍

		List<Survey> svList = new ArrayList<>();
		// 超長JPA語法 吐了
		if (isAll) {
			// 改參數名比較好讀 published=未發布 所以能看到全部包含未發布的問卷(給後台看的)
			svList = surveydao.findByTitleContainingAndStartdateGreaterThanEqualAndEnddateLessThanEqual(title,
					startdate, enddate);
		} else {
			svList = surveydao.findByTitleContainingAndStartdateGreaterThanEqualAndEnddateLessThanEqualAndPublishedTrue(
					title, startdate, enddate);
			// 前台搜尋 看不到未發布問卷
		}
		
		return new ResSurveyList(svList, RtnCode.SUCCESSFUL);
	}
	// =======================↑後台搜尋問卷列表(包含未發布)↑========================================================
	// =======================↓搜尋同張問卷的所有問題↓========================================================

//	@Override
//	public ResSurveyQues searchQuestionList(int qid) {
//		//找同一張問卷的所有問題
//		if(qid<=0) {
//			return new ResSurveyQues(null,RtnCode.SURVEY_Q_ID_ERROR);
//		}
//		List<SurveyQuestion> allQuesList=surveyQdao.findAllByQidIn(qid);
//		//原本參數是ArrayList dao新增同名函式int參數 如果不新增參數可以寫Array.as(qid)
//		return new ResSurveyQues(allQuesList,RtnCode.SUCCESSFUL);
//	}
	// =======================↑搜尋同張問卷的所有問題↑========================================================

	private ResponseSurvey checkParam(RequestSurvey req) {
		// 私有方法檢查送進來的資料對不對 確認參數

		Survey sv = req.getSurvey();
		LocalDate date = LocalDate.now();// 當天日期
		// 問卷本體
		if (!StringUtils.hasText(sv.getTitle()) || !StringUtils.hasText(sv.getDescription())
				|| sv.getStart_date() == null || sv.getEnd_date() == null
				|| sv.getStart_date().isAfter(sv.getEnd_date()) || sv.getStart_date().isBefore(date)) {
			// 四個檢查 有沒有標題 有沒有描述 起始時間不為null
			ResponseSurvey res = new ResponseSurvey(RtnCode.SURVEY_ERROR);
			return res;
		}

		List<SurveyQuestion> sqList = req.getSurveyQuestionList();
		// 問卷的問題
		for (SurveyQuestion sqId : sqList) {
			if (sqId.getQuid() <= 0 || !StringUtils.hasText(sqId.getTitle()) || !StringUtils.hasText(sqId.getType())
					|| !StringUtils.hasText(sqId.getOptions())) {
				// 檢查送過來的ID有沒有小於0 有沒有 題目標題 類型 選項
				return new ResponseSurvey(RtnCode.QUESTION_ERROR);
			}
		}

		return null;
	}

	private ResponseSurvey checkQidOrempty(RequestSurvey req) {
		// 確認問題的qid是否有 或是qid跟id不相等
		List<SurveyQuestion> sqList = req.getSurveyQuestionList();
		// 取得題目list
		for (SurveyQuestion sq : sqList) {
			if (sq.getQid() <= 0 || req.getSurvey().getid() != sq.getQid()) {
				// 如果qid小於等於0 或是問卷的id跟每個題目的qid不相等
				ResponseSurvey res = new ResponseSurvey(RtnCode.SURVEY_Q_ID_ERROR);
				return res;
			}
		}
		return null;
	}
}
