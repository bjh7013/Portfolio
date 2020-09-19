package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Retirecompany {
	static int N;
	static int[] Ti;
	static int[] Pi;
	static boolean[] b;
	static int temp = 0;
	static int result = 0;
	static Queue<Data> q = new LinkedList<>();
	static Stack<Integer> stack = new Stack<>();

	public static void main(String[] args) throws FileNotFoundException  {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		Ti = new int[N];
		Pi = new int[N];
		b = new boolean[N];
		for (int i = 0; i < N; i++) {
			Ti[i] = sc.nextInt();
			Pi[i] = sc.nextInt();
		}

		dfs(0);
		System.out.println(result);
		result=0;
	}

	static public void dfs(int cnt) {
		if (cnt >= N) {
			for (int i = 0; i < stack.size(); i++) {
				temp += stack.get(i);
			}
//			System.out.print(temp+" ");
//			if (temp == 80) {
//				for (int i = 0; i < stack.size(); i++) {
//					System.out.print(stack.get(i) + " ");
//				}
//				System.out.println();
//			}
			if (temp > result)
				result = temp;
			temp = 0;
			return;
		}
		for (int i = cnt; i < N; i++) {
			if (b[i])
				continue;
			if (i + Ti[i] > N) {
				dfs(cnt + Ti[i]);
				continue;
			}
			stack.push(Pi[i]);
			b[i] = true;
//			System.out.println(Pi[i]);
			dfs(i + Ti[i]);
			b[i] = false;
			stack.pop();
		}
	}

	static class Data {
		int T;
		int P;

		public Data(int t, int p) {
			super();
			T = t;
			P = p;
		}
	}
}
