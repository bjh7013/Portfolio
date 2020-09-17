package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 다항식계산 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		/**
		 * f0(x)= 1, f1(x) = x ti =1이면 fi(x) = fai(x) + fbi(x) ti =2이면 fi(x) = ai ×
		 * fbi(x) ti= 3이면 fi(x) = fai(x) × fbi(x) x1,x2, ⋯, xM이 주어질 때,fN(x1), fN(x2), ⋯,
		 * fN(xM)
		 */
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // N
			int[] t = new int[N + 1];
			int[] a = new int[N + 1];
			int[] b = new int[N + 1];
			long[] f = new long[N + 1];
			long mod = 998244353;
			f[0] = 1;
			for (int i = 2; i < N + 1; i++) {
				t[i] = sc.nextInt();
				a[i] = sc.nextInt();
				b[i] = sc.nextInt();
			}
			int M = sc.nextInt();
			System.out.print("#"+tc+" ");
			for (int j = 1; j < M + 1; j++) {
				f[1] = sc.nextInt();
				for (int i = 2; i < N + 1; i++) {
					if (t[i] == 1) {
						f[i] = (f[a[i]] + f[b[i]])%mod;
					}
					if (t[i] == 2) {
						f[i] = (a[i] * f[b[i]])%mod;
					}
					if (t[i] == 3) {
						f[i] = (f[a[i]] * f[b[i]])%mod;
					}
				}
				System.out.print(f[N] + " ");
			}
			System.out.println();
		}
	}
}
