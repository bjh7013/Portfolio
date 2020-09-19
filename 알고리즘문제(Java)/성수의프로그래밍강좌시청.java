package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class 성수의프로그래밍강좌시청 {
	static int N;
	static int K;
	static double val = Double.MIN_VALUE;
	static double[] M;
	static boolean[] b;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 총 강좌 갯수
			K = sc.nextInt(); // k개 선택
			M = new double[N + 1]; // 강좌 수준
			b = new boolean[N + 1];
			val = Double.MIN_VALUE;
//			(A+M)/2 이것이 K번 반복
			for (int i = 1; i < N + 1; i++) {
				M[i] = sc.nextDouble();
			}
			Arrays.sort(M);
			for (int i = M.length-K; i < M.length; i++) {
				val = (val + M[i]) / 2;
			}
//			dfs(1,0);
			System.out.print("#" + tc + " ");
			System.out.printf("%f\n", val);
		}
	}

//	static void dfs(int j,int cnt) {
//		if (cnt == K) {
//			double tempsol = 0;
//			for (int i = 1; i < N + 1; i++) {
//				if (b[i]) {
//					tempsol = (tempsol + M[i]) / 2;
//					System.out.print(i+" ");
//				}
//			}
//			System.out.println();
//			val = Math.max(val, tempsol);
//			return;
//		}
//		for (int i = j; i < N + 1; i++) {
//			if (b[i])
//				continue;
//			b[i] = true;
//			dfs(i+1,cnt + 1);
//			b[i] = false;
//		}
//	}
}
