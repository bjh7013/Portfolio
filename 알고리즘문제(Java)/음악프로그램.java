package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class 음악프로그램 {
	static int N;
	static int M;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		StringBuilder sb = new StringBuilder("");
		int cnt=0;
		int[] count = new int[N + 1];
		String a;
		List<Integer>[] list = new ArrayList[N + 1];

		for (int i = 0; i < N + 1; i++) {
			list[i] = new ArrayList<Integer>();
		}

		for (int i = 1; i < M + 1; i++) {
			int number = sc.nextInt();
			int[] num = new int[number + 1];
			for (int j = 1; j < number + 1; j++) {
				num[j] = sc.nextInt();
			}
			for (int j = 1; j < number; j++) {
				for (int k = j + 1; k < number + 1; k++) {
					list[num[j]].add(num[k]);
					count[num[k]]++;
				}
			}
		}
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i < N + 1; i++) {
			if (count[i] == 0)
				q.add(i);
		}

		while (!q.isEmpty()) {
			int val = q.poll();
			sb.append(val+"\n");
			for (int i = 0; i < list[val].size(); i++) {
				if(--count[list[val].get(i)]==0)
					q.add(list[val].get(i));
			}
			cnt++;
		}
		if(cnt==N) {
			System.out.println(sb);
		}else {
			System.out.println(0); 
		}

	}
}
