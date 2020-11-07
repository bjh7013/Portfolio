create database ssazitdb;
use ssazitdb;

CREATE TABLE User (
	user_no int PRIMARY KEY AUTO_INCREMENT comment '회원번호',
    user_id varchar(20) UNIQUE KEY NOT Null comment '회원아이디',
    user_pw varchar(20) Not Null comment '비밀번호',
    user_name varchar(20) Not Null comment '이름',
    nick_name varchar(20) Not Null comment '닉네임',
    e_mail varchar(50) Not Null comment '이메일',								-- 20 -> 50
    phone_num varchar(20) Not Null comment '휴대폰번호',
    join_date date Not Null comment '가입일시',
    last_login date comment '최근접속일시',
    withdrawal_date date comment '탈퇴일시',
    is_admin boolean Not Null default false comment '관리자여부(true,false)',
    profile_img varchar(50) Not Null Default 'resource/user_img/default.PNG' comment '프로필이미지(경로)',
    user_exp int Not Null default 0 comment '경험치',
    user_level varchar(20) Not Null default '9zit' comment '레벨'
) COMMENT '회원테이블';

CREATE TABLE Message (
	message_no int primary key auto_increment comment '쪽지 번호',
    message_to int Not Null comment '쪽지 받는사람',
    message_from int Not Null comment '쪽지 보낸사람',
    content text Not Null comment '쪽지 내용',
    send_date datetime Not Null default now() comment '전송 일시',
    is_read boolean Not Null default false comment '읽음 여부',
    send_delete boolean Not Null default false comment '보낸사람이)보낸메세지 삭제',
    receive_delete boolean Not Null default false comment '받은사람이)받은메세지 삭제',
    CONSTRAINT Message_ibfk_1 foreign key (message_to) references User (user_no) on delete cascade,        -- delete cascade
    CONSTRAINT Message_ibfk_2 foreign key (message_from) references User (user_no) on delete cascade       -- delete cascade
) comment '쪽지';

CREATE TABLE Block (
	blocker int comment '차단한 사람',
    blocking int comment '차단당한 사람',
    block_date datetime Not Null comment '차단 일시',
    CONSTRAINT Block_ibfk_1 foreign key (blocker) references User (user_no) on delete cascade,           -- delete cascade
    CONSTRAINT Block_ibfk_2 foreign key (blocking) references User (user_no) on delete cascade,          -- delete cascade
    Primary key (blocker,blocking)
) comment '차단';

CREATE TABLE Follow (
	follower int comment '팔로우하는사람',
    following int comment '팔로우당하는사람',
    follow_date datetime Not Null comment '팔로우일시',
    CONSTRAINT Follow_ibfk_1 foreign key (follower) references User (user_no) on delete cascade,            -- delete cascade
    CONSTRAINT Follow_ibfk_2 foreign key (following) references User (user_no) on delete cascade,           -- delete cascade
    primary key (follower,following)
) comment '팔로우';

CREATE TABLE Level (
	level varchar(20) primary key comment '레벨이름',
    level_start int Not Null comment '경험치범위시작(이상)',
    level_end int Not Null comment '경험치범위끝(미만)',
    level_img varchar(50) Not Null comment '레벨 이미지(경로)',
    level_color varchar(20) Not Null comment '레벨 색(RGB)'
) comment '레벨';

CREATE TABLE Blog(
	blog_no int primary key auto_increment comment '블로그번호',
    user_no int Not Null comment '소유자번호',
    blog_name varchar(20) Not Null comment '블로그명',
    blog_birth datetime Not Null default now() comment '블로그생성일시',
    CONSTRAINT Blog_ibfk_1 foreign key (user_no) references User (user_no) on delete cascade                -- delete cascade
) comment '블로그';

CREATE TABLE Myboard (
	board_no int primary key auto_increment comment '게시판번호',
    blog_no int Not Null comment '블로그번호',
    group_no int Not Null default 0 comment '그룹번호',
    seq_no int Not Null default 0 comment '게시판 순서(그룹 내 순서)',
    depth int Not Null default 0 comment '계층',
    board_name varchar(20) Not Null comment '게시판명',       
	board_type varchar(2) Not Null comment '게시글 구분(알고A/자유글N/구분선L)',
    CONSTRAINT Myboard_ibfk_1 foreign key (blog_no) references Blog (blog_no) on delete cascade                -- delete cascade
) comment '게시판';

CREATE TABLE Acategory (
	acategory_no int primary key auto_increment comment '알고리즘 문제 코드',
    acategory_source varchar(50) Not Null comment '알고리즘 문제 사이트',               -- 20 -> 50
    acategory_number int Not Null comment '알고리즘 문제 번호',
    title varchar(20) Not Null comment '알고리즘 문제명',
    user_no int Not Null default 1 comment '(사용자 입력시)사용자식별자',                           -- notnull 해제 vs default 계정 추가
    link text comment '사이트 링크',
    CONSTRAINT Acategory_ibfk_1 foreign key (user_no) references User (user_no) on delete set null,     -- delete set null
    unique key (acategory_source, acategory_number)
) comment '알고리즘 문제 분류';

CREATE TABLE Category (
	category_no int primary key auto_increment comment '카테고리 번호',
    category_name varchar(20) Not Null comment '카테고리명'
) comment '카테고리 분류';

CREATE TABLE article (
   article_no int NOT NULL AUTO_INCREMENT COMMENT '게시글 번호',
   user_no int NOT NULL COMMENT '작성자 번호',
   board_no int NOT NULL COMMENT '게시판 번호',
   title varchar(500) NOT NULL COMMENT '제목',
   content text NOT NULL COMMENT '내용',
   write_date datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일시',
   update_date datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일시',
   open_scope varchar(20) NOT NULL COMMENT '공개범위(공개(public)/친구공개(protected)/비공개(private))',
   thumb_nail text NOT NULL COMMENT '썸네일 이미지',
   article_type varchar(2) NOT NULL COMMENT '게시글 구분(알고(A)/자유글(N))',
   sharable tinyint(1) NOT NULL DEFAULT '1' COMMENT '공유가능여부',
   review_request tinyint(1) NOT NULL DEFAULT '1' COMMENT '질문 요청여부',
   like_count int NOT NULL DEFAULT '0' COMMENT '추천수(참조칼럼)',
   acategory_no int DEFAULT NULL COMMENT '알고리즘 카테고리 번호',
   category_no int DEFAULT NULL COMMENT '일반 카테고리 번호',
   reference int comment '참조 article',
   PRIMARY KEY (article_no),
   KEY user_no (user_no),
   KEY board_no (board_no),
   KEY article_ibfk_3 (acategory_no),
   KEY article_ibfk_4 (category_no),
   CONSTRAINT Article_ibfk_1 FOREIGN KEY (user_no) REFERENCES user (user_no) on delete cascade,                                   -- delete cascade
   CONSTRAINT Article_ibfk_2 FOREIGN KEY (board_no) REFERENCES myboard (board_no) on delete cascade,                              -- delete cascade
   CONSTRAINT Article_ibfk_3 FOREIGN KEY (acategory_no) REFERENCES acategory (acategory_no) on delete set null, -- delete set null (되면 default 값으로)-> not null 일때 set null?
   CONSTRAINT Article_ibfk_4 FOREIGN KEY (category_no) REFERENCES category (category_no) on delete set null, -- delete set null 
   CONSTRAINT `article_ibfk_5` FOREIGN KEY (`reference`) REFERENCES `article` (`article_no`) ON DELETE SET NULL	 -- set default?
 ) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='게시글';

CREATE TABLE Articlelike (
	article_no int comment '게시글 번호',
    user_no int comment '게시글 추천자',
    like_date datetime Not Null default now() comment '좋아요 일시',
    CONSTRAINT articlelike_ibfk_1 FOREIGN KEY (article_no) REFERENCES article (article_no) on delete cascade,           
    CONSTRAINT articlelike_ibfk_2 FOREIGN KEY (user_no) REFERENCES user (user_no) on delete cascade,                   
    primary key (article_no,user_no)
) comment '게시글 좋아요';

CREATE TABLE Book (
	book_no int primary key auto_increment comment '책 번호',
    writer int Not Null comment '작성자 번호',
    book_title varchar(50) Not Null comment '책 제목',                        -- 20 -> 50
    book_index text Not Null comment '책 목차',
    book_content text Not Null comment '책 내용',
    book_reference text Not Null comment '출처',
    version int Not Null default 1 comment '버전',
	write_date datetime Not Null default now() comment '작성 일시',
    update_date datetime Not Null default now() comment '수정 일시',
	cover_front varchar(50) Not Null default 'resource/book_img/default_front.JPEG' comment '표지(앞)',
    cover_middle varchar(50) Not Null default 'resource/book_img/default_middle.JPG' comment '표지(중)',
    cover_back varchar(50) Not Null default 'resource/book_img/default_back.JPG' comment '표지(뒤)',
    open_scope varchar(20) Not Null comment '공개범위(공개(public)/친구공개(protected)/비공개(private))',
    like_count int Not Null default 0 comment '추천수(참조칼럼)',
    category_no int DEFAULT NULL COMMENT '일반 카테고리 번호',
    CONSTRAINT Book_ibfk_1 foreign key (writer) references User (user_no) on delete cascade,   -- delete cascade
    CONSTRAINT Book_ibfk_2 foreign key (category_no) references category (category_no) on delete set null -- delete set null(default)
) comment '책';

create table Series(
    series_no int AUTO_INCREMENT primary key COMMENT '시리즈 번호',
    series_name varchar(60) not null COMMENT '시리즈명',
    series_desc text COMMENT '시리즈 설명'
) comment '시리즈';

create table Bookseries(
    book_no int COMMENT '책 번호',
    series_no int COMMENT '시리즈 번호',
    primary key(book_no, series_no),
    CONSTRAINT Bookseries_ibfk_1 foreign key (book_no) references book (book_no) on delete cascade,   -- delete cascade
    CONSTRAINT Bookseries_ibfk_2 foreign key (series_no) references series(series_no) on delete cascade  -- delete cascade
) comment '책 시리즈 연결';

CREATE TABLE Booklike (
	book_no int comment '책 번호',
    user_no int comment '책 추전자',
    like_date datetime Not Null default now() comment '좋아요 일시',
    CONSTRAINT Booklike_ibfk_1 foreign key (book_no) references Book (book_no) on delete cascade, -- delete cascade
    CONSTRAINT Booklike_ibfk_2 foreign key (user_no) references User (user_no) on delete cascade, -- delete cascade
    primary key (book_no, user_no)
) comment '책 좋아요';

CREATE TABLE Bookfavorite (
	favorite_no int primary key auto_increment comment '즐겨찾기 번호',
	user_no int Not Null comment '사용자 번호',
    book_no int Not Null comment '책 번호',
    favorite_date datetime Not Null default now() comment '즐겨찾기 추가 일시',
    CONSTRAINT Bookfavorite_ibfk_1 foreign key (user_no) references User (user_no) on delete cascade, -- delete cascade
    CONSTRAINT Bookfavorite_ibfk_2 foreign key (book_no) references Book (book_no) on delete cascade  -- delete cascade
) comment '책 즐겨찾기'; 

CREATE TABLE Articlefavorite (
	favorite_no int primary key auto_increment comment '즐겨찾기 번호',
    user_no int Not Null comment '사용자 번호',
    article_no int Not Null comment '글 번호',
    favorite_date datetime Not Null default now() comment '즐겨찾기 추가 일시',
    CONSTRAINT Articlefavorite_ibfk_1 foreign key (user_no) references User (user_no) on delete cascade,     -- delete cascade
    CONSTRAINT Articlefavorite_ibfk_2 foreign key (article_no) references Article (article_no) on delete cascade -- delete cascade
) comment '글 즐겨찾기';

CREATE TABLE Tag (
	tag_no int primary key auto_increment comment '태그 번호',
    tag_name varchar(20) Not Null comment '태그명',
    group_no int comment '그룹 번호(같은 그룹 번호 = 같은 의미의 태그)'
) comment '대표태그';

CREATE TABLE Arttag (
	article_no int comment '글 번호',
    tag_no int comment '태그 번호',
    CONSTRAINT Arttag_ibfk_1 foreign key (article_no) references Article (article_no) on delete cascade, -- delete cascade
    CONSTRAINT Arttag_ibfk_2 foreign key (tag_no) references Tag (tag_no) on delete cascade,             -- delete cascade
    primary key (article_no, tag_no)
) comment '글 태그';

CREATE TABLE Tmptag (
	tmptag_no int primary key auto_increment comment '임시태그 번호',
    article_no int comment '글 번호',
    book_no int comment '책 번호',
    tmptag_name varchar(20) Not Null comment '임시 태그명',         
    CONSTRAINT Tmptag_ibfk_1 foreign key (book_no) references Book (book_no) on delete cascade, -- delete cascade
    CONSTRAINT Tmptag_ibfk_2 foreign key (article_no) references Article (article_no) on delete cascade  -- delete cascade
) comment '임시태그';

CREATE TABLE Comment (
	comment_no int primary key auto_increment comment '댓글 번호',
    article_no int comment '게시글 번호',
    book_no int comment '책 번호',
    user_no int Not Null comment '작성자',
    content text Not Null comment '내용',
    write_date datetime Not Null default now() comment '작성일자',
    is_pick boolean Not Null default false comment '채택여부(true/false)',
    like_count int Not Null default 0 comment '추천수(참조칼럼)',
    CONSTRAINT Comment_ibfk_1 foreign key (article_no) references Article (article_no) on delete cascade, 
    CONSTRAINT Comment_ibfk_2 foreign key (book_no) references Book (book_no) on delete cascade,			
    CONSTRAINT Comment_ibfk_3 foreign key (user_no) references User (user_no) on delete cascade			
) comment '댓글';

CREATE TABLE Commentlike (
	comment_no int comment '댓글 번호',
    user_no int comment '댓글 추천자',
    like_date datetime Not Null default now() comment '좋아요 일시',
    constraint commentlike_ibfk_1 FOREIGN KEY (comment_no) REFERENCES comment (comment_no) on delete cascade,					
    CONSTRAINT commentlike_ibfk_2 FOREIGN KEY (user_no) REFERENCES user (user_no) on delete cascade,	
    primary key (comment_no,user_no)
) comment '댓글 좋아요';

CREATE TABLE Booktag(
    book_no int comment '책 번호',
    tag_no int comment '태그 번호',
    primary key(book_no, tag_no),
    CONSTRAINT Booktag_ibfk_1 foreign key(book_no) references book(book_no) on delete cascade,	-- delete cascade
    CONSTRAINT Booktag_ibfk_2 foreign key(tag_no) references tag(tag_no) on delete cascade    -- delete cascade
) comment '책 태그 연결';

CREATE TABLE noti (
   noti_no int NOT NULL AUTO_INCREMENT COMMENT '알림 번호',
   receiver int NOT NULL COMMENT '알림 수신자',
   noti_type varchar(20) NOT NULL COMMENT '알림타입(like-좋아요, comment-댓글, favorite-관심글, share-공유, pick-채택, follow-팔로우, message-쪽지)',
   noti_date datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '알림 일시',
   sender int NOT NULL COMMENT '알림 송신자',
   article_no int DEFAULT NULL,
   book_no int DEFAULT NULL,
   PRIMARY KEY (noti_no),
   KEY receiver (receiver),
   KEY sender (sender),
   KEY article_no (article_no),
   KEY noti_ibfk_4 (book_no),
   CONSTRAINT noti_ibfk_1 FOREIGN KEY (receiver) REFERENCES user (user_no) ON DELETE CASCADE,
   CONSTRAINT noti_ibfk_2 FOREIGN KEY (sender) REFERENCES user (user_no) ON DELETE CASCADE,
   CONSTRAINT noti_ibfk_3 FOREIGN KEY (article_no) REFERENCES article (article_no) ON DELETE CASCADE,
   CONSTRAINT noti_ibfk_4 FOREIGN KEY (book_no) REFERENCES book (book_no) ON DELETE CASCADE
 ) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='알림';