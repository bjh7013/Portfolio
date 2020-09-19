package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class 키순서플로이드와샬 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

//		int T = sc.nextInt();
//		for (int tc = 1; tc <= T; tc++) {
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] count = new int[N + 1];
		boolean[][] check = new boolean[N + 1][N + 1];
		boolean[][] map = new boolean[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			map[a][b] = true;
		}

		Queue<Integer> q = new LinkedList<>();
		int result = 0;
		for (int i = 1; i < N + 1; i++) {
			q.add(i);
			while (!q.isEmpty()) {
				int temp = q.poll();
				check[i][temp]=true;
				for (int j = 1; j < N + 1; j++) {
					if (map[temp][j]) {
						q.add(j);
					}
				}
			}
		}

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if(check[i][j]) {
					count[i]++;
					count[j]++;
				}
			}
		}
		for (int i = 1; i < N + 1; i++) {
			if(count[i]==N+1)
				result++;
		}
		
		System.out.println(result);

//		}
	}
}
