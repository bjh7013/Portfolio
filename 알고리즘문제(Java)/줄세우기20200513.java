package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class 줄세우기20200513 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] cnt = new int[N + 1];
		StringBuffer sb = new StringBuffer("");
		List<Integer>[] list = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			list[a].add(b);
			cnt[b]++;
		}
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i < N + 1; i++) {
			if (cnt[i] == 0)
				q.add(i);
		}

		while (!q.isEmpty()) {
			int val = q.poll();
			sb.append(val + " ");
			for (int i = 0; i < list[val].size(); i++) {
				if (--cnt[list[val].get(i)] == 0) {
					q.add(list[val].get(i));
				}
			}
		}

		System.out.println(sb);
	}
}
