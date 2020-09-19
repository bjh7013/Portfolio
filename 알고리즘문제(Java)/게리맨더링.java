package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class 게리맨더링 {
	static int N;
	static int result;
	static int[] pop;
	static int[] root;
	static boolean[] check;
	static boolean[] resultc;
	static boolean[] b;
	static List<Integer>[] list;
	static Stack<Integer> stack;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		pop = new int[N + 1];
		root = new int[N + 1];
		list = new ArrayList[N + 1];
		check = new boolean[N + 1];
		resultc = new boolean[N + 1];
		b = new boolean[N + 1];
		result = Integer.MAX_VALUE;
		stack = new Stack<>();
		for (int i = 0; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 1; i < N + 1; i++) {
			pop[i] = sc.nextInt();
		}
		for (int i = 1; i < N + 1; i++) {
			int num = sc.nextInt();
			for (int j = 0; j < num; j++) {
				list[i].add(sc.nextInt());
			}
		}
		setRoot();
		powerset(1, 0);
		if (result == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
	}

	static void powerset(int y, int cnt) {
		int temp1 = 0;
		int temp2 = 0;
		setRoot();
		b = new boolean[N + 1];
		resultc = new boolean[N + 1];
		int tempcnt = 0;
		for (int i = 0; i < stack.size(); i++) {
			b[stack.get(i)] = true;
			tempcnt++;
		}
		if (tempcnt == 1) {
			for (int i = 1; i < N + 1; i++) {
				if (b[i])
					resultc[i] = true;
			}
		}
		if (tempcnt == 5) {
			for (int i = 1; i < N + 1; i++) {
				if (!b[i])
					resultc[i] = true;
			}
		}
		for (int i = 1; i < N + 1; i++) {
			if (b[i]) {
				for (int j = 0; j < list[i].size(); j++) {
					if (b[list[i].get(j)]) {
						union(i, list[i].get(j));
						resultc[list[i].get(j)] = true;
					}
				}
			} else {
				for (int j = 0; j < list[i].size(); j++) {
					if (!b[list[i].get(j)]) {
						union(i, list[i].get(j));
						resultc[list[i].get(j)] = true;
					}
				}
			}
		}
		Arrays.sort(root);
		int rootcnt = 0;

		for (int i = 1; i < N; i++) {
			if (root[i] != root[i + 1]) {
				rootcnt++;
			}
		}
		if (rootcnt == 1) {
			for (int i = 1; i < N + 1; i++) {
				if (i == N) {
					for (int j = 1; j < N + 1; j++) {
						if (b[j]) {
							temp1 += pop[j];
						} else {
							temp2 += pop[j];
						}
					}
					result = Math.min(result, Math.abs(temp1 - temp2));
				}
			}
		}
		for (int i = 1; i < N + 1; i++) {
			if (check[i])
				continue;
			for (int j = 1; j < i + 1; j++) {
				check[j] = true;
			}
			stack.push(i);
			powerset(y, cnt + 1);
			for (int j = 1; j < i + 1; j++) {
				check[j] = false;
			}
			stack.pop();
		}
	}

	static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if (px != py) {
			root[py] = px;
		}
	}

	static int find(int x) {
		if (x == root[x])
			return x;
		return root[x] = find(root[x]);
	}

	static void setRoot() {
		for (int i = 1; i < N + 1; i++) {
			root[i] = i;
		}
	}
}
