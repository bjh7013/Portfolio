package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 범준이의제주도여행계획 {
	static int N;
	static int M;
	static int[][] map;
	static boolean[] check;
	static char[] loc;
	static int[] time;
	static int[] gain;
	static int start;
	static int result;
	static int[] path;
	static int[] solpath;
	static int solcnt;
	static int[] memo;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 지점 개수
			M = sc.nextInt(); // 휴가 기간
			map = new int[N + 2][N + 2];
			check = new boolean[N + 2];
			loc = new char[N + 2];
			time = new int[N + 2];
			gain = new int[N + 2];
			path = new int[2000];
			solpath = new int[2000];
			start = 0;
			result = 0;
			solcnt = 0;
			memo = new int[N + 2];

			for (int i = 1; i < N + 1; i++) {
				for (int j = i + 1; j < N + 1; j++) {
					int dist = sc.nextInt();
					map[i][j] = dist;
					map[j][i] = dist;
				}
			}
			for (int i = 1; i < N + 1; i++) {
				loc[i] = sc.next().charAt(0);
				if (loc[i] == 'P') {
					time[i] = sc.nextInt();
					gain[i] = sc.nextInt();
				}
				if (loc[i] == 'A') {
					start = i;
				}
			}
			dfs(start, 0, 0, 1, 0);
			if (result == 0) {
				System.out.println("#" + tc + " " + result);
			} else {
				System.out.print("#" + tc + " " + result + " ");
				for (int i = 1; i < solcnt; i++) {
					System.out.print(solpath[i] + " ");
				}
				System.out.println(start);
			}
		}

	}

	static int dfs(int target, int cost, int get, int day, int cnt) {
		if (day == M + 1 && target == start) {
			if (result < get) {
				solcnt = cnt;
				result = Math.max(result, get);
				for (int i = 0; i < cnt; i++) {
					solpath[i] = path[i];
				}
			}
			return memo[target] = result;
		}
		if (memo[target] != 0 && memo[target] >= get)
			return memo[target];
		if (day > M) {
			return 1;
		}
		path[cnt] = target;
		if (check[target])
			return 1;
		if (loc[target] == 'P') {
			check[target] = true;
		}
		for (int i = 1; i < N + 1; i++) {
			if (i == target) // 자기자신은 안감
				continue;
			if (cost + time[i] + map[target][i] > 9 * 60) { // 하루 9시간 넘으면 넘김
				continue;
			}
//			if (memo[i] != 0 && memo[i] >= (get + gain[i]))
//				continue;
			if (loc[target] == 'H' && loc[i] == 'H') // 호텔에서 호텔 넘김
				continue;
			if (loc[i] == 'P') { // 노는곳이면!
				memo[i] = Math.max(dfs(i, cost + time[i] + map[target][i], get + gain[i], day, cnt + 1), memo[i]);// 이동시간 + 노는 시간
			} else if (loc[i] == 'H') { // 호텔,공항이면
				memo[i] = Math.max(dfs(i, 0, get + gain[i], day + 1, cnt + 1), memo[i]);
			} else {
				if (day == M) {
					memo[i] = Math.max(dfs(i, 0, get + gain[i], day + 1, cnt + 1), memo[i]);
				}
			}
		}
		if (loc[target] == 'P') {
			check[target] = false;
		}
		return memo[target];
	}
}
