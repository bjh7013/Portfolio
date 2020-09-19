package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class 조합백준 {
	static BigInteger[][] memory;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		memory = new BigInteger[N + 1][M + 1];
		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < M + 1; j++) {
				memory[i][j] = BigInteger.valueOf(0);
			}
		}
		BigInteger result = BigInteger.valueOf(0);
		result = result.add(factorial(N, M));

		System.out.println(result);
	}

	static BigInteger factorial(int N, int R) {
		if (memory[N][R] != BigInteger.valueOf(0)) {
			return memory[N][R];
		}
		if (R == 0) {
			return memory[N][R] = BigInteger.valueOf(1);
		}
		if (N == R) {
			return memory[N][R] = BigInteger.valueOf(1);
		}
		return memory[N][R] = factorial(N - 1, R).add(factorial(N - 1, R - 1));
	}
}
