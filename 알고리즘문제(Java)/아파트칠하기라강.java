package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 아파트칠하기라강 {
	static long[][] data;
	static int mod;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		data = new long[2][N + 1];
		mod = 1000000007;
		if (N == 0) {
			System.out.println(0);
			return;
		}
		if (N == 1) {
			System.out.println(2);
			return;
		}
		if (N == 2) {
			System.out.println(7);
			return;
		}
		data[0][0] = 1;
		data[0][1] = 2;
		data[0][2] = 7;
		for (int i = 3; i <= N; i++) {
			data[1][i] = (data[1][i - 1] + data[0][i - 3]) % mod;
			data[0][i] = (3 * data[0][i - 2] + 2 * data[0][i - 1] + 2 * data[1][i]) % mod;
		}
		System.out.println(data[0][N]);
	}

}
