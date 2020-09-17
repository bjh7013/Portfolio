use scott; --  scott으로 하겠습니다!

create table product (pcode int(10), pname varchar(10), price int(10)); -- 1번 문제

insert into product values(1000,'TV',3000000);
insert into product values(2000,'노트북',1000000);
insert into product values(4000,'냉장고',2000000);
insert into product values(3000,'세탁기',2000000);
insert into product values(2001,'데스크탑',1500000);
insert into product values(3001,'건조기',1000000);
--  2번 완료
select * from product where 1=1; -- 2번 확인

select *,round(price*0.85) from product where 1=1; -- 3번 문제
update product set price=price*0.8 where pname='TV';-- 4번 문제
select * from product where 1=1; -- 4번 확인
select sum(price) as "총가격"from product;-- 5번