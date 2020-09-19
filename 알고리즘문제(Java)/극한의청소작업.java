package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 극한의청소작업 {
	static long A;
	static long B;
	static long myval;
	static int cntminus = 0;
	static long totalcnt = 0;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			A = sc.nextLong();
			B = sc.nextLong();
			cntminus = 0;
			totalcnt = 0;
			long sol = 0;
			myval = 1;
			long cntA = cal(A, 0);
			cntA = totalcnt;
			myval = 1;
			totalcnt = 0;
			long cntB = cal(B, 0);
			cntB = totalcnt;

			if (A > 0) {
				sol = cntB - cntA;
			} else if (B < 0) {
				sol = cntA - cntB + 2;
			} else {
				sol = cntB + cntA;
			}
			System.out.println("#" + tc + " " + (sol - cntminus));
		}

	}

	static long cal(long val, long cnt) {
		if (val == 0) {
			totalcnt = cnt;
			return 1;
		}
		if (val < 0) {
			val *= (-1);
			cntminus++;
		}
		if (val > 0) {
			myval *= 9;
			if (val % 10 != 0) {
				if (val % 10 < 4) {
					cnt = cnt + (val % 10) * (myval / 9);
				} else {
					cnt = cnt + ((val % 10) - 1) * (myval / 9);
				}
				val -= (val % 10);
				return cal(val / 10, cnt);
			} else {
				return cal(val / 10, cnt);
			}

		} else {
			return val;
		}
	}

}
