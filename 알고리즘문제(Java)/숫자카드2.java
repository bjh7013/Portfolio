package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 숫자카드2 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int[] map = new int[20000003];
		StringBuffer sb = new StringBuffer("");
		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			int num = sc.nextInt() + 10000000;
			map[num]++;
		}
		int M = sc.nextInt();
		for (int i = 0; i < M; i++) {
			int num = sc.nextInt() + 10000000;
			sb.append(map[num] + " ");
		}
		System.out.println(sb);
	}
}
