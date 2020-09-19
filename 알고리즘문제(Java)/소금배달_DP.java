package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class 소금배달_DP {
	static int[] data;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		data = new int[N + 1];
		for (int i = 0; i < data.length; i++) {
			if (i % 3 == 0) {
				data[i] = i / 3;
			} else {
				data[i] = Integer.MAX_VALUE;
			}
			System.out.print(data[i] + " ");
		}
		System.out.println();
		for (int i = 5; i < data.length; i++) {
			if (data[i - 5] != Integer.MAX_VALUE && data[i]>data[i-5]+1) {
				data[i] = data[i-5]+1;
			}
			System.out.print(data[i] + " ");
		}
		System.out.println(data[N - 1]);
	}
}
