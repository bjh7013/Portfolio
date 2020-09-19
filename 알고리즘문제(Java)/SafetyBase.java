package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SafetyBase {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int B = sc.nextInt();
			int tempi = 0;
			int tempj = 0;
			int[][] map = new int[N + 4][N + 4];
			int[][] cnt = new int[N + 4][N + 4];

			for (int i = 0; i < B; i++) {
				tempi = sc.nextInt();
				tempj = sc.nextInt();
				map[tempi + 1][tempj + 1]++;
			}
//			for (int i = 2; i < N + 2; i++) {
//				for (int j = 2; j < N + 2; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}
			for (int i = 2; i < N + 2; i++) {
				for (int j = 2; j < N + 2; j++) {
					if (map[i][j] > 0) {
						while (map[i][j]!=0) {
							cnt[i][j]++;
							cnt[i + 1][j]++;
							cnt[i + 2][j]++;
							cnt[i - 1][j]++;
							cnt[i - 2][j]++;
							cnt[i][j + 1]++;
							cnt[i][j + 2]++;
							cnt[i][j - 1]++;
							cnt[i][j - 2]++;
							map[i][j]--;
						}
					}
				}
			}
			int temp = -1;
			for (int i = 2; i < N + 2; i++) {
				for (int j = 2; j < N + 2; j++) {
					if (temp < cnt[i][j])
						temp = cnt[i][j];
				}
			}

			System.out.println("#"+tc+" "+temp);

		}
	}
}