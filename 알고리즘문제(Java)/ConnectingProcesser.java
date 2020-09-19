package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class ConnectingProcesser {
	static int N;
	static int[][] map;
	static boolean[][] b;
	static int numresult = 0;
	static int cnt = 0;
	static int tempcnt = 0;
	static int tempnum = 0;
	static int valresult = 987654321;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			map = new int[N + 2][N + 2];
			b = new boolean[N + 2][N + 2];
			numresult = 987654321;
			valresult = 987654312;
			tempnum = 987654321;
			cnt = 0;
			tempcnt = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][j] == 1)
						tempcnt++;
				}
			}

			dfs(0, 0, 1, 1);
			if (valresult == 987654312)
				System.out.println("#" + tc + " 0");
			if (valresult != 987654312)
				System.out.println("#" + tc + " " + valresult);
		}
	}

	public static void dfs(int num, int val, int y, int x) {
		if (cnt == tempcnt) {
//			System.out.println(val+" "+num);
//			for (int i = y; i <= N; i++) {
//				for (int j = x; j <= N; j++) {
//					if (b[i][j]) {
//						if (map[i][j] == 1) {
//							System.out.print("2 ");
//							continue;
//						}
//						System.out.print("1 ");
//					}
//					if (!b[i][j]) {
//						System.out.print("0 ");
//					}
//				}
//				System.out.println();
//			}
//			System.out.println();
			if (num <= numresult) {
				if (num == numresult) {
					valresult = Math.min(val, valresult);
					return;
				}
				if (num < numresult) {
					numresult = num;
					valresult = 987654321;
					valresult = Math.min(val, valresult);
				}
			}
			return;
		}

		All: for (int i = y; i <= N; i++) {
			for (int j = x; j <= N; j++) {
				if (b[i][j])
					continue;
				if (map[i][j] == 1) {
//					if ((val >= valresult) && (num <= numresult))
//						return;
					b[i][j] = true;
					cnt++;
					if (i == 1 || j == 1 || i == N || j == N) {
						dfs(num + 1, val++, 1, 1);
						break All;
					}
					if ((i > 1 && j > 1) && (i < N && j < N)) {
						int temptemp = 0;
						for (int k = 1; k < i; k++) {
							if (map[i - k][j] == 1) {
								break;
							}
							if (b[i - k][j]) {
								break;
							}
							if (k == i - 1) {
								for (int l = 1; l < i; l++) {
									b[i - l][j] = true;
								}
								dfs(num + 1, val + i - 1, 1, 1);
								for (int l = 1; l < i; l++) {
									b[i - l][j] = false;
								}
								temptemp = 1;
							}
						}
						for (int k = 1; k < j; k++) {
							if (map[i][j - k] == 1) {
								break;
							}
							if (b[i][j - k]) {
								break;
							}
							if (k == j - 1) {
								for (int l = 1; l < j; l++) {
									b[i][j - l] = true;
								}
								dfs(num + 1, val + j - 1, 1, 1);
								for (int l = 1; l < j; l++) {
									b[i][j - l] = false;
								}
								temptemp = 1;

							}
						}
						for (int k = 1; k <= N - i; k++) {
							if (map[i + k][j] == 1) {
								break;
							}
							if (b[i + k][j]) {
								break;
							}
							if (k == N - i) {
								for (int l = 1; l <= N - i; l++) {
									b[i + l][j] = true;
								}
								dfs(num + 1, val + N - i, 1, 1);
								for (int l = 1; l <= N - i; l++) {
									b[i + l][j] = false;
								}
								temptemp = 1;

							}
						}
						for (int k = 1; k <= N - j; k++) {
							if (map[i][j + k] == 1) {
								break;
							}
							if (b[i][j + k]) {
								break;
							}
							if (k == N - j) {
								for (int l = 1; l <= N - j; l++) {
									b[i][j + l] = true;
								}
								dfs(num + 1, val + N - j, 1, 1);
								for (int l = 1; l <= N - j; l++) {
									b[i][j + l] = false;
								}
								temptemp = 1;

							}
						}
						if (temptemp == 0) {
							dfs(num, val, 1, 1);
						}
						b[i][j] = false;
						cnt--;
					}
					break All;
				}
			}
		}
	}
}
