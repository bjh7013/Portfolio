package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class DfsBfs {
	static int[][] map;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 정점
		int M = sc.nextInt(); // 간선
		int V = sc.nextInt(); // 정점번호
		int tempx, tempy;
		map = new int[N + 1][N + 1];
		b = new boolean[N + 1];
		for (int i = 0; i < M; i++) {
			tempy = sc.nextInt();
			tempx = sc.nextInt();
			map[tempy][tempx] = 1;
			map[tempx][tempy] = 1;
		}
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();

		}

		dfs(N, V);
		System.out.println();
		bfs(N, V);
	}

	static boolean[] b;

	public static void dfs(int N, int start) {
		int cnt = 0;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(start);
		while (!stack.isEmpty()) {
			start = stack.pop();
			for (int i = 1; i < N + 1; i++) {
				if (b[i])
					continue;
				if (map[start][i] == 1) {
					stack.push(i);
					b[start] = true;
					break;
				}
			}
			System.out.print(start + " ");

		}
		for (int i = 1; i < N + 1; i++) {
			b[i] = false;
		}

	}

	public static void bfs(int N, int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		while (!q.isEmpty()) {
			start = q.poll();
			b[start] = true;
			for (int i = 1; i < N + 1; i++) {
				if (b[i])
					continue;
				if (map[start][i] == 1) {
					q.offer(i);
					b[i] = true;
				}
			}
			System.out.print(start + " ");
		}
		for (int i = 1; i < N + 1; i++) {
			b[i] = false;
		}
	}
}
