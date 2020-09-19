package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class 수지맞는여행 {
	static int result = 0;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static boolean[][] b;
	static boolean[] alpha;
	static Stack<Character> stack = new Stack<>();

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int R = sc.nextInt();
			int C = sc.nextInt();
			char[][] map = new char[R + 2][C + 2];
			b = new boolean[R + 2][C + 2];
			alpha = new boolean[100];
			stack = new Stack<>();
			result=0;
			String[] s = new String[R];
			for (int i = 0; i < R; i++) {
				s[i] = sc.next();
			}
			for (int i = 1; i <= R; i++) {
				for (int j = 1; j <= C; j++) {
					map[i][j] = s[i - 1].charAt(j - 1);
				}
			}
			dfs(R, C, map, 1, 1, 1);
			System.out.println("#"+tc+" "+result);
		}
	}

	public static void dfs(int R, int C, char[][] map, int y, int x,int cnt) {
		if (alpha[(int) map[y][x]])
			return;
		if (b[y][x])
			return;
		alpha[(int) map[y][x]] = true;
		b[y][x] = true;
		result=Math.max(result, cnt);
		for (int i = 0; i < 4; i++) {
			if (map[y + dy[i]][x + dx[i]] >= 'A' && map[y + dy[i]][x + dx[i]] <= 'Z') {
				dfs(R, C, map, y + dy[i], x + dx[i],cnt+1);
			}
		}
		alpha[(int) map[y][x]] = false;
		b[y][x] = false;
	}
}
