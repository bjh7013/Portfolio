package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 프린터큐 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 문서의 수
			int M = sc.nextInt(); // 내가 원하는 문서번호
			boolean[] check = new boolean[N];
			int[] doc = new int[N];
			int[] priority = new int[N];
			int count = 0;
			Queue<Integer> q = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				priority[i] = sc.nextInt();// n번째의 우선순위
				q.add(i); // n번째
			}

			All: while (!q.isEmpty()) {
				int num = q.poll();
				for (int i = 0; i < N; i++) {
					if (check[i])
						continue;
					if (priority[i] > priority[num]) {
						q.add(num);
						continue All;
					}
				}
				count++;
				check[num] = true;
				if(M==num) {
					break;
				}
			}
			System.out.println(count);
		}
	}
}
