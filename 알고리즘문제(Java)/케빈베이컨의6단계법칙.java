package testProject;
/*
 * 백준 1389번 케빈베이컨의6단계법칙
 * 
 * SSAFY_구미_3기 백정훈 코드
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 케빈베이컨의6단계법칙 {
	static int N;
	static int M;
	static boolean[][] check;
	static int[][] value;
	static List<Integer>[] list;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 유저의 수
		M = sc.nextInt(); // 친구관계의 수
		check = new boolean[N + 1][N + 1];
		value = new int[N + 1][N + 1];
		list = new ArrayList[N + 1];
		int[] result = new int[N + 1];
		int index = 0;
		int min = 9876543;
		for (int i = 0; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			list[a].add(b);
			list[b].add(a);
		}
		for (int i = 1; i < N + 1; i++) {
			check[i][i] = true;
		}
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				value[i][j] = 987654;
			}
		}
		for (int i = 1; i < N + 1; i++) {
			dfs(i, i, 1);
		}
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (i == j)
					continue;
				result[i] += value[i][j];
			}
		}
		for (int i = 1; i < N + 1; i++) {
			if (min > result[i]) {
				min = result[i];
				index = i;
			}
		}
		System.out.println(index);
	}

	static void dfs(int root, int start, int depth) {
		for (int i = 0; i < list[start].size(); i++) {
			if (check[root][list[start].get(i)] && value[root][list[start].get(i)] < depth)
				continue;
			if (check[list[start].get(i)][root] && value[list[start].get(i)][root] < depth)
				continue;
			check[root][list[start].get(i)] = true;
			check[list[start].get(i)][root] = true;
			value[root][list[start].get(i)] = depth;
			value[list[start].get(i)][root] = depth;
			dfs(root, list[start].get(i), depth + 1);
		}
	}
}
