package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class 사탕나눠주기 {
	static int N;
	static int M;
	static List<Integer>[] list;
	static int result = 0;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 어린이 수
			M = sc.nextInt(); // 사탕 수
			int[] child = new int[N];
			list = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				list[i] = new ArrayList<>();
			}
			for (int i = 0; i < N; i++) {
				int count = sc.nextInt();
				for (int j = 0; j < count; j++) {
					int a = sc.nextInt();
					if (list[i].contains(a))
						continue;
					list[i].add(a);
				}
			}
			result = 0;
			combination(0);

			System.out.println(result);

		}
	}

	static void combination(int cnt) {
		if (cnt == N) {
			int tempcnt=0;
			 List<Integer>[] list2 = new ArrayList[N];
			 for (int i = 0; i < N; i++) {
					list2[i] = new ArrayList<>();
				}
			 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < list[i].size(); j++) {
					if(list2[i].contains(list[i].get(j)))
						continue;
					list2[i].add(list[i].get(j));
				}
			}
			for (int i = 0; i < N; i++) {
				tempcnt += list2[i].size();
			}
			result=Math.max(result, tempcnt);
			return;
		}
		for (int i = cnt + 1; i < M + 1; i++) {
			list[cnt].add(i);
			combination(cnt + 1);
			list[cnt].remove(list[cnt].size() - 1);
		}
	}
}
