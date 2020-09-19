package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 주사위윷놀이 {
	static int[][] map;
	static int[] score;
	static int[] num;
	static boolean[] end;
	static boolean[] b;
	static int[] pins;
	static int result;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		map = new int[33][6];
		score = new int[33];
		end = new boolean[5]; // 끝났을 때
		b = new boolean[33]; // 현재 있는 곳
		num = new int[11];
		pins = new int[4];
		result = 0;

		for (int i = 0; i < 32; i++) {
			for (int j = 1; j < 6; j++) {
				map[i][j] = i + j;
			}
		}

		for (int i = 0; i < 10; i++) {
			num[i] = sc.nextInt();
		}

		for (int i = 0; i < 21; i++) {
			score[i] = i * 2;
		}
		score[21] = 13;
		score[22] = 16;
		score[23] = 19;
		score[24] = 22;
		score[25] = 24;
		score[26] = 28;
		score[27] = 27;
		score[28] = 26;
		score[29] = 25;
		score[30] = 30;
		score[31] = 35;

		map[5][1] = 21;
		map[5][2] = 22;
		map[5][3] = 23;
		map[5][4] = 29;
		map[5][5] = 30;

		map[21][1] = 22;
		map[21][2] = 23;
		map[21][3] = 29;
		map[21][4] = 30;
		map[21][5] = 31;

		map[22][1] = 23;
		map[22][2] = 29;
		map[22][3] = 30;
		map[22][4] = 31;
		map[22][5] = 20;

		map[23][1] = 29;
		map[23][2] = 30;
		map[23][3] = 31;
		map[23][4] = 20;
		map[23][5] = 32;

		map[10][1] = 24;
		map[10][2] = 25;
		map[10][3] = 29;
		map[10][4] = 30;
		map[10][5] = 31;

		map[24][1] = 25;
		map[24][2] = 29;
		map[24][3] = 30;
		map[24][4] = 31;
		map[24][5] = 20;

		map[25][1] = 29;
		map[25][2] = 30;
		map[25][3] = 31;
		map[25][4] = 20;
		map[25][5] = 32;

		map[15][1] = 26;
		map[15][2] = 27;
		map[15][3] = 28;
		map[15][4] = 29;
		map[15][5] = 30;

		map[27][5] = 20;

		map[28][4] = 20;
		map[28][5] = 32;

		map[29][3] = 20;
		map[29][4] = 32;
		map[29][5] = 32;

		map[30][2] = 20;
		map[30][3] = 32;
		map[30][4] = 32;
		map[30][5] = 32;

		map[31][1] = 20;
		map[31][2] = 32;
		map[31][3] = 32;
		map[31][4] = 32;
		map[31][5] = 32;

		map[16][5] = 32;

		map[17][4] = 32;
		map[17][5] = 32;

		map[18][3] = 32;
		map[18][4] = 32;
		map[18][5] = 32;

		map[19][2] = 32;
		map[19][3] = 32;
		map[19][4] = 32;
		map[19][5] = 32;

		map[20][1] = 32;
		map[20][2] = 32;
		map[20][3] = 32;
		map[20][4] = 32;
		map[20][5] = 32;

		dfs(0, 0);
		System.out.println(result);
	}

	static void dfs(int sum, int cnt) {
		if (cnt == 10) {
//			System.out.println(sum);
			result = Math.max(result, sum);
			return;
		}

		All: for (int i = 0; i < 4; i++) {
			if (pins[i] == -1)
				continue;
			int now = pins[i];
			int next = map[now][num[cnt]];
			if (next == 32) {
				pins[i] = -1;
				dfs(sum, cnt + 1);
				pins[i] = now;
			}
			for (int j = 0; j < 4; j++) {
				if (i == j)
					continue;
				if (pins[j] == next) {
					continue All;
				}
			}
			pins[i] = next;
			dfs(sum + score[pins[i]], cnt + 1);
			pins[i] = now;
		}
	}
}
