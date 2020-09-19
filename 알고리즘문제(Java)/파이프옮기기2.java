package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 파이프옮기기2 {
	static int[] dx = { 1 };
	static int[] dy = {};

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[][] map = new int[N + 2][N + 2];
		for (int i = 0; i < N + 2; i++) {
			Arrays.fill(map[i], 1);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		int cnt = 0;
		long[][][] check = new long[N + 1][N + 1][3];// 0가로1세로2대각
		check[0][1][0] = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j + 1] == 0) {
					check[i][j + 1][0] += check[i][j][0] + check[i][j][2];
				}
				if (map[i + 1][j] == 0) {
					check[i + 1][j][1] += check[i][j][1] + check[i][j][2];
				}
				if (map[i + 1][j + 1] == 0 && map[i][j + 1] == 0 && map[i + 1][j] == 0) {
					check[i + 1][j + 1][2] += check[i][j][0] + check[i][j][1] + check[i][j][2];
				}
			}
		}
		System.out.println(check[N-1][N-1][0] + check[N-1][N-1][1] + check[N-1][N-1][2]);

	}

	static class Pipe {
		int y, x, status;

		public Pipe(int y, int x, int status) {
			super();
			this.y = y;
			this.x = x;
			this.status = status;
		}
	}
}
