package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class NomalizeBagpack {
	static int N;
	static int K;
	static int tempresult = 0;
	static int result = -1;
	static int tempw = 0;
	static int tempv = 0;
	static int[] W;
	static int[] V;
	static int[] temp;
	static boolean[] b;
	static Stack<Integer> stack = new Stack<>();
	static Stack<Integer> stackw = new Stack<>();

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();
		W = new int[N + 1];
		V = new int[N + 1];
		temp = new int[N + 1];
		b = new boolean[N + 1];
		int[][] bag = new int[N + 1][K + 1];
		int cnt = 1;
		for (int i = 1; i < N + 1; i++) {
			W[i] = sc.nextInt();
			V[i] = sc.nextInt();
		}

		while (cnt != N + 1) {
			for (int i = 1; i < K + 1; i++) {
				bag[cnt][i] = bag[cnt - 1][i];
				if (W[cnt] <= i) {
					bag[cnt][i]=Math.max(bag[cnt-1][i], bag[cnt-1][i-W[cnt]]+V[cnt]);
				}
			}
//			for (int i = 1; i < K + 1; i++) {
//				System.out.print(bag[cnt][i] + " ");
//			}
//			System.out.println();
			cnt++;
		}
		System.out.println(bag[cnt - 1][K]);

	}

}
