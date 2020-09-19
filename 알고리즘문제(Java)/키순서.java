package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class 키순서 {
	static int N;
	static int M;
	static int[][] map;
	static boolean[] check;
	static int[] count;
	static int result;
	static int cnt;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		check = new boolean[N + 1];
		count = new int[N + 1];
		map = new int[N + 1][N + 1];
		
		for (int i = 1; i < M + 1; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			map[a][b] = 1;
		}

		result = 0;
		for (int i = 1; i < N + 1; i++) {
			cnt = 0;
//			나보다 큰 사람 세기
			check = new boolean[N + 1];
			dfs(i);
//			나보다 작은 사람 세기
			check = new boolean[N + 1];
			dfs2(i);
			
			if (cnt == N - 1) {
				result++;
			}
		}

		System.out.println(result);
	}

	static void dfs(int y) {
		check[y]=true;
		for (int i = 1; i < N + 1; i++) {
			if(check[i])
				continue;
			if(map[y][i]==1) {
				cnt++;
				dfs(i);
			}
		}
	}
	
	static void dfs2(int y) {
		check[y]=true;
		for (int i = 1; i < N + 1; i++) {
			if(check[i])
				continue;
			if(map[i][y]==1) {
				cnt++;
				dfs2(i);
			}
		}
	}
}
