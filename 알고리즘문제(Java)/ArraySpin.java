package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.Stack;

public class ArraySpin {
	static int[][] map;
	static int[][] realmap;
	static boolean[] b;
	static int[] r;
	static int[] c;
	static int[] s;
	static int R;
	static int C;
	static int K;
	static int sol = 987654123;
	static Stack<Integer> stack = new Stack<>();

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		K = sc.nextInt();
		map = new int[R + 2][C + 2];
		realmap = new int[R + 2][C + 2];
		b = new boolean[K + 2];
		r = new int[K];
		c = new int[K];
		s = new int[K];

		for (int i = 1; i < R + 1; i++) {
			for (int j = 1; j < C + 1; j++) {
				realmap[i][j] = sc.nextInt();
			}
		}
		for (int i = 1; i < R + 1; i++) {
			map[i] = realmap[i].clone();
		}
		for (int i = 0; i < K; i++) {
			r[i] = sc.nextInt();
			c[i] = sc.nextInt();
			s[i] = sc.nextInt();
		}

		p(0);

		int tempsol = 0;

		System.out.println(sol);
	}

	static int tempcnt = 0;

	public static void dfs(int cnt, int val) {
		if (cnt >= K)
			return;
		if (val == s[cnt])
			return;

		int temp = map[r[cnt] - s[cnt] + val][c[cnt] - s[cnt] + val];

		for (int i = r[cnt] - s[cnt] + val; i <= r[cnt] + s[cnt] - val; i++) {
			for (int j = c[cnt] - s[cnt] + val; j <= c[cnt] - s[cnt] + val; j++) {
				map[i][j] = map[i + 1][j];
			}
			tempcnt = 1;
		}

		for (int i = r[cnt] + s[cnt] - val; i <= r[cnt] + s[cnt] - val; i++) {
			for (int j = c[cnt] - s[cnt] + val; j <= c[cnt] + s[cnt] - val; j++) {
				map[i][j] = map[i][j + 1];
			}
			tempcnt = 1;
		}

		for (int i = r[cnt] + s[cnt] - val; i >= r[cnt] - s[cnt] + val; i--) {
			for (int j = c[cnt] + s[cnt] - val; j <= c[cnt] + s[cnt] - val; j++) {
				map[i][j] = map[i - 1][j];
			}
			tempcnt = 1;
		}

		for (int i = r[cnt] - s[cnt] + val; i >= r[cnt] - s[cnt] + val; i--) {
			for (int j = c[cnt] + s[cnt] - val; j >= c[cnt] - s[cnt] + val + 1; j--) {
				map[i][j] = map[i][j - 1];
				if (j == c[cnt] - s[cnt] + val + 1)
					map[i][j] = temp;
			}
			tempcnt = 1;
		}

		dfs(cnt, val + 1);
	}

	public static void p(int cnt) {
		if (cnt == K) {
			for (int i = 0; i < stack.size(); i++) {
//				System.out.print(stack.get(i)-1);
				dfs(stack.get(i)-1, 0);
			}
//			System.out.println();
			int tempsol = 0;

			for (int i = 1; i < R + 1; i++) {
				tempsol = 0;
				for (int j = 1; j < C + 1; j++) {
					tempsol += map[i][j];
				}
				sol = Math.min(sol, tempsol);
			}
			for (int j = 1; j < R + 1; j++) {
				map[j] = realmap[j].clone();
			}
			return;
		}
		for (int i = 1; i < K + 1; i++) {
			if (b[i])
				continue;
			b[i] = true;
			stack.push(i);
			p(cnt + 1);
			stack.pop();
			b[i] = false;
		}
	}
}
