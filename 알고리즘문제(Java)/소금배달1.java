package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author Baek
 * 	월말평가 소금배달문제
 * 1. 그리디 - 5,3 2개라서 가능함
 * 2. 브루트포스,백트래킹 - 내가 했던거
 * 3. DP
 */
public class 소금배달1 {
	static int[] data;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int N=sc.nextInt();
		data = new int[N+1];
		System.out.println(dfs(N));
	}
	static int dfs(int n) {
		if(n==1) {
			return data[n]=2;
		}
		if(n==2) {
			return data[n]=5;
		}
		if(data[n]!=0) {
			return data[n];
		}
		return data[n]=2*dfs(n-1)+dfs(n-2);
	}
}
