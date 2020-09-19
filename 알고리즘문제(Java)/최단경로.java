package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 최단경로 {
	static class Dij implements Comparable<Dij> {
		int y, val;

		public Dij(int y, int val) {
			this.y = y;
			this.val = val;
		}

		@Override
		public int compareTo(Dij o) {

			return Integer.compare(this.val, o.val);
		}

		@Override
		public String toString() {
			return val + "";
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		int K = sc.nextInt() - 1; // 시작 정점
		List<Dij>[] list = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			int c = sc.nextInt();
			list[a].add(new Dij(b, c)); // 가지고 있는데이터들 모음
		}
		PriorityQueue<Dij> pq = new PriorityQueue<>(); // 1~V개 데이터 key값 포함
		boolean[] check = new boolean[V];
		Dij[] D = new Dij[V];
		for (int i = 0; i < V; i++) {
			if (i == K) {
				D[i] = new Dij(i, 0);
				check[i] = true;
			} else {
				D[i] = new Dij(i, Integer.MAX_VALUE);
			}
			pq.add(D[i]);
		}

		while (!pq.isEmpty()) {
			Dij d = pq.poll();
			if (d.val == Integer.MAX_VALUE)
				break;
			for (Dij next : list[d.y]) {
				if (!check[next.y] && D[next.y].val > D[d.y].val + next.val) {
					D[next.y].val = D[d.y].val + next.val;
					pq.remove(D[next.y]);
					pq.add(D[next.y]);
				}
			}
			check[d.y] = true;
		}

		for (int i = 0; i < D.length; i++) {
			if (D[i].val == Integer.MAX_VALUE) {
				System.out.println("INF");
			} else {
				System.out.println(D[i]);
			}
		}
	}
}
