package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PipeMoving {
	static int N;
	static int[][] map;
	static int[][] b;
	static int result = 0;
	static int max = 987654321;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N + 2][N + 2];
		b = new int[N + 2][N + 2];

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		dfs(1, 1, 1); // 상태가 1이면 가로 2이면 세로 3이면 대각선
		System.out.println(result);
	}

	public static void dfs(int i, int j, int status) {
		if (b[N][N] >= 1) {
//			for (int k = 1; k < N + 1; k++) {
//				for (int l = 1; l < N + 1; l++) {
//					System.out.print(b[k][l] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			result++;
			return;
		}
		if (i > N || j > N) {
			return;
		}
		if (status == 1) {
			if (j >= N)
				return;
			if(map[i][j+1]==1)
				return;
			b[i][j]++;
			b[i][j + 1]++;
			if(b[N][N]>=1)
				result--;
			dfs(i, j + 1, 1);
			dfs(i, j + 1, 3);
			b[i][j]--;
			b[i][j + 1]--;
		}
		if (status == 2) {
			if (i >= N)
				return;
			if(map[i+1][j]==1)
				return;
			b[i][j]++;
			b[i + 1][j]++;
			if(b[N][N]>=1)
				result--;
			dfs(i + 1, j, 2);
			dfs(i + 1, j, 3);
			b[i][j]--;
			b[i + 1][j]--;
		}
		if (status == 3) {
			if (i >= N || j >= N)
				return;
			if(map[i+1][j]==1 || map[i][j+1]==1 || map[i+1][j+1]==1)
				return;
			b[i][j]++;
			b[i][j + 1]++;
			b[i + 1][j]++;
			b[i + 1][j + 1]++;
			if(b[N][N]>=1)
				result-=2;
			dfs(i + 1, j + 1, 1);
			dfs(i + 1, j + 1, 2);
			dfs(i + 1, j + 1, 3);
			b[i][j]--;
			b[i][j + 1]--;
			b[i + 1][j]--;
			b[i + 1][j + 1]--;
		}

//		for (int i = 1; i < N + 1; i++) {
//			for (int j = 1; j < N + 1; j++) {
//				if (b[i][j])
//					continue;
//				b[i][j]=true;
//			}
//		}
	}
}
