package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * 백준 1107번 리모컨
 * 
 * SSAFY_구미_3기 백정훈 코드
 */
public class 리모컨 {
	static String s;
	static int M;
	static boolean[] check;
	static int choice;
	static int count;
	static int totalcnt;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		s = sc.next(); // 숫자
		M = sc.nextInt(); // 고장난 갯수
		check = new boolean[10];
		choice = 99999999;
		count = 0;
		totalcnt=99999999;
		int result = 0;

		for (int i = 0; i < M; i++) {
			int button = sc.nextInt();
			check[button] = true; // true인건 고장남
		}

		for (int i = 0; i < s.length()+1; i++) {
			dfs(0, i, 0);
		}
		if(Math.abs(100-Integer.parseInt(s))<(Math.abs(choice-Integer.parseInt(s))+count)) {
			System.out.println(Math.abs(100-Integer.parseInt(s)));
		}else {
			System.out.println(Math.abs(choice-Integer.parseInt(s))+count);
		}
//		System.out.println(Integer.parseInt(s));
//		System.out.println(choice);
//		System.out.println(count+"카운트");
	}

	static void dfs(int value, int length, int cnt) {
		if (length == s.length() + 1) {
//			System.out.println(value);
			if((Math.abs(choice-Integer.parseInt(s))+count)>Math.abs(value-Integer.parseInt(s))+cnt) {
				choice=value;
				count = cnt;
				totalcnt = Math.abs(choice-Integer.parseInt(s))+count;
			}
			return;
		}
		for (int i = 9; i >= 0; i--) {
			if (check[i]) // 고장난거면 넘긴다.
				continue;
			dfs(value + ((int) Math.pow(10, s.length() - (length)) * i), length + 1,cnt+1);
		}
	}
}
