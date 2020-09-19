package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 우뚝선산 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[] H = new int[N + 2];
			for (int i = 1; i <= N; i++) {
				H[i] = sc.nextInt();
			}

			int tempj = 0;
			int tempi = 0;
			int cnt = 0;
			int tempcnt = 0;
			for (int i = 1; i < N; i++) {
				tempi = 0;
				tempj = 0;
				tempcnt = 0;
				for (int j = i; j < N; j++) {
					if (H[j] > H[j + 1] && tempj == 0)
						break;
					if (H[j] < H[j + 1] && tempj == 2) {
						break;
					}
					if (H[j] < H[j + 1]) {
						tempj = 1;
						tempi++;
						continue;
					}
					if (H[j] > H[j + 1] && tempj == 1 || tempj == 2) {
						tempcnt++;
						tempj = 2;
						continue;
					}
				}
				cnt += tempcnt * tempi;
				i+=tempi;
			}
			System.out.println("#" + tc + " " + cnt);
		}
	}
}
