package testProject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

//백준 1238
public class 파티 {
	static class Dij implements Comparable<Dij> {
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

	public static void main(String[] args) throws IOException  {
		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s=br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken())-1;
		List<Dij>[] list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			s=br.readLine();
			st = new StringTokenizer(s);
			list[Integer.parseInt(st.nextToken())-1].add(new Dij(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())));
		}
		Dij[] data = new Dij[N];
		int[][] result = new int[2][N];
		int cnt = 0;
		for (int j = 0; j < N; j++, cnt++) {
			PriorityQueue<Dij> pq = new PriorityQueue<>();
			boolean[] check = new boolean[N];
			for (int i = 0; i < N; i++) {
				if (i == cnt) {
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
				if(j!=X && check[X]) {
					break;
				}
			}
			if (j == X) {
				for (int i = 0; i < N; i++) {
					if (data[i].val == Integer.MAX_VALUE) {
						result[1][i] = -1;
						continue;
					}
					result[1][i] = data[i].val;// 목적지에서 i마을까지 되돌아가는경로
				}
			}
			if (data[X].val == Integer.MAX_VALUE) {
				result[0][j] = -1;
			} else {
				result[0][j] = data[X].val;// 시작점에서 목적지X까지 경로
			}
		}
		int val = -1;
		boolean[] b = new boolean[N];
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if (i == j)
					continue;
				if (b[j])
					continue;
				if (result[0][j] == -1 || result[1][j] == -1) {
					b[j] = true;
					continue;
				}
				val = Math.max(val, result[0][j] + result[1][j]);
			}
		}
		System.out.println(val);

	}
}
