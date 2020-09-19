package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class 내전경기 {
	static int K;
	static int[] root;
	static int[] teaminfo;
	static int[][] mymap;
	static boolean[][] b;
	static int ans = 0;
	static int cnt = 1;
	static Map<String, Integer> map;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			map = new HashMap<>();
			K = sc.nextInt(); // 시너지 조합 횟수
			root = new int[103]; // 각각의 루트

			String[][] s = new String[K + 2][2];

			cnt = 1;
			for (int i = 1; i < K + 1; i++) {
				s[i][0] = sc.next();
				s[i][1] = sc.next();
				if (!map.containsKey(s[i][0]))
					map.put(s[i][0], cnt++);
				if (!map.containsKey(s[i][1]))
					map.put(s[i][1], cnt++);
			}
			mymap = new int[cnt + 1][cnt + 1];
			b = new boolean[cnt + 1][cnt + 1];
			teaminfo = new int[cnt + 1];
			ans = 1;
			for (int i = 1; i < K + 1; i++) {
				mymap[map.get(s[i][0])][map.get(s[i][1])] = 1;
				mymap[map.get(s[i][1])][map.get(s[i][0])] = 1;
			}
//			for (int i = 1; i < cnt; i++) {
//				for (int j = 1; j < cnt; j++) {
//					System.out.print(mymap[i][j]);
//				}
//				System.out.println();
//			}

			Queue<Integer> q = new LinkedList<Integer>();
			All: for (int j = 1; j < cnt; j++) {
				if (teaminfo[j] == 0) {
					teaminfo[j] = 1;
					q.add(j);
					while (!q.isEmpty()) {
						int me = q.poll();
						for (int i = 1; i < cnt; i++) {
							if (mymap[me][i] == 1) { // 시너지 O
								if (teaminfo[i] != 0 && teaminfo[i] == teaminfo[me]) { // 팀이 결정된 애네 ? 근데 나랑 이미 같은팀?? ..
									ans = 0;
//									System.out.print(i);
									break All;
								}
								if (teaminfo[i] == 0) {
									teaminfo[i] = teaminfo[me] == 1 ? 2 : 1;
									q.add(i);
								}
								}
						}
					}
				}
			}
			if (ans == 1) {
				System.out.println("#"+tc+" "+"Yes");
			}
			if (ans == 0) {
				System.out.println("#"+tc+" "+"No");
			}

		}
	}

}
