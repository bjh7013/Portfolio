package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class 우주신과의교감 {
	static int N;
	static int M;
	static List<Planet> list;
	static int[] root;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		list = new ArrayList<>();
		root = new int[N + 1];
		int cnt = 0;
		double result = 0;
		init();
		for (int i = 1; i < N+1; i++) {
			long x = sc.nextLong();
			long y = sc.nextLong();
			list.add(new Planet(x, y, i));
		}
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			union(a, b);
		}

		PriorityQueue<Dist> q = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
//				if (find(list.get(i).idx) == find(list.get(j).idx))
//					continue;
				q.add(new Dist(list.get(i).idx, list.get(j).idx, Math.sqrt(
						Math.pow(list.get(i).x - list.get(j).x, 2) + Math.pow(list.get(i).y - list.get(j).y, 2))));
			}
		}

		while (!q.isEmpty()) {
			Dist dist = q.poll();
			if (find(dist.idx1) == find(dist.idx2)) {
				continue;
			}
			union(dist.idx1, dist.idx2);
			result += dist.dist;
//			cnt++;
		}
		System.out.printf("%.2f",result);

	}

	static void union(int y, int x) {
		int tempy = find(y);
		int tempx = find(x);
		if (tempy != tempx)
			root[tempy] = tempx;
	}

	static int find(int x) {
		if (x == root[x])
			return x;
		else
			return root[x] = find(root[x]);
	}

	static void init() {
		for (int i = 0; i < N + 1; i++) {
			root[i] = i;
		}
	}

	static class Planet {
		long x, y;
		int idx;
		public Planet(long x, long y, int idx) {
			super();
			this.x = x;
			this.y = y;
			this.idx = idx;
		}

		

	}

	static class Dist implements Comparable<Dist> {
		int idx1, idx2;
		double dist;

		public Dist(int idx1, int idx2, double dist) {
			super();
			this.idx1 = idx1;
			this.idx2 = idx2;
			this.dist = dist;
		}

		@Override
		public int compareTo(Dist o) {
			return Double.compare(this.dist, o.dist);
		}

	}
}
