package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FourSquares {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] map = new int[N + 1];
		map[0] = 0;
		map[1] = 1;

		for (int i = 2; i < N + 1; i++) {
			int temp = 987654321;
			for (int j = 1; j * j <= i; j++) {
				temp = Math.min(temp, map[i - j * j]);
			}
			map[i] = temp + 1;
		}
		System.out.println(map[N]);
	}
}
