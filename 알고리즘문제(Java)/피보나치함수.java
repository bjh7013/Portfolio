package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * 백준 1004번 피보나치 함수
 * 
 * SSAFY_구미_3기 백정훈 코드
 */
public class 피보나치함수 {
	static int[] zero;
	static int[] one;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			zero = new int[N + 1];
			one = new int[N + 1];
			System.out.print(fibonaccizero(N) + " ");
			System.out.println(fibonaccione(N));
		}
	}

	static int fibonaccizero(int n) {
		if (n == 0) {
			return 1;
		} else if (n == 1) {
			return 0;
		} else {
			if (zero[n] == 0)
				return zero[n] = fibonaccizero(n - 2) + fibonaccizero(n - 1);
			else {
				return zero[n];
			}
		}
	}
	
	static int fibonaccione(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			if (one[n] == 0)
				return one[n] = fibonaccione(n - 2) + fibonaccione(n - 1);
			else {
				return one[n];
			}
		}
	}
}
