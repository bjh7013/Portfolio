package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class 재관이의대량할인 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[] clothes = new int[N];
			for (int i = 0; i < N; i++) {
				clothes[i] = sc.nextInt();
			}
			Arrays.sort(clothes);
			int cnt = 0;
			long result = 0;
			for (int i = N - 1; i > -1; i--) {
				cnt++;
				if (cnt % 3 != 0) {
					result += clothes[i];
				}
			}
			System.out.println("#"+tc+" "+result);
		}
	}
}
