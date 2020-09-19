package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class 나무재테크 {

	static class Tree implements Comparable<Tree> {
		int r, c, age;

		public Tree(int r, int c, int age) {
			super();
			this.r = r;
			this.c = c;
			this.age = age;
		}

		public Tree() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public int compareTo(Tree o) {
			if (this.age > o.age) {
				return 1;
			} else {

				return -1;
			}
		}

	}

	static int[][] s2d2;
//	양분들 각 칸의 양
	static int[][] map;
//	현재 살아 있는 모든 나무들
	static PriorityQueue<Tree> trees = new PriorityQueue<>();
//	봄에 사용할 큐
	static Queue<Tree> dead = new LinkedList<>();
//	여름에 사용할 큐
	static Queue<Tree> alive = new LinkedList<>();
//	봄에 사용할 큐
	static int N;
	static int M;
	static int K;
	static int dy[] = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int dx[] = { 0, 0, -1, 1, -1, 1, -1, 1 };

	public static void main(String[] args) throws FileNotFoundException  {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		map = new int[N + 2][N + 2];
		s2d2 = new int[N + 2][N + 2];
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				int val = sc.nextInt();
				map[i][j] = 5;
				s2d2[i][j] = val;
			}
		}

		for (int i = 0; i < M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int z = sc.nextInt();
			trees.add(new Tree(x, y, z));
		}

		for (int i = 0; i < K; i++) {
			spring();
			summer();
			autumn();
			winter();
		}
		System.out.println(trees.size());
	}

	static void spring() {
//		trees 큐를 하나씩 꺼내면서
//		나무의 위치에 양분이 충분하면 나무의 나이만큼 양분을 없애고 한 살 늘리고 alive 큐로 삽입
//		아니라면 dead큐로 삽임
		while (!trees.isEmpty()) {
			Tree t = trees.poll();
			if (map[t.r][t.c] >= t.age) {
				map[t.r][t.c] -= t.age;
				alive.add(new Tree(t.r, t.c, t.age + 1));
			} else {
				dead.add(new Tree(t.r, t.c, t.age));
			}
		}
	}

	static void summer() {
//	dead큐를 모두 돌면서, 나무의 나이의 반만큼을
		while (!dead.isEmpty()) {
			Tree t = dead.poll();
			map[t.r][t.c] += (t.age / 2);
		}
	}

	static void autumn() {
//	나무의 나이가 5의 배수 일 때 인접한 8칸에 나이1나무 번식
		while (!alive.isEmpty()) {
			Tree t = alive.poll();
			if (t.age % 5 == 0) {
				for (int i = 0; i < 8; i++) {
					if (t.r + dy[i] < 1 || t.r + dy[i] > N || t.c + dx[i] < 1 || t.c + dx[i] > N)
						continue;
					trees.add(new Tree(t.r + dy[i], t.c + dx[i], 1));
				}
			}

			trees.add(new Tree(t.r, t.c, t.age));

		}
	}

	static void winter() {
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				map[i][j] += s2d2[i][j];
			}
		}
	}
}