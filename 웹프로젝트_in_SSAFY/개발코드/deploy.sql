-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ssazitdb
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `acategory`
--

DROP TABLE IF EXISTS `acategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acategory` (
  `acategory_no` int NOT NULL AUTO_INCREMENT COMMENT '알고리즘 문제 코드',
  `acategory_source` varchar(20) NOT NULL COMMENT '알고리즘 문제 사이트',
  `acategory_number` int NOT NULL COMMENT '알고리즘 문제 번호',
  `title` varchar(20) NOT NULL COMMENT '알고리즘 문제명',
  `link` text COMMENT '사이트 링크',
  `user_no` int NOT NULL,
  PRIMARY KEY (`acategory_no`),
  UNIQUE KEY `acategory_unique` (`acategory_source`,`acategory_number`),
  KEY `acategory_fk_1` (`user_no`),
  CONSTRAINT `acategory_fk_1` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='알고리즘 문제 분류';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acategory`
--

LOCK TABLES `acategory` WRITE;
/*!40000 ALTER TABLE `acategory` DISABLE KEYS */;
INSERT INTO `acategory` VALUES (2,'백준',1697,'숨바꼭질','https://www.acmicpc.net/problem/1697',18),(4,'백준',1261,'알고스팟','https://www.acmicpc.net/problem/1261',18),(6,'백준',1607,'원숭이 타워','https://www.acmicpc.net/problem/1607',17),(7,'SWEA',1247,'최적 경로','https://swexpertacademy.com/main/code/problem/problemDetail.do',22),(8,'swea',5521,'상원이의 생일파티','https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWWO3kT6F2oDFAV4&categoryId=AWWO3kT6F2oDFAV4&categoryType=CODE',22);
/*!40000 ALTER TABLE `acategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `article_no` int NOT NULL AUTO_INCREMENT COMMENT '게시글 번호',
  `user_no` int NOT NULL COMMENT '작성자 번호',
  `board_no` int NOT NULL COMMENT '게시판 번호',
  `title` varchar(500) NOT NULL COMMENT '제목',
  `content` text NOT NULL COMMENT '내용',
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성/수정 일시',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일시',
  `open_scope` varchar(20) NOT NULL COMMENT '공개범위(공개(public)/친구공개(protected)/비공개(private))',
  `thumb_nail` text NOT NULL COMMENT '썸네일 이미지',
  `article_type` varchar(2) NOT NULL COMMENT '게시글 구분(알고(A)/자유글(N))',
  `sharable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '공유가능여부',
  `review_request` tinyint(1) NOT NULL DEFAULT '1' COMMENT '질문 요청여부',
  `like_count` int NOT NULL DEFAULT '0' COMMENT '추천수(참조칼럼)',
  `acategory_no` int DEFAULT NULL COMMENT '알고리즘 카테고리 번호',
  `category_no` int DEFAULT NULL COMMENT '일반 카테고리 번호',
  `reference` int DEFAULT NULL,
  PRIMARY KEY (`article_no`),
  KEY `user_no` (`user_no`),
  KEY `board_no` (`board_no`),
  KEY `article_ibfk_3` (`acategory_no`),
  KEY `article_ibfk_4` (`category_no`),
  KEY `article_ibfk_5` (`reference`),
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`),
  CONSTRAINT `article_ibfk_2` FOREIGN KEY (`board_no`) REFERENCES `myboard` (`board_no`),
  CONSTRAINT `article_ibfk_3` FOREIGN KEY (`acategory_no`) REFERENCES `acategory` (`acategory_no`),
  CONSTRAINT `article_ibfk_4` FOREIGN KEY (`category_no`) REFERENCES `category` (`category_no`),
  CONSTRAINT `article_ibfk_5` FOREIGN KEY (`reference`) REFERENCES `article` (`article_no`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='게시글';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,1,1,'testart','testttt','2020-07-22 11:32:42','2020-08-06 14:01:48','public','test.jpg','N',0,0,2,NULL,1,NULL),(8,17,2,'테그테스트','<p>Content of the editor.ㅁㄴㅇㄻㄴㅇㄻㄴㅇㄻㄴㅇ</p><table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:500px\"><tbody><tr><td>ㅎㅇ</td><td>ㅁㅁㅁㅁ</td></tr><tr><td><br></td><td><br></td></tr><tr><td><br></td><td><br></td></tr></tbody></table><p><br></p><blockquote><p>ddddddddddddddddddddddddddddddddddddddddd</p></blockquote><p><br></p><p><br></p><div tabindex=\"-1\" contenteditable=\"false\" data-cke-widget-wrapper=\"1\" data-cke-filter=\"off\" class=\"cke_widget_wrapper cke_widget_block cke_widget_codeSnippet cke_widget_focused cke_widget_selected\" data-cke-display-name=\"코드 스니펫\" data-cke-widget-id=\"1\" role=\"region\" aria-label=\"코드 스니펫 위젯\"><pre data-cke-widget-data=\"%7B%22lang%22%3A%22abap%22%2C%22code%22%3A%22sadfasdf%22%2C%22classes%22%3Anull%7D\" data-cke-widget-upcasted=\"1\" data-cke-widget-keep-attr=\"0\" data-widget=\"codeSnippet\" class=\"cke_widget_element\"><code class=\"language-abap hljs\">sadfasdf<span class=\"line-numbers-rows\"><span></span></span></code></pre><img src=\"data:image/gif;base64,R0lGODlhAQABAPABAP///wAAACH5BAEKAAAALAAAAAABAAEAAAICRAEAOw==\" class=\"cke_reset cke_widget_mask\"><span class=\"cke_reset cke_widget_drag_handler_container\" style=\"background: url(&quot;https://cdn.ckeditor.com/4.14.1/full-all/plugins/widget/images/handle.png&quot;) rgba(220, 220, 220, 0.5); top: -15px; left: 0px;\"><img class=\"cke_reset cke_widget_drag_handler\" data-cke-widget-drag-handler=\"1\" src=\"data:image/gif;base64,R0lGODlhAQABAPABAP///wAAACH5BAEKAAAALAAAAAABAAEAAAICRAEAOw==\" width=\"15\" title=\"움직이려면 클릭 후 드래그 하세요\" height=\"15\" role=\"presentation\"></span></div><p><br></p><p><br></p><div data-cke-hidden-sel=\"1\" data-cke-temp=\"1\" style=\"position:fixed;top:0;left:-1000px;width:0;height:0;overflow:hidden;\">코드 스니펫 위젯</div>','2020-08-05 22:01:25','2020-08-06 14:01:48','private','','N',1,0,0,NULL,1,NULL),(11,17,2,'뭐지','<pre>\n<code class=\"language-cpp\">int a = 10;</code></pre>\n\n<p>&nbsp;</p>\n','2020-08-05 22:57:04','2020-08-06 14:01:48','public','','N',1,0,0,NULL,1,NULL),(13,9,5,'사실은 영주바보','<p>사실임 ㅋㅋ</p>\n','2020-07-29 13:36:41','2020-08-06 14:01:48','public','','A',1,0,2,2,NULL,NULL),(14,9,5,'사실은 영주바보','<p>사실임 ㅋㅋ</p>\n','2020-07-29 13:36:43','2020-08-06 14:01:48','public','','A',1,0,2,2,NULL,NULL),(15,9,5,'사실은 영주바보','<p>사실임 ㅋㅋ</p>\n','2020-07-29 13:36:44','2020-08-06 14:01:48','public','','A',1,0,0,4,NULL,NULL),(16,9,5,'사실은 영주바보','<p>사실임 ㅋㅋ</p>\n','2020-07-29 13:36:47','2020-08-06 14:01:48','public','','A',1,0,1,4,NULL,NULL),(19,17,9,'숨바꼭질풀이','<p><iframe frameborder=\"0\" height=\"360\" src=\"https://www.youtube.com/embed/5eS_gbUbAM0\" width=\"640\"></iframe>리치 텍스트 편집기, editor3</p>\n\n<p>리치 텍스트 편집기, editor2</p>\n\n<p>리치 텍스트 편집기, editor5</p>\n\n<p>&nbsp;</p>\n\n<pre>\n<code class=\"language-java\">int a = 10;\nint b = 20;\nCar car = new Car(10, 20);\nint c; </code></pre>\n\n<p>숨바숨바 Content of the editor</p>\n','2020-08-05 21:20:31','2020-08-10 23:04:46','public','','A',1,0,1,4,NULL,NULL),(20,17,9,'원숭이타워','<blockquote>\n<p>원숭이 타워 Content of the editor.</p>\n</blockquote>\n\n<p><img alt=\"\" data-widget=\"image\" src=\"http://localhost:9999/userfiles/images/annoying%20pear.png\" style=\"height:62px; width:45px\" /></p>\n','2020-08-06 10:43:51','2020-08-06 14:01:48','public','','A',1,0,0,6,NULL,NULL),(22,17,2,'code','<p>Content of the editor.<iframe frameborder=\"0\" height=\"360\" src=\"https://www.youtube.com/embed/Sp2sVbatOw8\" width=\"640\"></iframe></p>\n\n<p>&nbsp;</p>\n\n<pre>\n<code class=\"language-c\">int c = 11123123</code></pre>\n\n<p>&nbsp;</p>\n','2020-08-06 10:43:33','2020-08-06 14:01:48','protected','','N',1,0,0,NULL,1,NULL),(23,17,3,'code','<div class=\"youtube-embed-wrapper\" style=\"position:relative;padding-bottom:56.25%;padding-top:30px;height:0;overflow:hidden\"><iframe width=\"640\" height=\"360\" src=\"https://www.youtube.com/embed/gM1bsBkUryA\" style=\"position:absolute;top:0;left:0;width:100%;height:100%\" frameborder=\"0\" allowfullscreen=\"\"></iframe></div><p>of the edi</p><p><sub><strong><em>ㄴㅇㄻㄴㄹ<sup>​​​​​​​fddfdfdfdfdf</sup></em></strong></sub></p>','2020-08-05 21:56:34','2020-08-06 14:01:48','protected','','N',1,0,0,NULL,1,NULL),(24,17,3,'code','<p style=\"text-align:right\">JavaScript</p>\n\n<pre>\n<code class=\"language-javascript\">let a = 0;\nlet obg = [\'aaaa\',\'bbbb\'];</code></pre>\n','2020-08-06 20:35:46','2020-08-06 14:01:48','protected','','N',1,0,1,NULL,1,NULL),(25,17,3,'code','<pre>\n<code class=\"language-javascript\">let a = 0;\nlet obg = [\'aaaa\',\'bbbb\'];</code></pre>\n\n<p>코드코드</p>\n\n<blockquote>\n<p>sdfasdfasdfasfasdfasdfadsf</p>\n</blockquote>\n\n<p>ㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷ&nbsp;</p>\n','2020-08-06 21:00:24','2020-08-06 14:01:48','protected','','N',1,0,1,NULL,1,NULL),(30,17,3,'ㅇㅇㅇㅇㅇㅇㅇㅇeeeeeeeee','<p>asdfasfasdfasdfasdfasdfasdfasdf</p>\n','2020-08-04 11:42:07','2020-08-06 14:01:48','public','','N',1,0,1,NULL,1,NULL),(31,7,19,'웃으면 행복해여','<p>jeje</p>\n','2020-08-04 14:29:34','2020-08-10 16:03:04','public','','N',1,0,0,NULL,1,NULL),(32,10,16,'휘핑 크림 추가','<pre>\n<code class=\"language-c\">print(\'용수오빠바보\')</code></pre>\n\n<p>​​​​​<iframe frameborder=\"0\" height=\"360\" src=\"https://www.youtube.com/embed/2Yhbw-Jrv3w\" width=\"640\"></iframe></p>\n','2020-08-06 11:11:07','2020-08-06 14:01:48','','','N',1,0,1,NULL,1,NULL),(33,9,14,'안녕','<p>ㅎㅎㅎ</p>','2020-08-05 11:43:10','2020-08-06 14:01:48','public','','N',1,0,0,NULL,1,NULL),(34,7,19,'ㅎㅎㅎㅎ','<blockquote><p>ㅎㅎㅎㅎ</p></blockquote>','2020-08-05 16:53:06','2020-08-06 14:01:48','public','','N',1,0,0,NULL,1,NULL),(35,7,19,'ㅎㅎㅎㅎ','<blockquote><p>ㅎㅎㅎㅎ</p></blockquote>','2020-08-05 16:53:11','2020-08-06 14:01:48','public','','N',1,0,0,NULL,1,NULL),(36,10,16,'노동요 듣고 힘내세요!!!!','<p><iframe frameborder=\"0\" height=\"360\" src=\"https://www.youtube.com/embed/TpPwI_Lo0YY\" width=\"640\"></iframe></p>\n\n<p>시간을 지배하는 sakeL의 노동요 입니다^.^</p>\n','2020-08-06 17:51:15','2020-08-06 14:01:48','public','','N',1,0,0,NULL,1,NULL),(39,20,43,'잘먹었습니다','<p>ㅎㅎ</p>\n','2020-08-06 14:35:08','2020-08-06 14:35:08','public','','N',1,0,0,NULL,1,NULL),(41,7,19,'아아 마이크테스트','<p>아아 마이크테스트 마이크테스트</p>\n\n<p>&nbsp;</p>\n','2020-08-06 21:25:29','2020-08-06 21:25:29','public','','N',1,0,1,NULL,1,NULL),(42,10,45,'테스트','<p>이 헌법중 공무원의 임기 또는 중임제한에 관한 규정은 이 헌법에 의하여 그 공무원이 최초로 선출 또는 임명된 때로부터 적용한다. 정당은 그 목적&middot;조직과 활동이 민주적이어야 하며, 국민의 정치적 의사형성에 참여하는데 필요한 조직을 가져야 한다. 군사재판을 관할하기 위하여 특별법원으로서 군사법원을 둘 수 있다. 제3항의 승인을 얻지 못한 때에는 그 처분 또는 명령은 그때부터 효력을 상실한다. 이 경우 그 명령에 의하여 개정 또는 폐지되었던 법률은 그 명령이 승인을 얻지 못한 때부터 당연히 효력을 회복한다. 새로운 회계연도가 개시될 때까지 예산안이 의결되지 못한 때에는 정부는 국회에서 예산안이 의결될 때까지 다음의 목적을 위한 경비는 전년도 예산에 준하여 집행할 수 있다.</p>\n\n<p>국민의 자유와 권리는 헌법에 열거되지 아니한 이유로 경시되지 아니한다. 국가는 법률이 정하는 바에 의하여 재외국민을 보호할 의무를 진다. 근로자는 근로조건의 향상을 위하여 자주적인 단결권&middot;단체교섭권 및 단체행동권을 가진다. 공무원의 직무상 불법행위로 손해를 받은 국민은 법률이 정하는 바에 의하여 국가 또는 공공단체에 정당한 배상을 청구할 수 있다. 이 경우 공무원 자신의 책임은 면제되지 아니한다. 헌법재판소는 법관의 자격을 가진 9인의 재판관으로 구성하며, 재판관은 대통령이 임명한다. 국회는 정부의 동의없이 정부가 제출한 지출예산 각항의 금액을 증가하거나 새 비목을 설치할 수 없다.</p>\n','2020-08-06 21:39:06','2020-08-06 21:39:06','public','','N',1,0,0,NULL,1,NULL),(43,9,5,'알괴퍼','<p>스파트</p>\n','2020-08-06 23:04:04','2020-08-06 23:04:04','public','','A',1,0,0,4,NULL,NULL),(45,22,62,'0807 알고리즘 풀기!!','<p>2차 세계 대전에서 연합군과 독일군의 전투가 점점 치열해지고 있다.<br />\n전투가 진행중인 지역은 대규모 폭격과 시가전 등으로 인해 도로 곳곳이 파손된 상태이다.<br />\n그림 1(a)에서와 같이 도로들은 전투로 인해 트럭이나 탱크와 같은 차량들이 지날 갈 수 없다.<br />\n전투에서 승리하기 위해서는 기갑사단과 보급부대가 신속하게 이동하기 위한 도로가 있어야 한다.<br />\n공병대는 출발지(S) 에서 도착지(G)까지 가기 위한 도로 복구 작업을 빠른 시간 내에 수행하려고 한다.<br />\n도로가 파여진 깊이에 비례해서 복구 시간은 증가한다.<br />\n출발지에서 도착지까지 가는 경로 중에 복구 시간이 가장 짧은 경로에 대한 총 복구 시간을 구하시오.<br />\n깊이가 1이라면 복구에 드는 시간이 1이라고 가정한다.</p>\n\n<pre>\n<code class=\"language-python\">from collections import deque\n\ndx = [-1, 1, 0, 0]\ndy = [0, 0, -1, 1]\n\ndef bfs():\n    while q:\n        x, y = q.popleft()\n        for n in range(4):\n            xn, yn = x+dx[n], y+dy[n]\n            if 0 &lt;= xn &lt; N and 0 &lt;= yn &lt; N:\n                if const[x][y]+road[xn][yn] &lt; const[xn][yn]:\n                    const[xn][yn] = const[x][y]+road[xn][yn]\n                    q.append((xn, yn))\n\nT = int(input())\nfor tc in range(1, T+1):\n    q = deque()\n    N = int(input())\n    road = [list(map(int, input())) for _ in range(N)]\n    const = [[100000 for _ in range(N)] for _ in range(N)]\n    const[0][0] = 0\n    q.append((0, 0))\n    bfs()\n    print(\'#{} {}\'.format(tc, const[-1][-1]))</code></pre>\n\n<p>&nbsp;</p>\n','2020-08-07 10:31:54','2020-08-07 10:31:54','','','A',1,0,0,2,NULL,NULL),(46,22,62,'0806 알고리즘 스터디','<p>영어 소문자로 된 문자열이 있다. 문자열의 길이가 n일 때 접미어들은 문자열의 길이만큼 존재한다.<br />\n&ldquo;monster&rdquo;라는 문자열에는 아래 그램의 왼쪽과 같은 접미어들이 있고 사전적으로 정렬했을 경우에&nbsp;아래 그림과 같이 정렬된다.</p>\n\n<pre>\n<code class=\"language-python\">T = int(input())\nfor tc in range(1, T+1):\n    K = int(input())\n    S = input()\n    suffixes = []\n    for i in range(len(S)):\n        suffixes.append(S[i:])\n    suffixes.sort()\n    if K &gt; len(suffixes):\n        print(\'#{} none\'.format(tc))\n    else:\n        print(\'#{} {}\'.format(tc, suffixes[K-1]))</code></pre>\n\n<p>&nbsp;</p>\n','2020-08-07 10:34:08','2020-08-07 10:34:08','','','A',1,0,0,4,NULL,NULL),(47,22,62,'swea 1247 (코드 피드백 받아요~~)','<p>삼성전자의 서비스 기사인 김대리는 회사에서 출발하여 냉장고 배달을 위해 N명의 고객을 방문하고 자신의 집에 돌아가려한다.<br />\n회사와 집의 위치, 그리고 각 고객의 위치는 이차원 정수 좌표 (x, y)로 주어지고 (0&nbsp;&le;&nbsp;x&nbsp;&le;&nbsp;100, 0&nbsp;&le;&nbsp;y&nbsp;&le;&nbsp;100)<br />\n두 위치 (x1, y1)와 (x2, y2) 사이의 거리는 |x1-x2| + |y1-y2|으로 계산된다.<br />\n여기서 |x|는 x의 절대값을 의미하며 |3| = |-3| = 3이다. 회사의 좌표, 집의 좌표, 고객들의 좌표는 모두 다르다.<br />\n회사에서 출발하여 N명의 고객을 모두 방문하고 집으로 돌아오는 경로 중 가장 짧은 것을 찾으려 한다.<br />\n회사와 집의 좌표가 주어지고, 2명에서 10명 사이의 고객 좌표가 주어질 때,<br />\n회사에서 출발해서 이들을&nbsp;<u>모두 방문하고</u>&nbsp;집에 돌아가는 경로 중 총 이동거리가 가장 짧은 경로를 찾는 프로그램을 작성하라.<br />\n여러분의 프로그램은 가장 짧은 경로의 이동거리만 밝히면 된다.<br />\n이 문제는 가장 짧은 경로를 &lsquo;효율적으로&rsquo; 찾는 것이 목적이 아니다.<br />\n여러분은 모든 가능한 경로를 살펴서 해를 찾아도 좋다.<br />\n모든 경우를 체계적으로 따질 수 있으면 정답을 맞출 수 있다.</p>\n\n<pre>\n<code class=\"language-python\">def dfs(x, y, d):\n    global result\n    # print(x, y)\n    if result &lt; d:\n        return\n    if visited.count(1) == N:\n        d += abs(x-pos[2]) + abs(y-pos[3])\n        if result &gt; d:\n            result = d\n        return\n    for i in range(4, 2*N+4, 2):\n        if visited[(i-4)//2] == 0:\n            visited[(i-4)//2] = 1\n            dfs(pos[i], pos[i+1], d+(abs(x-pos[i])+abs(y-pos[i+1])))\n            visited[(i-4)//2] = 0\n\nT = int(input())\nfor tc in range(1, T+1):\n    N = int(input())\n    pos = list(map(int, input().split()))\n    visited = [0 for _ in range(N)]\n    result = 2000\n    dfs(pos[0], pos[1], 0)\n    print(\'#{} {}\'.format(tc, result))</code></pre>\n\n<p>&nbsp;</p>\n','2020-08-07 10:38:43','2020-08-07 10:38:43','public','','A',1,0,0,7,NULL,NULL),(48,22,55,'오늘의 점심은 짬뽕ㅎㅎ','<p>오늘 먹은 짬뽕 존맛탱</p>\n\n<p>&nbsp;</p>\n\n<p><img alt=\"\" height=\"225\" src=\"https://ncube.net/wp-content/uploads/2016/07/IMG_0241.jpg\" width=\"300\" /></p>\n','2020-08-07 11:03:23','2020-08-07 11:03:03','public','','N',1,0,0,NULL,5,NULL),(49,22,55,'카레 먹고 싶은날 ^.^','<p>카레엔 가라야게 추가지!!</p>\n\n<p>&nbsp;</p>\n\n<p><img alt=\"\" height=\"200\" src=\"https://cheerio6202.files.wordpress.com/2014/01/img_6218.jpg\" width=\"300\" /></p>\n','2020-08-07 11:07:01','2020-08-07 11:07:01','public','','N',1,0,1,NULL,5,NULL),(50,22,51,'아인슈패너 만들기 진짜 맛있어 ㅎ','<p>집에서 아인슈패너 만들기</p>\n\n<p>넘나 간단한 재료로 만들어 볼께요 ㅎㅎ&nbsp;</p>\n\n<p>예쁜 컵에 담아서 만들거에요</p>\n\n<blockquote>\n<p>재료 : 휘핑크림, 카누마일드, 연유</p>\n</blockquote>\n','2020-08-07 11:32:27','2020-08-07 11:32:27','public','','N',1,0,0,NULL,5,NULL),(51,22,51,'소프트콘 아이스크림으로 아이스크림 라떼 만들기v','<p>요즘 핫하다는 매일우유 소프트콘 세븐일레븐으로 달려가서 하나를 겟 해봅니다ㅎㅎ</p>\n\n<p>마침 2+1 행사중!!!! 하나는 그냥 먹으려구용</p>\n\n<p>1. 우유를 가득넣고</p>\n\n<p>2. 카누를 풀어 라떼를 만듭니다.</p>\n\n<p>3. 그 위에 아이스크림을 올리면 완성!</p>\n','2020-08-07 11:36:44','2020-08-07 11:36:44','public','','N',1,0,0,NULL,5,NULL),(53,22,62,'오늘의 알고리즘','<p>상원이의 생일 파티가 곧 열린다!<br />\n<br />\n그렇기에 상원이는 반 친구들에게 생일 파티 초대장을 주려고 한다.<br />\n<br />\n그러나 파티가 어색하게 되는 것을&nbsp;원치 않는 상원이는&nbsp;모든 친구들에게 초대장을&nbsp;줄 생각이&nbsp;없다.<br />\n<br />\n같은 반에 있지만, 서로 친한 친구가 아닐 수도 있기 때문이다.<br />\n<br />\n상원이는 우선 자신과 친한 친구들에게는 모두 초대장을 주기로 했다.<br />\n<br />\n여기에 더해서 친한 친구의 친한 친구인 경우에도 초대장을 주기로 했다.<br />\n<br />\n총 몇 명의 친구들에게 초대장을 주어야 하는지 구하는 프로그램을 작성하라.<br />\n<br />\n상원이의 반에는 N명이 있으며, 각 학생들은 1번에서 N번까지의 번호가 붙어 있다.<br />\n<br />\n여기서 1번 학생이 상원이다.<br />\n&nbsp;</p>\n\n<pre>\n<code class=\"language-python\">T = int(input())\nfor tc in range(1, T+1):\n    N, M = map(int, input().split())\n    # 초대받은 학생 \n    invited = [0 for _ in range(N+1)]\n    # 친구 관계 저장\n    friends = [[] for _ in range(N+1)]\n    # 상원이의 친한 친구\n    best_friends = []\n    for _ in range(M):\n        a, b = map(int, input().split())\n        if a == 1:\n            best_friends.append(b)\n            invited[b] = 1\n        elif b == 1:\n            best_friends.append(a)\n            invited[a] = 1\n        else:\n            friends[a].append(b)\n            friends[b].append(a)\n    # 친한 친구의 친한 친구\n    for bf in best_friends:\n        for friend in friends[bf]:\n            if invited[friend] == 0:\n                invited[friend] = 1\n\n    print(\'#{} {}\'.format(tc, invited.count(1)))</code></pre>\n\n<p><iframe allowfullscreen=\"\" frameborder=\"0\" height=\"360\" src=\"https://www.youtube.com/embed/wAFL7OcHGiI\" width=\"640\"></iframe></p>\n','2020-08-07 13:09:00','2020-08-07 13:09:00','public','','A',1,0,0,8,NULL,NULL),(54,22,62,'알고리즘 문제풀이','<p>상원이의 생일 파티가 곧 열린다!<br />\n그렇기에 상원이는 반 친구들에게 생일 파티 초대장을 주려고 한다.<br />\n그러나 파티가 어색하게 되는 것을&nbsp;원치 않는 상원이는&nbsp;모든 친구들에게 초대장을&nbsp;줄 생각이&nbsp;없다.<br />\n같은 반에 있지만, 서로 친한 친구가 아닐 수도 있기 때문이다.</p>\n\n<pre>\n<code class=\"language-python\">T = int(input())\nfor tc in range(1, T+1):\n    N, M = map(int, input().split())\n    # 초대받은 학생 \n    invited = [0 for _ in range(N+1)]\n    # 친구 관계 저장\n    friends = [[] for _ in range(N+1)]\n    # 상원이의 친한 친구\n    best_friends = []\n    for _ in range(M):\n        a, b = map(int, input().split())\n        if a == 1:\n            best_friends.append(b)\n            invited[b] = 1\n        elif b == 1:\n            best_friends.append(a)\n            invited[a] = 1\n        else:\n            friends[a].append(b)\n            friends[b].append(a)\n    # 친한 친구의 친한 친구\n    for bf in best_friends:\n        for friend in friends[bf]:\n            if invited[friend] == 0:\n                invited[friend] = 1\n\n    print(\'#{} {}\'.format(tc, invited.count(1)))</code></pre>\n\n<p><iframe frameborder=\"0\" height=\"360\" src=\"https://www.youtube.com/embed/wAFL7OcHGiI\" width=\"640\"></iframe></p>\n','2020-08-07 13:42:51','2020-08-10 16:13:21','public','','A',1,0,0,8,NULL,NULL),(55,17,3,'공유글입니다','<p>카레엔 가라야게 추가지!!(공유 공유)</p>\n\n<p><img alt=\"\" height=\"200\" src=\"https://cheerio6202.files.wordpress.com/2014/01/img_6218.jpg\" width=\"300\" /></p>\n','2020-08-09 15:42:51','2020-08-09 15:42:51','','','N',1,0,0,NULL,5,49),(56,17,3,'bb','<p>vv</p>\n','2020-08-09 15:43:49','2020-08-09 15:43:49','','','N',1,0,0,NULL,5,NULL),(57,9,14,'씨플플테스트','<pre>\n<code class=\"language-cpp\">#include \"iostream\"\nusing namespace std;</code></pre>\n\n<p>아녕</p>\n','2020-08-11 16:56:37','2020-08-11 16:56:37','public','','N',1,0,0,NULL,2,NULL),(58,17,3,'테스트','<p>&nbsp;</p>\n\n<p>dddd</p>\n\n<p><img alt=\"\" height=\"278\" src=\"http://localhost:9999/userfiles/images/annoying%20pear.png\" width=\"202\" /></p>\n\n<p><img alt=\"\" height=\"191\" src=\"http://localhost:9999/userfiles/images/thinking%20face%20monocle.png\" width=\"212\" /></p>\n\n<p>ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ</p>\n','2020-08-12 22:01:03','2020-08-12 22:01:03','public','http://localhost:9999/userfiles/images/annoying%20pear.png','N',1,0,0,NULL,1,NULL);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articlefavorite`
--

DROP TABLE IF EXISTS `articlefavorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articlefavorite` (
  `favorite_no` int NOT NULL AUTO_INCREMENT COMMENT '즐겨찾기 번호',
  `user_no` int NOT NULL COMMENT '사용자 번호',
  `article_no` int NOT NULL COMMENT '글 번호',
  `favorite_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '즐겨찾기 추가 일시',
  PRIMARY KEY (`favorite_no`),
  KEY `user_no` (`user_no`),
  KEY `article_no` (`article_no`),
  CONSTRAINT `articlefavorite_ibfk_1` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`),
  CONSTRAINT `articlefavorite_ibfk_2` FOREIGN KEY (`article_no`) REFERENCES `article` (`article_no`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='글 즐겨찾기';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articlefavorite`
--

LOCK TABLES `articlefavorite` WRITE;
/*!40000 ALTER TABLE `articlefavorite` DISABLE KEYS */;
INSERT INTO `articlefavorite` VALUES (12,17,45,'2020-08-09 15:20:00'),(13,17,30,'2020-08-09 18:02:22'),(14,9,1,'2020-08-12 23:11:39'),(15,1,14,'2020-08-13 09:32:04'),(16,7,31,'2020-08-13 11:23:44'),(17,7,48,'2020-08-13 11:24:30');
/*!40000 ALTER TABLE `articlefavorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articlelike`
--

DROP TABLE IF EXISTS `articlelike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articlelike` (
  `article_no` int NOT NULL COMMENT '게시글 번호',
  `user_no` int NOT NULL COMMENT '게시글 추천자',
  `like_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '좋아요 일시',
  PRIMARY KEY (`article_no`,`user_no`),
  KEY `articlelike_ibfk_2` (`user_no`),
  CONSTRAINT `articlelike_ibfk_1` FOREIGN KEY (`article_no`) REFERENCES `article` (`article_no`) ON DELETE CASCADE,
  CONSTRAINT `articlelike_ibfk_2` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='게시글 좋아요';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articlelike`
--

LOCK TABLES `articlelike` WRITE;
/*!40000 ALTER TABLE `articlelike` DISABLE KEYS */;
INSERT INTO `articlelike` VALUES (1,1,'2020-08-06 18:51:44'),(1,10,'2020-08-06 18:52:55'),(13,9,'2020-08-07 10:38:19'),(13,17,'2020-08-06 12:35:50'),(14,1,'2020-08-13 09:31:58'),(14,17,'2020-08-05 16:51:54'),(16,9,'2020-08-13 11:07:12'),(19,17,'2020-08-09 16:07:32'),(24,17,'2020-08-06 20:34:35'),(25,17,'2020-08-06 11:41:35'),(30,17,'2020-08-09 14:35:46'),(32,10,'2020-08-06 11:11:37'),(41,7,'2020-08-06 21:26:12'),(49,17,'2020-08-07 12:52:40');
/*!40000 ALTER TABLE `articlelike` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`ssazit`@`%`*/ /*!50003 TRIGGER `article_like_insert` BEFORE INSERT ON `articlelike` FOR EACH ROW BEGIN
	update
		Article
	SET
		like_count = like_count + 1
	WHERE
		article_no = NEW.article_no;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`ssazit`@`%`*/ /*!50003 TRIGGER `article_like_delete` BEFORE DELETE ON `articlelike` FOR EACH ROW BEGIN
	update
		Article
	SET
		like_count = like_count - 1
	WHERE
		article_no = OLD.article_no;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `articleshare`
--

DROP TABLE IF EXISTS `articleshare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articleshare` (
  `article_no` int NOT NULL COMMENT '게시글 번호',
  `user_no` int NOT NULL COMMENT '게시글 공유자',
  `share_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '공유 일시',
  PRIMARY KEY (`article_no`,`user_no`),
  KEY `user_no` (`user_no`),
  CONSTRAINT `articleshare_ibfk_1` FOREIGN KEY (`article_no`) REFERENCES `article` (`article_no`),
  CONSTRAINT `articleshare_ibfk_2` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='게시글 공유';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articleshare`
--

LOCK TABLES `articleshare` WRITE;
/*!40000 ALTER TABLE `articleshare` DISABLE KEYS */;
/*!40000 ALTER TABLE `articleshare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `arttag`
--

DROP TABLE IF EXISTS `arttag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `arttag` (
  `article_no` int NOT NULL COMMENT '글 번호',
  `tag_no` int NOT NULL COMMENT '대표태그 번호',
  PRIMARY KEY (`article_no`,`tag_no`),
  KEY `tag_no` (`tag_no`),
  CONSTRAINT `arttag_ibfk_1` FOREIGN KEY (`article_no`) REFERENCES `article` (`article_no`) ON DELETE CASCADE,
  CONSTRAINT `arttag_ibfk_2` FOREIGN KEY (`tag_no`) REFERENCES `tag` (`tag_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='글 태그';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arttag`
--

LOCK TABLES `arttag` WRITE;
/*!40000 ALTER TABLE `arttag` DISABLE KEYS */;
INSERT INTO `arttag` VALUES (1,1),(8,1),(11,1),(13,1),(14,1),(15,1),(16,1),(20,1),(22,1),(23,1),(24,1),(25,1),(41,1),(56,1),(58,1),(13,2),(42,2),(53,2),(54,2),(13,3),(30,3),(53,3),(30,5),(39,5),(39,11);
/*!40000 ALTER TABLE `arttag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `block`
--

DROP TABLE IF EXISTS `block`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `block` (
  `blocker` int NOT NULL COMMENT '차단한 사람',
  `blocking` int NOT NULL COMMENT '차단당한 사람',
  `block_date` datetime NOT NULL COMMENT '차단 일시',
  PRIMARY KEY (`blocker`,`blocking`),
  KEY `blocking` (`blocking`),
  CONSTRAINT `block_ibfk_1` FOREIGN KEY (`blocker`) REFERENCES `user` (`user_no`),
  CONSTRAINT `block_ibfk_2` FOREIGN KEY (`blocking`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='차단';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `block`
--

LOCK TABLES `block` WRITE;
/*!40000 ALTER TABLE `block` DISABLE KEYS */;
INSERT INTO `block` VALUES (7,8,'2020-08-10 16:28:31'),(7,11,'2020-08-07 11:22:53'),(22,3,'2020-08-07 13:13:30');
/*!40000 ALTER TABLE `block` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog` (
  `blog_no` int NOT NULL AUTO_INCREMENT COMMENT '블로그번호',
  `user_no` int NOT NULL COMMENT '소유자번호',
  `blog_name` varchar(20) NOT NULL COMMENT '블로그명',
  `blog_birth` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '블로그생성일시',
  PRIMARY KEY (`blog_no`),
  KEY `user_no` (`user_no`),
  CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='블로그';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (1,1,'happy house','2020-07-22 11:32:42'),(2,17,'그레이색이야','2020-07-27 15:34:58'),(3,10,'고먐미 귀며뭐','2020-07-28 09:07:55'),(4,7,'용수서버야 블로그내놔라','2020-07-29 09:27:04'),(5,9,'갓정훈님의 블로그','2020-07-29 11:44:17'),(6,3,'모지?','2020-07-29 20:52:38'),(12,20,'갈비탕 먹즈아!!!!!!!!11','2020-08-06 11:53:47'),(13,22,'행복한 merge되세요','2020-08-06 20:03:37'),(14,24,'라바빼고 다 꺼저','2020-08-12 11:57:46');
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `book_no` int NOT NULL AUTO_INCREMENT COMMENT '책 번호',
  `book_title` varchar(20) NOT NULL COMMENT '책 제목',
  `version` int NOT NULL DEFAULT '1' COMMENT '버전',
  `cover_front` varchar(50) NOT NULL DEFAULT 'resource/book_img/default_front.JPEG' COMMENT '표지(앞)',
  `cover_middle` varchar(50) NOT NULL DEFAULT 'resource/book_img/default_middle.JPG' COMMENT '표지(중)',
  `cover_back` varchar(50) NOT NULL DEFAULT 'resource/book_img/default_back.JPG' COMMENT '표지(뒤)',
  `like_count` int NOT NULL DEFAULT '0' COMMENT '추천수(참조칼럼)',
  `writer` int NOT NULL COMMENT '작성자 번호',
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성 일시',
  `update_date` datetime DEFAULT NULL COMMENT '수정 일시',
  `open_scope` varchar(20) NOT NULL COMMENT '공개범위(공개(public)/친구공개(protected)/비공개(private))',
  `category_no` int DEFAULT NULL COMMENT '일반 카테고리 번호',
  `book_index` text NOT NULL COMMENT '책 목차',
  `book_content` text NOT NULL COMMENT '책 내용',
  `book_reference` text NOT NULL COMMENT '출처',
  PRIMARY KEY (`book_no`),
  KEY `book_fk_1` (`writer`),
  KEY `book_ibfk_2` (`category_no`),
  CONSTRAINT `book_fk_1` FOREIGN KEY (`writer`) REFERENCES `user` (`user_no`),
  CONSTRAINT `book_ibfk_2` FOREIGN KEY (`category_no`) REFERENCES `category` (`category_no`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='책';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (3,'내가 \'이용수\'다',1,'resource/book_img/3/3_front.PNG','resource/book_img/3/3_middle.PNG','resource/book_img/3/3_back.PNG',0,1,'2020-08-07 09:56:03','2020-08-07 09:56:03','public',NULL,'','',''),(4,'바보는 정말 영주인가',1,'resource/book_img/4/4_front.PNG','resource/book_img/4/4_middle.PNG','resource/book_img/4/4_back.PNG',0,1,'2020-08-07 09:57:55','2020-08-07 09:57:55','public',NULL,'','',''),(5,'내 연관검색어는 열정',1,'resource/book_img/5/5_front.PNG','resource/book_img/default_middle.JPG','resource/book_img/5/5_back.PNG',0,1,'2020-08-07 09:59:21','2020-08-07 09:59:21','public',NULL,'','',''),(6,'찌니와 함께 떠나는 맛집 여행',1,'resource/book_img/6/6_front.JPEG','resource/book_img/default_middle.JPG','resource/book_img/6/6_back.JPEG',0,1,'2020-08-07 10:00:31','2020-08-07 10:00:31','public',NULL,'','',''),(7,'boj 브론즈 풀이집',1,'resource/book_img/7/7_front.JPEG','resource/book_img/7/7_middle.PNG','resource/book_img/7/7_back.PNG',0,22,'2020-08-07 11:17:27','2020-08-07 11:17:27','public',4,'','',''),(8,'추천 맛집 리스트',1,'resource/book_img/8/8_front.PNG','resource/book_img/8/8_middle.PNG','resource/book_img/8/8_back.PNG',0,22,'2020-08-07 11:25:44','2020-08-07 11:25:44','public',5,'','',''),(9,'추천맛집2',1,'resource/book_img/9/9_front.PNG','resource/book_img/9/9_middle.JPEG','resource/book_img/9/9_back.JPEG',0,22,'2020-08-07 12:01:19','2020-08-07 12:01:20','public',5,'','',''),(10,'알고리즘 피드백 모음',1,'resource/book_img/10/10_front.JPEG','resource/book_img/10/10_middle.JPEG','resource/book_img/10/10_back.JPEG',0,22,'2020-08-07 12:04:17','2020-08-07 12:04:17','protected',4,'','',''),(11,'나만의 책',1,'resource/book_img/default_front.JPEG','resource/book_img/default_middle.JPG','resource/book_img/default_back.JPG',0,22,'2020-08-07 13:01:24',NULL,'public',2,'','',''),(12,'작품',1,'resource/book_img/12/12_front.JPEG','resource/book_img/12/12_middle.PNG','resource/book_img/12/12_back.PNG',0,22,'2020-08-07 13:43:56','2020-08-07 13:43:56','public',5,'','',''),(17,'북북',1,'resource/book_img/default_front.JPEG','resource/book_img/default_middle.JPG','resource/book_img/default_back.JPG',0,17,'2020-08-11 10:52:37',NULL,'public',2,'<p>목차</p>\n\n<p>1. 챕터1</p>\n\n<p>2. 챕터2</p>\n\n<p>3. 챕터3</p>\n\n<p>4. 챕터4</p>\n\n<p>5. 챕터5</p>\n','','<p>출처</p>\n\n<p>(번호. 아이디.(게시글작성날짜).게시글명/챕터명)</p>\n\n<p>1. bjh7013.(2020.08.09).영주바보 그건맞지/chapter1</p>\n\n<p>&nbsp;</p>\n'),(18,'북북2',1,'resource/book_img/default_front.JPEG','resource/book_img/default_middle.JPG','resource/book_img/default_back.JPG',0,17,'2020-08-11 10:55:04',NULL,'public',5,'<p>목차</p>\n\n<p>1. 챕터1</p>\n\n<p>2. 챕터2</p>\n\n<p>3. 챕터3</p>\n\n<p>4. 챕터4</p>\n\n<p>5. 챕터5</p>\n','<p>asdfasfasdfasdfasdfasdfasdfasdf<iframe frameborder=\"0\" height=\"360\" src=\"https://www.youtube.com/embed/5eS_gbUbAM0\" width=\"640\"></iframe>리치 텍스트 편집기, editor3치 텍스트 편집기, editor2</p>\n\n<div style=\"page-break-after:always\"><span style=\"display:none\">&nbsp;</span></div>\n\n<p>리</p>\n\n<p>리치 텍스트 편집기, editor5</p>\n\n<p>&nbsp;</p>\n\n<pre>\n<code class=\"language-java\">int a = 10;\nint b = 20;\nCar car = new Car(10, 20);\nint c; </code></pre>\n\n<p>숨바숨바 Content of the editor</p>\n\n<div style=\"page-break-after:always\"><span style=\"display:none\">&nbsp;</span></div>\n\n<p>&nbsp;</p>\n\n<blockquote>\n<p>원숭이 타워 Content of the editor.</p>\n</blockquote>\n\n<p><img alt=\"\" data-widget=\"image\" height=\"62\" src=\"http://localhost:9999/userfiles/images/annoying%20pear.png\" width=\"45\" /></p>\n\n<div style=\"page-break-after:always\"><span style=\"display:none\">&nbsp;</span></div>\n\n<p>&nbsp;</p>\n\n<p>Content of the editor.ㅁㄴㅇㄻㄴㅇㄻㄴㅇㄻㄴㅇ</p>\n\n<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:500px\">\n	<tbody>\n		<tr>\n			<td>ㅎㅇ</td>\n			<td>ㅁㅁㅁㅁ</td>\n		</tr>\n		<tr>\n			<td>&nbsp;</td>\n			<td>&nbsp;</td>\n		</tr>\n		<tr>\n			<td>&nbsp;</td>\n			<td>&nbsp;</td>\n		</tr>\n	</tbody>\n</table>\n\n<p>&nbsp;</p>\n\n<blockquote>\n<p>ddddddddddddddddddddddddddddddddddddddddd</p>\n</blockquote>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<pre>\n<code class=\"language-abap\">sadfasdf</code></pre>\n\n<div style=\"page-break-after:always\"><span style=\"display:none\">&nbsp;</span></div>\n\n<p>카레엔 가라야게 추가지!!(공유 공유)</p>\n\n<p><img alt=\"\" height=\"200\" src=\"https://cheerio6202.files.wordpress.com/2014/01/img_6218.jpg\" width=\"300\" /></p>\n\n<p>카레엔 가라야게</p>\n','<p>출처</p>\n\n<p>(번호. 아이디.(게시글작성날짜).게시글명/챕터명)</p>\n\n<p>1. bjh7013.(2020.08.09).영주바보 그건맞지/chapter1</p>\n\n<p>&nbsp;</p>\n'),(19,'wow',1,'resource/book_img/default_front.JPEG','resource/book_img/default_middle.JPG','resource/book_img/default_back.JPG',0,17,'2020-08-11 11:09:54',NULL,'public',3,'<p>목차</p>\n\n<p>1. 챕터1</p>\n\n<p>2. 챕터2</p>\n\n<p>3. 챕터3</p>\n\n<p>4. 챕터4</p>\n\n<p>5. 챕터5</p>\n','<p>&nbsp;asdfasfasdfasdfasdfasdfasdfasdf</p>\n\n<div style=\"page-break-after:always\"><span style=\"display:none\">&nbsp;</span></div>\n\n<p>&nbsp;</p>\n\n<p><iframe frameborder=\"0\" height=\"360\" src=\"https://www.youtube.com/embed/5eS_gbUbAM0\" width=\"640\"></iframe>리치 텍스트 편집기, editor3</p>\n\n<p>리치 텍스트 편집기, editor2</p>\n\n<p>리치 텍스트 편집기, editor5</p>\n\n<div style=\"page-break-after:always\"><span style=\"display:none\">&nbsp;</span></div>\n\n<pre>\n<code class=\"language-java\">int a = 10;\nint b = 20;\nCar car = new Car(10, 20);\nint c; </code></pre>\n\n<p>숨바숨바 Content of the editor</p>\n\n<p>&nbsp;</p>\n\n<pre>\n<code class=\"language-cpp\">int a = 10;</code></pre>\n\n<p>&nbsp;</p>\n\n<div style=\"page-break-after:always\"><span style=\"display:none\">&nbsp;</span></div>\n\n<blockquote>\n<p>원숭이 타워 Content of the editor.</p>\n</blockquote>\n\n<p><img alt=\"\" data-widget=\"image\" height=\"62\" src=\"http://localhost:9999/userfiles/images/annoying%20pear.png\" width=\"45\" /></p>\n\n<p>&nbsp;</p>\n\n<p>카레엔 가라야게 추가지!!(공유 공유)</p>\n\n<p><img alt=\"\" height=\"200\" src=\"https://cheerio6202.files.wordpress.com/2014/01/img_6218.jpg\" width=\"300\" /></p>\n<div class=\"page\"><p> asdfasfasdfasdfasdfasdfasdfasdf</p>\n\n </div><div class=\"page\">\n\n \n\n리치 텍스트 편집기, editor3\n\n리치 텍스트 편집기, editor2\n\n리치 텍스트 편집기, editor5\n\n </div><div class=\"page\">\n\n\nint a = 10;\nint b = 20;\nCar car = new Car(10, 20);\nint c; \n\n숨바숨바 Content of the editor\n\n \n\n\nint a = 10;\n\n \n\n </div>','<p>출처</p>\n\n<p>(번호. 아이디.(게시글작성날짜).게시글명/챕터명)</p>\n\n<p>1. bjh7013.(2020.08.09).영주바보 그건맞지/chapter1</p>\n\n<p>&nbsp;</p>\n'),(20,'얍얍',1,'resource/book_img/default_front.JPEG','resource/book_img/default_middle.JPG','resource/book_img/default_back.JPG',0,17,'2020-08-11 11:15:22',NULL,'public',5,'<p>목차</p>\n\n<p>1. 챕터1</p>\n\n<p>2. 챕터2</p>\n\n<p>3. 챕터3</p>\n\n<p>4. 챕터4</p>\n\n<p>5. 챕터5</p>\n','<div class=\"page\"><p><iframe frameborder=\"0\" height=\"360\" src=\"https://www.youtube.com/embed/5eS_gbUbAM0\" width=\"640\"></iframe>리치 텍스트 편집기, editor3</p>\n\n<p>리치 텍스트 편집기, editor2</p>\n\n<p>리치 텍스트 편집기, editor5</p>\n\n </div><div class=\"page\">\n\n\nint a = 10;\nint b = 20;\nCar car = new Car(10, 20);\nint c; \n\n숨바숨바 Content of the editor\n\n </div><div class=\"page\">\n\n \n\n\nlet a = 0;\nlet obg = [\'aaaa\',\'bbbb\'];\n\n코드코드\n\n\nsdfasdfasdfasfasdfasdfadsf\n\n\nㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷ \n\n \n\n \n\n </div><div class=\"page\">\n\nJavaScript\n\n\nlet a = 0;\nlet obg = [\'aaaa\',\'bbbb\'];\n\n \n\n </div>','<p>출처</p>\n\n<p>(번호. 아이디.(게시글작성날짜).게시글명/챕터명)</p>\n\n<p>1. bjh7013.(2020.08.09).영주바보 그건맞지/chapter1</p>\n\n<p>&nbsp;</p>\n'),(21,'제발 ㅠㅠ',1,'resource/book_img/default_front.JPEG','resource/book_img/default_middle.JPG','resource/book_img/default_back.JPG',0,22,'2020-08-11 11:36:13',NULL,'public',1,'<p>목차</p>\n\n<p>1. 챕터1</p>\n\n<p>2. 챕터2</p>\n\n<p>3. 챕터3</p>\n\n<p>4. 챕터4</p>\n\n<p>5. 챕터5</p>\n','<div class=\"page\"><p>2차 세계 대전에서 연합군과 독일군의 전투가 점점 치열해지고 있다.<br/>\n전투가 진행중인 지역은 대규모 폭격과 시가전 등으로 인해 도로 곳곳이 파손된 상태이다.<br/>\n그림 1(a)에서와 같이 도로들은 전투로 인해 트럭이나 탱크와 같은 차량들이 지날 갈 수 없다.<br/>\n전투에서 승리하기 위해서는 기갑사단과 보급부대가 신속하게 이동하기 위한 도로가 있어야 한다.<br/>\n공병대는 출발지(S) 에서 도착지(G)까지 가기 위한 도로 복구 작업을 빠른 시간 내에 수행하려고 한다.<br/>\n도로가 파여진 깊이에 비례해서 복구 시간은 증가한다.<br/>\n출발지에서 도착지까지 가는 경로 중에 복구 시간이 가장 짧은 경로에 대한 총 복구 시간을 구하시오.<br/>\n깊이가 1이라면 복구에 드는 시간이 1이라고 가정한다.</p>\n\n </div><div class=\"page\">\n\n<pre>\n<code class=\"language-python\">from collections import deque\n\ndx = [-1, 1, 0, 0]\ndy = [0, 0, -1, 1]\n\ndef bfs():\n    while q:\n        x, y = q.popleft()\n        for n in range(4):\n            xn, yn = x+dx[n], y+dy[n]\n            if 0 <= xn < N and 0 <= yn < N:\n                if const[x][y]+road[xn][yn] < const[xn][yn]:\n                    const[xn][yn] = const[x][y]+road[xn][yn]\n                    q.append((xn, yn))\n\nT = int(input())\nfor tc in range(1, T+1):\n    q = deque()\n    N = int(input())\n    road = [list(map(int, input())) for _ in range(N)]\n    const = [[100000 for _ in range(N)] for _ in range(N)]\n    const[0][0] = 0\n    q.append((0, 0))\n    bfs()\n    print(\'#{} {}\'.format(tc, const[-1][-1]))</code></pre>\n\n<p> </p>\n\n </div><div class=\"page\">\n\n<p>오늘 먹은 짬뽕 존맛탱</p>\n\n<p> </p>\n\n<p><img alt=\"\" height=\"225\" src=\"https://ncube.net/wp-content/uploads/2016/07/IMG_0241.jpg\" width=\"300\"/></p>\n\n </div><div class=\"page\">\n\n<p>요즘 핫하다는 매일우유 소프트콘 세븐일레븐으로 달려가서 하나를 겟 해봅니다ㅎㅎ</p>\n\n<p>마침 2+1 행사중!!!! 하나는 그냥 먹으려구용</p>\n\n<p>1. 우유를 가득넣고</p>\n\n<p>2. 카누를 풀어 라떼를 만듭니다.</p>\n\n<p>3. 그 위에 아이스크림을 올리면 완성!</p>\n\n<p> </p>\n\n<p>상원이의 생일 파티가 곧 열린다!<br/>\n그렇기에 상원이는 반 친구들에게 생일 파티 초대장을 주려고 한다.<br/>\n그러나 파티가 어색하게 되는 것을 원치 않는 상원이는 모든 친구들에게 초대장을 줄 생각이 없다.<br/>\n같은 반에 있지만, 서로 친한 친구가 아닐 수도 있기 때문이다.</p>\n\n </div><div class=\"page\">\n\n<pre>\n<code class=\"language-python\">T = int(input())\nfor tc in range(1, T+1):\n    N, M = map(int, input().split())\n    # 초대받은 학생 \n    invited = [0 for _ in range(N+1)]\n    # 친구 관계 저장\n    friends = [[] for _ in range(N+1)]\n    # 상원이의 친한 친구\n    best_friends = []\n    for _ in range(M):\n        a, b = map(int, input().split())\n        if a == 1:\n            best_friends.append(b)\n            invited[b] = 1\n        elif b == 1:\n            best_friends.append(a)\n            invited[a] = 1\n        else:\n            friends[a].append(b)\n            friends[b].append(a)\n    # 친한 친구의 친한 친구\n    for bf in best_friends:\n        for friend in friends[bf]:\n            if invited[friend] == 0:\n                invited[friend] = 1\n\n    print(\'#{} {}\'.format(tc, invited.count(1)))</code></pre>\n\n </div><div class=\"page\">\n\n<p><br/>\n<iframe frameborder=\"0\" height=\"360\" src=\"https://www.youtube.com/embed/wAFL7OcHGiI\" width=\"640\"></iframe></p>\n</div>','<p>출처</p>\n\n<p>(번호. 아이디.(게시글작성날짜).게시글명/챕터명)</p>\n\n<p>1. bjh7013.(2020.08.09).영주바보 그건맞지/chapter1</p>\n\n<p>&nbsp;</p>\n'),(22,'ssazit 번성하세요',1,'resource/book_img/default_front.JPEG','resource/book_img/default_middle.JPG','resource/book_img/default_back.JPG',0,10,'2020-08-12 21:32:48',NULL,'public',5,'<p>목차</p>\n\n<p>1. 챕터1</p>\n\n<p>2. 챕터2</p>\n\n<p>3. 챕터3</p>\n\n<p>4. 챕터4</p>\n\n<p>5. 챕터5</p>\n','<div class=\"page\">\n<p>ㅎㅎ</p>\n\n<p><iframe frameborder=\"0\" height=\"360\" src=\"https://www.youtube.com/embed/TpPwI_Lo0YY\" width=\"640\"></iframe></p>\n\n<p>시간을 지배하는 sakeL의 노동요 입니다^.^</p>\n</div>\n','<p>출처</p>\n\n<p>(번호. 아이디.(게시글작성날짜).게시글명/챕터명)</p>\n\n<p>1. bjh7013.(2020.08.09).영주바보 그건맞지/chapter1</p>\n\n<p>&nbsp;</p>\n');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookcategory`
--

DROP TABLE IF EXISTS `bookcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookcategory` (
  `book_no` int NOT NULL COMMENT '책 번호',
  `category` int NOT NULL COMMENT '카테고리 코드',
  PRIMARY KEY (`book_no`,`category`),
  KEY `category` (`category`),
  CONSTRAINT `bookcategory_ibfk_1` FOREIGN KEY (`book_no`) REFERENCES `book` (`book_no`),
  CONSTRAINT `bookcategory_ibfk_2` FOREIGN KEY (`category`) REFERENCES `category` (`category_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='책 카테고리';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookcategory`
--

LOCK TABLES `bookcategory` WRITE;
/*!40000 ALTER TABLE `bookcategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookfavorite`
--

DROP TABLE IF EXISTS `bookfavorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookfavorite` (
  `favorite_no` int NOT NULL AUTO_INCREMENT COMMENT '즐겨찾기 번호',
  `user_no` int NOT NULL COMMENT '사용자 번호',
  `book_no` int NOT NULL COMMENT '책 번호',
  `favorite_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '즐겨찾기 추가 일시',
  PRIMARY KEY (`favorite_no`),
  KEY `user_no` (`user_no`),
  KEY `book_no` (`book_no`),
  CONSTRAINT `bookfavorite_ibfk_1` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`),
  CONSTRAINT `bookfavorite_ibfk_2` FOREIGN KEY (`book_no`) REFERENCES `book` (`book_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='책 즐겨찾기';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookfavorite`
--

LOCK TABLES `bookfavorite` WRITE;
/*!40000 ALTER TABLE `bookfavorite` DISABLE KEYS */;
INSERT INTO `bookfavorite` VALUES (1,7,3,'2020-08-13 11:19:10');
/*!40000 ALTER TABLE `bookfavorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booklike`
--

DROP TABLE IF EXISTS `booklike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booklike` (
  `book_no` int NOT NULL COMMENT '책 번호',
  `user_no` int NOT NULL COMMENT '책 추전자',
  `like_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '좋아요 일시',
  PRIMARY KEY (`book_no`,`user_no`),
  KEY `user_no` (`user_no`),
  CONSTRAINT `booklike_ibfk_1` FOREIGN KEY (`book_no`) REFERENCES `book` (`book_no`),
  CONSTRAINT `booklike_ibfk_2` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='책 좋아요';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booklike`
--

LOCK TABLES `booklike` WRITE;
/*!40000 ALTER TABLE `booklike` DISABLE KEYS */;
/*!40000 ALTER TABLE `booklike` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`ssazit`@`%`*/ /*!50003 TRIGGER `book_like_insert` BEFORE INSERT ON `booklike` FOR EACH ROW BEGIN
	update
		Book
	SET
		like_count = like_count + 1
	WHERE
		book_no = NEW.book_no;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`ssazit`@`%`*/ /*!50003 TRIGGER `book_like_delete` BEFORE DELETE ON `booklike` FOR EACH ROW BEGIN
	update
		Book
	SET
		like_count = like_count - 1
	WHERE
		book_no = OLD.book_no;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `bookseries`
--

DROP TABLE IF EXISTS `bookseries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookseries` (
  `book_no` int NOT NULL COMMENT '책 번호',
  `series_no` int NOT NULL COMMENT '시리즈 번호',
  PRIMARY KEY (`book_no`,`series_no`),
  KEY `series_no` (`series_no`),
  CONSTRAINT `bookseries_ibfk_1` FOREIGN KEY (`book_no`) REFERENCES `book` (`book_no`),
  CONSTRAINT `bookseries_ibfk_2` FOREIGN KEY (`series_no`) REFERENCES `series` (`series_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookseries`
--

LOCK TABLES `bookseries` WRITE;
/*!40000 ALTER TABLE `bookseries` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookseries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booktag`
--

DROP TABLE IF EXISTS `booktag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booktag` (
  `book_no` int NOT NULL COMMENT '책 번호',
  `tag_no` int NOT NULL COMMENT '태그 번호',
  PRIMARY KEY (`book_no`,`tag_no`),
  KEY `tag_no` (`tag_no`),
  CONSTRAINT `booktag_ibfk_1` FOREIGN KEY (`book_no`) REFERENCES `book` (`book_no`),
  CONSTRAINT `booktag_ibfk_2` FOREIGN KEY (`tag_no`) REFERENCES `tag` (`tag_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='책 태그';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booktag`
--

LOCK TABLES `booktag` WRITE;
/*!40000 ALTER TABLE `booktag` DISABLE KEYS */;
INSERT INTO `booktag` VALUES (7,2),(7,3);
/*!40000 ALTER TABLE `booktag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_no` int NOT NULL AUTO_INCREMENT COMMENT '카테고리 번호',
  `category_name` varchar(20) NOT NULL COMMENT '카테고리명',
  PRIMARY KEY (`category_no`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='카테고리 분류';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'테스트카테고리'),(2,'프로그래밍 언어'),(3,'웹'),(4,'알고리즘'),(5,'일상'),(6,'스터디');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_no` int NOT NULL AUTO_INCREMENT COMMENT '댓글 번호',
  `article_no` int DEFAULT NULL COMMENT '게시글 번호',
  `book_no` int DEFAULT NULL COMMENT '책 번호',
  `user_no` int NOT NULL COMMENT '작성자',
  `content` text NOT NULL COMMENT '내용',
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일자',
  `is_pick` tinyint(1) NOT NULL DEFAULT '0' COMMENT '채택여부(true/false)',
  `like_count` int NOT NULL DEFAULT '0' COMMENT '추천수(참조칼럼)',
  PRIMARY KEY (`comment_no`),
  KEY `article_no` (`article_no`),
  KEY `book_no` (`book_no`),
  KEY `user_no` (`user_no`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`article_no`) REFERENCES `article` (`article_no`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`book_no`) REFERENCES `book` (`book_no`),
  CONSTRAINT `comment_ibfk_3` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='댓글';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,1,NULL,1,'good','2020-07-22 11:33:59',0,2),(3,25,NULL,17,'<pre>\n<code class=\"language-c\">char c = \'a\';</code></pre>\n\n<p>과연 들어갈 것인가..!</p>\n\n<blockquote>\n<p>gsdfgsdfgsdfgdfdfdfdf</p>\n</blockquote>\n','2020-08-06 10:47:44',1,2),(6,13,NULL,17,'<p>동의합니다 정말 맞습니다</p>','2020-08-05 15:33:01',0,1),(8,31,NULL,7,'<p>채택 테스트</p>\n','2020-08-11 14:14:47',1,1),(9,31,NULL,7,'<p>ㅎㅎㅎㅎ</p>','2020-08-05 15:56:24',0,0),(10,13,NULL,7,'<p>우와<img src=\"https://cdn.ckeditor.com/4.14.1/full-all/plugins/smiley/images/omg_smile.png\" data-cke-saved-src=\"https://cdn.ckeditor.com/4.14.1/full-all/plugins/smiley/images/omg_smile.png\" title=\"surprise\" alt=\"surprise\" width=\"23\" height=\"23\">​​​​​​​</p>','2020-08-05 16:50:57',0,1),(11,13,NULL,7,'<blockquote><p>옹오</p></blockquote>','2020-08-05 16:52:30',-1,1),(12,13,NULL,10,'<blockquote><h1 style=\"background: rgb(238, 238, 238); border: 1px solid rgb(204, 204, 204); padding: 5px 10px;\"><span style=\"font-family:Times New Roman,Times,serif;\"><span style=\"font-size:72px;\"><span style=\"color:#ffffff;\"><span class=\"marker\"><strong><span style=\"background-color:#f1c40f;\">도랏나&nbsp;</span></strong></span></span></span></span></h1></blockquote>','2020-08-05 16:58:24',1,0),(13,1,NULL,7,'<p>hihi<img src=\"https://cdn.ckeditor.com/4.14.1/full-all/plugins/smiley/images/angel_smile.png\" data-cke-saved-src=\"https://cdn.ckeditor.com/4.14.1/full-all/plugins/smiley/images/angel_smile.png\" title=\"angel\" alt=\"angel\" width=\"23\" height=\"23\"><img src=\"https://cdn.ckeditor.com/4.14.1/full-all/plugins/smiley/images/embarrassed_smile.png\" data-cke-saved-src=\"https://cdn.ckeditor.com/4.14.1/full-all/plugins/smiley/images/embarrassed_smile.png\" title=\"blush\" alt=\"blush\" width=\"23\" height=\"23\"><img src=\"https://cdn.ckeditor.com/4.14.1/full-all/plugins/smiley/images/tongue_smile.png\" data-cke-saved-src=\"https://cdn.ckeditor.com/4.14.1/full-all/plugins/smiley/images/tongue_smile.png\" title=\"cheeky\" alt=\"cheeky\" width=\"23\" height=\"23\">​​​​​​​</p>','2020-08-05 17:41:29',0,2),(14,32,NULL,10,'<p>굳굳 잘된당!!!</p>\n','2020-08-06 11:12:31',-1,1),(15,41,NULL,7,'<p>내 글에 댓글 아아 테스트 테스트</p>\n','2020-08-06 21:25:54',0,0),(16,25,NULL,17,'<p>정</p>\n','2020-08-06 21:36:07',0,0),(17,25,NULL,17,'<p>렬</p>\n','2020-08-06 21:36:13',0,0),(18,25,NULL,17,'<p>하</p>\n','2020-08-06 21:36:17',0,0),(19,25,NULL,17,'<p>즈</p>\n','2020-08-06 21:36:20',0,1),(20,25,NULL,17,'<p>아</p>\n','2020-08-06 21:36:23',1,0),(21,42,NULL,9,'<p>헌잘알이시네여&nbsp;<img alt=\"surprise\" height=\"23\" src=\"https://cdn.ckeditor.com/4.14.1/full-all/plugins/smiley/images/omg_smile.png\" title=\"surprise\" width=\"23\" /></p>\n','2020-08-07 11:00:37',0,1),(22,49,NULL,17,'<p><iframe allowfullscreen=\"\" frameborder=\"0\" height=\"360\" src=\"https://www.youtube.com/embed/zfMHTfJ3Ih0\" width=\"640\"></iframe></p>\n\n<p>백종원의 카레 레시피!</p>\n','2020-08-07 11:33:34',0,0),(23,47,NULL,17,'<p>내용이 별로네요.. 분발하세요<img alt=\"angry\" height=\"23\" src=\"https://cdn.ckeditor.com/4.14.1/full-all/plugins/smiley/images/angry_smile.png\" title=\"angry\" width=\"23\" /></p>\n','2020-08-07 12:10:50',1,3),(24,47,NULL,7,'<p>문제 잘 푸셨네요<img alt=\"blush\" height=\"23\" src=\"https://cdn.ckeditor.com/4.14.1/full-all/plugins/smiley/images/embarrassed_smile.png\" title=\"blush\" width=\"23\" /> 피드백은 다음기회에</p>\n','2020-08-07 12:24:09',1,1),(25,47,NULL,9,'<p>브론즈의 최강자급 알고리즘이네요!<img alt=\"yes\" height=\"23\" src=\"https://cdn.ckeditor.com/4.14.1/full-all/plugins/smiley/images/thumbs_up.png\" title=\"yes\" width=\"23\" /></p>\n','2020-08-07 12:24:43',0,0),(26,47,NULL,10,'<p>이 문제는 BFS로 하는게 더 효율적일 거 같아요^.^</p>\n\n<pre>\n<code class=\"language-python\">from collections import deque\n\ndef BFS_with_adj_list(graph, root):\n    visited = []\n    queue = deque([root])\n\n    while queue:\n        n = queue.popleft()\n        if n not in visited:\n            visited.append(n)\n            queue += graph[n] - set(visited)\n    return visited\n  \nprint(BFS_with_adj_list(graph_list, root_node))</code></pre>\n\n<p>&nbsp;</p>\n','2020-08-07 12:59:36',0,0),(27,30,NULL,17,'<p>무</p>\n','2020-08-07 13:02:50',0,0),(28,30,NULL,17,'<p>한</p>\n','2020-08-07 13:04:01',0,0),(29,30,NULL,17,'<p>스</p>\n','2020-08-07 13:05:45',0,0),(30,30,NULL,17,'<p>크</p>\n\n<p>&nbsp;</p>\n','2020-08-07 13:05:52',0,0),(31,30,NULL,17,'<p>롤</p>\n\n<p>&nbsp;</p>\n','2020-08-07 13:05:59',0,1),(32,30,NULL,17,'<p>ㄹ</p>\n\n<p>&nbsp;</p>\n','2020-08-07 13:06:03',0,1),(33,36,NULL,9,'<p>마 ... 장난 내랑 하나</p>\n','2020-08-11 15:59:40',0,1),(34,30,NULL,17,'<p>악</p>\n','2020-08-12 20:48:11',0,0),(35,30,NULL,17,'<p>ㅇㅇ</p>\n','2020-08-12 20:49:03',0,0),(36,30,NULL,17,'<p>ddddd</p>\n','2020-08-12 20:49:32',0,0),(37,30,NULL,17,'<p>fffff</p>\n','2020-08-12 20:49:38',0,0),(38,45,NULL,17,'<p>알림아 가라!</p>\n','2020-08-12 21:03:06',0,0),(39,30,NULL,22,'<p>글글</p>\n','2020-08-12 21:05:01',0,0),(48,32,NULL,17,'<p>추가</p>\n','2020-08-12 21:17:19',0,0),(49,13,NULL,17,'<p>TRUE</p>\n','2020-08-12 21:21:39',0,0),(54,NULL,22,10,'안녕','2020-08-12 21:42:43',0,0),(55,NULL,22,10,'ㅎㅎ','2020-08-12 21:45:29',0,0);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commentlike`
--

DROP TABLE IF EXISTS `commentlike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commentlike` (
  `comment_no` int NOT NULL COMMENT '댓글 번호',
  `user_no` int NOT NULL COMMENT '댓글 추천자',
  `like_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '좋아요 일시',
  PRIMARY KEY (`comment_no`,`user_no`),
  KEY `commentlike_ibfk_2` (`user_no`),
  CONSTRAINT `commentlike_ibfk_1` FOREIGN KEY (`comment_no`) REFERENCES `comment` (`comment_no`) ON DELETE CASCADE,
  CONSTRAINT `commentlike_ibfk_2` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='댓글 좋아요';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentlike`
--

LOCK TABLES `commentlike` WRITE;
/*!40000 ALTER TABLE `commentlike` DISABLE KEYS */;
INSERT INTO `commentlike` VALUES (1,7,'2020-08-05 17:41:36'),(1,10,'2020-08-06 18:52:58'),(3,9,'2020-08-05 11:42:59'),(3,17,'2020-08-05 16:49:22'),(6,17,'2020-08-05 15:33:05'),(8,7,'2020-08-05 15:56:28'),(10,17,'2020-08-05 16:52:01'),(11,17,'2020-08-06 12:35:47'),(13,7,'2020-08-05 17:41:34'),(13,10,'2020-08-06 18:52:59'),(14,10,'2020-08-06 11:11:52'),(19,17,'2020-08-06 21:52:58'),(21,9,'2020-08-07 11:12:29'),(23,9,'2020-08-07 12:24:50'),(23,17,'2020-08-07 12:25:29'),(23,22,'2020-08-07 12:22:49'),(24,9,'2020-08-07 12:24:49'),(31,17,'2020-08-09 14:50:42'),(32,17,'2020-08-09 14:35:53'),(33,9,'2020-08-11 15:59:43');
/*!40000 ALTER TABLE `commentlike` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`ssazit`@`%`*/ /*!50003 TRIGGER `comment_like_insert` BEFORE INSERT ON `commentlike` FOR EACH ROW BEGIN
	update
		Comment
	SET
		like_count = like_count + 1
	WHERE
		comment_no = NEW.comment_no;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`ssazit`@`%`*/ /*!50003 TRIGGER `comment_like_delete` BEFORE DELETE ON `commentlike` FOR EACH ROW BEGIN
	update
		Comment
	SET
		like_count = like_count - 1
	WHERE
		comment_no = OLD.comment_no;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `follower` int NOT NULL COMMENT '팔로우하는사람',
  `following` int NOT NULL COMMENT '팔로우당하는사람',
  `follow_date` datetime NOT NULL COMMENT '팔로우일시',
  PRIMARY KEY (`follower`,`following`),
  KEY `following` (`following`),
  CONSTRAINT `follow_ibfk_1` FOREIGN KEY (`follower`) REFERENCES `user` (`user_no`),
  CONSTRAINT `follow_ibfk_2` FOREIGN KEY (`following`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='팔로우';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (1,9,'2020-08-13 09:31:54'),(1,10,'2020-08-13 09:33:35'),(6,22,'2020-08-06 21:52:58'),(7,1,'2020-08-05 20:25:52'),(7,9,'2020-08-06 23:23:21'),(7,10,'2020-08-06 23:23:12'),(7,16,'2020-08-07 11:22:46'),(7,19,'2020-08-05 18:06:38'),(7,22,'2020-08-09 13:22:02'),(9,1,'2020-08-12 23:08:58'),(9,2,'2020-08-09 18:01:51'),(9,3,'2020-08-10 20:59:10'),(9,8,'2020-08-09 18:01:56'),(9,10,'2020-08-07 11:09:44'),(9,11,'2020-08-10 20:59:07'),(9,13,'2020-08-09 18:01:57'),(9,15,'2020-08-09 18:01:54'),(9,17,'2020-08-11 16:12:23'),(9,22,'2020-08-06 21:53:48'),(10,1,'2020-08-06 18:52:13'),(10,7,'2020-08-07 16:07:28'),(10,17,'2020-08-03 22:55:04'),(15,7,'2020-08-05 17:33:58'),(17,9,'2020-08-07 12:49:12'),(17,10,'2020-08-06 22:14:17'),(19,7,'2020-08-05 17:31:41'),(19,22,'2020-08-06 21:52:23'),(22,1,'2020-08-07 13:44:46'),(22,7,'2020-08-07 13:13:22'),(22,10,'2020-08-06 20:26:36');
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `level`
--

DROP TABLE IF EXISTS `level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `level` (
  `level` varchar(20) NOT NULL COMMENT '레벨이름',
  `level_start` int NOT NULL COMMENT '경험치범위시작(이상)',
  `level_end` int NOT NULL COMMENT '경험치범위끝(미만)',
  `level_img` varchar(50) NOT NULL COMMENT '레벨 이미지(경로)',
  `level_color` varchar(20) NOT NULL COMMENT '레벨 색(RGB)',
  PRIMARY KEY (`level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='레벨';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `level`
--

LOCK TABLES `level` WRITE;
/*!40000 ALTER TABLE `level` DISABLE KEYS */;
INSERT INTO `level` VALUES ('4zit',1000,10000,'',''),('5zit',500,1000,'',''),('6zit',200,500,'',''),('7zit',100,200,'',''),('8zit',40,100,'',''),('9zit',0,40,'',''),('SSazit',10000,100000,'','');
/*!40000 ALTER TABLE `level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `message_no` int NOT NULL AUTO_INCREMENT COMMENT '쪽지 번호',
  `message_to` int NOT NULL COMMENT '쪽지 받는사람',
  `message_from` int NOT NULL COMMENT '쪽지 보낸사람',
  `content` text NOT NULL COMMENT '쪽지 내용',
  `send_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '전송 일시',
  `is_read` tinyint(1) NOT NULL DEFAULT '0' COMMENT '읽음 여부',
  `send_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '보낸사람이)보낸메세지 삭제',
  `receive_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '받은사람이)받은메세지 삭제',
  PRIMARY KEY (`message_no`),
  KEY `message_to` (`message_to`),
  KEY `message_from` (`message_from`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`message_to`) REFERENCES `user` (`user_no`),
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`message_from`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='쪽지';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,2,9,'gdgdgdㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ','2020-07-23 17:26:21',0,1,0),(2,2,9,'ㅂㅇㅂㅇㅂㅇㅂㅇㅂㅇㅂㅇㅂㅇㅂㅇㅂㅇㅂㅇㅂㅇㅂㅇㅂㅇㅂㅇ','2020-07-23 17:26:52',0,1,0),(3,9,1,'dddddddddddddddddddddddddddd','2020-07-23 17:27:54',0,0,1),(4,9,1,'ssssssssssssssssssssssssssssssssssssssss','2020-07-23 17:27:58',0,0,1),(5,1,9,'안녕하세요','2020-07-24 13:54:48',0,1,0),(6,1,9,'하이요 트리님  ㅎㅎ','2020-07-24 14:29:12',0,1,0),(7,1,9,'ㅎㅁㄴㅇㅎㅇㄶㄴㅇㅁ','2020-07-24 14:33:26',0,1,0),(8,1,9,'ㅇㅁㄴㅇㅁㄴㅁㄴㅇㄴㅁㅇㄴㅁ','2020-07-24 14:34:04',0,1,0),(9,1,9,'안녕요!','2020-07-24 14:34:41',0,0,0),(10,7,9,'화가 난다','2020-07-24 15:38:16',1,0,0),(11,9,7,'왜 화가나요!?','2020-07-24 15:38:38',0,0,1),(12,10,7,'이광수','2020-07-24 15:38:42',0,0,0),(13,7,10,'......................................ㅇㅅㅇ????','2020-07-24 15:39:30',0,0,0),(14,7,9,'날 화나게 하지마','2020-07-24 15:39:35',0,1,0),(15,7,10,'?????????????????????????','2020-07-24 15:39:39',0,0,0),(16,7,10,'나더러 이광수래ㅠㅜㅜㅠㅠㅜ','2020-07-24 15:39:48',0,0,0),(17,10,7,'강퇴ㄴ ㅇㅅㅇ????','2020-07-24 15:39:50',0,0,0),(18,10,7,'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ속상했니','2020-07-24 15:40:12',0,0,0),(19,10,9,'조심해라.. 열받으니까..','2020-07-24 15:40:25',0,0,0),(20,9,10,'ㅋㅋㅋㅋㅋ화가난닷!!!!!!!!!!!!!!!','2020-07-24 15:41:33',1,0,0),(21,7,10,'넘행ㅠㅜㅠㅜ','2020-07-24 15:41:42',0,0,0),(22,9,7,'헌드레드님 답장 부.탁.드.려.여!','2020-07-24 15:46:53',1,0,0),(23,7,10,'안뇽오오오오오옹','2020-07-24 15:49:05',0,0,0),(24,7,9,'#&!(*#&!*(#^(!*^!*(@&!(*@&*(!@&','2020-07-24 15:49:12',0,1,0),(25,7,10,'내가 보냇지렁~~','2020-07-24 15:49:17',0,0,0),(26,9,10,'이 편지는 영국에서 최초로 시작되어 일년에 한 바퀴 돌면서 받는 사람에게 행운을 주었고 지금은 당신에게로 옮겨진 이 편지는','2020-07-24 15:50:54',1,0,1),(27,7,10,'이 편지는 영국에서 최초로 시작되어 일년에 한 바퀴 돌면서 받는 사람에게 행운을 주었고 지금은 당신에게로 옮겨진 이 편지는','2020-07-24 15:50:59',1,0,0),(28,7,9,'안녕','2020-07-24 15:56:54',0,1,0),(29,10,9,'sdadas','2020-07-24 16:31:34',0,1,0),(30,7,9,'지은아 안녕','2020-07-24 17:00:00',0,0,0),(31,9,7,'정훈이안뇽','2020-07-24 17:00:21',0,0,1),(32,7,9,'찌니안녕 0726','2020-07-26 13:18:49',0,0,0),(33,9,7,'쪽지보내지나아~?','2020-07-26 13:55:06',1,0,0),(34,9,7,'싫어! 이 바보야','2020-07-26 15:34:18',1,0,0),(35,7,9,'빨리해!!','2020-07-26 15:34:33',0,1,0),(36,9,7,'잘 모루겠눈데욤?!!!','2020-07-26 15:35:43',0,0,1),(37,7,9,'저녁 뭐 먹을까?','2020-07-26 17:30:08',0,0,0),(38,9,7,'90글자를쳐볼게요.90글자를쳐볼게요.90글자를쳐볼게요.90글자를쳐볼게요.90글자를쳐볼게요.90글자를쳐볼게요.90글자를쳐볼게요.90글자를쳐볼게요.90글자를쳐볼게요.','2020-07-28 09:37:02',0,0,0),(39,9,7,'90자이상되는지확인좀해볼게요90자이상되는지확인좀해볼게요90자이상되는지확인좀해볼게요90자이상되는지확인좀해볼게요90자이상되는지확인좀해볼게요90자이상되는지확인좀해볼게요','2020-07-28 11:41:31',0,0,0),(40,7,10,'안대애ㅐㅇ애애애','2020-07-28 13:20:44',0,0,0),(41,7,3,'안녀엉','2020-07-29 20:54:47',0,0,0),(42,1,17,'1234','2020-07-30 18:22:47',0,0,0),(43,9,17,'쪽지쪽지','2020-08-04 09:43:01',0,0,0),(44,9,10,'asdfghjkl','2020-08-04 16:04:16',0,0,0),(45,7,9,'끝나고 집으로 퇴근하고싶니?','2020-08-04 17:30:32',0,0,0),(46,9,7,'뭐야 이제 봤어','2020-08-05 16:20:07',0,0,0),(47,9,7,'쪽지는 왜요?','2020-08-06 18:03:10',0,0,0),(48,7,9,'으아아아아악','2020-08-06 18:04:33',0,0,0),(49,10,9,'넘쳐난다 에러!','2020-08-06 18:04:39',0,0,0),(50,17,9,'ㅎㅇ','2020-08-06 18:04:47',0,0,0),(51,22,7,'안녕?','2020-08-07 09:51:35',1,0,0),(52,7,22,'안녕!','2020-08-07 09:52:01',1,0,0),(53,1,22,'가니???','2020-08-07 12:28:01',0,0,0),(54,22,22,'나한테쓰기','2020-08-07 12:28:34',1,1,1),(55,22,22,'나한테 쓰기','2020-08-07 13:14:03',0,0,1),(56,22,9,'나랑 친구하자','2020-08-07 13:18:17',0,0,0),(57,22,7,'오늘 햄버거 맛있었니? 나는 아재버거 먹었어','2020-08-07 13:18:20',0,0,0),(58,22,9,'무SSA트! 특별과정! 너 코딩에 문제있냐?!','2020-08-07 13:18:49',0,0,0),(59,22,19,'오늘은 신나는 금요일!','2020-08-07 13:19:11',0,0,0),(60,22,1,'쪽지잘받았어, 쪽지 90글자까지 쓸 수 있네','2020-08-07 13:20:00',0,0,0),(61,22,7,'빠른답장부탁해!!','2020-08-07 13:20:18',0,0,0),(62,22,9,'영주야~~~ 제로 동물원~~~','2020-08-07 13:21:10',0,0,0),(63,22,9,'영주씨 뒷광고 하시는거 아니죠?','2020-08-07 13:21:21',0,0,0),(64,22,19,'유투브 가즈아~!','2020-08-07 13:21:28',0,0,0),(65,22,19,'비가 너무 많이온다','2020-08-07 13:21:49',0,0,0),(66,22,22,'나한테쓰기','2020-08-07 13:45:09',0,0,0),(67,10,9,'ㅁㅇㅁㄴㅇ','2020-08-09 14:40:56',0,0,0),(68,10,9,'뒷광고 멍멍씨','2020-08-09 14:41:33',0,0,0),(69,10,9,'만념멈멈씨','2020-08-09 14:42:21',0,0,0),(70,10,9,'안녕멈뭄','2020-08-09 15:01:28',0,0,0),(71,10,9,'하이','2020-08-09 16:46:13',0,0,0),(72,1,9,'트리트리','2020-08-09 17:29:34',0,0,0),(73,9,7,'안뇨옹?','2020-08-10 14:27:11',0,0,0),(74,10,9,'ㅋㅋㅋㅋ 멍멍','2020-08-10 23:04:24',0,0,0),(75,17,9,'마 용!','2020-08-11 16:12:30',0,0,0);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myboard`
--

DROP TABLE IF EXISTS `myboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `myboard` (
  `board_no` int NOT NULL AUTO_INCREMENT COMMENT '게시판번호',
  `blog_no` int NOT NULL COMMENT '블로그번호',
  `board_name` varchar(20) NOT NULL COMMENT '게시판명',
  `board_type` varchar(2) NOT NULL COMMENT '게시글 구분(알고A/자유글N/구분선L)',
  `seq_no` int NOT NULL DEFAULT '0' COMMENT '게시판 순서(그룹 내 순서)',
  `depth` int NOT NULL DEFAULT '0' COMMENT '계층',
  `group_no` int NOT NULL DEFAULT '0' COMMENT '그룹번호',
  PRIMARY KEY (`board_no`),
  KEY `myboard_ibfk_1` (`blog_no`),
  CONSTRAINT `myboard_ibfk_1` FOREIGN KEY (`blog_no`) REFERENCES `blog` (`blog_no`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='게시판';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myboard`
--

LOCK TABLES `myboard` WRITE;
/*!40000 ALTER TABLE `myboard` DISABLE KEYS */;
INSERT INTO `myboard` VALUES (1,1,'test','0',0,0,1),(2,2,'test1','N',1,0,9),(3,2,'테스트','N',0,0,3),(5,5,'용수 바보게시판','A',0,1,5),(9,2,'알고','A',0,0,9),(12,5,'정훈 바보 게시판','N',0,19,12),(13,5,'우현 바보 게시판','N',0,15,13),(14,5,'테스트1','N',1,19,12),(16,3,'카페모카','N',0,1,16),(17,3,'카페라떼','A',0,3,17),(18,3,'바닐라라떼','A',1,1,16),(19,4,'찌니게시판1','N',0,1,19),(33,5,'하하하호호호','N',0,20,33),(36,3,'아메리카노','N',0,4,36),(39,12,'시원한 물냉','N',2,3,40),(40,12,'오늘의 점심','N',0,3,40),(43,12,'꺼억','N',1,3,40),(44,3,'딸기스무디','N',0,2,44),(45,3,'흑당라떼','N',2,1,16),(46,3,'금오산라떼','N',3,1,16),(47,3,'얼그레이','N',0,5,47),(49,13,'카페모카','N',0,1,49),(50,13,'카페라떼','N',0,2,50),(51,13,'바닐라라떼','N',1,2,50),(52,13,'금오산라떼','N',2,2,50),(53,13,'아이스아메리카노','A',0,3,53),(55,13,'휘핑크림','N',1,1,49),(62,13,'얼그레이','A',0,4,62),(63,14,'test1','N',0,1,63),(64,14,'subtest1','N',1,1,63),(65,14,'subtest2','N',2,1,63),(66,4,'맛집소개','N',0,3,66),(70,14,'codetest1','A',0,2,70),(71,5,'지은 바보 게시판','N',0,17,71),(73,5,'♥','N',0,18,73);
/*!40000 ALTER TABLE `myboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `series`
--

DROP TABLE IF EXISTS `series`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `series` (
  `series_no` int NOT NULL AUTO_INCREMENT COMMENT '시리즈 번호',
  `series_name` varchar(60) NOT NULL COMMENT '시리즈명',
  `series_desc` text NOT NULL COMMENT '시리즈 설명',
  PRIMARY KEY (`series_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `series`
--

LOCK TABLES `series` WRITE;
/*!40000 ALTER TABLE `series` DISABLE KEYS */;
/*!40000 ALTER TABLE `series` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `tag_no` int NOT NULL AUTO_INCREMENT COMMENT '대표태그 번호',
  `tag_name` varchar(20) NOT NULL COMMENT '태그명',
  `group_no` int NOT NULL COMMENT '그룹 번호(같은 그룹 번호 = 같은 의미의 태그)',
  PRIMARY KEY (`tag_no`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='대표태그';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'테스트',1),(2,'bfs',2),(3,'dfs',3),(4,'test',1),(5,'breadth first search',2),(6,'breadth-first search',2),(7,'너비 우선 탐색',2),(8,'너비우선 탐색',2),(9,'너비우선탐색',2),(10,'depth first search',3),(11,'depth-first search',3),(12,'깊이 우선 탐색',3),(13,'깊이우선 탐색',3),(14,'깊이우선탐색',3);
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tmptag`
--

DROP TABLE IF EXISTS `tmptag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tmptag` (
  `tmptag_no` int NOT NULL AUTO_INCREMENT COMMENT '임시태그 번호',
  `article_no` int DEFAULT NULL COMMENT '글 번호',
  `book_no` int DEFAULT NULL COMMENT '책 번호',
  `tmptag_name` varchar(20) NOT NULL COMMENT '임시 태그명',
  PRIMARY KEY (`tmptag_no`),
  KEY `book_no` (`book_no`),
  KEY `tmptag_ibfk_2` (`article_no`),
  CONSTRAINT `tmptag_ibfk_1` FOREIGN KEY (`book_no`) REFERENCES `book` (`book_no`),
  CONSTRAINT `tmptag_ibfk_2` FOREIGN KEY (`article_no`) REFERENCES `article` (`article_no`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='임시태그';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tmptag`
--

LOCK TABLES `tmptag` WRITE;
/*!40000 ALTER TABLE `tmptag` DISABLE KEYS */;
INSERT INTO `tmptag` VALUES (2,11,NULL,' 내 태그'),(6,19,NULL,'bfs'),(12,32,NULL,'카페모카'),(14,41,NULL,'태그'),(15,42,NULL,'ipsum'),(16,NULL,3,'드래곤워터'),(17,NULL,3,'이용수'),(18,NULL,3,'\'그\''),(19,NULL,3,'위인전'),(20,NULL,4,'영주바보'),(21,NULL,4,'그건맞지'),(22,NULL,4,'그치그치'),(23,NULL,4,'맞지맞지'),(24,NULL,4,'바보=영주'),(25,NULL,4,'영주=바보'),(26,NULL,5,'헌드래드'),(27,NULL,5,'백정훈'),(28,NULL,5,'빽정훈'),(29,NULL,5,'캡틴정훈'),(30,NULL,5,'인간에게가장해로운벌래는대충'),(31,NULL,5,'대충'),(32,NULL,5,'내가바로4조팀장'),(33,NULL,6,'지짜마시쒀'),(34,NULL,6,'찌니'),(35,NULL,6,'맛집'),(36,NULL,6,'우울할땐'),(37,NULL,6,'고기앞으로'),(38,45,NULL,'매일매일'),(39,45,NULL,'알고리즘'),(40,45,NULL,'그래프'),(41,46,NULL,'매일매일'),(42,46,NULL,'알고리즘'),(43,46,NULL,'boj'),(44,47,NULL,'매일매일'),(45,47,NULL,'알고리즘'),(46,48,NULL,'점심'),(47,48,NULL,'뭐먹지'),(48,49,NULL,'점심'),(49,49,NULL,'뭐먹지'),(50,NULL,8,'점심'),(51,NULL,8,'뭐먹지'),(52,50,NULL,'홈카페'),(53,50,NULL,'아인슈패너'),(54,51,NULL,'홈카페'),(55,51,NULL,'아이스크림라떼'),(56,NULL,9,'점심'),(57,NULL,9,'뭐먹지'),(60,53,NULL,'매일매일'),(61,53,NULL,'알고리즘'),(62,54,NULL,'알고리즘'),(63,NULL,12,'책'),(64,NULL,12,'알고리즘'),(65,55,NULL,'음식'),(66,NULL,17,'북북'),(67,NULL,18,'북북'),(68,NULL,19,'wow'),(69,NULL,20,'yap'),(70,NULL,21,'ㅠㅠ'),(71,57,NULL,'c'),(72,57,NULL,'알고'),(73,NULL,22,'4조'),(74,NULL,22,'화이팅');
/*!40000 ALTER TABLE `tmptag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_no` int NOT NULL AUTO_INCREMENT COMMENT '회원번호',
  `user_id` varchar(20) NOT NULL,
  `user_pw` varchar(20) NOT NULL COMMENT '비밀번호',
  `user_name` varchar(20) NOT NULL COMMENT '이름',
  `nick_name` varchar(20) NOT NULL COMMENT '닉네임',
  `e_mail` varchar(20) NOT NULL COMMENT '이메일',
  `phone_num` varchar(20) NOT NULL COMMENT '휴대폰번호',
  `join_date` date NOT NULL COMMENT '가입일시',
  `last_login` date DEFAULT NULL COMMENT '최근접속일시',
  `withdrawal_date` date DEFAULT NULL COMMENT '탈퇴일시',
  `is_admin` tinyint(1) NOT NULL DEFAULT '0' COMMENT '관리자여부(true,false)',
  `user_exp` int NOT NULL DEFAULT '0' COMMENT '경험치',
  `user_level` varchar(20) NOT NULL DEFAULT '9zit' COMMENT '레벨',
  `profile_img` varchar(50) NOT NULL DEFAULT 'resource/user_img/default.PNG' COMMENT '프로필이미지(경로)',
  PRIMARY KEY (`user_no`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'tree','1234','신우현','tree','ssafy@ssafy.com','010-1111-2222','2020-07-22',NULL,NULL,0,200,'6zit','resource/user_img/1_profile.PNG'),(2,'test','1234','testName','hundred','hundred@naver.com','010-2222-3333','2020-07-22',NULL,NULL,0,0,'9zit','resource/user_img/2_profile.PNG'),(3,'bjh','1111','백정훈','bjh','bjh7013@gmail.com','01099345504','2020-07-22',NULL,NULL,0,0,'9zit','resource/user_img/3_profile.PNG'),(6,'aaaa','aaaa','aaaa','aaaa','aaaa','010-3333-4444','2020-07-22',NULL,NULL,0,0,'9zit','resource/user_img/6_profile.PNG'),(7,'jieun','qweqwe','박지은','찌니','이메일주소','연락처','2020-07-22',NULL,NULL,0,0,'9zit','resource/user_img/7_profile.PNG'),(8,'test1','1234','test','짱구조아','bjh7013@gmail.com','010-2222-3232','2020-07-22',NULL,NULL,0,0,'9zit','resource/user_img/8_profile.PNG'),(9,'bjh7013','123123','백정훈','헌드레드','bjh7013@gmail.com','010-9934-5504','2020-07-22',NULL,NULL,0,0,'9zit','resource/user_img/9_profile.PNG'),(10,'0zoo','qaz1qaz1','강아지','멍멍','asdgj@asfdsg.com','01023457632','2020-07-22',NULL,NULL,0,0,'9zit','resource/user_img/10_profile.PNG'),(11,'aaaaa','1234','aaa','aaa','aaa','010-3333-4444','2020-07-23',NULL,'2020-07-23',0,0,'9zit','resource/user_img/default.PNG'),(12,'1234','1234','1234','1234','1234','1234','2020-07-23',NULL,'2020-07-23',0,0,'9zit','resource/user_img/default.PNG'),(13,'12','12','12','12','12','12','2020-07-23',NULL,'2020-07-23',0,0,'9zit','resource/user_img/default.PNG'),(14,'123','123','12311','123','123','123','2020-07-23',NULL,'2020-07-23',0,0,'9zit','resource/user_img/default.PNG'),(15,'jieun2','jieun2jieun2','박지은','찌니2요','park940313@naver.com','010-8527-4654','2020-07-23',NULL,NULL,0,0,'9zit','resource/user_img/15_profile.PNG'),(16,'listenlisten','llcl','cant','listen','ssafy@ssafy.com','000-0000-0000','2020-07-24',NULL,NULL,0,0,'9zit','resource/user_img/default.PNG'),(17,'yong','1234','이용수','yong','yong@naver.com','010-1234-1234','2020-07-27',NULL,NULL,0,3000,'4zit','resource/user_img/17_profile.PNG'),(18,'admin','1234','admin','admin','admin@gmail.com','010-2222-2211','2020-07-29',NULL,NULL,1,10000,'SSazit','resource/user_img/default.PNG'),(19,'babo','qweqwe','나는야깹띤','깹띤정훈','qweqwe','qweqwe','2020-08-05',NULL,NULL,0,0,'9zit','resource/user_img/19_profile.PNG'),(20,'KINKIN','qaz1qaz1','즐앵','즐앵','adsfdgf@dfg.com','234231423546','2020-08-06',NULL,NULL,0,0,'9zit','resource/user_img/20_profile.PNG'),(21,'undefined','qaz1qaz1','노블','로그','dasfh@dsgn.com','21342546357','2020-08-06',NULL,NULL,0,0,'9zit','resource/user_img/default.PNG'),(22,'reviewGO','qaz1qaz1','리뷰GO!','리뷰GO!','sadfdsg@sdafs.com','1342356','2020-08-06',NULL,NULL,0,0,'9zit','resource/user_img/22_profile.PNG'),(23,'yong2','qweqwe','미묨수','미묨수','미묨수.com','미묨수','2020-08-11',NULL,NULL,0,0,'9zit','resource/user_img/23_profile.PNG'),(24,'doratna','qaz1qaz1','><도랏나','도랏나','asdfdgdh@asfgdsh.com','141342567','2020-08-12',NULL,NULL,0,0,'9zit','resource/user_img/default.PNG');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`ssazit`@`%`*/ /*!50003 TRIGGER `user_level_update` BEFORE UPDATE ON `user` FOR EACH ROW BEGIN
	  SET
		NEW.user_level = (
			select level from level where level_end = (
				select MIN(level_end) from level where level_end > NEW.user_exp));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping events for database 'ssazitdb'
--

--
-- Dumping routines for database 'ssazitdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-13 11:30:23
