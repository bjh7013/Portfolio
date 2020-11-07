use ssazitdb;
select * from level;
insert into level values ('9zit', 0, 40);
insert into level values ('8zit', 40, 100);
insert into level values ('7zit', 100, 200);
insert into level values ('6zit', 200, 500);
insert into level values ('5zit', 500, 1000);
insert into level values ('4zit', 1000, 10000);
insert into level values ('SSazit', 10000, 100000);

select row_number() over (order by level_start) as 'num', level, level_start, level_end 
from level;

select * from user;

insert into user(user_id, user_pw, user_name, nick_name, e_mail, phone_num, join_date) values('tree', '1234', '신우현', 'tree', 'ssafy@ssafy.com', '010-1111-2222', now());
update user set user_exp = 200 where user_no = 1;

-- user_level 매핑 trigger

DELIMITER //
CREATE TRIGGER user_level_update 
BEFORE UPDATE ON User 
FOR EACH ROW
BEGIN
	  SET
		NEW.user_level = (
			select level from level where level_end = (
				select MIN(level_end) from level where level_end > NEW.user_exp));
END; //
DELIMITER ;

drop trigger user_level_update;



-- article like 관련~

insert into blog(user_no, blog_name, blog_birth) values(1, 'happy house', now());
select * from blog;
insert into myboard(blog_no, board_name, board_type, seq_no) values(1, 'test', '0', 0);
select * from myboard;
insert into article(user_no, board_no, title, content, write_date, open_scope, thumb_nail, article_type, sharable, review_request)
	values(1, 1, 'testart', 'testttt', now(), 'public', 'test.jpg', 'N', false, false);
select * from article;
insert into articlelike values(1, 1, now());
select * from articlelike;
delete from articlelike where user_no = 1;

-- article like triggers(insert)

DELIMITER //
CREATE TRIGGER article_like_insert
BEFORE INSERT ON Articlelike
FOR EACH ROW
BEGIN
	update
		Article
	SET
		like_count = like_count + 1
	WHERE
		article_no = NEW.article_no;
END; //
DELIMITER ;

drop trigger article_like_insert;

-- article like triggers(delete)

DELIMITER //
CREATE TRIGGER article_like_delete
BEFORE DELETE ON Articlelike
FOR EACH ROW
BEGIN
	update
		Article
	SET
		like_count = like_count - 1
	WHERE
		article_no = OLD.article_no;
END; //
DELIMITER ;

drop trigger article_like_delete;

-- comment like 관련
insert into comment(article_no, user_no, content) values(1, 1, 'good');
select * from comment;
insert into commentlike(comment_no, user_no) values(1,1);
select * from commentlike;
delete from commentlike where user_no = 1;

-- trigger(insert)

DELIMITER //
CREATE TRIGGER comment_like_insert
BEFORE INSERT ON Commentlike
FOR EACH ROW
BEGIN
	update
		Comment
	SET
		like_count = like_count + 1
	WHERE
		comment_no = NEW.comment_no;
END; //
DELIMITER ;

drop trigger comment_like_insert;

-- trigger(delete)
DELIMITER //
CREATE TRIGGER comment_like_delete
BEFORE DELETE ON Commentlike
FOR EACH ROW
BEGIN
	update
		Comment
	SET
		like_count = like_count - 1
	WHERE
		comment_no = OLD.comment_no;
END; //
DELIMITER ;

drop trigger comment_like_delete;

-- book like 관련
select * from book;
insert into book(book_title, series, version, cover_front, cover_middle, cover_back) values('testbook', 'test', 1, 'default', 'default', 'default');
select * from booklike;
insert into booklike values(1, 1, now());
delete from booklike where user_no = 1;

-- trigger(insert)

DELIMITER //
CREATE TRIGGER book_like_insert
BEFORE INSERT ON Booklike
FOR EACH ROW
BEGIN
	update
		Book
	SET
		like_count = like_count + 1
	WHERE
		book_no = NEW.book_no;
END; //
DELIMITER ;

drop trigger book_like_insert;

-- trigger(delete)
DELIMITER //
CREATE TRIGGER book_like_delete
BEFORE DELETE ON Booklike
FOR EACH ROW
BEGIN
	update
		Book
	SET
		like_count = like_count - 1
	WHERE
		book_no = OLD.book_no;
END; //
DELIMITER ;

drop trigger book_like_delete;


