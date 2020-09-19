package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class NandM {
	static int N;
	static int M;
	static StringBuffer sb = new StringBuffer("");

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		int[] num = new int[N+1];
		for(int i=1;i<=N;i++) {
			num[i]=sc.nextInt();
		}
		Arrays.sort(num);
		b = new boolean[N + 1];
		nandm(num, 0);
	}

	static boolean[] b;

	public static void nandm(int[] num, int cnt) {

		for (int i = 1; i <= num.length-1; i++) {
			if (b[i])
				continue;
			sb.append(num[i]);
			sb.append(" ");
//			for (int j = 1; j <= i - 1; j++) {
				b[i] = true;
//			}
			if (cnt == M - 1) {
				System.out.println(sb);
				sb.deleteCharAt(cnt * 2 + 1);
				sb.deleteCharAt(cnt * 2);
//				for (int j = 1; j <= i - 1; j++) {
					b[i] = false;
//				}
				continue;
			}
			nandm(num, cnt + 1);
//			for (int j = 1; j <= i - 1; j++) {
				b[i] = false;
//			}
			sb.deleteCharAt(cnt * 2 + 1);
			sb.deleteCharAt(cnt * 2);
		}
	}
}