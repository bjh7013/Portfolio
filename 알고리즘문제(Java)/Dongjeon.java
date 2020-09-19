package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dongjeon {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] dong = new int[N + 1];
		int[] dongcnt = new int[N + 1];
		int[] val = new int[K + 1];
		for (int i = 1; i <= K; i++) {
			val[i] = 100001;
		}
		int cnt = 0;
		int result = -1;
		for (int i = 1; i < N + 1; i++) {
			dong[i] = sc.nextInt();
		}
		val[0] = 0;
		for (int i = 1; i < N + 1; i++) {
			int temp = val[K];
			for (int j = dong[i]; j < K + 1; j++) {
				val[j]=Math.min(val[j], val[j-dong[i]]+1);
//				System.out.print(val[j]+" ");
			}
//			System.out.println();
			result = Math.min(temp, val[K]);
		}
		if (result >= 100001)
			System.out.println("-1");
		if (result < 100001)
			System.out.println(val[K]);

	}
}
