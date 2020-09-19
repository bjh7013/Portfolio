package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 동전0 {
	static List<Integer> list;
	static int N;
	static int K;
	static boolean check;
	static int sol;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 동전의 종류
		K = sc.nextInt(); // 가치
		list = new ArrayList<>();
		int[] coin = new int[N + 1];
		check = false;
		sol = 0;
		int value = 0;
		for (int i = 0; i < N; i++) {
			coin[i] = sc.nextInt();
		}
		All:for (int i = N - 1; i > -1; i--) {
			while (true) {
				if (value + coin[i] > K)
					break;
				if (value + coin[i] == K)
					break All;
				value += coin[i];
				sol++;
			}
		}
		System.out.println(sol+1);
	}
}
