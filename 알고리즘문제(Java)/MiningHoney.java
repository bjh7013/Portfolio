package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class MiningHoney {
	static int N;
	static int M;
	static int result;
	static int result2;
	static int sol;
	static int Maxhoney;
	static int[][] map;
	static int[] val;
	static int[] value1;
	static int[] value2;
	static boolean[][] b;
	static LinkedList<Integer> list = new LinkedList<>();
	static Stack<Integer> stack = new Stack<>();
	static Stack<Integer> s = new Stack<>();

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			result = 0;
			result2 = 0;
			sol = 0;
			Maxhoney = sc.nextInt();
			val = new int[M + 1];
			value1 = new int[M + 1];
			value2 = new int[M + 1];
			map = new int[N + 2][N + 2];
			b = new boolean[N + 2][N + 2];
			v = new boolean[M + 2];

			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					map[i][j] = sc.nextInt();
				}
			}
//			if (tc == T) {
			dfs(0);
			System.out.println("#" + tc + " " + sol);
//			}
		}
	}

	public static void dfs(int cnt) {
		if (cnt == 2) {
			int[] arr = new int[M];
			int[] arr2 = new int[M];
			for (int i = 0; i < stack.size() / 2; i++) {
				arr[i] = stack.get(i);
			}
			result = 0;
			dfs2(arr, 0);
			v = new boolean[M + 2];
			for (int i = stack.size() / 2; i < stack.size(); i++) {
				arr2[i - stack.size() / 2] = stack.get(i);
			}
			result2 = 0;
//			System.out.println();
			dfs3(arr2, 0);
			sol = Math.max(sol, result + result2);
			v = new boolean[M + 2];
//			for (int i = 0; i < stack.size(); i++) {
//				System.out.print(stack.get(i) + " ");
//			}
//			System.out.println();
			return;
		}
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (b[i][j])
					continue;
				if (j + M - 1 > N)
					continue;
				for (int k = j; k < j + M; k++) {
					stack.push(map[i][k]);
					if (cnt == 1) {
						for (int m = 0; m < i + 1; m++) {
							for (int l = 0; l < k; l++) {
								b[m][l] = true;
							}
						}
					}
					if (cnt == 0) {
						for (int m = 0; m < i + 1; m++) {
							for (int l = 0; l < k + 1; l++) {
								b[m][l] = true;
							}
						}
					}
				}
				dfs(cnt + 1);
				for (int k = j; k < j + M; k++) {
					if (cnt == 1) {
						for (int m = 0; m < i + 1; m++) {
							for (int l = 0; l < k; l++) {
								b[m][l] = false;
							}
						}
					}
					if (cnt == 0) {
						for (int m = 0; m < i + 1; m++) {
							for (int l = 0; l < k + 1; l++) {
								b[m][l] = false;
							}
						}
					}
					stack.pop();
				}
			}
		}
	}

	static boolean[] v;

	public static void dfs2(int[] arr, int cnt) {
		if (cnt == M) {
			return;
		}

		for (int i = 0; i < M; i++) {
			if (v[i])
				continue;
			int temp = 0;
			for (int j = 0; j <= i; j++) {
				v[j] = true;
			}
			s.push(arr[i]);
			for (int j = 0; j < s.size(); j++) {
				temp += s.get(j);
			}
//			for (int j = 0; j < s.size(); j++) {
//				System.out.print(s.get(j) + " ");
//			}
//			System.out.println();
			int tempresult = 0;
			if (temp <= Maxhoney) {

				for (int j = 0; j < s.size(); j++) {
					tempresult += s.get(j) * s.get(j);
				}
				result = Math.max(result, tempresult);
			}
			dfs2(arr, cnt + 1);
			for (int j = 0; j <= i; j++) {
				v[j] = false;
			}
			s.pop();
		}
	}

	public static void dfs3(int[] arr, int cnt) {
		if (cnt == M) {
			return;
		}

		for (int i = 0; i < M; i++) {
			if (v[i])
				continue;
			int temp = 0;
			for (int j = 0; j <= i; j++) {
				v[j] = true;
			}
			s.push(arr[i]);
			for (int j = 0; j < s.size(); j++) {
				temp += s.get(j);
			}
//			for (int j = 0; j < s.size(); j++) {
//				System.out.print(s.get(j) + " ");
//			}
//			System.out.println();
			int tempresult = 0;
			if (temp <= Maxhoney) {

				for (int j = 0; j < s.size(); j++) {
					tempresult += s.get(j) * s.get(j);
				}
				result2 = Math.max(result2, tempresult);
			}
			dfs3(arr, cnt + 1);
			for (int j = 0; j <= i; j++) {
				v[j] = false;
			}
			s.pop();
		}
	}

}
