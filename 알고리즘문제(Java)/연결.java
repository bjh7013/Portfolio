package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class 연결 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int M = sc.nextInt();
		int N = sc.nextInt();
		Loc[] AB = new Loc[4]; // A1,A2,B1,B2

		int[][] checkA = new int[N + 2][M + 2];
		int[][] checkB = new int[N + 2][M + 2];
		boolean[][] checkAPath = new boolean[N + 2][M + 2];
		boolean[][] checkBPath = new boolean[N + 2][M + 2];

		for (int i = 0; i < 4; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			AB[i] = new Loc(y, x, 0);
		}
		Queue<Loc> q = new LinkedList<>();
		q.add(new Loc(AB[0].y, AB[0].x, AB[0].cnt));
		Loc tempA1 = new Loc(0, 0, 0);
		Loc tempA2 = new Loc(0, 0, 0);
		Loc tempB1 = new Loc(0, 0, 0);
		Loc tempB2 = new Loc(0, 0, 0);
//		System.out.println(AB[3].y+" "+AB[3].x);
		while (!q.isEmpty()) { // A최단경로
			Loc loc = q.poll();
//			System.out.println(loc.y+" "+loc.x);
			if (loc.y == AB[1].y && loc.x == AB[1].x) {
				tempA1.x = loc.x;
				tempA1.y = loc.y;
				tempA1.cnt = loc.cnt;
				break;
			}
			if ((loc.y == AB[2].y && loc.x == AB[2].x) && (loc.y == AB[3].y && loc.x == AB[3].x))
				continue;
			if (checkA[loc.y][loc.x] >= 1)
				continue;
			checkA[loc.y][loc.x] = loc.cnt + 1;
			for (int k = 0; k < 4; k++) {
				int tempy = loc.y + dy[k];
				int tempx = loc.x + dx[k];
				if (tempy < 0 || tempy > N || tempx < 0 || tempx > M)
					continue;
				if ((tempy == AB[2].y && tempx == AB[2].x) || (tempy == AB[3].y && tempx == AB[3].x))
					continue;
				if (checkA[tempy][tempx] >= 1)
					continue;
				q.add(new Loc(tempy, tempx, loc.cnt + 1));
			}
		}

		q.clear();
		q.add(tempA1);
		while (!q.isEmpty()) { // 지나간길 찾기
			Loc loc = q.poll();
			if (loc.cnt == -1)
				break;
			if (checkAPath[loc.y][loc.x])
				continue;
			checkAPath[loc.y][loc.x] = true;
			for (int k = 0; k < 4; k++) {
				int tempy = loc.y + dy[k];
				int tempx = loc.x + dx[k];
				if (tempy < 0 || tempy > N || tempx < 0 || tempx > M)
					continue;
				if (checkAPath[tempy][tempx])
					continue;
				if (checkA[tempy][tempx] == loc.cnt) {
					q.add(new Loc(tempy, tempx, loc.cnt - 1));
					break;
				}
			}
		}
//		for (int i = 0; i < N + 1; i++) {
//			for (int j = 0; j < M + 1; j++) {
//				System.out.print(checkAPath[i][j] + " ");
//			}
//			System.out.println();
//		}
//		for (int i = 0; i < N + 1; i++) {
//			for (int j = 0; j < M + 1; j++) {
//				System.out.print(checkA[i][j] + " ");
//			}
//			System.out.println();
//		}

		q.clear();
		q.add(new Loc(AB[2].y, AB[2].x, AB[2].cnt));
		while (!q.isEmpty()) { // A이후 B최단경로
			Loc loc = q.poll();
			if (loc.y == AB[3].y && loc.x == AB[3].x) {
				tempB1.x = loc.x;
				tempB1.y = loc.y;
				tempB1.cnt = loc.cnt;
				break;
			}
			if (checkB[loc.y][loc.x] >= 1)
				continue;
			checkB[loc.y][loc.x] = loc.cnt + 1;
			for (int k = 0; k < 4; k++) {
				int tempy = loc.y + dy[k];
				int tempx = loc.x + dx[k];
				if (tempy < 0 || tempy > N || tempx < 0 || tempx > M)
					continue;
				if (checkAPath[tempy][tempx])
					continue;
				if (checkB[tempy][tempx] >= 1)
					continue;
				q.add(new Loc(tempy, tempx, loc.cnt + 1));
			}
		}

		checkA = new int[N + 2][M + 2];
		checkB = new int[N + 2][M + 2];
		checkAPath = new boolean[N + 2][M + 2];
		checkBPath = new boolean[N + 2][M + 2];

		q.clear();
		q.add(new Loc(AB[2].y, AB[2].x, AB[2].cnt));
		while (!q.isEmpty()) { // B최단경로
			Loc loc = q.poll();
			if (loc.y == AB[3].y && loc.x == AB[3].x) {
				tempB2.x = loc.x;
				tempB2.y = loc.y;
				tempB2.cnt = loc.cnt;
				break;
			}
			if ((loc.y == AB[0].y && loc.x == AB[0].x) || (loc.y == AB[1].y && loc.x == AB[1].x))
				continue;
			if (checkB[loc.y][loc.x] >= 1)
				continue;
			checkB[loc.y][loc.x] = loc.cnt + 1;
			for (int k = 0; k < 4; k++) {
				int tempy = loc.y + dy[k];
				int tempx = loc.x + dx[k];
				if (tempy < 0 || tempy > N || tempx < 0 || tempx > M)
					continue;
				if ((tempy == AB[0].y && tempx == AB[0].x) || (tempy == AB[1].y && tempx == AB[1].x))
					continue;
				if (checkB[tempy][tempx] >= 1)
					continue;
				q.add(new Loc(tempy, tempx, loc.cnt + 1));
			}
		}

		q.clear();
		q.add(tempB2);
		while (!q.isEmpty()) { // 지나간길 찾기
			Loc loc = q.poll();
			if (loc.cnt == -1)
				break;
			if (checkBPath[loc.y][loc.x])
				continue;
			checkBPath[loc.y][loc.x] = true;
			for (int k = 0; k < 4; k++) {
				int tempy = loc.y + dy[k];
				int tempx = loc.x + dx[k];
				if (tempy < 0 || tempy > N || tempx < 0 || tempx > M)
					continue;
				if (checkBPath[tempy][tempx])
					continue;
				if (checkB[tempy][tempx] == loc.cnt) {
					q.add(new Loc(tempy, tempx, loc.cnt - 1));
					break;
				}
			}
		}

		q.clear();
		q.add(new Loc(AB[0].y, AB[0].x, AB[0].cnt));
		while (!q.isEmpty()) { // B이후 A최단경로
			Loc loc = q.poll();
			if (loc.y == AB[1].y && loc.x == AB[1].x) {
				tempA2.x = loc.x;
				tempA2.y = loc.y;
				tempA2.cnt = loc.cnt;
				break;
			}
			if (checkA[loc.y][loc.x] >= 1)
				continue;
			checkA[loc.y][loc.x] = loc.cnt + 1;
			for (int k = 0; k < 4; k++) {
				int tempy = loc.y + dy[k];
				int tempx = loc.x + dx[k];
				if (tempy < 0 || tempy > N || tempx < 0 || tempx > M)
					continue;
				if (checkBPath[tempy][tempx])
					continue;
				if (checkA[tempy][tempx] >= 1)
					continue;
				q.add(new Loc(tempy, tempx, loc.cnt + 1));
			}
		}
//		System.out.println(tempA1.cnt);
//		System.out.println(tempB1.cnt);
//		System.out.println(tempA2.cnt);
//		System.out.println(tempB2.cnt);

		int solcnt = 0;
		if (tempA1.cnt == 0) {
			solcnt++;
		}
		if (tempA2.cnt == 0) {
			solcnt++;
		}
		if (tempB1.cnt == 0) {
			solcnt++;
		}
		if (tempB2.cnt == 0) {
			solcnt++;
		}
		if (solcnt >= 2) {
			System.out.println("IMPOSSIBLE");
		} else if (solcnt == 0) {
			if ((tempA1.cnt + tempB1.cnt) > (tempA2.cnt + tempB2.cnt)) {
				System.out.println(tempA2.cnt + tempB2.cnt);
			} else {
				System.out.println(tempA1.cnt + tempB1.cnt);
			}
		} else {
			if (tempA1.cnt == 0 || tempB1.cnt == 0)
				System.out.println(tempA2.cnt + tempB2.cnt);
			if (tempA2.cnt == 0 || tempB2.cnt == 0)
				System.out.println(tempA1.cnt + tempB1.cnt);
		}
	}

	static class Loc {
		int y, x, cnt;

		public Loc(int y, int x, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}

	}
}
