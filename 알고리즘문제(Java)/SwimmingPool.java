package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SwimmingPool {
	static int[] health; // 일,월,3월,년
	static int[] M;	// 각각 횟수
	static int cost;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int sol = 99999999;
			health = new int[4 + 1];
			for (int i = 1; i < 4 + 1; i++) {
				health[i] = sc.nextInt();
			}
			M = new int[12 + 1];
			cost = 99999999;
			for (int i = 1; i < 12 + 1; i++) {
				M[i] = sc.nextInt();
			}
			dfs(1,0);
			System.out.println("#" + tc + " " + cost);
		}
	}

	public static void dfs(int now, int val) {
		if(now>=13) {
			cost=Math.min(val, cost);
			return;
		}
		dfs(now+1,val+M[now]*health[1]);  //day
		dfs(now+1,val+health[2]);  //month
		dfs(now+3,val+health[3]);  //3-month
		dfs(now+12,health[4]);  //year
	}

}
