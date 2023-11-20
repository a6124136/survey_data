CREATE TABLE IF NOT EXISTS`survey_title` (
  `title_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(60) DEFAULT NULL COMMENT '問卷可以重複\n',
  `description` varchar(100) DEFAULT NULL COMMENT '描述?',
  `status_is_published` tinyint DEFAULT '0' COMMENT 'Tinyint是Sql的布林\n1=true\n0=false\n預設未發布=0',
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`title_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
