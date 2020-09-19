package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class 곱셈 {
	static long result = 0;
	static Stack<Long> s = new Stack<>();

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		long A = sc.nextLong();
		long B = sc.nextLong();
		long C = sc.nextLong();

		divide(A, B, C);
		System.out.println(result);
	}

	static void divide(long A, long B, long C) {
		if (B == 1) {
			result = A;
			for (int i = 0; i < s.size(); i++) {
				result = (result * s.get(i)) % C;
			}
			result = result % C;
			return;
		}
		long tempresult = 0;
		if (B % 2 == 1) { // B가 홀 수 일 때

			tempresult = (A * A) % C;
			s.add(A);
			divide(tempresult, B / 2, C);

		} else {
			tempresult = (A * A) % C;
			divide(tempresult, B / 2, C);
		}
	}
}
