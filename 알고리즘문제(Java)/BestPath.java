package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class BestPath {
	static int N;
	static boolean[] b;
	static int[] pathi;
	static int[] pathj;
	static int[] tempi;
	static int[] tempj;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		long start = System.currentTimeMillis();
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 방문지점수
			b = new boolean[N + 2];
			pathi = new int[N + 2]; // 0,1 회사집
			pathj = new int[N + 2];
			tempi = new int[N + 2]; // 0,1 회사집
			tempj = new int[N + 2];
			for (int i = 0; i < N + 2; i++) {
				pathi[i] = sc.nextInt();
				pathj[i] = sc.nextInt();
			}

			p(N, 0);
			System.out.println("#" + tc + " " + result);
			result = 10000000;
			tempresult = 0;
			distance=0;
		}
	}

	static int result = 10000000;
	static int tempresult = 0;
	static int distance = 0;

	public static void p(int N, int cnt) {
		for (int i = 0; i < N; i++) {
			if (b[i])
				continue;
			b[i] = true;
			if (result < distance) {
				b[i] = false;
				continue;
			}
			tempi[cnt] = pathi[i + 2];
			tempj[cnt] = pathj[i + 2];
			if (cnt == 0) {
				distance += Math.abs(pathi[0] - tempi[0]);
				distance += Math.abs(pathj[0] - tempj[0]);
			}
			if (cnt >= N - 1) {
				distance += Math.abs(pathi[1] - tempi[N - 1]);
				distance += Math.abs(pathj[1] - tempj[N - 1]);
				distance += Math.abs(tempi[cnt - 1] - tempi[cnt]);
				distance += Math.abs(tempj[cnt - 1] - tempj[cnt]);
			}
			if (cnt > 0 && cnt < N - 1) {
				distance += Math.abs(tempi[cnt - 1] - tempi[cnt]);
				distance += Math.abs(tempj[cnt - 1] - tempj[cnt]);
			}
			if (cnt >= N-1) {
				if (result > distance)
					result = distance;
				if (cnt == 0) {
					distance -= Math.abs(pathi[0] - tempi[0]);
					distance -= Math.abs(pathj[0] - tempj[0]);
				}
				if (cnt >= N - 1) {
					distance -= Math.abs(pathi[1] - tempi[N - 1]);
					distance -= Math.abs(pathj[1] - tempj[N - 1]);
					distance -= Math.abs(tempi[cnt - 1] - tempi[cnt]);
					distance -= Math.abs(tempj[cnt - 1] - tempj[cnt]);
				}
				if (cnt > 0 && cnt < N - 1) {
					distance -= Math.abs(tempi[cnt - 1] - tempi[cnt]);
					distance -= Math.abs(tempj[cnt - 1] - tempj[cnt]);
				}
				b[i] = false;
				break;
			}
			p(N, cnt + 1);
			if (cnt == 0) {
				distance -= Math.abs(pathi[0] - tempi[0]);
				distance -= Math.abs(pathj[0] - tempj[0]);
			}
			if (cnt >= N - 1) {
				distance -= Math.abs(pathi[1] - tempi[N - 1]);
				distance -= Math.abs(pathj[1] - tempj[N - 1]);
				distance -= Math.abs(tempi[cnt - 1] - tempi[cnt]);
				distance -= Math.abs(tempj[cnt - 1] - tempj[cnt]);
			}
			if (cnt > 0 && cnt < N - 1) {
				distance -= Math.abs(tempi[cnt - 1] - tempi[cnt]);
				distance -= Math.abs(tempj[cnt - 1] - tempj[cnt]);
			}
			b[i] = false;
		}
	}

}