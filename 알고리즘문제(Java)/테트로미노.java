package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 테트로미노 {
	static int N;
	static int M;
	static int[][] map;
	static int[][] memo;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int result = 0;
	static boolean[][] check;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N + 2][M + 2];
		memo = new int[N + 2][M + 2];
		result = 0;
		check = new boolean[N + 2][M + 2];

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < M + 1; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < M + 1; j++) {
				dfs(i, j, map[i][j], 0); // ㅜ 빼고 전부 다 계산
			}
		}
		
		for (int i = 2; i < N; i++) { // ㅜ 자의 테두리 빼고 계산
			for (int j = 2; j < M; j++) {
				int tempsol = map[i][j];
				for (int k = 0; k < 4; k++) {
					tempsol+=map[i+dy[k]][j+dx[k]];
				}
				for (int k = 0; k < 4; k++) {
					result=Math.max(tempsol-map[i+dy[k]][j+dx[k]], result);
				}
			}
		}
		
		for (int j = 2; j < M; j++) {
			result=Math.max(map[1][j]+map[1][j-1]+map[1][j+1]+map[2][j], result);
			result=Math.max(map[N][j]+map[N][j-1]+map[N][j+1]+map[N-1][j], result);
		}
		for (int i = 2; i < N; i++) {
			result=Math.max(map[i][1]+map[i-1][1]+map[i+1][1]+map[i][2], result);
			result=Math.max(map[i][M]+map[i-1][M]+map[i+1][M]+map[i][M-1], result);
		}
		
		
		System.out.println(result);

	}

	static void dfs(int y, int x, int value, int cnt) {	
		if (cnt == 3) {
			result = Math.max(result, value);
			return;
		}
		check[y][x] = true;
		for (int k = 0; k < 4; k++) {
			if (y + dy[k] < 1 || y + dy[k] > N || x + dx[k] < 1 || x + dx[k] > M)
				continue;
			if (check[y + dy[k]][x + dx[k]])
				continue;
			dfs(y + dy[k], x + dx[k], value + map[y + dy[k]][x + dx[k]], cnt + 1);
		}
		check[y][x] = false;
	}
}
