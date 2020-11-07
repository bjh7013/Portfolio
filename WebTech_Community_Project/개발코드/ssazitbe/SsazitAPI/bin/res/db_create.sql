create database ssazitdb;
use ssazitdb;

CREATE TABLE User (
	user_no int PRIMARY KEY AUTO_INCREMENT comment '회원번호',
    user_id varchar(20) UNIQUE KEY NOT Null comment '회원아이디',
    user_pw varchar(20) Not Null comment '비밀번호',
    user_name varchar(20) Not Null comment '이름',
    nick_name varchar(20) Not Null comment '닉네임',
    e_mail varchar(20) Not Null comment '이메일',
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
    foreign key (message_to) references User (user_no),
    foreign key (message_from) references User (user_no)
) comment '쪽지';

CREATE TABLE Block (
	blocker int comment '차단한 사람',
    blocking int comment '차단당한 사람',
    block_date datetime Not Null comment '차단 일시',
    foreign key (blocker) references User (user_no),
    foreign key (blocking) references User (user_no),
    Primary key (blocker,blocking)
) comment '차단';

CREATE TABLE Follow (
	follower int comment '팔로우하는사람',
    following int comment '팔로우당하는사람',
    follow_date datetime Not Null comment '팔로우일시',
    foreign key (follower) references User (user_no),
    foreign key (following) references User (user_no),
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
    foreign key (user_no) references User (user_no)
) comment '블로그';

CREATE TABLE Myboard (
	board_no int primary key auto_increment comment '게시판번호',
    blog_no int Not Null comment '블로그번호',
    group_no int Not Null default 0 comment '그룹번호',
    seq_no int Not Null default 0 comment '게시판 순서(그룹 내 순서)',
    depth int Not Null default 0 comment '계층',
    board_name varchar(20) Not Null comment '게시판명',
	board_type varchar(2) Not Null comment '게시글 구분(알고A/자유글N/구분선L)',
    foreign key (blog_no) references Blog (blog_no)
) comment '게시판';

CREATE TABLE Acategory (
	acategory_no int primary key auto_increment comment '알고리즘 문제 코드',
    acategory_source varchar(20) Not Null comment '알고리즘 문제 사이트',
    acategory_number int Not Null comment '알고리즘 문제 번호',
    title varchar(20) Not Null comment '알고리즘 문제명',
    user_no int Not Null comment '(사용자 입력시)사용자식별자',
    link text comment '사이트 링크',
    foreign key (user_no) references User (user_no),
    unique key (acategory_source, acategory_number)
) comment '알고리즘 문제 분류';

CREATE TABLE Category (
	category_no int primary key auto_increment comment '카테고리 번호',
    category_name varchar(20) Not Null comment '카테고리명'
) comment '카테고리 분류';

CREATE TABLE Article (
	article_no int primary key auto_increment comment '게시글 번호',
    user_no int Not Null comment '작성자 번호',
    board_no int Not Null comment '게시판 번호',
    title varchar(20) Not Null comment '제목',
    content text Not Null comment '내용',
    write_date datetime Not Null default now() comment '작성/수정 일시',
    open_scope varchar(20) Not Null comment '공개범위(공개(public)/친구공개(protected)/비공개(private))',
    thumb_nail text Not Null comment '썸네일 이미지',
    article_type varchar(2) Not Null comment '게시글 구분(알고(A)/자유글(N))',
    sharable boolean Not Null default true comment '공유가능여부',
    review_request boolean Not Null default true comment '질문 요청여부',
    like_count int Not Null default 0 comment '추천수(참조칼럼)',
    foreign key (user_no) references User (user_no),
    foreign key (board_no) references Myboard (board_no)
) comment '게시글';

CREATE TABLE Artcategory (
	article_no int comment '게시글 번호',
    category int comment '카테고리 코드',
    foreign key (article_no) references Article (article_no),
    foreign key (category) references Category (category_no),
	primary key (article_no,category) 
) comment '글 카테고리';

CREATE TABLE Artalgo (
	article_no int comment '게시글 번호',
    acategory_no int comment '알고리즘 문제 코드',
    foreign key (article_no) references Article (article_no),
    foreign key (acategory_no) references Acategory (acategory_no),
    primary key (article_no, acategory_no)
) comment '글 알고리즘문제';

CREATE TABLE Articlelike (
	article_no int comment '게시글 번호',
    user_no int comment '게시글 추천자',
    like_date datetime Not Null default now() comment '좋아요 일시',
    foreign key (article_no) references Article (article_no),
    foreign key (user_no) references User (user_no),
    primary key (article_no,user_no)
) comment '게시글 좋아요';

CREATE TABLE Articleshare (
	article_no int comment '게시글 번호',
    user_no int comment '게시글 공유자',
    share_date datetime Not Null default now() comment '공유 일시',
    foreign key (article_no) references Article (article_no),
    foreign key (user_no) references User (user_no),
    primary key (article_no, user_no)
) comment '게시글 공유';

CREATE TABLE Book (
	book_no int primary key auto_increment comment '책 번호',
    writer int Not Null comment '작성자 번호',
    book_title varchar(20) Not Null comment '책 제목',
    version int Not Null default 1 comment '버전',
	write_date datetime Not Null default now() comment '작성 일시',
    update_date datetime comment '수정 일시',
    cover_front varchar(20) Not Null comment '표지(앞)',
    cover_middle varchar(20) Not Null comment '표지(중)',
    cover_back varchar(20) Not Null comment '표지(뒤)',
    open_scope varchar(20) Not Null comment '공개범위(공개(public)/친구공개(protected)/비공개(private))',
    like_count int Not Null default 0 comment '추천수(참조칼럼)',
    foreign key (writer) references User (user_no)
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
    foreign key (book_no) references book (book_no),
    foreign key (series_no) references series(series_no)
) comment '책 시리즈 연결';

CREATE TABLE Bcontent (
	book_no int comment '책 번호',
    seq_no int comment '순서',
    chapter varchar(20) Not Null comment '소제목',
    content text Not Null comment '내용',
    article_no int Not Null comment '게시글번호(출처추적용)',
    foreign key (book_no) references Book (book_no),
    foreign key (article_no) references Article (article_no),
    primary key(book_no,seq_no)
) comment '책 속지';

CREATE TABLE Booklike (
	book_no int comment '책 번호',
    user_no int comment '책 추전자',
    like_date datetime Not Null default now() comment '좋아요 일시',
    foreign key (book_no) references Book (book_no),
    foreign key (user_no) references User (user_no),
    primary key (book_no, user_no)
) comment '책 좋아요';

CREATE TABLE Bookfavorite (
	favorite_no int primary key auto_increment comment '즐겨찾기 번호',
	user_no int Not Null comment '사용자 번호',
    book_no int Not Null comment '책 번호',
    favorite_date datetime Not Null default now() comment '즐겨찾기 추가 일시',
    foreign key (user_no) references User (user_no),
    foreign key (book_no) references Book (book_no)
) comment '책 즐겨찾기'; 

CREATE TABLE Articlefavorite (
	favorite_no int primary key auto_increment comment '즐겨찾기 번호',
    user_no int Not Null comment '사용자 번호',
    article_no int Not Null comment '글 번호',
    favorite_date datetime Not Null default now() comment '즐겨찾기 추가 일시',
    foreign key (user_no) references User (user_no),
    foreign key (article_no) references Article (article_no)
) comment '글 즐겨찾기';

CREATE TABLE Bookcategory (
	book_no int comment '책 번호',
    category int comment '카테고리 코드',
    foreign key (book_no) references Book (book_no),
    foreign key (category) references Category (category_no),
    primary key (book_no,category)
) comment '책 카테고리';

CREATE TABLE Tag (
	tag_no int primary key auto_increment comment '대표태그 번호',
    tag_name varchar(20) Not Null comment '태그명'
) comment '대표태그';

CREATE TABLE Arttag (
	article_no int comment '글 번호',
    tag_no int comment '대표태그 번호',
    foreign key (article_no) references Article (article_no),
    foreign key (tag_no) references Tag (tag_no),
    primary key (article_no, tag_no)
) comment '글 태그';

CREATE TABLE Simtag (
	simtag_no int primary key auto_increment comment '유사태그 번호',
    tag_no int Not null comment '대표태그 번호',
    simtag_name varchar(20) Not Null comment '유사 태그명',
    foreign key (tag_no) references Tag (tag_no)
) comment '유사태그';

CREATE TABLE Tmptag (
	tmptag_no int primary key auto_increment comment '임시태그 번호',
    article_no int comment '글 번호',
    book_no int comment '책 번호',
    tmptag_name varchar(20) Not Null comment '임시 태그명',
    foreign key (book_no) references Book (book_no),
    foreign key (article_no) references Article (article_no)
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
    foreign key (article_no) references Article (article_no),
    foreign key (book_no) references Book (book_no),
    foreign key (user_no) references User (user_no)
) comment '댓글';

CREATE TABLE Commentlike (
	comment_no int comment '댓글 번호',
    user_no int comment '댓글 추천자',
    like_date datetime Not Null default now() comment '좋아요 일시',
    foreign key (comment_no) references Comment (comment_no),
    foreign key (user_no) references User (user_no),
    primary key (comment_no,user_no)
) comment '댓글 좋아요';

CREATE TABLE Booktag(
    book_no int comment '책 번호',
    tag_no int comment '태그 번호',
    primary key(book_no, tag_no),
    foreign key(book_no) references book(book_no),
    foreign key(tag_no) references tag(tag_no)
) comment '책 태그 연결';