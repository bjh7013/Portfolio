package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class 숨바꼭질3 {
	static int result;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 나의 위치
		int K = sc.nextInt(); // 상대의 위치
		boolean[] check = new boolean[200001];
		result = Integer.MAX_VALUE;
		PriorityQueue<Sumba> q = new PriorityQueue<>();
		q.add(new Sumba(N, 0));
		while (!q.isEmpty()) {
			Sumba sumba = q.poll();
			if (sumba.num == K) {
//				System.out.println(sumba.num);
				result = Math.min(result, sumba.cnt);
				break;
			}
			if (check[sumba.num])
				continue;
			check[sumba.num] = true;
			if (sumba.num < 100001)
				if (!check[sumba.num * 2])
					q.add(new Sumba(sumba.num * 2, sumba.cnt));
			if (sumba.num < K)
				if (!check[sumba.num + 1])
					q.add(new Sumba(sumba.num + 1, sumba.cnt + 1));
			if (sumba.num < 1)
				continue;
			if (!check[sumba.num - 1])
				q.add(new Sumba(sumba.num - 1, sumba.cnt + 1));
		}
		System.out.println(result);
	}

	static class Sumba implements Comparable<Sumba> {
		int num, cnt;

		public Sumba(int num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Sumba o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cnt, o.cnt);
		}
	}

}
