package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 하나로 {
	static int N;
	static double E;
	static boolean[] b;
	static long[] X;
	static long[] Y;
	static double[][] Distance;
	static int[] root;
	static int[] val;
	static double result;
	static Island[] island;
	static ArrayList<Island> list = new ArrayList<>();
	static PriorityQueue<Island> q = new PriorityQueue<>();
//	static List<Island> q = new LinkedList<>();

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		double start = System.currentTimeMillis();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			result = 0;
			island = new Island[N + 1];
			list.clear();
			q.clear();
			X = new long[N + 1];
			Y = new long[N + 1];
			root = new int[1001];
			int cnt = 0;
			int[] check = new int[1001];
			for (int i = 0; i <= N; i++) {
				root[i] = i;
			}
			Distance = new double[N + 1][N + 1];
			val = new int[N + 1];
			b = new boolean[N + 1];
			for (int i = 1; i < N + 1; i++) {
				X[i] = sc.nextInt();
			}
			for (int i = 1; i < N + 1; i++) {
				Y[i] = sc.nextInt();
			}
			E = sc.nextDouble();
//			if(tc!=3)
//				continue;
//			for (int i = 1; i < N + 1; i++) {
//				island[i]=new Island(Y[i],X[i],0.0);
//			}
			for (int i = 1; i < N + 1; i++) {	
				for (int j = i + 1; j < N + 1; j++) {
					double aa = ((X[i] - X[j]) * (X[i] - X[j]) + (Y[i] - Y[j]) * (Y[i] - Y[j])) * E;
					q.offer(new Island(i, j, aa));
				}
			}
//			Collections.sort(list, new Comparator<Island>() {
//
//				@Override
//				public int compare(Island o1, Island o2) {
//					if (o1.dis < o2.dis) {
//						return -1;
//					} else if (o1.dis > o2.dis) {
//						return 1;
//					}
//					return 0;
//				}
//			});
			int qsize=q.size();
			for (int k = 0; k < qsize; k++) {
//				System.out.println(q.peek().dis);
				if (!checkunion(q.peek().i, q.peek().j)) {
					union(q.peek().j, q.peek().i);
					result += q.peek().dis;
					cnt++;
				}
				q.remove();
				if (cnt == N - 1)
					break;
			}

			System.out.println("#" + tc + " " + Math.round(result));
		}
//		System.out.println(System.currentTimeMillis() - start);
	}

	static class Island implements Comparable<Island> {
		int i, j;
		double dis;

		public Island(int i, int j, double dis) {
			super();
			this.i = i;
			this.j = j;
			this.dis = dis;
		}

		@Override
		public int compareTo(Island you) {
			if (this.dis < you.dis) {
				return -1;
			} else if (this.dis > you.dis) {
				return 1;
			}
			return 0;
		}

	}

	static void union(int i, int j) {
		int tempi = find(i);
		int tempj = find(j);
		if (tempi != tempj) {
			root[tempj] = tempi;
		}

	}

	static boolean checkunion(int i, int j) {
		int tempi = find(i);
		int tempj = find(j);
		if (tempi != tempj) {
			return false;
		} else {
			return true;
		}

	}

	static int find(int i) {
		if (root[i] == i) {
			return i;
		} else {
			return root[i] = find(root[i]);
		}
	}

}
