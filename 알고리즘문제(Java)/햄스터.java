package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 햄스터 {
	static int[] cage;
	static int[] result;
	static int N;
	static int X;
	static int M;
	static int sum;
	static int numcheck;
	static int[] l;
	static int[] r;
	static int[] s;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 총 햄스터 우리 갯수 0~N개
			X = sc.nextInt(); // 각 우리의 햄스터 수 0~X마리
			M = sc.nextInt(); // 경근이의 기록 M개
			sum = 0;
			cage = new int[N + 2];
			result = new int[N + 2];
			numcheck = 0;
			l = new int[M + 2]; // l번 우리에서
			r = new int[M + 2]; // r번 우리까지
			s = new int[M + 2]; // 햄스터의 수
			for (int i = 1; i < M + 1; i++) {
				l[i] = sc.nextInt();
				r[i] = sc.nextInt();
				s[i] = sc.nextInt();
			}
			dfs(1);
			System.out.print("#"+tc+" ");
			if (numcheck == 1) {
				for (int i = 1; i < N + 1; i++) {
					System.out.print(result[i] + " ");
				}
				System.out.println();
			}
			if (numcheck != 1) {
				System.out.println("-1");
			}
		}
	}

	public static void dfs(int cnt) {
		if (cnt == N + 1) {
			int check = 0;
			for (int i = 1; i < M + 1; i++) {
				int result = 0;
				for (int j = l[i]; j < r[i] + 1; j++) {
					result += cage[j];
				}
				if (result == s[i])
					check++;
			}
			if (check == M) {
				int tempsum = 0;
				for (int i = 1; i < N + 1; i++) {
					tempsum += cage[i];
				}
				if (tempsum >= sum) {
					if (tempsum == sum) {
						for (int i = 1; i < N + 1; i++) {
							if (result[i] > cage[i]) {
								for (int j = 1; j < N + 1; j++) {
									result[i] = cage[i];
								}
								break;
							}
							if (result[i] < cage[i]) {
								break;
							}
						}
					}
					if (tempsum > sum) {
						sum = tempsum;
						for (int i = 1; i < N + 1; i++) {
							result[i] = cage[i];
						}
					}
				}
//				for (int i = 1; i < N + 1; i++) {
//					System.out.print(cage[i] + " ");
//				}
//				System.out.println();
				numcheck = 1;
			}
			return;
		}
		for (int i = 0; i < X + 1; i++) {
			cage[cnt] = i;
			dfs(cnt + 1);
		}
	}
}
