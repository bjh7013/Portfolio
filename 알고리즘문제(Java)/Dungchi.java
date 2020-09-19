package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dungchi {
	static int N;
	static int[] w;
	static int[] h;
	static int[] result;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		w = new int[N];
		h = new int[N];
		result = new int[N];
		for (int i = 0; i < N; i++) {
			w[i] = sc.nextInt();
			h[i] = sc.nextInt();
			result[i] = 1;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (w[i] < w[j] && h[i] < h[j]) {
					result[i]++;
					continue;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			System.out.println(result[i]);
		}

	}
}
