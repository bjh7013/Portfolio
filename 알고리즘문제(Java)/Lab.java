package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Lab {
	static int[][] map;
	static int[][] temp;
	static boolean[][] b;
	static boolean[][] bt;
	static int result = -1;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int temtem = 1;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt();
		int C = sc.nextInt();
		map = new int[R + 2][C + 2];
		temp = new int[R + 2][C + 2];

		b = new boolean[R + 2][C + 2];
		bt = new boolean[R + 2][C + 2];
		for (int i = 0; i < R + 2; i++) {
			for (int j = 0; j < C + 2; j++) {
				map[i][j] = -1;
			}
		}
		for (int i = 1; i < R + 1; i++) {
			for (int j = 1; j < C + 1; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		dfs(R, C, 0);
		System.out.println(result);
	}

	static public void dfs(int R, int C, int cnt) {
		if (cnt == 3) {
			int tempcnt = 0;
			
			for(int i = 0; i < R + 2; i++) {
				temp[i]=map[i].clone();
			}

			sol(R, C);
//			for (int i = 1; i < R + 1; i++) {
//				for (int j = 1; j < C + 1; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
			for (int i = 1; i < R + 1; i++) {
				for (int j = 1; j < C + 1; j++) {
					if (map[i][j] == 0)
						tempcnt++;
				}
			}
			temtem=1;
			for(int i = 0; i < R + 2; i++) {
				map[i]=temp[i].clone();
			}
//			map = temp;
			bt = new boolean[R + 2][C + 2];
			if (tempcnt > result)
				result = tempcnt;
			return;
		}
		for (int i = 1; i < R + 1; i++) {
			for (int j = 1; j < C + 1; j++) {
				if (b[i][j])
					continue;
				if (map[i][j] == 0) {
					b[i][j] = true;
					map[i][j] = 1;
					dfs(R, C, cnt + 1);
					map[i][j] = 0;
					b[i][j] = false;
				}
			}
		}
	}

	static public void sol(int R, int C) {
		int temptemp=0;
		if (temtem == 0)
			return ;
		for (int i = 1; i < R + 1; i++) {
			for (int j = 1; j < C + 1; j++) {
				if (bt[i][j])
					continue;
				if (map[i][j] == 2) {
					bt[i][j] = true;
					for (int k = 0; k < dx.length; k++) {
						if (map[i + dy[k]][j + dx[k]] == 0) {
							map[i + dy[k]][j + dx[k]] = 2;
							temptemp++;
						}
					}
				}
			}
		}
		if(temptemp==0)
			temtem=0;
		sol(R, C);
		return ;
	}

}
