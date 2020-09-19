package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 조합 {
	static long modval;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int R = sc.nextInt();
			long sol = 0;
			long[] Nmod = new long[N + 1];
			modval = 1234567891;
			Nmod[0] = 1;

			for (int i = 1; i < N + 1; i++) {
				Nmod[i] = (Nmod[i - 1] * i) % modval;
			}

			long floor = (Nmod[R] * Nmod[N - R]) % modval;
			long transfloor = ferma(floor, modval - 2);

			System.out.println("#" + tc + " " + (Nmod[N]*transfloor)%modval);
		}
	}

	private static long ferma(long n, long x) {
		if (x == 0)
			return 1;
		long tmp = ferma(n, x / 2);
		long ret = (tmp * tmp) % modval;
		if (x % 2 == 0)
			return ret;
		else
			return (ret * n) % modval;
	}
}
