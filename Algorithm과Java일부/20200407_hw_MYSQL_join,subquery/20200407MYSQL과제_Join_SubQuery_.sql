use scott;
select * from emp;
select ename,job,sal from emp inner join dept on emp.deptno=dept.deptno where loc='chicago'; -- 1번 문제
select empno,ename,job,emp.deptno from emp,dept where MGR is null and dept.deptno=emp.deptno;-- 2번 문제
select ename,job,mgr from emp where mgr=(select mgr from emp where ename='blake');-- 3번 문제
select ename from emp order by hiredate limit 0,5;-- 4번 문제
select ename,job,dname from emp,dept where mgr=(select empno from emp where ename='jones') and dept.deptno=emp.deptno;-- 5번문제