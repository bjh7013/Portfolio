CREATE TABLE EMP1
       (EMPNO int(4)  PRIMARY KEY,
	ENAME VARCHAR(10),
	JOB VARCHAR(9),
	MGR int(4),
	HIREDATE DATE,
	SAL decimal(7,2),
	COMM decimal(7,2),
	DEPTNO int(2));

INSERT INTO EMP1 VALUES
(7369,'SMITH','CLERK',7902,cast('2010-10-19' as date),800,NULL,20);
INSERT INTO EMP1 VALUES
(7499,'ALLEN','SALESMAN',7698,cast('2000-10-19' as date),1600,300,30);
INSERT INTO EMP1 VALUES
(7521,'WARD','SALESMAN',7698,cast('2013-05-19' as date),1250,500,30);
INSERT INTO EMP1 VALUES
(7566,'JONES','MANAGER',7839,cast('2018-10-19' as date),2975,NULL,20);
INSERT INTO EMP1 VALUES
(7654,'MARTIN','SALESMAN',7698,cast('2008-04-19' as date),1250,1400,30);
INSERT INTO EMP1 VALUES
(7698,'BLAKE','MANAGER',7839,cast('2016-11-19' as date),2850,NULL,30);
INSERT INTO EMP1 VALUES
(7782,'CLARK','MANAGER',7839,cast('2017-10-19' as date),2450,NULL,10);
INSERT INTO EMP1 VALUES
(7788,'SCOTT','ANALYST',7566,cast('2013-10-11' as date),3000,NULL,20);
INSERT INTO EMP1 VALUES
(7839,'KING','PRESIDENT',NULL,cast('2014-08-19' as date),5000,NULL,10);
INSERT INTO EMP1 VALUES
(7844,'TURNER','SALESMAN',7698,cast('2010-10-19' as date),1500,0,30);
INSERT INTO EMP1 VALUES
(7876,'ADAMS','CLERK',7788,cast('2000-09-19' as date),1100,NULL,20);
INSERT INTO EMP1 VALUES
(7900,'JAMES','CLERK',7698,cast('2018-10-19' as date),950,NULL,30);
INSERT INTO EMP1 VALUES
(7902,'FORD','ANALYST',7566,cast('2003-10-19' as date),3000,NULL,20);
INSERT INTO EMP1 VALUES
(7934,'MILLER','CLERK',7782,cast('2002-10-19' as date),1300,NULL,10);
select * from EMP1 where 1=1; -- 전체출력
select * from EMP1 where hiredate like '2014%'; -- 1번 문제
select * from EMP1 where 1=1 and ename like 's%' or ename like '%s'; -- 2번 문제
select * from EMP1 where 1=1 and comm is null; -- 3번 문제
select ename, deptno, sal, (sal*12+sal*1.5) 'YearPay'from EMP1 where 1=1 and deptno='30';-- 4번 문제
select ename,sal,(sal*0.15)'경조비' from EMP1 where 1=1 and sal>='2000'; -- 5번 문제