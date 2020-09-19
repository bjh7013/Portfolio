package testProject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 행성터널 {
	static int[] root;
	static int[] memo;
	static int N;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);

		N = Integer.parseInt(st.nextToken());
		Turnel[] list = new Turnel[N];
		PriorityQueue<Sol> truelist = new PriorityQueue<>();
		root = new int[N + 1];
		memo = new int[N + 1];

		init();
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			st = new StringTokenizer(s);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			list[i] = new Turnel(i, x, y, z);
		}
		Arrays.sort(list, new Comparator<Turnel>() {

			@Override
			public int compare(Turnel o1, Turnel o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1.x, o2.x);
			}
		});
		for (int i = 1; i < N; i++) {
			truelist.add(new Sol(list[i - 1].idx, list[i].idx, Math.abs(list[i - 1].x - list[i].x)));
		}
		Arrays.sort(list, new Comparator<Turnel>() {

			@Override
			public int compare(Turnel o1, Turnel o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1.y, o2.y);
			}
		});
		for (int i = 1; i < N; i++) {
			truelist.add(new Sol(list[i - 1].idx, list[i].idx, Math.abs(list[i - 1].y - list[i].y)));
		}
		Arrays.sort(list, new Comparator<Turnel>() {

			@Override
			public int compare(Turnel o1, Turnel o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1.z, o2.z);
			}
		});
		for (int i = 1; i < N; i++) {
			truelist.add(new Sol(list[i - 1].idx, list[i].idx, Math.abs(list[i - 1].z - list[i].z)));
		}
		int cnt = 0;
		int result = 0;

		while (cnt != N - 1) {
			Sol sol = truelist.poll();
			if (find(sol.idx1) == find(sol.idx2)) {
				continue;
			}
			union(sol.idx1, sol.idx2);
			result += sol.dist;
			cnt++;
		}
		System.out.println(result);
	}

	static void init() {
		for (int i = 0; i < N + 1; i++) {
			root[i] = i;
		}
	}

	static void union(int y, int x) {
		int tempy = find(y);
		int tempx = find(x);
		if (tempy != tempx)
			root[tempy] = tempx;
	}

	static int find(int x) {
		if (root[x] == x)
			return root[x] = x;
		else {
			return root[x] = find(root[x]);
		}
	}

	static class Turnel {
		int idx, x, y, z;

		public Turnel(int idx, int x, int y, int z) {
			super();
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.z = z;
		}

	}

	static class Sol implements Comparable<Sol> {
		int idx1, idx2, dist;

		public Sol(int idx1, int idx2, int dist) {
			super();
			this.idx1 = idx1;
			this.idx2 = idx2;
			this.dist = dist;
		}

		@Override
		public int compareTo(Sol o) {

			return Integer.compare(this.dist, o.dist);
		}

	}
}
