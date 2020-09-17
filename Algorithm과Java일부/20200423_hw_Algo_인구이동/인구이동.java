package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class 인구이동 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int L = sc.nextInt();
		int R = sc.nextInt();
		int[][] map = new int[N + 2][N + 2];
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = sc.nextInt();
			}
		}
//		for (int i = 1; i < N + 1; i++) {
//			for (int j = 1; j < N + 1; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		boolean end = true;
		int sol = -1;
		int temp = 1;

		while (end) {
			sol++;
			end = false;
			temp = 1;
			int[][] check = new int[N + 2][N + 2];
			boolean[][] b = new boolean[N + 2][N + 2];
			int[] sum = new int[N + 2];
			int[] avg = new int[N + 2];
			int[] sumcount = new int[N + 2];
			Queue<Pop> list = new LinkedList<>();
			boolean tempcheck = false;
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (b[i][j])
						continue;
					list.add(new Pop(i, j, temp));
					tempcheck = false;
					while (!list.isEmpty()) {
						Pop pop = list.poll();
						if (b[pop.y][pop.x])
							continue;
						b[pop.y][pop.x] = true;
						for (int k = 0; k < 4; k++) {
							if (pop.y + dy[k] < 1 || pop.x + dx[k] < 1 || pop.y + dy[k] > N || pop.x + dx[k] > N)
								continue;
							if(b[pop.y + dy[k]][pop.x + dx[k]])
								continue;
							if (Math.abs(map[pop.y + dy[k]][pop.x + dx[k]] - map[pop.y][pop.x]) >= L
									&& Math.abs(map[pop.y + dy[k]][pop.x + dx[k]] - map[pop.y][pop.x]) <= R) {
								list.add(new Pop(pop.y + dy[k], pop.x + dx[k], temp));
								tempcheck = true;
							}
						}
						if (tempcheck) {
							check[pop.y][pop.x] = temp;
							sum[pop.cnt] += map[pop.y][pop.x];
							sumcount[pop.cnt] += 1;
						}
					}
					if (tempcheck)
						temp++;
				}
			}
			for (int i = 1; i < temp; i++) {
				avg[i] = sum[i] / sumcount[i];
				end = true;
			}
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (check[i][j] != 0) {
						map[i][j]=avg[check[i][j]];
					}
				}
			}

		}
		System.out.println(sol);
	}

	static class Pop {
		int y, x, cnt;

		public Pop(int y, int x, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}
}
