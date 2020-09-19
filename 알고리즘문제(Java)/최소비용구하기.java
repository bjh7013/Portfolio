package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 최소비용구하기 {
	static class Dij implements Comparable<Dij>{
		int y, val;

		public Dij(int y, int val) {
			super();
			this.y = y;
			this.val = val;
		}

		@Override
		public int compareTo(Dij o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.val, o.val);
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		List<Dij>[] list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			list[sc.nextInt() - 1].add(new Dij(sc.nextInt() - 1, sc.nextInt()));
		}
		int start = sc.nextInt() - 1;
		int end = sc.nextInt() - 1;
		boolean[] check = new boolean[N];
		PriorityQueue<Dij> pq = new PriorityQueue<>();
		Dij[] data = new Dij[N];
		for (int i = 0; i < N; i++) {
			if (i == start) {
				data[i] = new Dij(i, 0);
				check[i] = true;
			} else {
				data[i] = new Dij(i, Integer.MAX_VALUE);
			}
			pq.add(data[i]);
		}
		while (!pq.isEmpty()) {
			Dij dij = pq.poll();
			if (dij.val == Integer.MAX_VALUE)
				break;
			for (Dij next : list[dij.y]) {
				if (!check[next.y] && data[next.y].val > data[dij.y].val + next.val) {
					data[next.y].val = data[dij.y].val + next.val;
					pq.remove(data[next.y]);
					pq.add(data[next.y]);
				}
			}
			check[dij.y] = true;
		}
		System.out.println(data[end].val);

	}
}
